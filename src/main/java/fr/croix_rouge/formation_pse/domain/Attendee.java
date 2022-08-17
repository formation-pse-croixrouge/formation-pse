package fr.croix_rouge.formation_pse.domain;


import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Builder
@Getter
public class Attendee {
  private Long id;
  private String firstName;
  private String lastName;
  private final TechnicalAssessmentEvaluation technicalAssessmentEvaluation;

  public Attendee(Long id, String firstName, String lastName, TechnicalAssessmentEvaluation technicalAssessmentEvaluation) {
    validate(firstName, lastName, technicalAssessmentEvaluation);
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.technicalAssessmentEvaluation = technicalAssessmentEvaluation;
  }

  public static Attendee newAttendee(String firstName, String lastName, TechnicalAssessmentStructure structure) {
    return Attendee.builder()
      .firstName(firstName)
      .lastName(lastName)
      .technicalAssessmentEvaluation(TechnicalAssessmentEvaluation.generateIncomplete(structure))
      .build();
  }

  private void validate(String firstName, String lastName, TechnicalAssessmentEvaluation technicalAssessmentEvaluation) {
    if(isBlank(firstName) || isBlank(lastName)) {
      throw new IllegalArgumentException("An attendee needs a firstname and a lastname");
    }
  }

  public void update(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public TechnicalAssessmentStructure getTechnicalAssessmentStructure() {
    return technicalAssessmentEvaluation.toStructure();
  }

  public TechnicalAssessmentEvaluation getTechnicalAssessmentEvaluation() {
    return technicalAssessmentEvaluation;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Attendee attendee = (Attendee) o;
    return Objects.equals(firstName, attendee.firstName) && Objects.equals(lastName, attendee.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName);
  }
}
