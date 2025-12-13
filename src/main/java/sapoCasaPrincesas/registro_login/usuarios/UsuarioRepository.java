package sapoCasaPrincesas.registro_login.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import sapoCasaPrincesas.registro_login.usuarios.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}

