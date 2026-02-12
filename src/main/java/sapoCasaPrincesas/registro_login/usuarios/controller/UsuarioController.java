package sapoCasaPrincesas.registro_login.usuarios.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapoCasaPrincesas.registro_login.usuarios.dto.*;
import sapoCasaPrincesas.registro_login.usuarios.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Expongo este endpoint para obtener todos los usuarios sin revelar información sensible.
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> obtenerTodos() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    // Uso este método para consultar un usuario específico y devolverlo como DTO.
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerPorId(@PathVariable Long id) {
        UsuarioDTO usuario = usuarioService.obtenerPorId(id);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(usuario);
    }

    // Manejo el registro devolviendo mensajes claros según la validación del servicio.
    @PostMapping
    public ResponseEntity<String> registrar(@RequestBody UsuarioRegistroDTO dto) {

        // El servicio devuelve:
        // - String con mensaje de error
        // - null si todo está OK
        String error = usuarioService.registrar(dto);

        if (error != null) {
            return ResponseEntity.badRequest().body(error);
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario registrado correctamente");
    }

    // Permito actualizar datos del usuario sin exponer la contraseña.
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(
            @PathVariable Long id,
            @RequestBody UsuarioDTO dto) {

        // Valido el email aquí para evitar pasar datos inválidos al servicio.
        if (dto.email() != null && !dto.email().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
            return ResponseEntity.badRequest().body("El formato del email es inválido");

        boolean actualizado = usuarioService.actualizar(id, dto);

        if (!actualizado) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Datos inválidos o usuario no encontrado");
        }

        return ResponseEntity.ok("Usuario actualizado correctamente");
    }

    // Manejo el login devolviendo el DTO del usuario si las credenciales son correctas.
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

    // Expongo este endpoint para permitir que el usuario cambie su contraseña.
    @PostMapping("/cambiar-contrasena")
    public ResponseEntity<String> cambiarContrasena(
            @RequestBody UsuarioCambioContrasenaDTO dto) {

        // Realizo validaciones básicas antes de llamar al servicio.
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

    // Permito eliminar usuarios devolviendo mensajes claros según el resultado.
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {

        boolean eliminado = usuarioService.eliminar(id);

        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado");
        }

        return ResponseEntity.ok("Usuario eliminado correctamente");
    }
}


