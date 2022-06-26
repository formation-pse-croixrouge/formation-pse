package fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db;

import fr.croix_rouge.formation_pse.domain.Training;
import fr.croix_rouge.formation_pse.domain.ports.TrainingRepository;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities.TrainingJpa;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

  @Override
  public Set<Training> all() {
    return dao.findAll().stream()
      .map(TrainingJpa::toDomain)
      .collect(Collectors.toSet());
  }

  @Override
  public Training findById(Long trainingIdToRetrieve) {
    Optional<TrainingJpa> trainingJpaOptional = dao.findById(trainingIdToRetrieve);
    if(trainingJpaOptional.isEmpty()) {
      return null;
    }
    return trainingJpaOptional.get().toDomain();
  }

  @Override
  public void delete(Long trainingId) {
    dao.deleteById(trainingId);
  }
}
