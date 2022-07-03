package fr.croix_rouge.formation_pse.infrastructure.adapters.primary;

import fr.croix_rouge.formation_pse.domain.Trainer;
import fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto.GetAllTrainersResponse;
import fr.croix_rouge.formation_pse.usecases.getAllTrainers.GetAllTrainersUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/trainers")
public class TrainerController {

  private final GetAllTrainersUseCase getAllTrainersUseCase;

  public TrainerController(GetAllTrainersUseCase getAllTrainersUseCase) {
    this.getAllTrainersUseCase = getAllTrainersUseCase;
  }

  @GetMapping("")
  public ResponseEntity<GetAllTrainersResponse> getAllTrainers() {
    Set<Trainer> trainers = getAllTrainersUseCase.execute();
    return ResponseEntity.ok(new GetAllTrainersResponse(trainers));
  }
}
