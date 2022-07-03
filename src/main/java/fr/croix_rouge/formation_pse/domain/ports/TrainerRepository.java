package fr.croix_rouge.formation_pse.domain.ports;

import fr.croix_rouge.formation_pse.domain.Trainer;

import java.util.Set;

public interface TrainerRepository {
  Set<Trainer> findAllByNivol(Set<String> trainersNivol);

  Set<Trainer> findAll();
}
