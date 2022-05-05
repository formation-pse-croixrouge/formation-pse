package fr.croix_rouge.formation_pse.domain;

import java.time.LocalDate;

public class Training {
  private final Long id;
  private final LocalDate startDate;
  private final LocalDate endDate;
  private final Address address;
  private final PseUser createdBy;

  public Training(Long id, LocalDate startDate, LocalDate endDate, Address address, PseUser createdBy) {
    this.id = id;
    this.startDate = startDate;
    this.endDate = endDate;
    this.address = address;
    this.createdBy = createdBy;
  }

  public Long getId() {
    return id;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public Address getAddress() {
    return address;
  }

  public PseUser getCreatedBy() {
    return createdBy;
  }

  public void calculateNextStartDate() {

  }
}
