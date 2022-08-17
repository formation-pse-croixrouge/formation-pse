package fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.fake;

import fr.croix_rouge.formation_pse.domain.Training;
import fr.croix_rouge.formation_pse.domain.ports.TrainingRepository;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

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
  public void update(Training training) {
    Training trainingToUpdate = trainings.stream()
      .filter(training1 -> Objects.equals(training1.getId(), training.getId()))
      .findFirst().get();
    trainings.remove(trainingToUpdate);
    trainings.add(training);
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
      .attendees(trainingToSave.getAttendees())
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

// TODO : Lors du remplissage du formulaire d'un attendee, récupérer structure depuis attendee
// TODO : Lors de la sauvegarde, mettre à jour depuis attendee

