package sapoCasaPrincesas.registro_login.usuarios.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapoCasaPrincesas.registro_login.usuarios.model.Usuario;
import sapoCasaPrincesas.registro_login.usuarios.service.UsuarioService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // lista todos los usuaros en Bd sapo
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    // obtiene usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.obtenerPorId(id);

        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // registro:  nuevos usuarios en BD sapo
    @PostMapping
    public ResponseEntity<String> crear(@RequestBody Usuario usuario) {

        if (usuarioService.emailExiste(usuario.getEmail())) {
            return ResponseEntity.badRequest().body("El email ya está registrado");
        }

        boolean resultado = usuarioService.crear(usuario);

        if (resultado) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Usuario creado correctamente");
        }

        return ResponseEntity.badRequest().body("Error al crear usuario");
    }

    // login:  Ingreso a sistema de usuarios registrados en BD sapo
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {

        boolean valido = usuarioService.validarLogin(usuario.getEmail(), usuario.getContrasena());

        if (!valido) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales incorrectas");
        }

        return ResponseEntity.ok("Login exitoso");
    }

    // cambiar contraseña (usuario olvido contraseña)
    @PostMapping("/cambiar-contrasena")
    public ResponseEntity<String> cambiarContrasena(@RequestBody Map<String, String> body) {

        String email = body.get("email");
        String actual = body.get("actual");
        String nueva = body.get("nueva");

        Usuario usuario = usuarioService.obtenerPorEmail(email);
        if (usuario == null) {
            return ResponseEntity.badRequest().body("El correo no está registrado");
        }

        boolean actualizado = usuarioService.cambiarContrasena(usuario, actual, nueva);

        if (!actualizado) {
            return ResponseEntity.badRequest().body("La contraseña actual es incorrecta");
        }

        return ResponseEntity.ok("La contraseña ha sido actualizada correctamente");
    }
}
























