package fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db;

import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities.TrainingJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface TrainingDao extends JpaRepository<TrainingJpa, Long> {

}
