package sapoCasaPrincesas.registro_login.auth.controller;

import sapoCasaPrincesas.registro_login.usuarios.model.Usuario;
import sapoCasaPrincesas.registro_login.usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final UsuarioService usuarioService;

    @Autowired
    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> datos) {
        String email = datos.get("email");
        String contrasena = datos.get("contrasena"); // viene del JSON

        Usuario usuario = usuarioService.login(email, contrasena);

        if (usuario != null) {
            return ResponseEntity.ok("Login exitoso. Bienvenido " + usuario.getNombre());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
}













