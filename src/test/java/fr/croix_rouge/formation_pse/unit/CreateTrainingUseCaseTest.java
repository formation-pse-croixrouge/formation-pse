package fr.croix_rouge.formation_pse.unit;

import fr.croix_rouge.formation_pse.domain.PseUser;
import fr.croix_rouge.formation_pse.domain.Training;
import fr.croix_rouge.formation_pse.domain.commands.CreateTrainingCommand;
import fr.croix_rouge.formation_pse.factories.PseUserTestFactory;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.inmemory.TrainingInMemoryRepository;
import fr.croix_rouge.formation_pse.usecases.CreateTrainingUseCase;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class CreateTrainingUseCaseTest {

  TrainingInMemoryRepository trainingInMemoryRepository = new TrainingInMemoryRepository();
  CreateTrainingUseCase sut = new CreateTrainingUseCase(trainingInMemoryRepository);

  @Test
  void shouldCreateATraining() {
    PseUser user = PseUserTestFactory.organizer();
    CreateTrainingCommand trainingToCreateCommand = CreateTrainingCommand.builder()
      .user(user)
      .startDate(LocalDate.now())
      .endDate(LocalDate.now().plusDays(1))
      .addressLabel("3, rue du renard")
      .addressPostalCode(69100)
      .addressCity("Villeurbanne")
      .build();

    sut.create(trainingToCreateCommand);

    assertThat(trainingInMemoryRepository.all()).hasSize(1);
    Training savedTraining = trainingInMemoryRepository.all().iterator().next();
    assertThat(savedTraining.getId()).isNotNull();
    assertThat(savedTraining.getStartDate()).isEqualTo(trainingToCreateCommand.getStartDate());
    assertThat(savedTraining.getEndDate()).isEqualTo(trainingToCreateCommand.getEndDate());
    assertThat(savedTraining.getAddress().getLabel()).isEqualTo(trainingToCreateCommand.getAddressLabel());
    assertThat(savedTraining.getAddress().getCity()).isEqualTo(trainingToCreateCommand.getAddressCity());
    assertThat(savedTraining.getAddress().getPostalCode()).isEqualTo(trainingToCreateCommand.getAddressPostalCode());
    assertThat(savedTraining.getCreatedBy()).isEqualTo(trainingToCreateCommand.getUser());
  }
}
