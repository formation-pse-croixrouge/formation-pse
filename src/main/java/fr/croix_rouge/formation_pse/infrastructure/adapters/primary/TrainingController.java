package fr.croix_rouge.formation_pse.infrastructure.adapters.primary;

import fr.croix_rouge.formation_pse.domain.PseUser;
import fr.croix_rouge.formation_pse.domain.ports.TrainerRepository;
import fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto.GetAllTrainingResponse;
import fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto.SingleTrainingResponse;
import fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto.UpdateTrainingRequest;
import fr.croix_rouge.formation_pse.usecases.updateTraining.UpdateTrainingCommand;
import fr.croix_rouge.formation_pse.usecases.updateTraining.UpdateTrainingUseCase;
import fr.croix_rouge.formation_pse.usecases.createTraining.CreateTrainingCommand;
import fr.croix_rouge.formation_pse.domain.ports.PseUserRepository;
import fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto.CreateTrainingRequest;
import fr.croix_rouge.formation_pse.usecases.createTraining.CreateTrainingUseCase;
import fr.croix_rouge.formation_pse.usecases.deleteTraining.DeleteTrainingUseCase;
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
  private final DeleteTrainingUseCase deleteTrainingUseCase;
  private final PseUserRepository userRepository;
  private final UpdateTrainingUseCase updateTrainingUseCase;
  private final TrainerRepository trainerRepository;

  public TrainingController(CreateTrainingUseCase createTrainingUseCase,
                            GetAllTrainingsUseCase getAllTrainingsUseCase,
                            GetSingleTrainingUseCase getSingleTrainingUseCase,
                            DeleteTrainingUseCase deleteTrainingUseCase,
                            PseUserRepository userRepository,
                            UpdateTrainingUseCase updateTrainingUseCase, TrainerRepository trainerRepository) {
    this.createTrainingUseCase = createTrainingUseCase;
    this.getAllTrainingsUseCase = getAllTrainingsUseCase;
    this.getSingleTrainingUseCase = getSingleTrainingUseCase;
    this.deleteTrainingUseCase = deleteTrainingUseCase;
    this.userRepository = userRepository;
    this.updateTrainingUseCase = updateTrainingUseCase;
    this.trainerRepository = trainerRepository;
  }

  @PostMapping("")
  public ResponseEntity<Void> createTraining(@RequestBody CreateTrainingRequest trainingRequest, Authentication authentication) {
    PseUser user = userRepository.findByNivol(authentication.getName());
    CreateTrainingCommand createTrainingCommand = trainingRequest.toCommand(user);
    createTrainingUseCase.create(createTrainingCommand);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("")
  public GetAllTrainingResponse getAllTrainings() {
    return GetAllTrainingResponse.from(getAllTrainingsUseCase.handle());
  }

  @GetMapping("/{id}")
  public SingleTrainingResponse getSingleTraining(@PathVariable Long id) {
    return SingleTrainingResponse.fromDomain(getSingleTrainingUseCase.handle(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTraining(@PathVariable Long id) {
    deleteTrainingUseCase.handle(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateTraining(@PathVariable Long id, @RequestBody UpdateTrainingRequest updateTrainingRequest) {
    UpdateTrainingCommand updateTrainingCommand = updateTrainingRequest.toCommand(id, trainerRepository);
    updateTrainingUseCase.handle(updateTrainingCommand);
    return ResponseEntity.noContent().build();
  }
}
