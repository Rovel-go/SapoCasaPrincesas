package sapoCasaPrincesas.registro_login.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirige al dashboard
        request.getRequestDispatcher("/WEB-INF/jsp/Dashboard.jsp").forward(request, response);
    }
}










