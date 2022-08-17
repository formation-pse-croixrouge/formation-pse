package fr.croix_rouge.formation_pse.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class TechnicalAssessmentEvaluation {
  private final List<TechnicalAssessmentEvaluationModule> modules;

  public TechnicalAssessmentEvaluation(List<TechnicalAssessmentEvaluationModule> modules) {
    this.modules = modules;
  }

  public static TechnicalAssessmentEvaluation generateIncomplete(TechnicalAssessmentStructure structure) {
    List<TechnicalAssessmentEvaluationModule> modules = structure.getModules().stream()
      .map(TechnicalAssessmentEvaluationModule::fromModule)
      .collect(Collectors.toList());
    return new TechnicalAssessmentEvaluation(modules);
  }

  public Set<Grade> getGrades() {
    return Set.copyOf(modules.stream().map(module -> module.grades).flatMap(Collection::stream).collect(Collectors.toSet()));
  }

  public TechnicalAssessmentStructure toStructure() {
    return new TechnicalAssessmentStructure(modules.stream()
      .map(module -> TechnicalAssessmentModule.builder()
        .title(module.getTitle())
        .skills(module.getGrades().stream()
          .map(Grade::getSkill)
          .collect(Collectors.toList())
        )
        .build()
      )
      .collect(Collectors.toList()));
  }

  @Getter
  public static class Grade {
    private final String skill;
    private Boolean value;

    public Grade(String skill, Boolean value) {
      this.skill = skill;
      this.value = value;
    }

    private Grade(String skill) {
      this.skill = skill;
    }

    public static List<Grade> fromSkills(List<String> skills) {
      List<Grade> grades = new ArrayList<>();
      for (String s : skills) {
        grades.add(new Grade(s));
      }
      return grades;
    }

    public boolean isIncomplete() {
      return value == null;
    }
  }

  @Getter
  public static class TechnicalAssessmentEvaluationModule {
    private final String title;
    private final List<Grade> grades;

    public TechnicalAssessmentEvaluationModule(String title, List<Grade> grades) {
      this.title = title;
      this.grades = grades;
    }

    public static TechnicalAssessmentEvaluationModule fromModule(TechnicalAssessmentModule module) {
      return new TechnicalAssessmentEvaluationModule(module.getTitle(), Grade.fromSkills(module.getSkills()));
    }
  }
}
