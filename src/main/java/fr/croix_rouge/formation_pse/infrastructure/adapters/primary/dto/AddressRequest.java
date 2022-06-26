package fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto;

import fr.croix_rouge.formation_pse.domain.Address;

public class AddressRequest {
  private String city;
  private String label;
  private Integer postalCode;

  public Address toDomain() {
    return Address.builder()
      .city(city)
      .label(label)
      .postalCode(postalCode)
      .build();
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public Integer getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(Integer postalCode) {
    this.postalCode = postalCode;
  }
}
