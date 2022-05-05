package fr.croix_rouge.formation_pse.infrastructure.adapters.primary;

import fr.croix_rouge.formation_pse.domain.PseUser;
import fr.croix_rouge.formation_pse.domain.ports.PseUserRepository;
import fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto.UserAuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final PseUserRepository userRepository;

  public UserController(PseUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/authenticate")
  public ResponseEntity<UserAuthenticationResponse> authenticate(Authentication authentication) {
    PseUser user = userRepository.findByNivol(authentication.getName());
    if(user == null) {
      throw new UsernameNotFoundException(String.format("User %s not found", authentication.getName()));
    }
    return ResponseEntity.ok(new UserAuthenticationResponse(user));
  }
}
