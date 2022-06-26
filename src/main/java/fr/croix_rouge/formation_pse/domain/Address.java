package fr.croix_rouge.formation_pse.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class Address {
  private final String label;
  private final Integer postalCode;
  private final String city;
  public Address(String label, Integer postalCode, String city) {
    validate(label, postalCode, city);
    this.label = label;
    this.postalCode = postalCode;
    this.city = city;
  }

  private void validate(String label, Integer postalCode, String city) {
    if(isBlank(label)) {
      throw new IllegalArgumentException("Address label cannot be blank");
    }
    if(postalCode == null) {
      throw new IllegalArgumentException("Address postal code cannot be null");
    }
    if(isBlank(city)) {
      throw new IllegalArgumentException("Address city cannot be blank");
    }
  }
}
