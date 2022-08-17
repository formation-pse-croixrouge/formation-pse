package fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto;

import fr.croix_rouge.formation_pse.domain.ports.TrainerRepository;
import fr.croix_rouge.formation_pse.usecases.updateTraining.UpdateTrainingCommand;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UpdateTrainingRequest {
  private LocalDate endDate;
  private LocalDate startDate;
  private AddressRequest address;
  private Set<String> trainers;
  private Set<AttendeeDto> attendees;

  public UpdateTrainingCommand toCommand(Long id, TrainerRepository trainerRepository) {
    return UpdateTrainingCommand.builder()
      .id(id)
      .endDate(endDate)
      .startDate(startDate)
      .address(address.toDomain())
      .trainers(trainerRepository.findAllByNivol(trainers))
      .attendees(attendees.stream().map(AttendeeDto::toDomain).collect(Collectors.toSet()))
      .build();
  }
}
