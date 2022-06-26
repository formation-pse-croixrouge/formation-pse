package fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto;

import fr.croix_rouge.formation_pse.usecases.updateTraining.UpdateTrainingCommand;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateTrainingRequest {
  private LocalDate endDate;
  private LocalDate startDate;
  private AddressRequest address;

  public UpdateTrainingCommand toCommand(Long id) {
    return UpdateTrainingCommand.builder()
      .id(id)
      .endDate(endDate)
      .startDate(startDate)
      .address(address.toDomain())
      .build();
  }
}
