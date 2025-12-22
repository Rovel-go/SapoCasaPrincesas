package sapoCasaPrincesas.registro_login.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sapoCasaPrincesas.registro_login.model.Usuario;
import sapoCasaPrincesas.registro_login.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registrar(String nombre, String apellidos, String email, String password) {

        // LOG para confirmar que el método se ejecuta
        System.out.println(">>> EJECUTANDO registrar() PARA: " + email);

        // Validaciones básicas
        if (nombre.isBlank() || apellidos.isBlank() || email.isBlank() || password.isBlank()) {
            throw new IllegalArgumentException("campos-vacios");
        }

        if (password.length() < 6) {
            throw new IllegalArgumentException("contraseña-corta");
        }

        // Validar email duplicado
        if (usuarioRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("email-existe");
        }

        // Crear usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setEmail(email);
        usuario.setPasswordHash(passwordEncoder.encode(password));

        // LOG antes de guardar
        System.out.println(">>> GUARDANDO USUARIO EN BD: " + usuario.getEmail());

        // Guardar en MySQL
        usuarioRepository.save(usuario);

        // LOG después de guardar
        System.out.println(">>> USUARIO GUARDADO CORRECTAMENTE");
    }
}

