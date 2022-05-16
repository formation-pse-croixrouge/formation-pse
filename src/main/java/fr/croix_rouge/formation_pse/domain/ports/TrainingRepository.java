package fr.croix_rouge.formation_pse.domain.ports;

import fr.croix_rouge.formation_pse.domain.Training;

import java.util.Set;

public interface TrainingRepository {
  void save(Training trainingToSave);

  Set<Training> all();

  Training findById(Long trainingIdToRetrieve);
}
