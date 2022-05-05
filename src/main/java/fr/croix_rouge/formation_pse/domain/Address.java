package fr.croix_rouge.formation_pse.domain;

public class Address {
  private final String label;
  private final int postalCode;
  private final String city;
  public Address(String label, int postalCode, String city) {
    this.label = label;
    this.postalCode = postalCode;
    this.city = city;
  }

  public String getLabel() {
    return label;
  }

  public int getPostalCode() {
    return postalCode;
  }

  public String getCity() {
    return city;
  }
}
