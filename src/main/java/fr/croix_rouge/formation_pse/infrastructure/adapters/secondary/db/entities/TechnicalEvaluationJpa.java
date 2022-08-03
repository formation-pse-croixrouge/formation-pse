package fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class TechnicalEvaluationJpa implements Serializable {
  private String type;
  private String description;
}
