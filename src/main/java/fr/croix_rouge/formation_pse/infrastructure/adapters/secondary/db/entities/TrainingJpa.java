package fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities;

import fr.croix_rouge.formation_pse.domain.Address;
import fr.croix_rouge.formation_pse.domain.Attendee;
import fr.croix_rouge.formation_pse.domain.Training;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "TRAININGS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class TrainingJpa {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
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

  @ManyToOne
  @JoinColumn(name = "creator_id", nullable = false)
  private PseUserJpa creator;

  @ManyToMany(cascade = {CascadeType.MERGE})
  @JoinTable(
    name = "TRAININGS_USERS",
    joinColumns = {@JoinColumn(name = "training_id")},
    inverseJoinColumns = {@JoinColumn(name = "user_id")}
  )
  private Set<PseUserJpa> trainers;

  @ElementCollection(fetch = FetchType.EAGER)
  private Set<AttendeeJpa> attendees;

  public Long getId() {
    return id;
  }

  public static TrainingJpa fromDomain(Training training) {
    return TrainingJpa.builder()
      .id(training.getId())
      .startDate(training.getStartDate())
      .endDate(training.getEndDate())
      .addressCity(training.getAddressCity())
      .addressLabel(training.getAddressLabel())
      .addressPostalCode(training.getAddressPostalCode())
      .trainers(training.getTrainers().stream()
        .map(PseUserJpa::fromTrainer)
        .collect(Collectors.toSet())
      )
      .creator(PseUserJpa.fromDomain(training.getCreatedBy()))
      .attendees(training.getAttendees().stream()
        .map(attendee -> new AttendeeJpa(attendee.getFirstName(), attendee.getLastName()))
        .collect(Collectors.toSet())
      )
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
      .trainers(trainers.stream().map(PseUserJpa::toTrainer).collect(Collectors.toSet()))
      .createdBy(creator.toDomain())
      .attendees(attendees.stream()
        .map(attendeeJpa -> new Attendee(attendeeJpa.getFirstName(), attendeeJpa.getLastName()))
        .collect(Collectors.toSet())
      )
      .build();
  }
}
