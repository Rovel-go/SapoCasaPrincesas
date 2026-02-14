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

    // ============================
    // POST — Registro (PÚBLICO)
    // ============================

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

    // ============================
    // POST — Login (PÚBLICO)
    // ============================

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

    // ============================
    // POST — Recuperar contraseña (PÚBLICO)
    // ============================

    @PostMapping("/recuperar")
    public ResponseEntity<?> recuperarContrasena(@RequestBody UsuarioLoginDTO dto) {

        if (dto.email() == null || dto.email().isBlank()) {
            return ResponseEntity.badRequest()
                    .body("{\"mensaje\": \"El email es obligatorio\"}");
        }

        if (!dto.email().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            return ResponseEntity.badRequest()
                    .body("{\"mensaje\": \"El formato del email es inválido\"}");
        }

        String nuevaTemporal = usuarioService.recuperarContrasena(dto.email());

        if (nuevaTemporal == null) {
            return ResponseEntity.badRequest()
                    .body("{\"mensaje\": \"No existe un usuario con ese email\"}");
        }

        return ResponseEntity.ok(
                "{ \"mensaje\": \"Se ha generado una nueva contraseña temporal\", " +
                        "\"contrasenaTemporal\": \"" + nuevaTemporal + "\" }"
        );
    }
}

