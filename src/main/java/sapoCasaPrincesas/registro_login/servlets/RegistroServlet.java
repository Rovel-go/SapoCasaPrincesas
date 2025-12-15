package sapoCasaPrincesas.registro_login.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/Registro.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // TODO: Persistir/validar según tu evidencia. Aquí solo mostramos mensaje.
        request.setAttribute("mensaje", "Usuario registrado: " + username);

        request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp").forward(request, response);
    }
}








