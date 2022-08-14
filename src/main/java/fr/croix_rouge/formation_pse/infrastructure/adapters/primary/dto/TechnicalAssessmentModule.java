package fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TechnicalAssessmentModule {
  private String title;
  private List<String> skills;
}
