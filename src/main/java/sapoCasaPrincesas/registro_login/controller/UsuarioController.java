package sapoCasaPrincesas.registro_login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sapoCasaPrincesas.registro_login.service.UsuarioService;



@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registrar")
    public String registrarUsuario(@RequestParam String nombre,
                                   @RequestParam String apellidos,
                                   @RequestParam String email,
                                   @RequestParam String password) {

        try {
            usuarioService.registrar(nombre, apellidos, email, password);
            return "redirect:/login?registro=exito";

        } catch (IllegalArgumentException e) {

            // e.getMessage() contiene:
            // "campos-vacios", "contraseña-corta", "email-existe"
            return "redirect:/registro?error=" + e.getMessage();
        }
    }
}





















