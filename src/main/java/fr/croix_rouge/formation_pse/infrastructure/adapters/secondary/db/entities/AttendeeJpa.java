package fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities;

import fr.croix_rouge.formation_pse.domain.Attendee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "attendees")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AttendeeJpa extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String firstName;
  private String lastName;

  @ManyToOne
  @JoinColumn(name = "training_id")
  private TrainingJpa training;

  @Type(type = "json")
  @Column(columnDefinition = "json", updatable = false, nullable = false)
  private TechnicalAssessmentEvaluationJpa technicalAssessmentEvaluation;

  public static AttendeeJpa fromDomain(Attendee attendee) {
    return AttendeeJpa.builder()
      .id(attendee.getId())
      .firstName(attendee.getFirstName())
      .lastName(attendee.getLastName())
      .technicalAssessmentEvaluation(TechnicalAssessmentEvaluationJpa.fromDomain(attendee.getTechnicalAssessmentEvaluation()))
      .build();
  }
}
