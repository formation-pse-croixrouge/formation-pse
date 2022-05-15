package fr.croix_rouge.formation_pse.infrastructure.adapters.primary;

import fr.croix_rouge.formation_pse.domain.PseUser;
import fr.croix_rouge.formation_pse.domain.commands.CreateTrainingCommand;
import fr.croix_rouge.formation_pse.domain.exceptions.BadRequestException;
import fr.croix_rouge.formation_pse.domain.ports.PseUserRepository;
import fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto.CreateTrainingRequest;
import fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto.BaseResponse;
import fr.croix_rouge.formation_pse.usecases.CreateTrainingUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
  @ResponseBody
  public ResponseEntity<?> createTraining(@Valid @RequestBody CreateTrainingRequest trainingRequest, Authentication authentication) throws BadRequestException {
    PseUser user = userRepository.findByNivol(authentication.getName());
    CreateTrainingCommand createTrainingCommand = trainingRequest.toCommand(user);
    createTrainingUseCase.create(createTrainingCommand);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
