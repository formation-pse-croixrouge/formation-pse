package fr.croix_rouge.formation_pse.integration;

import fr.croix_rouge.formation_pse.domain.PseUser;
import fr.croix_rouge.formation_pse.domain.TechnicalAssessmentStructure;
import fr.croix_rouge.formation_pse.domain.Training;
import fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto.TechnicalAssessmentModule;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.TrainingDao;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.TrainingDbAdapter;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities.AddressJpa;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities.AttendeeJpa;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities.PseUserJpa;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities.TechnicalAssessmentModuleJpa;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities.TechnicalAssessmentStructureJpa;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities.TrainingJpa;
import fr.croix_rouge.formation_pse.utils.DbIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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
    return TrainingJpa.builder()
      .startDate(LocalDate.parse("2021-01-01"))
      .endDate(LocalDate.parse("2021-12-31"))
      .technicalAssessmentStructure(new TechnicalAssessmentStructureJpa(Set.of(TechnicalAssessmentModuleJpa.builder()
        .title("a title")
        .skills(Set.of("do stuff", "do nothing"))
        .build())))
      .creator(aUser)
      .address(AddressJpa.builder()
        .postalCode(2222)
        .label("3 avenue du Bournard")
        .city("Paris")
        .build())
      .trainers(Set.of(aUser))
      .attendees(Set.of(AttendeeJpa.builder().firstName("Victor").lastName("Lodes").build()))
      .endDate(LocalDate.now()).build();
  }

  private PseUserJpa createUser() {
    entityManager.createNativeQuery("INSERT INTO USERS(id, first_name, last_name, nivol) VALUES (1L, 'John', 'Doe', 'A_NIVOL')").executeUpdate();
    return entityManager.createQuery("Select user from PseUserJpa user", PseUserJpa.class).getSingleResult();
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
    TechnicalAssessmentModuleJpa module = savedTraining.getTechnicalAssessmentStructure().getModules().iterator().next();
    TechnicalAssessmentModule moduleToSave = trainingToSave.getTechnicalAssessmentStructure().getModules().iterator().next();
    assertThat(module.getSkills()).isEqualTo(moduleToSave.getSkills());
    assertThat(module.getTitle()).isEqualTo(moduleToSave.getTitle());
  }

  @Test
  void shouldFindAll() {
    // GIVEN
    TrainingJpa trainingJpa = aTrainingJpa();
    dao.save(trainingJpa);

    // WHEN
    Set<Training> all = sut.all();

    assertThat(all).hasSize(1);
    Training savedTraining = all.iterator().next();
    assertThat(savedTraining.getTechnicalAssessmentStructure().getModules().iterator().next().getSkills())
      .isEqualTo(trainingJpa.getTechnicalAssessmentStructure().getModules().iterator().next().getSkills());

    assertThat(savedTraining.getTechnicalAssessmentStructure().getModules().iterator().next().getTitle())
      .isEqualTo(trainingJpa.getTechnicalAssessmentStructure().getModules().iterator().next().getTitle());
  }
}
