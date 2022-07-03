package fr.croix_rouge.formation_pse.domain;

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
  private final LocalDate startDate;
  private final LocalDate endDate;
  private final Address address;
  private final PseUser createdBy;
  private final Set<Trainer> trainers;

  public Training(Long id, LocalDate startDate, LocalDate endDate, Address address, PseUser createdBy, Set<Trainer> trainers) {
    validate(startDate, endDate, createdBy, trainers);
    this.id = id;
    this.startDate = startDate;
    this.endDate = endDate;
    this.address = address;
    this.createdBy = createdBy;
    this.trainers = trainers;
  }

  private void validate(LocalDate startDate, LocalDate endDate, PseUser createdBy, Set<Trainer> trainers) {
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
}
