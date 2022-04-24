package fr.croix_rouge.formation_pse.infrastructure.secondary.db;

import fr.croix_rouge.formation_pse.infrastructure.secondary.db.entities.PseUserJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PseUserDao extends JpaRepository<PseUserJpa, String> {

  PseUserJpa findByNivol(String nivol);
}
