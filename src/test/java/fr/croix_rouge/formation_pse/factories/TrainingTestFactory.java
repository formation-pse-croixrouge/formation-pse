package fr.croix_rouge.formation_pse.factories;

import fr.croix_rouge.formation_pse.domain.Address;
import fr.croix_rouge.formation_pse.domain.Training;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static fr.croix_rouge.formation_pse.factories.PseUserTestFactory.*;

public class TrainingTestFactory {

  public static Training.TrainingBuilder aTraining() {
    return Training.builder()
      .id(12L)
      .createdBy(organizer())
      .address(Address.builder()
        .city("Paris")
        .postalCode(75011)
        .label("3 rue de l'Ours")
        .build()
      )
      .startDate(LocalDate.parse("2021-01-01"))
      .endDate(LocalDate.parse("2021-12-31"));
  }
}
