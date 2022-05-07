package fr.croix_rouge.formation_pse.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Address {
  private final String label;
  private final Integer postalCode;
  private final String city;
  public Address(String label, Integer postalCode, String city) {
    this.label = label;
    this.postalCode = postalCode;
    this.city = city;
  }
}
