package fr.croix_rouge.formation_pse.usecases.updateTraining;

import fr.croix_rouge.formation_pse.domain.Training;
import fr.croix_rouge.formation_pse.domain.ports.TrainingRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UpdateTrainingUseCase {

  private final TrainingRepository trainingRepository;

  public UpdateTrainingUseCase(TrainingRepository trainingRepository) {
    this.trainingRepository = trainingRepository;
  }

  @Transactional
  public void handle(UpdateTrainingCommand updateTrainingCommand) {
    Training training = trainingRepository.findById(updateTrainingCommand.getId());
    training.update(updateTrainingCommand);
    trainingRepository.update(training);
  }
}
