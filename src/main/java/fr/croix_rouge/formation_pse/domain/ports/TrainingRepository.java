package fr.croix_rouge.formation_pse.domain.ports;

import fr.croix_rouge.formation_pse.domain.Training;
import fr.croix_rouge.formation_pse.usecases.updateTraining.UpdateTrainingCommand;

import java.util.Set;

public interface TrainingRepository {
  void save(Training trainingToSave);

  Set<Training> all();

  Training findById(Long trainingIdToRetrieve);

  void delete(Long trainingId);

  void update(UpdateTrainingCommand updateTrainingCommand);
}
