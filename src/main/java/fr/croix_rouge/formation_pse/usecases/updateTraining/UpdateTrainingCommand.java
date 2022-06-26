package fr.croix_rouge.formation_pse.usecases.updateTraining;

import fr.croix_rouge.formation_pse.domain.Address;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class UpdateTrainingCommand {
  private Long id;
  private LocalDate endDate;
  private LocalDate startDate;
  private Address address;

}
