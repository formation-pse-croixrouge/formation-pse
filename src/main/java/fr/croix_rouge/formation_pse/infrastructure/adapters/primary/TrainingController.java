package fr.croix_rouge.formation_pse.infrastructure.adapters.primary;

import fr.croix_rouge.formation_pse.domain.PseUser;
import fr.croix_rouge.formation_pse.domain.commands.CreateTrainingCommand;
import fr.croix_rouge.formation_pse.domain.ports.PseUserRepository;
import fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto.CreateTrainingRequest;
import fr.croix_rouge.formation_pse.usecases.CreateTrainingUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/trainings")
public class TrainingController {

  private final CreateTrainingUseCase createTrainingUseCase;
  private final PseUserRepository userRepository;

  public TrainingController(CreateTrainingUseCase createTrainingUseCase, PseUserRepository userRepository) {
    this.createTrainingUseCase = createTrainingUseCase;
    this.userRepository = userRepository;
  }

  @PostMapping("")
  public ResponseEntity<String> createTraining(
          @Valid @RequestBody CreateTrainingRequest trainingRequest, Authentication authentication) {
    PseUser user = userRepository.findByNivol(authentication.getName());
    CreateTrainingCommand createTrainingCommand = trainingRequest.toCommand(user);
    createTrainingUseCase.create(createTrainingCommand);
    return new ResponseEntity<>("Training created successfully.", HttpStatus.CREATED);
  }
}
