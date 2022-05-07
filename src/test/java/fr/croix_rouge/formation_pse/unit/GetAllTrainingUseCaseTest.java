package fr.croix_rouge.formation_pse.unit;

import fr.croix_rouge.formation_pse.domain.Training;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.fake.FakeTrainingRepository;
import fr.croix_rouge.formation_pse.usecases.getAllTrainings.GetAllTrainingsUseCase;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static fr.croix_rouge.formation_pse.factories.TrainingTestFactory.aTraining;
import static org.assertj.core.api.Assertions.assertThat;

class GetAllTrainingUseCaseTest {

  public static final LocalDate FIRST_OF_JANUARY = LocalDate.parse("2021-01-01");
  public static final LocalDate SECOND_OF_MARCH = LocalDate.parse("2021-03-02");
  public static final LocalDate FIRST_OF_FEBRUARY = LocalDate.parse("2021-02-01");
  private final FakeTrainingRepository fakeTrainingRepository = new FakeTrainingRepository();

  private final GetAllTrainingsUseCase sut = new GetAllTrainingsUseCase(fakeTrainingRepository);


  @Test
  void shouldRetrieveAllTrainingsSortedByStartDateAsc() {
    // GIVEN
    Training firstTraining = aTraining().id(38L)
      .startDate(FIRST_OF_JANUARY).build();
    Training thirdTraining = aTraining().id(39L)
      .startDate(SECOND_OF_MARCH).build();
    Training secondTraining = aTraining().id(40L)
      .startDate(FIRST_OF_FEBRUARY).build();
    fakeTrainingRepository.saveAll(List.of(firstTraining, thirdTraining, secondTraining));

    // WHEN
    List<Training> retrievedTrainings = sut.handle();

    // THEN
    assertThat(retrievedTrainings).hasSize(3);
    assertThat(retrievedTrainings.get(0).getStartDate()).isEqualTo(FIRST_OF_JANUARY);
    assertThat(retrievedTrainings.get(1).getStartDate()).isEqualTo(FIRST_OF_FEBRUARY);
    assertThat(retrievedTrainings.get(2).getStartDate()).isEqualTo(SECOND_OF_MARCH);
  }

  @Test
  void shouldReturnEmptyArrayWhenNoTrainings() {
    // WHEN
    List<Training> retrievedTrainings = sut.handle();

    // THEN
    assertThat(retrievedTrainings).hasSize(0);
  }
}
