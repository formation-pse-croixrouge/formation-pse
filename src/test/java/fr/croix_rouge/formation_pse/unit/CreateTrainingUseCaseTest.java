package fr.croix_rouge.formation_pse.unit;

import fr.croix_rouge.formation_pse.domain.Attendee;
import fr.croix_rouge.formation_pse.domain.PseUser;
import fr.croix_rouge.formation_pse.domain.Trainer;
import fr.croix_rouge.formation_pse.domain.Training;
import fr.croix_rouge.formation_pse.factories.PseUserTestFactory;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.fake.FakeTrainerRepository;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.fake.FakeTrainingRepository;
import fr.croix_rouge.formation_pse.usecases.createTraining.CreateTrainingCommand;
import fr.croix_rouge.formation_pse.usecases.createTraining.CreateTrainingUseCase;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static fr.croix_rouge.formation_pse.factories.TrainerTestFactory.aTrainer;
import static org.assertj.core.api.Assertions.assertThat;

class CreateTrainingUseCaseTest {

  FakeTrainingRepository fakeTrainingRepository = new FakeTrainingRepository();
  FakeTrainerRepository fakeTrainerRepository = new FakeTrainerRepository();
  CreateTrainingUseCase sut = new CreateTrainingUseCase(fakeTrainingRepository, fakeTrainerRepository);

  @Test
  void shouldCreateATraining() {
    PseUser user = PseUserTestFactory.organizer();
    Trainer aTrainer = saveTrainer();
    CreateTrainingCommand trainingToCreateCommand = CreateTrainingCommand.builder()
      .user(user)
      .startDate(LocalDate.now())
      .endDate(LocalDate.now().plusDays(1))
      .addressLabel("3, rue du renard")
      .addressPostalCode(69100)
      .addressCity("Villeurbanne")
      .trainersNivol(Set.of(aTrainer.getNivol()))
      .attendees(Set.of(Attendee.builder()
        .firstName("John")
        .lastName("Rambo")
        .build()))
      .build();

    sut.create(trainingToCreateCommand);

    assertThat(fakeTrainingRepository.all()).hasSize(1);
    Training savedTraining = fakeTrainingRepository.all().iterator().next();
    assertThat(savedTraining.getId()).isNotNull();
    assertThat(savedTraining.getStartDate()).isEqualTo(trainingToCreateCommand.getStartDate());
    assertThat(savedTraining.getEndDate()).isEqualTo(trainingToCreateCommand.getEndDate());
    assertThat(savedTraining.getAddress().getLabel()).isEqualTo(trainingToCreateCommand.getAddressLabel());
    assertThat(savedTraining.getAddress().getCity()).isEqualTo(trainingToCreateCommand.getAddressCity());
    assertThat(savedTraining.getAddress().getPostalCode()).isEqualTo(trainingToCreateCommand.getAddressPostalCode());
    assertThat(savedTraining.getCreatedBy()).isEqualTo(trainingToCreateCommand.getUser());
    assertThat(savedTraining.getAttendees()).isEqualTo(trainingToCreateCommand.getAttendees());
  }

  private Trainer saveTrainer() {
    Trainer trainer = aTrainer().build();
    fakeTrainerRepository.save(trainer);
    return trainer;
  }
}
