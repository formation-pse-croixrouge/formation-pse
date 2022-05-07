package fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db;

import fr.croix_rouge.formation_pse.domain.Training;
import fr.croix_rouge.formation_pse.domain.ports.TrainingRepository;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities.TrainingJpa;
import org.springframework.stereotype.Component;

@Component
public class TrainingDbAdapter implements TrainingRepository {

  private final TrainingDao dao;

  public TrainingDbAdapter(TrainingDao dao) {
    this.dao = dao;
  }

  @Override
  public void save(Training trainingToSave) {
    TrainingJpa entityToSave = TrainingJpa.fromDomain(trainingToSave);
    dao.save(entityToSave);
  }
}
