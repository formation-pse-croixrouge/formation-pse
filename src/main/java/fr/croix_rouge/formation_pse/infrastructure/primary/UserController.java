package fr.croix_rouge.formation_pse.infrastructure.primary;

import fr.croix_rouge.formation_pse.domain.PseUser;
import fr.croix_rouge.formation_pse.domain.ports.PseUserRepository;
import fr.croix_rouge.formation_pse.infrastructure.primary.dto.UserAuthenticationRequest;
import fr.croix_rouge.formation_pse.infrastructure.primary.dto.UserAuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final PseUserRepository userRepository;

  public UserController(PseUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @PostMapping("/authenticate")
  public ResponseEntity<UserAuthenticationResponse> authenticate(@RequestBody UserAuthenticationRequest authenticationRequest) {
    PseUser user = userRepository.findByNivol(authenticationRequest.getNivol());
    if(user == null) {
      throw new UsernameNotFoundException(String.format("User %s not found", authenticationRequest.getNivol()));
    }
    return ResponseEntity.ok(new UserAuthenticationResponse(user));
  }
}
