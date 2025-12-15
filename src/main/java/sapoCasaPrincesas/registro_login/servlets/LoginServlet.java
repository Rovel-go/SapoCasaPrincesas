package sapoCasaPrincesas.registro_login.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirige al formulario de login
        request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Captura datos del formulario
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validación simple de ejemplo
        if ("admin".equals(username) && "1234".equals(password)) {
            // Usuario válido → pasa al dashboard
            request.setAttribute("usuario", username);
            request.getRequestDispatcher("/WEB-INF/jsp/Dashboard.jsp").forward(request, response);
        } else {
            // Usuario inválido → vuelve al login con error
            request.setAttribute("error", "Credenciales inválidas, intenta de nuevo.");
            request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp").forward(request, response);
        }
    }
}





