package fr.croix_rouge.formation_pse.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.List;

@EqualsAndHashCode
@Builder
@Getter
public class TechnicalAssessmentStructure {
  private final List<TechnicalAssessmentModule> modules;

  public TechnicalAssessmentStructure(List<TechnicalAssessmentModule> modules) {
    validate(modules);
    this.modules = modules;
  }

  private void validate(List<TechnicalAssessmentModule> modules) {
    if(CollectionUtils.isEmpty(modules)) {
      throw new IllegalArgumentException("A technical assessment must contain at least one module");
    }
  }
}
