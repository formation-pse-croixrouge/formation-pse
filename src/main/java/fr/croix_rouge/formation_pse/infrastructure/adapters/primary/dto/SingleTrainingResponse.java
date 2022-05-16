package fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto;

import fr.croix_rouge.formation_pse.domain.Training;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SingleTrainingResponse {
  private Long id;
  private LocalDate startDate;
  private LocalDate endDate;
  private String address;
  private Integer postalCode;
  private String city;

  public static SingleTrainingResponse fromDomain(Training training) {
    SingleTrainingResponse response = new SingleTrainingResponse();
    response.id = training.getId();
    response.startDate = training.getStartDate();
    response.endDate = training.getEndDate();
    response.address = training.getAddressLabel();
    response.postalCode = training.getAddressPostalCode();
    response.city = training.getAddressCity();
    return response;
  }
}
