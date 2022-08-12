package fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities;

import fr.croix_rouge.formation_pse.domain.TechnicalAssessmentStructure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class TechnicalAssessmentStructureJpa {
  private Set<TechnicalAssessmentModuleJpa> modules;

  public static TechnicalAssessmentStructureJpa fromDomain(TechnicalAssessmentStructure technicalAssessmentStructure) {
    return new TechnicalAssessmentStructureJpa(TechnicalAssessmentModuleJpa.fromDomain(technicalAssessmentStructure.getModules()));
  }

  public TechnicalAssessmentStructure toDomain() {
    return TechnicalAssessmentStructure.builder()
      .modules(TechnicalAssessmentModuleJpa.toDomain(modules))
      .build();
  }
}
