package fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.fake;

import fr.croix_rouge.formation_pse.domain.Trainer;
import fr.croix_rouge.formation_pse.domain.ports.TrainerRepository;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FakeTrainerRepository implements TrainerRepository {
  private final Set<Trainer> trainers = new LinkedHashSet<>();
  @Override
  public Set<Trainer> findAllByNivol(Set<String> trainersNivol) {
    return trainers.stream()
      .filter(t -> trainersNivol.contains(t.getNivol()))
      .collect(Collectors.toSet());
  }

  @Override
  public Set<Trainer> findAll() {
    throw new RuntimeException("Not implemented");
  }

  public void save(Trainer trainer) {
    trainers.add(trainer);
  }
}
