package sapoCasaPrincesas.registro_login.usuarios.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapoCasaPrincesas.registro_login.usuarios.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Uso este método para obtener un usuario por email, ya que el email es mi identificador principal.
    Usuario findByEmail(String email);

    // Este método me permite validar rápidamente si un correo ya está registrado antes de crear un usuario.
    boolean existsByEmail(String email);
}




