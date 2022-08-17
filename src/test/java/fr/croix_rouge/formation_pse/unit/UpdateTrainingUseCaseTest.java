package fr.croix_rouge.formation_pse.unit;

import fr.croix_rouge.formation_pse.domain.Address;
import fr.croix_rouge.formation_pse.domain.Attendee;
import fr.croix_rouge.formation_pse.domain.TechnicalAssessmentEvaluation;
import fr.croix_rouge.formation_pse.domain.Training;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.fake.FakeTrainingRepository;
import fr.croix_rouge.formation_pse.usecases.updateTraining.UpdateTrainingCommand;
import fr.croix_rouge.formation_pse.usecases.updateTraining.UpdateTrainingUseCase;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static fr.croix_rouge.formation_pse.factories.TrainingTestFactory.aTraining;
import static org.assertj.core.api.Assertions.assertThat;

class UpdateTrainingUseCaseTest {

  public static final LocalDate FOURTH_OF_MARCH = LocalDate.parse("2021-03-04");
  public static final LocalDate NOW = LocalDate.now();

  private final FakeTrainingRepository fakeTrainingRepository = new FakeTrainingRepository();
  private final UpdateTrainingUseCase sut = new UpdateTrainingUseCase(fakeTrainingRepository);

  @Test
  void shouldUpdateExistingTraining() {
    // GIVEN
    Training firstTraining = aTraining().id(38L).build();
    fakeTrainingRepository.saveAll(List.of(firstTraining));

    UpdateTrainingCommand updateTrainingCommand = UpdateTrainingCommand.builder()
      .id(38L)
      .startDate(FOURTH_OF_MARCH)
      .endDate(NOW)
      .address(Address.builder()
        .postalCode(333)
        .label("A new address")
        .city("A new city")
        .build())
      .trainers(firstTraining.getTrainers())
      .attendees(new HashSet<>(Arrays.asList(Attendee.builder()
        .firstName("A brand")
        .lastName("new")
        .technicalAssessmentEvaluation(TechnicalAssessmentEvaluation.generateIncomplete(firstTraining.getTechnicalAssessmentStructure()))
        .build())))
      .build();

    // WHEN
    sut.handle(updateTrainingCommand);

    // THEN
    Training updatedTraining = fakeTrainingRepository.findById(38L);
    assertThat(updatedTraining.getStartDate()).isEqualTo(updateTrainingCommand.getStartDate());
    assertThat(updatedTraining.getEndDate()).isEqualTo(updateTrainingCommand.getEndDate());
    assertThat(updatedTraining.getAttendees().iterator().next()).isEqualTo(updateTrainingCommand.getAttendees().iterator().next());
    assertThat(updatedTraining.getAddress()).isEqualTo(updateTrainingCommand.getAddress());

  }
}
