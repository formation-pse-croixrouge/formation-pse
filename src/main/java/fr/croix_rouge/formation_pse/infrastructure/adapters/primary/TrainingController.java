package fr.croix_rouge.formation_pse.infrastructure.adapters.primary;

import fr.croix_rouge.formation_pse.domain.PseUser;
import fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto.GetAllTrainingResponse;
import fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto.SingleTrainingResponse;
import fr.croix_rouge.formation_pse.usecases.createTraining.CreateTrainingCommand;
import fr.croix_rouge.formation_pse.domain.ports.PseUserRepository;
import fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto.CreateTrainingRequest;
import fr.croix_rouge.formation_pse.usecases.createTraining.CreateTrainingUseCase;
import fr.croix_rouge.formation_pse.usecases.getAllTrainings.GetAllTrainingsUseCase;
import fr.croix_rouge.formation_pse.usecases.getSingleTraining.GetSingleTrainingUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/trainings")
public class TrainingController {

  private final CreateTrainingUseCase createTrainingUseCase;
  private final GetAllTrainingsUseCase getAllTrainingsUseCase;
  private final GetSingleTrainingUseCase getSingleTrainingUseCase;
  private final PseUserRepository userRepository;

  public TrainingController(CreateTrainingUseCase createTrainingUseCase,
                            GetAllTrainingsUseCase getAllTrainingsUseCase,
                            GetSingleTrainingUseCase getSingleTrainingUseCase,
                            PseUserRepository userRepository) {
    this.createTrainingUseCase = createTrainingUseCase;
    this.getAllTrainingsUseCase = getAllTrainingsUseCase;
    this.getSingleTrainingUseCase = getSingleTrainingUseCase;
    this.userRepository = userRepository;
  }

  @PostMapping("")
  public ResponseEntity<String> createTraining(@RequestBody CreateTrainingRequest trainingRequest, Authentication authentication) {
    PseUser user = userRepository.findByNivol(authentication.getName());
    CreateTrainingCommand createTrainingCommand = trainingRequest.toCommand(user);
    createTrainingUseCase.create(createTrainingCommand);
    return new ResponseEntity<>("Training created successfully.", HttpStatus.CREATED);
  }

  @GetMapping("")
  public GetAllTrainingResponse getAllTrainings() {
    return GetAllTrainingResponse.from(getAllTrainingsUseCase.handle());
  }

  @GetMapping("/{id}")
  public SingleTrainingResponse getSingleTraining(@PathVariable Long id) {
    return SingleTrainingResponse.fromDomain(getSingleTrainingUseCase.handle(id));
  }
}
