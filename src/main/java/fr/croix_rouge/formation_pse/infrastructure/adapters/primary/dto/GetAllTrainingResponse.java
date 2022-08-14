package fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto;

import fr.croix_rouge.formation_pse.domain.Training;
import lombok.AllArgsConstructor;
import lombok.Data;

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
}
