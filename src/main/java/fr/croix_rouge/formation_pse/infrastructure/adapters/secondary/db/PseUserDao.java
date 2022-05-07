package fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db;

import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities.PseUserJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PseUserDao extends JpaRepository<PseUserJpa, String> {

  PseUserJpa findByNivol(String nivol);
}
