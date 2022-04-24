package fr.croix_rouge.formation_pse.domain;

public class PseUser {
  private final String nivol;
  private final String firstName;
  private final String lastName;
  private final String password;

  public PseUser(String nivol, String firstName, String lastName, String password) {
    this.nivol = nivol;
    this.firstName = firstName;
    this.lastName = lastName;
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getNivol() {
    return nivol;
  }

  public String getPassword() {
    return password;
  }
}
