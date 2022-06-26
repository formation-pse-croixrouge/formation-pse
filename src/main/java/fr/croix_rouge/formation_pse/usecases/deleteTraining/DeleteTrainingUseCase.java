package fr.croix_rouge.formation_pse.usecases.deleteTraining;

import fr.croix_rouge.formation_pse.domain.ports.TrainingRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteTrainingUseCase {
  private final TrainingRepository trainingRepository;

  public DeleteTrainingUseCase(TrainingRepository trainingRepository) {
    this.trainingRepository = trainingRepository;
  }

  public void handle(Long trainingId) {
    trainingRepository.delete(trainingId);
  }
}
