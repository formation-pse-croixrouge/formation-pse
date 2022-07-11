package fr.croix_rouge.formation_pse.usecases.updateTraining;

import fr.croix_rouge.formation_pse.domain.Address;
import fr.croix_rouge.formation_pse.domain.Trainer;
import fr.croix_rouge.formation_pse.domain.Training;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Builder
@Getter
public class UpdateTrainingCommand {
  private final Long id;
  private final LocalDate endDate;
  private final LocalDate startDate;
  private final Address address;
  private final Set<Trainer> trainers;

  public UpdateTrainingCommand(Long id, LocalDate endDate, LocalDate startDate, Address address, Set<Trainer> trainers) {
    if(CollectionUtils.isEmpty(trainers)) {
      throw new IllegalArgumentException("At least one trainer is needed");
    }
    this.id = id;
    this.endDate = endDate;
    this.startDate = startDate;
    this.address = address;
    this.trainers = trainers;
  }
}
