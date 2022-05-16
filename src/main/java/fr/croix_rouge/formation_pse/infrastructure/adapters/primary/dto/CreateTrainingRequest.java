package fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto;

import fr.croix_rouge.formation_pse.domain.PseUser;
import fr.croix_rouge.formation_pse.usecases.createTraining.CreateTrainingCommand;

import java.time.LocalDate;

public class CreateTrainingRequest {
  private LocalDate startDate;
  private LocalDate endDate;
  private AddressRequest address;

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public AddressRequest getAddress() {
    return address;
  }

  public void setAddress(AddressRequest address) {
    this.address = address;
  }

  public CreateTrainingCommand toCommand(PseUser user) {
    return new CreateTrainingCommand(
      user, startDate, endDate, address.getLabel(), address.getPostalCode(), address.getCity()
    );
  }
}
