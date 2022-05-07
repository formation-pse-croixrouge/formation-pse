package fr.croix_rouge.formation_pse.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Address {
  private final String label;
  private final int postalCode;
  private final String city;
  public Address(String label, int postalCode, String city) {
    this.label = label;
    this.postalCode = postalCode;
    this.city = city;
  }
}
