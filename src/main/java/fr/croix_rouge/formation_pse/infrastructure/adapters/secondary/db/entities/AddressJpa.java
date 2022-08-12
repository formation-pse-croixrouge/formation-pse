package fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressJpa {
  @Column(name = "ADDRESS_LABEL")
  private String label;

  @Column(name = "ADDRESS_POSTAL_CODE")
  private Integer postalCode;

  @Column(name = "ADDRESS_CITY")
  private String city;
}
