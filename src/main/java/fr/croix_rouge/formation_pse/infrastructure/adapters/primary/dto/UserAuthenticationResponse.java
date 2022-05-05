package fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto;

import fr.croix_rouge.formation_pse.domain.PseUser;

public class UserAuthenticationResponse {
  private String firstName;
  private String lastName;
  private String nivol;

  public UserAuthenticationResponse(PseUser user) {
    this.nivol = user.getNivol();
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getNivol() {
    return nivol;
  }

  public void setNivol(String nivol) {
    this.nivol = nivol;
  }
}
