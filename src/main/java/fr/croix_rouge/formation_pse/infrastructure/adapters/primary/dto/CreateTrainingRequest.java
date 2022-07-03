package fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.croix_rouge.formation_pse.domain.PseUser;
import fr.croix_rouge.formation_pse.usecases.createTraining.CreateTrainingCommand;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class CreateTrainingRequest {
  private LocalDate startDate;
  private LocalDate endDate;
  private AddressRequest address;
  @JsonProperty("trainers")
  private List<String> trainersNivol;

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

  public List<String> getTrainersNivol() {
    return trainersNivol;
  }

  public void setTrainersNivol(List<String> trainersNivol) {
    this.trainersNivol = trainersNivol;
  }

  public CreateTrainingCommand toCommand(PseUser user) {
    return CreateTrainingCommand.builder()
      .user(user)
      .startDate(startDate)
      .endDate(endDate)
      .addressLabel(address.getLabel())
      .addressPostalCode(address.getPostalCode())
      .addressCity(address.getCity())
      .trainersNivol(Set.copyOf(trainersNivol))
      .build();
  }
}
