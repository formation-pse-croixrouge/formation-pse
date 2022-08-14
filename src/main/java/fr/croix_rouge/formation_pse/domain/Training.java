package fr.croix_rouge.formation_pse.domain;

import fr.croix_rouge.formation_pse.usecases.updateTraining.UpdateTrainingCommand;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class Training {
  private final Long id;
  private LocalDate startDate;
  private LocalDate endDate;
  private Address address;
  private final PseUser createdBy;
  private Set<Trainer> trainers;
  private Set<Attendee> attendees;

  public Training(Long id, LocalDate startDate, LocalDate endDate, Address address, PseUser createdBy, Set<Trainer> trainers, Set<Attendee> attendees) {
    validate(startDate, endDate, createdBy, trainers, attendees);
    this.id = id;
    this.startDate = startDate;
    this.endDate = endDate;
    this.address = address;
    this.createdBy = createdBy;
    this.trainers = trainers;
    this.attendees = attendees;
  }

  private void validate(LocalDate startDate, LocalDate endDate, PseUser createdBy, Set<Trainer> trainers, Set<Attendee> attendees) {
    if(createdBy == null) {
      throw new IllegalArgumentException("A training needs a user");
    }
    if(startDate == null) {
      throw new IllegalArgumentException("A training needs a startDate");
    }
    if(endDate == null || endDate.isBefore(startDate)) {
      throw new IllegalArgumentException("Wrong endDate");
    }
    if(CollectionUtils.isEmpty(trainers)) {
      throw new IllegalArgumentException("A training needs at least one trainer");
    }
    if(CollectionUtils.isEmpty(attendees)) {
      throw new IllegalArgumentException("A training needs at least one attendee");
    }
  }

  public String getAddressLabel() {
    return address.getLabel();
  }
  public Integer getAddressPostalCode() {
    return address.getPostalCode();
  }
  public String getAddressCity() {
    return address.getCity();
  }

  public void update(UpdateTrainingCommand updateTrainingCommand) {
    validate(updateTrainingCommand.getStartDate(),
      updateTrainingCommand.getEndDate(),
      createdBy,
      updateTrainingCommand.getTrainers(),
      updateTrainingCommand.getAttendees());
    this.startDate = updateTrainingCommand.getStartDate();
    this.endDate = updateTrainingCommand.getEndDate();
    this.trainers = updateTrainingCommand.getTrainers();
    this.attendees = updateTrainingCommand.getAttendees();
  }

  public TechnicalAssessmentStructure getTechnicalAssessmentStructure() {
    return attendees.iterator().next().getTechnicalAssessmentStructure();
  }
}
