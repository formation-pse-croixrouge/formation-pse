package fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db;

import fr.croix_rouge.formation_pse.domain.PseUser;
import fr.croix_rouge.formation_pse.domain.ports.PseUserRepository;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities.PseUserJpa;
import org.springframework.stereotype.Repository;

@Repository
public class PseUserAdapter implements PseUserRepository {

  private final PseUserDao dao;

  public PseUserAdapter(PseUserDao dao) {
    this.dao = dao;
  }

  @Override
  public PseUser findByNivol(String nivol) {
    PseUserJpa pseUserJPA = dao.findByNivol(nivol);
    if(pseUserJPA == null) {
      return null;
    }
    return pseUserJPA.toDomain();
  }
}
