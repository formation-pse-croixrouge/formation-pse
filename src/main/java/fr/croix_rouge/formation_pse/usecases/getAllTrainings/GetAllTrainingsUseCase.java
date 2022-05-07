package fr.croix_rouge.formation_pse.usecases.getAllTrainings;

import fr.croix_rouge.formation_pse.domain.Training;
import fr.croix_rouge.formation_pse.domain.ports.TrainingRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllTrainingsUseCase {

  private final TrainingRepository trainingRepository;

  public GetAllTrainingsUseCase(TrainingRepository trainingRepository) {
    this.trainingRepository = trainingRepository;
  }

  public List<Training> handle() {
    return trainingRepository.all()
      .stream()
      .sorted(Comparator.comparing(Training::getStartDate))
      .collect(Collectors.toList());
  }
}
