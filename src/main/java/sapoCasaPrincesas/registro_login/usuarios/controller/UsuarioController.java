package sapoCasaPrincesas.registro_login.usuarios.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapoCasaPrincesas.registro_login.usuarios.dto.*;
import sapoCasaPrincesas.registro_login.usuarios.service.UsuarioService;
import sapoCasaPrincesas.registro_login.config.AdminKeyValidator;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AdminKeyValidator adminKeyValidator;

    public UsuarioController(UsuarioService usuarioService, AdminKeyValidator adminKeyValidator) {
        this.usuarioService = usuarioService;
        this.adminKeyValidator = adminKeyValidator;
    }

    // ============================
    // GET — Público
    // ============================

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> obtenerTodos() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerPorId(@PathVariable Long id) {
        UsuarioDTO usuario = usuarioService.obtenerPorId(id);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(usuario);
    }

    // ============================
    // POST — Registro (PÚBLICO)
    // ============================

    @PostMapping
    public ResponseEntity<String> registrar(@RequestBody UsuarioRegistroDTO dto) {

        String error = usuarioService.registrar(dto);

        if (error != null) {
            return ResponseEntity.badRequest().body(error);
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario registrado correctamente");
    }

    // ============================
    // PUT — ADMIN
    // ============================

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(
            @RequestHeader(value = "SAPO-ADMIN-KEY", required = false) String adminKey,
            @PathVariable Long id,
            @RequestBody UsuarioDTO dto) {

        if (!adminKeyValidator.isValid(adminKey)) {
            return ResponseEntity.status(403).body("Acceso denegado");
        }

        if (dto.email() != null && !dto.email().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
            return ResponseEntity.badRequest().body("El formato del email es inválido");

        boolean actualizado = usuarioService.actualizar(id, dto);

        if (!actualizado) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Datos inválidos o usuario no encontrado");
        }

        return ResponseEntity.ok("Usuario actualizado correctamente");
    }

    // ============================
    // POST — Login (PÚBLICO)
    // ============================

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioLoginDTO dto) {

        boolean valido = usuarioService.validarLogin(dto);

        if (!valido) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales incorrectas");
        }

        UsuarioDTO usuario = usuarioService.obtenerPorEmail(dto.email());

        return ResponseEntity.ok(usuario);
    }

    // ============================
    // POST — Cambiar contraseña (PÚBLICO)
    // ============================

    @PostMapping("/cambiar-contrasena")
    public ResponseEntity<String> cambiarContrasena(
            @RequestBody UsuarioCambioContrasenaDTO dto) {

        if (dto.email() == null || dto.email().isBlank())
            return ResponseEntity.badRequest().body("El email es obligatorio");

        if (!dto.email().contains("@"))
            return ResponseEntity.badRequest().body("El formato del email es inválido");

        if (dto.actual() == null || dto.actual().isBlank())
            return ResponseEntity.badRequest().body("La contraseña actual es obligatoria");

        if (dto.nueva() == null || dto.nueva().isBlank())
            return ResponseEntity.badRequest().body("La nueva contraseña es obligatoria");

        boolean cambiado = usuarioService.cambiarContrasena(dto);

        if (!cambiado) {
            return ResponseEntity.badRequest()
                    .body("Datos incorrectos o contraseña actual inválida");
        }

        return ResponseEntity.ok("Contraseña actualizada correctamente");
    }

    // ============================
    // DELETE — ADMIN
    // ============================

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(
            @RequestHeader(value = "SAPO-ADMIN-KEY", required = false) String adminKey,
            @PathVariable Long id) {

        if (!adminKeyValidator.isValid(adminKey)) {
            return ResponseEntity.status(403).body("Acceso denegado");
        }

        boolean eliminado = usuarioService.eliminar(id);

        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado");
        }

        return ResponseEntity.ok("Usuario eliminado correctamente");
    }
}

