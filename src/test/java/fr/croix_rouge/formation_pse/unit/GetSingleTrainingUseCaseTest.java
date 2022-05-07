package fr.croix_rouge.formation_pse.unit;

import fr.croix_rouge.formation_pse.domain.Training;
import fr.croix_rouge.formation_pse.domain.exceptions.TrainingNotFoundException;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.fake.FakeTrainingRepository;
import fr.croix_rouge.formation_pse.usecases.getSingleTraining.GetSingleTrainingUseCase;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;

import java.util.List;

import static fr.croix_rouge.formation_pse.factories.TrainingTestFactory.aTraining;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GetSingleTrainingUseCaseTest {

  private final FakeTrainingRepository fakeTrainingRepository = new FakeTrainingRepository();
  private final GetSingleTrainingUseCase sut = new GetSingleTrainingUseCase(fakeTrainingRepository);

  @Test
  void shouldRetrieveTrainingWithSpecificId() {
    // GIVEN
    long trainingIdToRetrieve = 38L;
    Training trainingToRetrieve = aTraining().id(trainingIdToRetrieve).build();
    Training randomTraining = aTraining().id(22L).build();
    Training randomTraining1 = aTraining().id(3L).build();
    Training randomTraining2 = aTraining().id(5L).build();
    fakeTrainingRepository.saveAll(List.of(trainingToRetrieve, randomTraining1, randomTraining, randomTraining2));

    // WHEN
    Training retrievedTraining = sut.handle(trainingIdToRetrieve);

    // THEN
    assertThat(retrievedTraining).isEqualTo(trainingToRetrieve);
  }

  @Test
  void shouldThrowWhenNotFound() {
    // GIVEN
    long trainingIdToRetrieve = 38L;
    Training randomTraining = aTraining().id(22L).build();
    Training randomTraining1 = aTraining().id(3L).build();
    Training randomTraining2 = aTraining().id(5L).build();
    fakeTrainingRepository.saveAll(List.of(randomTraining1, randomTraining, randomTraining2));

    // WHEN
    ThrowableAssert.ThrowingCallable functionToRun = () -> sut.handle(trainingIdToRetrieve);

    // THEN
    assertThatThrownBy(functionToRun).isInstanceOf(TrainingNotFoundException.class);
  }
}
