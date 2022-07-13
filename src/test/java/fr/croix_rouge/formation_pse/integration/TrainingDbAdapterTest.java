package fr.croix_rouge.formation_pse.integration;

import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.TrainingDao;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.TrainingDbAdapter;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities.PseUserJpa;
import fr.croix_rouge.formation_pse.infrastructure.adapters.secondary.db.entities.TrainingJpa;
import fr.croix_rouge.formation_pse.utils.DbIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DbIntegrationTest
class TrainingDbAdapterTest {

  @Autowired
  private TrainingDbAdapter sut;

  @Autowired
  private TrainingDao dao;

  @Autowired
  private EntityManager entityManager;

  @Test
  void shouldDelete() {
    // GIVEN
    PseUserJpa aUser = createUser();
    TrainingJpa trainingJpa = TrainingJpa.builder()
      .creator(aUser)
      .endDate(LocalDate.now()).build();
    TrainingJpa savedTraining = dao.save(trainingJpa);

    // WHEN
    sut.delete(savedTraining.getId());

    // THEN
    List<TrainingJpa> savedTrainings = dao.findAll();
    assertThat(savedTrainings).isEmpty();
  }

  private PseUserJpa createUser() {
    entityManager.createNativeQuery("INSERT INTO USERS(id, first_name, last_name, nivol) VALUES (1L, 'John', 'Doe', 'A_NIVOL')").executeUpdate();
    return entityManager.createQuery("Select user from PseUserJpa user", PseUserJpa.class).getSingleResult();
  }
}
