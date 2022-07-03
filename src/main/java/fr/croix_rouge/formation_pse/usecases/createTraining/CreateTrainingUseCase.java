package fr.croix_rouge.formation_pse.usecases.createTraining;

import fr.croix_rouge.formation_pse.domain.Trainer;
import fr.croix_rouge.formation_pse.domain.Training;
import fr.croix_rouge.formation_pse.domain.ports.TrainerRepository;
import fr.croix_rouge.formation_pse.domain.ports.TrainingRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CreateTrainingUseCase {
  private final TrainingRepository trainingRepository;
  private final TrainerRepository trainerRepository;

  public CreateTrainingUseCase(TrainingRepository trainingRepository, TrainerRepository trainerRepository) {
    this.trainingRepository = trainingRepository;
    this.trainerRepository = trainerRepository;
  }

  public void create(CreateTrainingCommand trainingToCreateCommand) {
    Set<Trainer> trainers = trainerRepository.findAllByNivol(trainingToCreateCommand.getTrainersNivol());
    Training trainingToSave = trainingToCreateCommand.toDomain(trainers);
    trainingRepository.save(trainingToSave);
  }
}
