package fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities;

import fr.croix_rouge.formation_pse.domain.Training;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TRAININGS")
public class TrainingJpa {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;

  @Column(name = "START_DATE")
  private LocalDate startDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public static TrainingJpa fromDomain(Training training) {
    TrainingJpa trainingJpa = new TrainingJpa();
    trainingJpa.setStartDate(training.getStartDate());
    return trainingJpa;
  }
}
