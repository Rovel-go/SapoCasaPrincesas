package sapoCasaPrincesas.registro_login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sapoCasaPrincesas.registro_login.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar usuario por email (seguro)
    Optional<Usuario> findByEmail(String email);

    // Verificar si un email ya está registrado
    boolean existsByEmail(String email);
}













