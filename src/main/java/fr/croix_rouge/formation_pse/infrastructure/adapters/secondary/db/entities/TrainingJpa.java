package fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities;

import fr.croix_rouge.formation_pse.domain.Address;
import fr.croix_rouge.formation_pse.domain.Training;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TRAININGS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

  public Long getId() {
    return id;
  }

  public static TrainingJpa fromDomain(Training training) {
    return TrainingJpa.builder()
      .startDate(training.getStartDate())
      .endDate(training.getEndDate())
      .addressCity(training.getAddressCity())
      .addressLabel(training.getAddressLabel())
      .addressPostalCode(training.getAddressPostalCode())
      .build();
  }

  public Training toDomain() {
    return Training.builder()
      .id(id)
      .startDate(startDate)
      .endDate(endDate)
      .address(Address.builder()
        .label(addressLabel)
        .city(addressCity)
        .postalCode(addressPostalCode)
        .build())
      .build();
  }
}
