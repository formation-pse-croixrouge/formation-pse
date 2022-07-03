package fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.fake;

import fr.croix_rouge.formation_pse.domain.Training;
import fr.croix_rouge.formation_pse.domain.ports.TrainingRepository;
import fr.croix_rouge.formation_pse.usecases.updateTraining.UpdateTrainingCommand;

import java.util.*;

public class FakeTrainingRepository implements TrainingRepository {

  Set<Training> trainings = new LinkedHashSet<>();

  public Set<Training> all() {
    return Set.copyOf(trainings);
  }

  @Override
  public Training findById(Long trainingIdToRetrieve) {
    return trainings.stream()
      .filter(s -> Objects.equals(s.getId(), trainingIdToRetrieve))
      .findFirst()
      .orElse(null);
  }

  @Override
  public void delete(Long trainingId) {
    Training trainingToDelete = trainings.stream()
      .filter(training -> Objects.equals(training.getId(), trainingId))
      .findFirst()
      .orElseThrow(() -> new RuntimeException("training not found"));
    trainings.remove(trainingToDelete);
  }

  @Override
  public void update(UpdateTrainingCommand updateTrainingCommand) {
    throw new RuntimeException("not implemented");
  }

  @Override
  public void save(Training trainingToSave) {
    Training training = Training.builder()
      .id(trainingToSave.getId() == null ? generateRandomId() : trainingToSave.getId())
      .startDate(trainingToSave.getStartDate())
      .endDate(trainingToSave.getEndDate())
      .address(trainingToSave.getAddress())
      .createdBy(trainingToSave.getCreatedBy())
      .trainers(trainingToSave.getTrainers())
      .build();
    trainings.add(training);
  }

  public void saveAll(List<Training> trainingsToSave) {
    for (Training training : trainingsToSave) {
      save(training);
    }
  }

  private long generateRandomId() {
    long range = 1234567L;
    Random r = new Random();
    return (long) (r.nextDouble() * range);
  }
}

// TODO : Ajouter formateurs à l'update de formations.
// TODO : Ajouter participants à la création et update de formations.
// TODO : Faire un endpoint de création de formateurs mais pas de front.