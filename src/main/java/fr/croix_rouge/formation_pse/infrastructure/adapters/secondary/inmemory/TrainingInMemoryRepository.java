package fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.inmemory;

import fr.croix_rouge.formation_pse.domain.Training;
import fr.croix_rouge.formation_pse.domain.ports.TrainingRepository;

import java.util.LinkedHashSet;
import java.util.Set;

public class TrainingInMemoryRepository implements TrainingRepository {

  Set<Training> trainings = new LinkedHashSet<>();

  public Set<Training> all() {
    return Set.copyOf(trainings);
  }
}
