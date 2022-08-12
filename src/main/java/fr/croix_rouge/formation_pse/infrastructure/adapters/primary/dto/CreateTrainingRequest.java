package fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.croix_rouge.formation_pse.domain.Attendee;
import fr.croix_rouge.formation_pse.domain.PseUser;
import fr.croix_rouge.formation_pse.usecases.createTraining.CreateTrainingCommand;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class CreateTrainingRequest {
  private LocalDate startDate;
  private LocalDate endDate;
  private AddressRequest address;
  @JsonProperty("trainers")
  private List<String> trainersNivol;
  private Set<Attendee> attendees;
  private Set<TechnicalAssessmentModule> technicalAssessmentModules;

  public CreateTrainingCommand toCommand(PseUser user) {
    return CreateTrainingCommand.builder()
      .user(user)
      .startDate(startDate)
      .endDate(endDate)
      .addressLabel(address.getLabel())
      .addressPostalCode(address.getPostalCode())
      .addressCity(address.getCity())
      .trainersNivol(Set.copyOf(trainersNivol))
      .attendees(attendees)
      .technicalAssessmentModules(technicalAssessmentModules)
      .build();
  }
}
