package clinica.config;

import clinica.config.auth.AuthenticationService;
import clinica.config.auth.RegisterRequest;
import clinica.entities.security.Role;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
  private final AuthenticationService authenticationService;
  
  public DataLoader(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }
  
  @Override
  public void run(ApplicationArguments args) {
    
    authenticationService.register(
        RegisterRequest.builder()
            .username("admin")
            .email("admin@admin.com")
            .password("admin")
            .role(Role.ROLE_ADMIN)
            .build()
    );
  
    authenticationService.register(
        RegisterRequest.builder()
            .username("user")
            .email("user@admin.com")
            .password("user")
            .role(Role.ROLE_USER)
            .build()
    );
  }
}
