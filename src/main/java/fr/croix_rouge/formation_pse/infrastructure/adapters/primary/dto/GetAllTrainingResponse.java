package fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto;

import fr.croix_rouge.formation_pse.domain.Training;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class GetAllTrainingResponse {
  private List<SingleTrainingResponse> trainings;

  public static GetAllTrainingResponse from(List<Training> trainings) {
    List<SingleTrainingResponse> trainingResponses = trainings.stream()
      .map(SingleTrainingResponse::fromDomain)
      .collect(Collectors.toList());
    return new GetAllTrainingResponse(trainingResponses);
  }

  @Data
  public static class SingleTrainingResponse {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String address;
    private Integer postalCode;
    private String city;

    public static SingleTrainingResponse fromDomain(Training training) {
      SingleTrainingResponse response = new SingleTrainingResponse();
      response.id = training.getId();
      response.startDate = training.getStartDate();
      response.endDate = training.getEndDate();
      response.address = training.getAddressLabel();
      response.postalCode = training.getAddressPostalCode();
      response.city = training.getAddressCity();
      return response;
    }
  }
}
