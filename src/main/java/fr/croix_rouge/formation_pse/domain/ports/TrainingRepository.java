package fr.croix_rouge.formation_pse.domain.ports;

import fr.croix_rouge.formation_pse.domain.Training;

public interface TrainingRepository {
  void save(Training trainingToSave);
}
