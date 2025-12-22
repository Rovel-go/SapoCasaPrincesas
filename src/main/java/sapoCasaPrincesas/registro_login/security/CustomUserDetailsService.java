package sapoCasaPrincesas.registro_login.security;

import java.util.Collections;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sapoCasaPrincesas.registro_login.model.Usuario;
import sapoCasaPrincesas.registro_login.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // Evitar bucles cuando Spring Security llama sin credenciales válidas
        if (email == null || email.trim().isEmpty() || email.equals("null")) {
            return User.withUsername("dummy")
                    .password("dummy")
                    .authorities(Collections.emptyList())
                    .build();
        }

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return User.withUsername(usuario.getEmail())
                .password(usuario.getPasswordHash())
                .authorities(Collections.singleton(() -> "ROLE_USER"))
                .build();
    }
}
















