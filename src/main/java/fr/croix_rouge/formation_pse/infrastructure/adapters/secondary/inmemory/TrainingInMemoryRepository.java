package fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.inmemory;

import fr.croix_rouge.formation_pse.domain.Training;
import fr.croix_rouge.formation_pse.domain.ports.TrainingRepository;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class TrainingInMemoryRepository implements TrainingRepository {

  Set<Training> trainings = new LinkedHashSet<>();

  public Set<Training> all() {
    return Set.copyOf(trainings);
  }

  @Override
  public void save(Training trainingToSave) {
    long randomId = generateRandomId();
    Training training = new Training(
      randomId,
      trainingToSave.getStartDate(),
      trainingToSave.getEndDate(),
      trainingToSave.getAddress(),
      trainingToSave.getCreatedBy()
    );
    trainings.add(training);
  }

  private long generateRandomId() {
    long range = 1234567L;
    Random r = new Random();
    return (long)(r.nextDouble()*range);
  }
}
