package sapoCasaPrincesas.registro_login.usuarios.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sapoCasaPrincesas.registro_login.usuarios.dao.UsuarioRepository;
import sapoCasaPrincesas.registro_login.usuarios.dto.*;
import sapoCasaPrincesas.registro_login.usuarios.model.Usuario;
import jakarta.annotation.PostConstruct;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Uso esta validación para asegurar que todos los métodos del servicio trabajen con correos válidos.
    private boolean emailValido(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    // Devuelvo la lista de usuarios convertida a DTO para no exponer información sensible como el hash.
    public List<UsuarioDTO> obtenerTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(u -> new UsuarioDTO(
                        u.getId(),
                        u.getNombre(),
                        u.getApellidos(),
                        u.getEmail(),
                        u.getRol()
                ))
                .toList();
    }

    // Obtengo un usuario por ID y lo convierto a DTO para mantener consistencia en las respuestas.
    public UsuarioDTO obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(u -> new UsuarioDTO(
                        u.getId(),
                        u.getNombre(),
                        u.getApellidos(),
                        u.getEmail(),
                        u.getRol()
                ))
                .orElse(null);
    }

    // Uso este método para buscar usuarios por email sin exponer el modelo completo.
    public UsuarioDTO obtenerPorEmail(String email) {
        Usuario u = usuarioRepository.findByEmail(email);
        if (u == null) return null;

        return new UsuarioDTO(
                u.getId(),
                u.getNombre(),
                u.getApellidos(),
                u.getEmail(),
                u.getRol()
        );
    }

    // Registro un nuevo usuario validando cada campo para evitar datos inconsistentes.
    public String registrar(UsuarioRegistroDTO dto) {

        if (dto.nombre() == null || dto.nombre().isBlank())
            return "El nombre es obligatorio";

        if (dto.apellidos() == null || dto.apellidos().isBlank())
            return "Los apellidos son obligatorios";

        if (dto.email() == null || dto.email().isBlank())
            return "El email es obligatorio";

        if (!emailValido(dto.email()))
            return "El email tiene un formato inválido";

        if (dto.contrasena() == null || dto.contrasena().isBlank())
            return "La contraseña es obligatoria";

        if (usuarioRepository.existsByEmail(dto.email()))
            return "El email ya está registrado";

        // Creo el usuario con rol CLIENTE por defecto.
        Usuario nuevo = new Usuario();
        nuevo.setNombre(dto.nombre());
        nuevo.setApellidos(dto.apellidos());
        nuevo.setEmail(dto.email());
        nuevo.setRol("CLIENTE");
        nuevo.setPasswordHash(passwordEncoder.encode(dto.contrasena()));

        usuarioRepository.save(nuevo);

        return null; // null indica registro exitoso
    }

    // Valido login de forma simple cuando solo necesito un booleano.
    public boolean validarLogin(UsuarioLoginDTO dto) {

        if (!emailValido(dto.email())) return false;

        Usuario usuario = usuarioRepository.findByEmail(dto.email());
        if (usuario == null) return false;

        return passwordEncoder.matches(dto.contrasena(), usuario.getPasswordHash());
    }

    // Uso esta versión cuando necesito mensajes específicos para el frontend o Postman.
    public String validarLoginConMensaje(UsuarioLoginDTO dto) {

        if (dto.email() == null || dto.email().isBlank())
            return "El email es obligatorio";

        if (!emailValido(dto.email()))
            return "El formato del email es inválido";

        Usuario usuario = usuarioRepository.findByEmail(dto.email());
        if (usuario == null)
            return "No existe un usuario con ese email";

        if (dto.contrasena() == null || dto.contrasena().isBlank())
            return "La contraseña es obligatoria";

        if (!passwordEncoder.matches(dto.contrasena(), usuario.getPasswordHash()))
            return "La contraseña es incorrecta";

        return null; // login correcto
    }

    // Actualizo datos del usuario sin tocar la contraseña.
    public boolean actualizar(Long id, UsuarioDTO dto) {

        Usuario existente = usuarioRepository.findById(id).orElse(null);
        if (existente == null) return false;

        if (dto.nombre() != null && !dto.nombre().isBlank()) {
            existente.setNombre(dto.nombre());
        }

        if (dto.apellidos() != null && !dto.apellidos().isBlank()) {
            existente.setApellidos(dto.apellidos());
        }

        if (dto.email() != null) {
            if (!emailValido(dto.email())) return false;
            existente.setEmail(dto.email());
        }

        if (dto.rol() != null) {
            existente.setRol(dto.rol());
        }

        usuarioRepository.save(existente);
        return true;
    }

    // Cambio de contraseña validando email, contraseña actual y nueva contraseña.
    public boolean cambiarContrasena(UsuarioCambioContrasenaDTO dto) {

        if (!emailValido(dto.email())) return false;
        if (dto.actual() == null || dto.actual().isBlank()) return false;
        if (dto.nueva() == null || dto.nueva().isBlank()) return false;

        Usuario usuario = usuarioRepository.findByEmail(dto.email());
        if (usuario == null) return false;

        if (!passwordEncoder.matches(dto.actual(), usuario.getPasswordHash())) {
            return false;
        }

        usuario.setPasswordHash(passwordEncoder.encode(dto.nueva()));
        usuarioRepository.save(usuario);

        return true;
    }

    // Genero una contraseña temporal y la guardo en el hash.
    public String recuperarContrasena(String email) {

        if (!emailValido(email)) return null;

        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) return null;

        String temporal = generarContrasenaTemporal();
        usuario.setPasswordHash(passwordEncoder.encode(temporal));
        usuarioRepository.save(usuario);

        return temporal; // En producción se enviaría por correo
    }

    // Genero una contraseña temporal corta para facilitar pruebas en Postman.
    private String generarContrasenaTemporal() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    // Elimino un usuario solo si existe para evitar excepciones.
    public boolean eliminar(Long id) {
        if (!usuarioRepository.existsById(id)) return false;
        usuarioRepository.deleteById(id);
        return true;
    }

    // Creo un administrador inicial para asegurar acceso al sistema desde el primer arranque.
    @PostConstruct
    public void crearAdminInicial() {

        String emailAdmin = "govelrodrigo@hotmail.com";

        if (usuarioRepository.existsByEmail(emailAdmin)) {
            return; // Si ya existe, no lo vuelvo a crear
        }

        Usuario admin = new Usuario();
        admin.setNombre("Administrador");
        admin.setApellidos("Principal");
        admin.setEmail(emailAdmin);
        admin.setRol("ADMIN");
        admin.setPasswordHash(passwordEncoder.encode("%admin#9876"));

        usuarioRepository.save(admin);

        System.out.println(">>> ADMIN creado automáticamente");
    }

}

