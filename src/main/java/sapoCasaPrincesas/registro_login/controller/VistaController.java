package sapoCasaPrincesas.registro_login.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VistaController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }



    @GetMapping("/registro")
    public String registro(Authentication auth) {

        // Si ya está autenticado, no debe registrarse
        if (auth != null && auth.isAuthenticated()) {
            return "redirect:/dashboard";
        }

        return "registro";
    }

}
















