package fr.croix_rouge.formation_pse.integration;

import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.TrainingDao;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.TrainingDbAdapter;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities.TrainingJpa;
import fr.croix_rouge.formation_pse.utils.DbIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DbIntegrationTest
class TrainingDbAdapterTest {

  @Autowired
  private TrainingDbAdapter sut;

  @Autowired
  private TrainingDao dao;

  @Test
  void shouldDelete() {
    // GIVEN
    TrainingJpa trainingJpa = TrainingJpa.builder().endDate(LocalDate.now()).build();
    TrainingJpa savedTraining = dao.save(trainingJpa);

    // WHEN
    sut.delete(savedTraining.getId());

    // THEN
    List<TrainingJpa> savedTrainings = dao.findAll();
    assertThat(savedTrainings).isEmpty();
  }
}
