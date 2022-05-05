package fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities;

import fr.croix_rouge.formation_pse.domain.PseUser;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class PseUserJpa {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private String nivol;
  private String password;
  private String firstName;
  private String lastName;

  public PseUser toDomain() {
    return new PseUser(nivol, firstName, lastName, password);
  }
}
