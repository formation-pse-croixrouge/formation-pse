package fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.croix_rouge.formation_pse.domain.Attendee;
import fr.croix_rouge.formation_pse.domain.PseUser;
import fr.croix_rouge.formation_pse.domain.TechnicalAssessmentStructure;
import fr.croix_rouge.formation_pse.usecases.createTraining.CreateTrainingCommand;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class CreateTrainingRequest {
  private LocalDate startDate;
  private LocalDate endDate;
  private AddressRequest address;
  @JsonProperty("trainers")
  private List<String> trainersNivol;
  private Set<AttendeeRequest> attendees;
  private List<TechnicalAssessmentModule> technicalAssessmentModules;

  public CreateTrainingCommand toCommand(PseUser user) {
    return CreateTrainingCommand.builder()
      .user(user)
      .startDate(startDate)
      .endDate(endDate)
      .addressLabel(address.getLabel())
      .addressPostalCode(address.getPostalCode())
      .addressCity(address.getCity())
      .trainersNivol(Set.copyOf(trainersNivol))
      .attendees(attendees.stream()
        .map(attendeeRequest -> Attendee.newAttendee(attendeeRequest.getFirstName(), attendeeRequest.getLastName(), new TechnicalAssessmentStructure(technicalAssessmentModules)))
        .collect(Collectors.toSet())
      )
      .technicalAssessmentModules(technicalAssessmentModules)
      .build();
  }

  @Data
  @Builder
  public static class AttendeeRequest {
    private String firstName;
    private String lastName;
  }
}
