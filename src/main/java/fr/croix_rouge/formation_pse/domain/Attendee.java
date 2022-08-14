package fr.croix_rouge.formation_pse.domain;


import lombok.Builder;
import lombok.Getter;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Builder
@Getter
public class Attendee {
  private final String firstName;
  private final String lastName;
  private final TechnicalAssessmentEvaluation technicalAssessmentEvaluation;

  public Attendee(String firstName, String lastName, TechnicalAssessmentEvaluation technicalAssessmentEvaluation) {
    validate(firstName, lastName, technicalAssessmentEvaluation);
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
    if(technicalAssessmentEvaluation == null) {
      throw new IllegalArgumentException("An attendee needs a technical assessment evaluation");
    }
  }

  public TechnicalAssessmentStructure getTechnicalAssessmentStructure() {
    return technicalAssessmentEvaluation.toStructure();
  }

  public TechnicalAssessmentEvaluation getTechnicalAssessmentEvaluation() {
    return technicalAssessmentEvaluation;
  }
}
