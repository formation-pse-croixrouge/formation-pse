package fr.croix_rouge.formation_pse.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TechnicalAssessmentModule {
  private String title;
  private List<String> skills;
}
