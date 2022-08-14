package fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities;

import fr.croix_rouge.formation_pse.domain.TechnicalAssessmentEvaluation;
import fr.croix_rouge.formation_pse.domain.TechnicalAssessmentEvaluation.TechnicalAssessmentEvaluationModule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import static fr.croix_rouge.formation_pse.domain.TechnicalAssessmentEvaluation.Grade;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnicalAssessmentEvaluationJpa implements Serializable {
  private List<TechnicalAssessmentEvaluationModuleJpa> modules;

  public static TechnicalAssessmentEvaluationJpa fromDomain(TechnicalAssessmentEvaluation evaluation) {
    List<TechnicalAssessmentEvaluationModuleJpa> modulesJpa = evaluation.getModules().stream()
      .map(TechnicalAssessmentEvaluationModuleJpa::fromDomain)
      .collect(Collectors.toList());
    return new TechnicalAssessmentEvaluationJpa(modulesJpa);
  }

  public TechnicalAssessmentEvaluation toDomain() {
    List<TechnicalAssessmentEvaluationModule> moduleList = TechnicalAssessmentEvaluationModuleJpa.toDomain(modules);
    return new TechnicalAssessmentEvaluation(moduleList);
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class TechnicalAssessmentEvaluationModuleJpa {
    private String title;
    private List<GradeJpa> grades;

    public static TechnicalAssessmentEvaluationModuleJpa fromDomain(TechnicalAssessmentEvaluationModule module) {
      return new TechnicalAssessmentEvaluationModuleJpa(module.getTitle(), GradeJpa.fromDomain(module.getGrades()));
    }

    public static List<TechnicalAssessmentEvaluationModule> toDomain(List<TechnicalAssessmentEvaluationModuleJpa> modulesJpa) {
      return modulesJpa.stream()
        .map(moduleJpa -> moduleJpa.toDomain())
        .collect(Collectors.toList());
    }

    private TechnicalAssessmentEvaluationModule toDomain() {
      return new TechnicalAssessmentEvaluationModule(title, GradeJpa.toDomain(grades));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GradeJpa {
      private String skill;
      private Boolean value;

      public static List<GradeJpa> fromDomain(List<Grade> grades) {
        return grades.stream().map(GradeJpa::fromDomain).collect(Collectors.toList());
      }

      private static GradeJpa fromDomain(Grade grade) {
        return new GradeJpa(grade.getSkill(), grade.getValue());
      }

      public static List<Grade> toDomain(List<GradeJpa> grades) {
        return grades.stream()
          .map(gradeJpa -> gradeJpa.toDomain())
          .collect(Collectors.toList());
      }

      private Grade toDomain() {
        return new Grade(skill, value);
      }
    }

  }
}
