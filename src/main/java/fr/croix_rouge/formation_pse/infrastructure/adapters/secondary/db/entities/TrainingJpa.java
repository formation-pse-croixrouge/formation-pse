package fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities;

import fr.croix_rouge.formation_pse.domain.Training;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TRAININGS")
public class TrainingJpa {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;

  @Column(name = "START_DATE")
  private LocalDate startDate;

  @Column(name = "END_DATE")
  private LocalDate endDate;

  @Column(name = "ADDRESS_LABEL")
  private String addressLabel;

  @Column(name = "ADDRESS_POSTAL_CODE")
  private Integer addressPostalCode;

  @Column(name = "ADDRESS_CITY")
  private String addressCity;

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public void setAddressLabel(String addressLabel) {
    this.addressLabel = addressLabel;
  }

  public void setAddressPostalCode(Integer addressPostalCode) {
    this.addressPostalCode = addressPostalCode;
  }

  public void setAddressCity(String addressCity) {
    this.addressCity = addressCity;
  }

  public static TrainingJpa fromDomain(Training training) {
    TrainingJpa trainingJpa = new TrainingJpa();
    trainingJpa.setStartDate(training.getStartDate());
    trainingJpa.setEndDate(training.getEndDate());
    trainingJpa.setAddressLabel(training.getAddressLabel());
    trainingJpa.setAddressPostalCode(training.getAddressPostalCode());
    trainingJpa.setAddressCity(training.getAddressCity());
    return trainingJpa;
  }
}
