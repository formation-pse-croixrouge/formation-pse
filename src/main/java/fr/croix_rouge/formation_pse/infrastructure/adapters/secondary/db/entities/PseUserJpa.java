package fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities;

import fr.croix_rouge.formation_pse.domain.PseUser;
import fr.croix_rouge.formation_pse.domain.Trainer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PseUserJpa {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;

  @Column(unique = true, updatable = false, insertable = false)
  private String nivol;
  @Column(updatable = false, insertable = false)
  private String password;
  @Column(nullable = false)
  private String firstName;
  @Column(nullable = false)
  private String lastName;

  public static PseUserJpa fromDomain(PseUser createdBy) {
    return PseUserJpa.builder()
      .id(createdBy.getId())
      .nivol(createdBy.getNivol())
      .firstName(createdBy.getFirstName())
      .lastName(createdBy.getLastName())
      .build();
  }


  public PseUser toDomain() {
    return new PseUser(id, nivol, firstName, lastName, password);
  }

  public static PseUserJpa fromTrainer(Trainer trainer) {
    return PseUserJpa.builder()
      .id(trainer.getId())
      .nivol(trainer.getNivol())
      .firstName(trainer.getFirstName())
      .lastName(trainer.getLastName())
      .build();
  }

  public Trainer toTrainer() {
    return Trainer.builder()
      .id(id)
      .nivol(nivol)
      .firstName(firstName)
      .lastName(lastName)
      .build();
  }
}
