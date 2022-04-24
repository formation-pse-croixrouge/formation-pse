package fr.croix_rouge.formation_pse.domain.ports;

import fr.croix_rouge.formation_pse.domain.PseUser;

public interface PseUserRepository {

  PseUser findByNivol(String nivol);
}
