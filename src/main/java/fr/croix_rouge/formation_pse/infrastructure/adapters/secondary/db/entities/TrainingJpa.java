package fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities;

import fr.croix_rouge.formation_pse.domain.Address;
import fr.croix_rouge.formation_pse.domain.Attendee;
import fr.croix_rouge.formation_pse.domain.TechnicalAssessmentEvaluation;
import fr.croix_rouge.formation_pse.domain.Training;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "TRAININGS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TrainingJpa {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "START_DATE")
  private LocalDate startDate;

  @Column(name = "END_DATE")
  private LocalDate endDate;

  @Embedded
  @AttributeOverride( name = "label", column = @Column(name = "ADDRESS_LABEL"))
  @AttributeOverride( name = "city", column = @Column(name = "ADDRESS_CITY"))
  @AttributeOverride( name = "postalCode", column = @Column(name = "ADDRESS_POSTAL_CODE"))
  private AddressJpa address;

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

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "training", cascade = CascadeType.ALL)
  private Set<AttendeeJpa> attendees;

  public static TrainingJpa fromDomain(Training training) {
    Set<AttendeeJpa> attendeesJpa = training.getAttendees().stream()
      .map(AttendeeJpa::fromDomain)
      .collect(Collectors.toSet());
    TrainingJpa trainingJpa = TrainingJpa.builder()
      .id(training.getId())
      .startDate(training.getStartDate())
      .endDate(training.getEndDate())
      .address(AddressJpa.builder()
        .city(training.getAddressCity())
        .label(training.getAddressLabel())
        .postalCode(training.getAddressPostalCode())
        .build()
      )
      .trainers(training.getTrainers().stream()
        .map(PseUserJpa::fromTrainer)
        .collect(Collectors.toSet())
      )
      .creator(PseUserJpa.fromDomain(training.getCreatedBy()))
      .attendees(attendeesJpa)
      .build();
    attendeesJpa.forEach(attendeeJpa -> attendeeJpa.setTraining(trainingJpa));
    return trainingJpa;
  }

  public Training toDomain() {
    Set<Attendee> attendees = this.attendees.stream()
      .map(attendeeJpa -> {
        TechnicalAssessmentEvaluationJpa evaluationJpa = attendeeJpa.getTechnicalAssessmentEvaluation();
        TechnicalAssessmentEvaluation evaluation = evaluationJpa.toDomain();
        return new Attendee(attendeeJpa.getId(), attendeeJpa.getFirstName(), attendeeJpa.getLastName(), evaluation);
      })
      .collect(Collectors.toSet());
    return Training.builder()
      .id(id)
      .startDate(startDate)
      .endDate(endDate)
      .address(Address.builder()
        .label(address.getLabel())
        .city(address.getCity())
        .postalCode(address.getPostalCode())
        .build())
      .trainers(trainers.stream().map(PseUserJpa::toTrainer).collect(Collectors.toSet()))
      .createdBy(creator.toDomain())
      .attendees(attendees
      )
      .build();
  }
}
