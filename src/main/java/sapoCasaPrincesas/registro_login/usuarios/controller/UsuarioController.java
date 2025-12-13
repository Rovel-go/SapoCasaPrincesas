package sapoCasaPrincesas.registro_login.usuarios.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapoCasaPrincesas.registro_login.usuarios.model.Usuario;
import sapoCasaPrincesas.registro_login.usuarios.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // GET /usuarios → lista todos
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        List<Usuario> usuarios = usuarioService.obtenerTodos();
        return ResponseEntity.ok(usuarios);
    }

    // GET /usuarios/{id} → obtiene uno por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.obtenerPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // POST /usuarios → crea usuario
    @PostMapping
    public ResponseEntity<String> crear(@RequestBody Usuario usuario) {
        boolean creado = usuarioService.crear(usuario);
        if (creado) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("✅ Usuario creado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("❌ Error al crear usuario");
        }
    }

    // PUT /usuarios/{id} → actualiza usuario
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id); // asegura que el ID del path se use
        boolean actualizado = usuarioService.actualizar(id, usuario);
        if (actualizado) {
            return ResponseEntity.ok("✅ Usuario actualizado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("❌ Error al actualizar usuario");
        }
    }

    // DELETE /usuarios/{id} → elimina usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        boolean eliminado = usuarioService.eliminar(id);
        if (eliminado) {
            return ResponseEntity.ok("✅ Usuario eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("❌ Usuario no encontrado");
        }
    }

    // POST /usuarios/login → login con email y contraseña
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        if (usuario.getEmail() == null || usuario.getContrasena() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("❌ Faltan email o contraseña en el body");
        }

        Usuario u = usuarioService.login(usuario.getEmail(), usuario.getContrasena());
        if (u != null) {
            return ResponseEntity.ok(u);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("❌ Login fallido: email o contraseña incorrectos");
        }
    }
}





















