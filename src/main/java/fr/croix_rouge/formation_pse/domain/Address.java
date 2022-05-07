package fr.croix_rouge.formation_pse.domain;

public class Address {
  private final String label;
  private final String postalCode;
  private final String city;

  public Address(String label, String postalCode, String city) {
    this.label = label;
    this.postalCode = postalCode;
    this.city = city;
  }

  public String getLabel() {
    return label;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public String getCity() {
    return city;
  }
}
