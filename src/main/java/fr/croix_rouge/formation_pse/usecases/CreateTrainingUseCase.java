package fr.croix_rouge.formation_pse.usecases;

import fr.croix_rouge.formation_pse.domain.commands.CreateTrainingCommand;
import fr.croix_rouge.formation_pse.domain.ports.TrainingRepository;

public class CreateTrainingUseCase {
  private final TrainingRepository trainingRepository;

  public CreateTrainingUseCase(TrainingRepository trainingRepository) {
    this.trainingRepository = trainingRepository;
  }

  public void create(CreateTrainingCommand trainingToCreateCommand) {

  }
}
