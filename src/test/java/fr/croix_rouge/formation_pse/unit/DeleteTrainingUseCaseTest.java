package fr.croix_rouge.formation_pse.unit;

import fr.croix_rouge.formation_pse.domain.Training;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.fake.FakeTrainingRepository;
import fr.croix_rouge.formation_pse.usecases.deleteTraining.DeleteTrainingUseCase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static fr.croix_rouge.formation_pse.factories.TrainingTestFactory.aTraining;
import static org.assertj.core.api.Assertions.assertThat;

public class DeleteTrainingUseCaseTest {

  private final FakeTrainingRepository fakeTrainingRepository = new FakeTrainingRepository();
  private final DeleteTrainingUseCase sut = new DeleteTrainingUseCase(fakeTrainingRepository);

  @Test
  void shouldDeleteTraining() {

    Training trainingToDelete = aTraining().id(12L).build();
    Training randomTraining = aTraining().id(13L).build();
    fakeTrainingRepository.saveAll(List.of(trainingToDelete, randomTraining));

    sut.handle(trainingToDelete.getId());

    assertThat(fakeTrainingRepository.all()).hasSize(1);
    assertThat(fakeTrainingRepository.all().iterator().next().getId()).isEqualTo(13L);
  }
}
