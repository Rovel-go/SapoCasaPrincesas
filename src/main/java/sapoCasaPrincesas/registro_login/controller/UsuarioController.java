package sapoCasaPrincesas.registro_login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import sapoCasaPrincesas.registro_login.service.UsuarioService;

@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registrar")
    public RedirectView registrar(@RequestParam("nombre") String nombre,
                                  @RequestParam("apellidos") String apellidos,
                                  @RequestParam("email") String email,
                                  @RequestParam("password") String password) {
        try {
            usuarioService.registrar(nombre, apellidos, email, password);
            return new RedirectView("/login?registro=true");
        } catch (IllegalArgumentException e) {
            return new RedirectView("/registro?error=" + e.getMessage());
        }
    }

}







