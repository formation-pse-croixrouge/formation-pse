package fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db;

import fr.croix_rouge.formation_pse.domain.Trainer;
import fr.croix_rouge.formation_pse.domain.ports.TrainerRepository;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities.PseUserJpa;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TrainerDbAdapter implements TrainerRepository {

  private final EntityManager em;

  public TrainerDbAdapter(EntityManager entityManager) {
    this.em = entityManager;
  }

  @Override
  public Set<Trainer> findAllByNivol(Set<String> trainersNivol) {
    return em.createQuery("Select trainer from PseUserJpa trainer where trainer.nivol in (:nivols)", PseUserJpa.class)
      .setParameter("nivols", trainersNivol)
      .getResultList().stream()
      .map(PseUserJpa::toTrainer)
      .collect(Collectors.toSet());
  }

  @Override
  public Set<Trainer> findAll() {
    return em.createQuery("Select trainer from PseUserJpa trainer", PseUserJpa.class).getResultList().stream()
      .map(PseUserJpa::toTrainer)
      .collect(Collectors.toSet());
  }
}
