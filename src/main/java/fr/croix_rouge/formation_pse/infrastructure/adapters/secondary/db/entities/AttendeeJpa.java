package fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Builder
@NoArgsConstructor
@Setter
@Getter
public class AttendeeJpa extends BaseEntity {
  private String firstName;
  private String lastName;

  @Type(type = "json")
  @Column(columnDefinition = "json", updatable = false, nullable = false)
  private TechnicalAssessmentEvaluationJpa technicalAssessmentEvaluation;

  public AttendeeJpa(String firstName, String lastName, TechnicalAssessmentEvaluationJpa technicalAssessmentEvaluation) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.technicalAssessmentEvaluation = technicalAssessmentEvaluation;
  }
}
