package fr.croix_rouge.formation_pse.usecases.getSingleTraining;

import fr.croix_rouge.formation_pse.domain.Training;
import fr.croix_rouge.formation_pse.domain.exceptions.TrainingNotFoundException;
import fr.croix_rouge.formation_pse.domain.ports.TrainingRepository;
import org.springframework.stereotype.Service;

@Service
public class GetSingleTrainingUseCase {
  private final TrainingRepository trainingRepository;

  public GetSingleTrainingUseCase(TrainingRepository trainingRepository) {
    this.trainingRepository = trainingRepository;
  }

  public Training handle(Long trainingIdToRetrieve) {
    Training training = trainingRepository.findById(trainingIdToRetrieve);
    if(training == null) {
      throw new TrainingNotFoundException();
    }
    return training;
  }
}
