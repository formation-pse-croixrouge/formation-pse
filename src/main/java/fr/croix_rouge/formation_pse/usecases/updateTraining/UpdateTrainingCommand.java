package fr.croix_rouge.formation_pse.usecases.updateTraining;

import fr.croix_rouge.formation_pse.domain.Address;
import fr.croix_rouge.formation_pse.domain.Attendee;
import fr.croix_rouge.formation_pse.domain.Trainer;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Getter
public class UpdateTrainingCommand {
  private final Long id;
  private final LocalDate endDate;
  private final LocalDate startDate;
  private final Address address;
  private final Set<Trainer> trainers;
  private final Set<Attendee> attendees;

  public UpdateTrainingCommand(Long id, LocalDate endDate, LocalDate startDate, Address address, Set<Trainer> trainers, Set<Attendee> attendees) {
    this.id = id;
    this.endDate = endDate;
    this.startDate = startDate;
    this.address = address;
    this.trainers = trainers;
    this.attendees = attendees;
  }
}
