package fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto;

import fr.croix_rouge.formation_pse.domain.Attendee;
import fr.croix_rouge.formation_pse.domain.Training;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto.GetAllTrainersResponse.TrainerResponse;

@Data
public class SingleTrainingResponse {
  private Long id;
  private LocalDate startDate;
  private LocalDate endDate;
  private String address;
  private Integer postalCode;
  private String city;
  private Set<TrainerResponse> trainers;
  private Set<Attendee> attendees;
  private List<TechnicalAssessmentModuleResponse> technicalAssessmentModules;

  public static SingleTrainingResponse fromDomain(Training training) {
    SingleTrainingResponse response = new SingleTrainingResponse();
    response.id = training.getId();
    response.startDate = training.getStartDate();
    response.endDate = training.getEndDate();
    response.address = training.getAddressLabel();
    response.postalCode = training.getAddressPostalCode();
    response.city = training.getAddressCity();
    response.trainers = training.getTrainers().stream().map(TrainerResponse::fromDomain).collect(Collectors.toSet());
    response.attendees = training.getAttendees();
    response.technicalAssessmentModules = TechnicalAssessmentModuleResponse.fromDomain(training.getTechnicalAssessmentStructure().getModules());
    return response;
  }

  @Data
  @AllArgsConstructor
  public static class TechnicalAssessmentModuleResponse {
    private final String title;
    private final List<String> skills;

    public static List<TechnicalAssessmentModuleResponse> fromDomain(List<TechnicalAssessmentModule> modules) {
      return modules.stream()
        .map(TechnicalAssessmentModuleResponse::fromDomain)
        .collect(Collectors.toList());
    }

    private static TechnicalAssessmentModuleResponse fromDomain(TechnicalAssessmentModule module) {
      return new TechnicalAssessmentModuleResponse(module.getTitle(), module.getSkills());
    }
  }
}
