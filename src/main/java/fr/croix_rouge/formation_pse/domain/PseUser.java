package fr.croix_rouge.formation_pse.domain;

import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PseUser pseUser = (PseUser) o;
    return Objects.equals(nivol, pseUser.nivol);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nivol);
  }
}
