package fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto;

import fr.croix_rouge.formation_pse.domain.ports.TrainerRepository;
import fr.croix_rouge.formation_pse.usecases.updateTraining.UpdateTrainingCommand;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class UpdateTrainingRequest {
  private LocalDate endDate;
  private LocalDate startDate;
  private AddressRequest address;
  private Set<String> trainers;

  public UpdateTrainingCommand toCommand(Long id, TrainerRepository trainerRepository) {
    return UpdateTrainingCommand.builder()
      .id(id)
      .endDate(endDate)
      .startDate(startDate)
      .address(address.toDomain())
      .trainers(trainerRepository.findAllByNivol(trainers))
      .build();
  }
}
