package fr.croix_rouge.formation_pse.usecases.createTraining;

import fr.croix_rouge.formation_pse.domain.Address;
import fr.croix_rouge.formation_pse.domain.PseUser;
import fr.croix_rouge.formation_pse.domain.Trainer;
import fr.croix_rouge.formation_pse.domain.Training;
import lombok.Builder;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Builder
public class CreateTrainingCommand {
  private final PseUser user;
  private final LocalDate startDate;
  private final LocalDate endDate;
  private final String addressLabel;
  private final Integer addressPostalCode;
  private final String addressCity;
  private final Set<String> trainersNivol;

  public CreateTrainingCommand(PseUser user, LocalDate startDate, LocalDate endDate, String addressLabel, Integer addressPostalCode, String addressCity, Set<String> trainersNivol) {
    this.user = user;
    this.startDate = startDate;
    this.endDate = endDate;
    this.addressLabel = addressLabel;
    this.addressPostalCode = addressPostalCode;
    this.addressCity = addressCity;
    this.trainersNivol = trainersNivol == null ? null : Set.copyOf(trainersNivol);
  }

  public Training toDomain(Set<Trainer> trainers) {
    return Training.builder()
      .startDate(startDate)
      .endDate(endDate)
      .address(Address.builder()
        .label(addressLabel)
        .postalCode(addressPostalCode)
        .city(addressCity)
        .build()
      )
      .createdBy(user)
      .trainers(trainers)
      .build();
  }

  public PseUser getUser() {
    return user;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public String getAddressLabel() {
    return addressLabel;
  }

  public int getAddressPostalCode() {
    return addressPostalCode;
  }

  public String getAddressCity() {
    return addressCity;
  }

  public Set<String> getTrainersNivol() {
    return trainersNivol;
  }
}
