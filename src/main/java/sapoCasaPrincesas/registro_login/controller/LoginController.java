package sapoCasaPrincesas.registro_login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sapoCasaPrincesas.registro_login.repository.UsuarioRepository;

@Controller
public class LoginController {

    private final UsuarioRepository usuarioRepository;

    public LoginController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; // login.html
    }

    @PostMapping("/login-check")
    public String verificarLogin(@RequestParam String username,
                                 @RequestParam String password) {

        // 1. Verificar si el email existe
        if (!usuarioRepository.existsByEmail(username)) {
            return "redirect:/login?error=email-no-existe";
        }

        // 2. Si existe, dejar que Spring Security valide la contraseña
        return "redirect:/login";
    }
}








