package fr.croix_rouge.formation_pse.domain;


import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Builder
@Getter
public class Attendee {
  private final String firstName;
  private final String lastName;

  public Attendee(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Attendee attendee = (Attendee) o;
    return Objects.equals(firstName, attendee.firstName) && Objects.equals(lastName, attendee.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName);
  }
}
