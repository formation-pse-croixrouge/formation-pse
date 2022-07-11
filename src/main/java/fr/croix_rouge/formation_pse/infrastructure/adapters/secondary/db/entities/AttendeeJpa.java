package fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AttendeeJpa {
  private String firstName;
  private String lastName;

}
