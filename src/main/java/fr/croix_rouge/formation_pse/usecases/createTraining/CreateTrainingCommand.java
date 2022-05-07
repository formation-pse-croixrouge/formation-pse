package fr.croix_rouge.formation_pse.usecases.createTraining;

import fr.croix_rouge.formation_pse.domain.Address;
import fr.croix_rouge.formation_pse.domain.PseUser;
import fr.croix_rouge.formation_pse.domain.Training;

import java.time.LocalDate;

public class CreateTrainingCommand {
  private final PseUser user;
  private final LocalDate startDate;
  private final LocalDate endDate;
  private final String addressLabel;
  private final int addressPostalCode;
  private final String addressCity;

  public CreateTrainingCommand(PseUser user, LocalDate startDate, LocalDate endDate, String addressLabel, Integer addressPostalCode, String addressCity) {
    this.user = user;
    this.startDate = startDate;
    this.endDate = endDate;
    this.addressLabel = addressLabel;
    this.addressPostalCode = addressPostalCode;
    this.addressCity = addressCity;
  }

  public Training toDomain() {
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

  public static CreateTrainingCommandBuilder builder() {
    return new CreateTrainingCommandBuilder();
  }

  public static class CreateTrainingCommandBuilder {
    private PseUser user;
    private LocalDate startDate;
    private LocalDate endDate;
    private String addressLabel;
    private int addressPostalCode;
    private String addressCity;

    public CreateTrainingCommandBuilder user(PseUser user) {
      this.user = user;
      return this;
    }

    public CreateTrainingCommandBuilder startDate(LocalDate startDate) {
      this.startDate = startDate;
      return this;
    }

    public CreateTrainingCommandBuilder endDate(LocalDate endDate) {
      this.endDate = endDate;
      return this;
    }

    public CreateTrainingCommandBuilder addressLabel(String label) {
      this.addressLabel = label;
      return this;
    }

    public CreateTrainingCommandBuilder addressCity(String city) {
      this.addressCity = city;
      return this;
    }

    public CreateTrainingCommandBuilder addressPostalCode(int addressPostalCode) {
      this.addressPostalCode = addressPostalCode;
      return this;
    }

    public CreateTrainingCommand build() {
      return new CreateTrainingCommand(user, startDate, endDate, addressLabel, addressPostalCode, addressCity);
    }
  }
}
