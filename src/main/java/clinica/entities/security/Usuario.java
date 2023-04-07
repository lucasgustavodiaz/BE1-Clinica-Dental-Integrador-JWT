package clinica.entities.security;

import clinica.token.Token;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
  @SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_seq", allocationSize = 1)
  private Integer id;
  
  @Setter
  private String username;
  
  @Setter
  private String email;
  
  @Setter
  private String password;
  
  @Enumerated(EnumType.STRING)
  private Role role;
  
  @OneToMany(mappedBy = "user")
  private List<Token> tokens;
  
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }
  
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }
  
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }
  
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }
  
  @Override
  public boolean isEnabled() {
    return true;
  }
}
