package fr.croix_rouge.formation_pse.factories;

import fr.croix_rouge.formation_pse.domain.Trainer;

public class TrainerTestFactory {

  public static Trainer.TrainerBuilder aTrainer() {
    return Trainer.builder()
      .nivol("NIVOL_3333L")
      .firstName("John")
      .lastName("Doe");
  }
}
