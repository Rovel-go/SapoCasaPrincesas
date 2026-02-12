package sapoCasaPrincesas.registro_login.usuarios.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapoCasaPrincesas.registro_login.usuarios.dto.UsuarioLoginDTO;
import sapoCasaPrincesas.registro_login.usuarios.dto.UsuarioRegistroDTO;
import sapoCasaPrincesas.registro_login.usuarios.service.UsuarioService;
import sapoCasaPrincesas.registro_login.usuarios.dto.UsuarioDTO;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioRestController {

    private final UsuarioService usuarioService;

    public UsuarioRestController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Expongo este endpoint para registrar usuarios desde el frontend,
    // devolviendo mensajes JSON simples para facilitar el consumo.
    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody UsuarioRegistroDTO dto) {

        String error = usuarioService.registrar(dto);

        if (error != null) {
            return ResponseEntity.badRequest()
                    .body("{\"mensaje\": \"" + error + "\"}");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("{\"mensaje\": \"Usuario registrado correctamente\"}");
    }

    // Manejo el login devolviendo mensajes específicos para que el frontend
    // pueda mostrar exactamente qué falló.
    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody UsuarioLoginDTO dto) {

        String error = usuarioService.validarLoginConMensaje(dto);

        if (error != null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"mensaje\": \"" + error + "\"}");
        }

        UsuarioDTO usuario = usuarioService.obtenerPorEmail(dto.email());

        return ResponseEntity.ok(usuario);
    }

    // Expongo este endpoint para recuperar contraseña generando una temporal.
    // Decidí devolver la contraseña temporal directamente porque esta evidencia
    // no requiere integración con correo electrónico.
    @PostMapping("/recuperar")
    public ResponseEntity<?> recuperarContrasena(@RequestBody UsuarioLoginDTO dto) {

        // Valido que el email venga presente.
        if (dto.email() == null || dto.email().isBlank()) {
            return ResponseEntity.badRequest()
                    .body("{\"mensaje\": \"El email es obligatorio\"}");
        }

        // Valido el formato del email antes de llamar al servicio.
        if (!dto.email().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            return ResponseEntity.badRequest()
                    .body("{\"mensaje\": \"El formato del email es inválido\"}");
        }

        // Llamo al servicio para generar la contraseña temporal.
        String nuevaTemporal = usuarioService.recuperarContrasena(dto.email());

        // Si el servicio devuelve null, significa que el email no existe.
        if (nuevaTemporal == null) {
            return ResponseEntity.badRequest()
                    .body("{\"mensaje\": \"No existe un usuario con ese email\"}");
        }

        // Devuelvo la contraseña temporal para que el usuario pueda iniciar sesión.
        return ResponseEntity.ok(
                "{ \"mensaje\": \"Se ha generado una nueva contraseña temporal\", " +
                        "\"contrasenaTemporal\": \"" + nuevaTemporal + "\" }"
        );
    }

}
