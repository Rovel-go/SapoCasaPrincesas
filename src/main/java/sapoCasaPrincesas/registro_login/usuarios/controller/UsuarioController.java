package sapoCasaPrincesas.registro_login.usuarios.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapoCasaPrincesas.registro_login.usuarios.model.Usuario;
import sapoCasaPrincesas.registro_login.usuarios.service.UsuarioService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Endpoint de prueba
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Backend funcionando correctamente 🚀");
    }

    // Crear usuario
    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        boolean creado = usuarioService.crear(usuario);
        if (creado) {
            return ResponseEntity.ok(Map.of("mensaje", "✅ Usuario creado correctamente"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("mensaje", "❌ Error al crear usuario. Verifica email único y contraseña válida."));
        }
    }

    // Listar usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.obtenerPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.badRequest().body(Map.of("mensaje", "❌ Usuario no encontrado con ID: " + id));
        }
    }

    // Actualizar usuario
    @PutMapping
    public ResponseEntity<?> actualizarUsuario(@RequestBody Usuario usuario) {
        boolean actualizado = usuarioService.actualizar(usuario);
        if (actualizado) {
            return ResponseEntity.ok(Map.of("mensaje", "✅ Usuario actualizado correctamente"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("mensaje", "❌ Error al actualizar usuario. Verifica ID y datos enviados."));
        }
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        boolean eliminado = usuarioService.eliminar(id);
        if (eliminado) {
            return ResponseEntity.ok(Map.of("mensaje", "✅ Usuario eliminado correctamente con ID: " + id));
        } else {
            return ResponseEntity.badRequest().body(Map.of("mensaje", "❌ Error al eliminar usuario. ID no encontrado: " + id));
        }
    }

    // Login mejorado: devuelve datos del usuario
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String contrasena) {
        Usuario usuario = usuarioService.login(email, contrasena);
        if (usuario != null) {
            return ResponseEntity.ok(
                    Map.of(
                            "mensaje", "✅ Login exitoso",
                            "usuario", Map.of(
                                    "id", usuario.getId(),
                                    "nombre", usuario.getNombre(),
                                    "apellidos", usuario.getApellidos(),
                                    "email", usuario.getEmail()
                            )
                    )
            );
        } else {
            return ResponseEntity.badRequest().body(Map.of("mensaje", "❌ Login fallido. Email o contraseña incorrectos"));
        }
    }
}














