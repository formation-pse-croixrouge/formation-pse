package fr.croix_rouge.formation_pse.integration;

import fr.croix_rouge.formation_pse.domain.Attendee;
import fr.croix_rouge.formation_pse.domain.PseUser;
import fr.croix_rouge.formation_pse.domain.TechnicalAssessmentEvaluation;
import fr.croix_rouge.formation_pse.domain.TechnicalAssessmentStructure;
import fr.croix_rouge.formation_pse.domain.Training;
import fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto.TechnicalAssessmentModule;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.TrainingDao;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.TrainingDbAdapter;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities.AddressJpa;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities.AttendeeJpa;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities.PseUserJpa;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities.TechnicalAssessmentEvaluationJpa;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities.TechnicalAssessmentEvaluationJpa.TechnicalAssessmentEvaluationModuleJpa;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities.TechnicalAssessmentEvaluationJpa.TechnicalAssessmentEvaluationModuleJpa.GradeJpa;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities.TrainingJpa;
import fr.croix_rouge.formation_pse.utils.DbIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static fr.croix_rouge.formation_pse.factories.TrainingTestFactory.aTraining;
import static org.assertj.core.api.Assertions.assertThat;

@DbIntegrationTest
class TrainingDbAdapterTest {

  @Autowired
  private TrainingDbAdapter sut;

  @Autowired
  private TrainingDao dao;

  @Autowired
  private EntityManager entityManager;

  @Test
  void shouldDelete() {
    // GIVEN
    TrainingJpa trainingJpa = aTrainingJpa();
    TrainingJpa savedTraining = dao.save(trainingJpa);

    // WHEN
    sut.delete(savedTraining.getId());

    // THEN
    List<TrainingJpa> savedTrainings = dao.findAll();
    assertThat(savedTrainings).isEmpty();
  }

  private TrainingJpa aTrainingJpa() {
    PseUserJpa aUser = createUser();
    List<TechnicalAssessmentEvaluationModuleJpa> evaluationModuleJpas = List.of(
      new TechnicalAssessmentEvaluationModuleJpa("a title", List.of(new GradeJpa("do stuff", true))));
    return TrainingJpa.builder()
      .startDate(LocalDate.parse("2021-01-01"))
      .endDate(LocalDate.parse("2021-12-31"))
      .creator(aUser)
      .address(AddressJpa.builder()
        .postalCode(2222)
        .label("3 avenue du Bournard")
        .city("Paris")
        .build())
      .trainers(Set.of(aUser))
      .attendees(Set.of(AttendeeJpa.builder()
        .firstName("Victor")
        .lastName("Lodes")
        .technicalAssessmentEvaluation(new TechnicalAssessmentEvaluationJpa(evaluationModuleJpas))
        .build()))
      .endDate(LocalDate.now()).build();
  }

  private PseUserJpa createUser() {
    entityManager.createNativeQuery("INSERT INTO USERS(id, first_name, last_name, nivol) VALUES (1L, 'John', 'Doe', 'A_NIVOL')").executeUpdate();
    return entityManager.createQuery("Select user from PseUserJpa user", PseUserJpa.class).getSingleResult();
  }

  @Test
  void shouldSaveTechnicalAssessmentStructureAndKeepOrder() {
    // GIVEN
    PseUserJpa aUser = createUser();
    Training trainingToSave = aTraining()
      .createdBy(new PseUser(aUser.getId(), null, null, null, null))
      .attendees(Set.of(
        Attendee.builder()
          .firstName("Joe")
          .lastName("Dalton")
          .technicalAssessmentEvaluation(TechnicalAssessmentEvaluation.generateIncomplete(new TechnicalAssessmentStructure(List.of(
            TechnicalAssessmentModule.builder()
              .title("Second module")
              .skills(List.of("Third skill", "gogo"))
              .build(),
            TechnicalAssessmentModule.builder()
              .title("First module but put second for w/e reason")
              .skills(List.of("ZZZ", "What"))
              .build()
          ))))
          .build()
      ))
      .build();

    // WHEN
    sut.save(trainingToSave);

    TrainingJpa savedTraining = dao.findAll().get(0);
    AttendeeJpa anAttendee = savedTraining.getAttendees().iterator().next();
    TechnicalAssessmentEvaluationModuleJpa firstModule = anAttendee.getTechnicalAssessmentEvaluation().getModules().get(0);
    assertThat(firstModule.getTitle()).isEqualTo("Second module");
    assertThat(firstModule.getGrades().stream().map(GradeJpa::getSkill).collect(Collectors.toList()))
      .isEqualTo(List.of("Third skill", "gogo"));
  }

  @Test
  void shouldSave() {
    // GIVEN
    PseUserJpa aUser = createUser();
    Training trainingToSave = aTraining()
      .createdBy(new PseUser(aUser.getId(), null, null, null, null))
      .build();

    // WHEN
    sut.save(trainingToSave);

    TrainingJpa savedTraining = dao.findAll().get(0);
    assertThat(savedTraining.getStartDate()).isEqualTo(trainingToSave.getStartDate());
    assertThat(savedTraining.getEndDate()).isEqualTo(trainingToSave.getEndDate());
    assertThat(savedTraining.getAddress().getCity()).isEqualTo(trainingToSave.getAddress().getCity());
    assertThat(savedTraining.getAddress().getPostalCode()).isEqualTo(trainingToSave.getAddress().getPostalCode());
    assertThat(savedTraining.getAddress().getLabel()).isEqualTo(trainingToSave.getAddress().getLabel());
    assertThat(savedTraining.getCreator().getId()).isEqualTo(trainingToSave.getCreatedBy().getId());
    TechnicalAssessmentModule moduleToSave = trainingToSave.getTechnicalAssessmentStructure().getModules().iterator().next();
    String savedModuleTitle = getTitleOfFirstTechnicalAssessmentModule(savedTraining);
    List<String> savedModuleSkills = getTechnicalAssessmentSkills(savedTraining);
    assertThat(savedModuleSkills).isEqualTo(moduleToSave.getSkills());
    assertThat(savedModuleTitle).isEqualTo(moduleToSave.getTitle());
  }

  private String getTitleOfFirstTechnicalAssessmentModule(TrainingJpa savedTraining) {
    return savedTraining.getAttendees().iterator().next().getTechnicalAssessmentEvaluation().getModules().get(0).getTitle();
  }

  private List<String> getTechnicalAssessmentSkills(TrainingJpa savedTraining) {
    return savedTraining.getAttendees().iterator().next().getTechnicalAssessmentEvaluation().getModules().get(0).getGrades().stream()
        .map(GradeJpa::getSkill)
          .collect(Collectors.toList());
  }

  @Test
  void shouldFindAll() {
    // GIVEN
    TrainingJpa savedTrainingJpa = aTrainingJpa();
    dao.save(savedTrainingJpa);

    // WHEN
    Set<Training> all = sut.all();

    assertThat(all).hasSize(1);
    Training retrievedTrainingJpa = all.iterator().next();
    assertThat(retrievedTrainingJpa.getTechnicalAssessmentStructure().getModules().iterator().next().getSkills())
      .isEqualTo(getTechnicalAssessmentSkills(savedTrainingJpa));

    assertThat(retrievedTrainingJpa.getTechnicalAssessmentStructure().getModules().iterator().next().getTitle())
      .isEqualTo(getTitleOfFirstTechnicalAssessmentModule(savedTrainingJpa));
  }
}
