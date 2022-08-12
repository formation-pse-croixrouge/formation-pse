package fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities;

import fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto.TechnicalAssessmentModule;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class TechnicalAssessmentModuleJpa implements Serializable {
  private String title;
  private Set<String> skills;

  public static Set<TechnicalAssessmentModuleJpa> fromDomain(Set<TechnicalAssessmentModule> modules) {
    return modules.stream()
      .map(TechnicalAssessmentModuleJpa::fromDomain)
      .collect(Collectors.toSet());
  }

  private static TechnicalAssessmentModuleJpa fromDomain(TechnicalAssessmentModule module) {
    return TechnicalAssessmentModuleJpa.builder()
      .skills(module.getSkills())
      .title(module.getTitle())
      .build();
  }

  public static Set<TechnicalAssessmentModule> toDomain(Set<TechnicalAssessmentModuleJpa> modules) {
    return modules.stream()
      .map(s -> s.toDomain())
      .collect(Collectors.toSet());
  }

  private TechnicalAssessmentModule toDomain() {
    return TechnicalAssessmentModule.builder()
      .title(this.getTitle())
      .skills(this.getSkills())
      .build();
  }
}
