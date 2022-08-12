package fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities;

import fr.croix_rouge.formation_pse.domain.Address;
import fr.croix_rouge.formation_pse.domain.Attendee;
import fr.croix_rouge.formation_pse.domain.Training;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name="TRAININGS_ATTENDEES")
  private Set<AttendeeJpa> attendees;

  @Type(type = "json")
  @Column(columnDefinition = "json", updatable = false)
  private TechnicalAssessmentStructureJpa technicalAssessmentStructure;

  public static TrainingJpa fromDomain(Training training) {
    return TrainingJpa.builder()
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
      .attendees(training.getAttendees().stream()
        .map(attendee -> new AttendeeJpa(attendee.getFirstName(), attendee.getLastName()))
        .collect(Collectors.toSet())
      )
      .technicalAssessmentStructure(TechnicalAssessmentStructureJpa.fromDomain(training.getTechnicalAssessmentStructure()))
      .build();
  }

  public Training toDomain() {
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
      .attendees(attendees.stream()
        .map(attendeeJpa -> new Attendee(attendeeJpa.getFirstName(), attendeeJpa.getLastName()))
        .collect(Collectors.toSet())
      )
      .technicalAssessmentStructure(technicalAssessmentStructure.toDomain())
      .build();
  }
}
