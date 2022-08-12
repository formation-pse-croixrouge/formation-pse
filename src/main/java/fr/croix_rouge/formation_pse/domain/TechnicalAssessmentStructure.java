package fr.croix_rouge.formation_pse.domain;

import fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto.TechnicalAssessmentModule;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.Set;

@EqualsAndHashCode
@Builder
@Getter
public class TechnicalAssessmentStructure {
  private final Set<TechnicalAssessmentModule> modules;

  public TechnicalAssessmentStructure(Set<TechnicalAssessmentModule> modules) {
    validate(modules);
    this.modules = modules;
  }

  private void validate(Set<TechnicalAssessmentModule> modules) {
    if(CollectionUtils.isEmpty(modules)) {
      throw new IllegalArgumentException("A technical assessment must contain at least one module");
    }
  }
}
