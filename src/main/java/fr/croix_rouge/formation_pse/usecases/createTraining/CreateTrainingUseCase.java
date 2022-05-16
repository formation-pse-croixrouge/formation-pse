package fr.croix_rouge.formation_pse.usecases.createTraining;

import fr.croix_rouge.formation_pse.domain.Training;
import fr.croix_rouge.formation_pse.domain.ports.TrainingRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateTrainingUseCase {
  private final TrainingRepository trainingRepository;

  public CreateTrainingUseCase(TrainingRepository trainingRepository) {
    this.trainingRepository = trainingRepository;
  }

  public void create(CreateTrainingCommand trainingToCreateCommand) {
    Training trainingToSave = trainingToCreateCommand.toDomain();
    trainingRepository.save(trainingToSave);
  }
}
