package fr.croix_rouge.formation_pse.factories;

import fr.croix_rouge.formation_pse.domain.PseUser;

public class PseUserTestFactory {
  public static PseUser organizer() {
    return new PseUser(39L, "NIVOL123L", "test1", "good", "yey");
  }
}
