package fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto;

public class AddressRequest {
  private String city;
  private String label;
  private Integer postalCode;

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
