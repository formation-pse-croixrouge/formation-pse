package fr.croix_rouge.formation_pse.factories;

import fr.croix_rouge.formation_pse.domain.Address;
import fr.croix_rouge.formation_pse.domain.Attendee;
import fr.croix_rouge.formation_pse.domain.TechnicalAssessmentEvaluation;
import fr.croix_rouge.formation_pse.domain.TechnicalAssessmentStructure;
import fr.croix_rouge.formation_pse.domain.Trainer;
import fr.croix_rouge.formation_pse.domain.Training;
import fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto.TechnicalAssessmentModule;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static fr.croix_rouge.formation_pse.factories.PseUserTestFactory.organizer;

public class TrainingTestFactory {

  public static Training.TrainingBuilder aTraining() {
    List<TechnicalAssessmentModule> someModules = List.of(TechnicalAssessmentModule.builder()
      .title("Module 1 : Organisation des secours")
      .skills(List.of("Réaliser l'inventaire des sacs de PS", "Réaliser l’inventaire du matériel (lot A et VPSP)"))
      .build());
    TechnicalAssessmentStructure aStructure = new TechnicalAssessmentStructure(someModules);
    TechnicalAssessmentEvaluation anEmptyEvaluation = TechnicalAssessmentEvaluation.generateIncomplete(aStructure);
    return Training.builder()
      .id(8888L)
      .createdBy(organizer())
      .address(Address.builder()
        .city("Paris")
        .postalCode(75011)
        .label("3 rue de l'Ours")
        .build()
      )
      .trainers(Set.of(Trainer.builder()
          .nivol("A_NIVOL123")
          .firstName("John")
          .lastName("Doe")
        .build()))
      .attendees(Set.of(Attendee.builder()
          .firstName("Victor")
          .lastName("Rideau")
          .technicalAssessmentEvaluation(anEmptyEvaluation)
        .build()))
      .startDate(LocalDate.parse("2021-01-01"))
      .endDate(LocalDate.parse("2021-12-31"));
  }
}
