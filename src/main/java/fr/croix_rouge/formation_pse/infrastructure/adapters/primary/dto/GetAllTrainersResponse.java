package fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto;

import fr.croix_rouge.formation_pse.domain.Trainer;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class GetAllTrainersResponse {
  public final Set<TrainerResponse> trainers;

  public GetAllTrainersResponse(Set<Trainer> trainers) {
    this.trainers = trainers.stream()
      .map(TrainerResponse::fromDomain)
      .collect(Collectors.toSet());
  }

  @Builder
  @Data
  public static class TrainerResponse {
    private String nivol;
    private String firstName;
    private String lastName;

    public static TrainerResponse fromDomain(Trainer t) {
      return TrainerResponse.builder()
        .nivol(t.getNivol())
        .firstName(t.getFirstName())
        .lastName(t.getLastName())
        .build();
    }
  }
}
