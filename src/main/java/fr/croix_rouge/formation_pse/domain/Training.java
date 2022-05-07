package fr.croix_rouge.formation_pse.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
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

  public String getAddressLabel() { return address.getLabel(); }
  public Integer getAddressPostalCode() { return address.getPostalCode(); }
  public String getAddressCity() { return address.getCity(); }
}
