package fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddressRequest {
  @NotBlank(message = "The city is required.")
  private String city;

  @NotBlank(message = "The label is required.")
  private String label;

  @NotNull(message = "The postal code is required")
//  @Size(min = 6, max = 6, message = "The postal code format is invalid.")
  private String postalCode;

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

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }
}
