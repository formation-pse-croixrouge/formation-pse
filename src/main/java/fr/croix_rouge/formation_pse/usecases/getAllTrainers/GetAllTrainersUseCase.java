package fr.croix_rouge.formation_pse.usecases.getAllTrainers;

import fr.croix_rouge.formation_pse.domain.Trainer;
import fr.croix_rouge.formation_pse.domain.ports.TrainerRepository;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class GetAllTrainersUseCase {
  private final TrainerRepository trainerRepository;

  public GetAllTrainersUseCase(TrainerRepository trainerRepository) {
    this.trainerRepository = trainerRepository;
  }

  public Set<Trainer> execute() {
    return trainerRepository.findAll();
  }
}
