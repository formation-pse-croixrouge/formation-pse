package fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto;

public class UserAuthenticationRequest {
  private String nivol;
  private String password;

  public String getNivol() {
    return nivol;
  }

  public void setNivol(String nivol) {
    this.nivol = nivol;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
