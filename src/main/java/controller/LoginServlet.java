
package controller;

import com.ricuy.ws.Usuario;
import com.ricuy.ws.UsuarioService;
import com.ricuy.ws.UsuarioService_Service;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

      protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String correo = req.getParameter("correo");
        String contrasena = req.getParameter("contrasena");

        UsuarioService_Service service = new UsuarioService_Service();
        UsuarioService port = service.getUsuarioServicePort();

        Usuario u = port.login(correo, contrasena);

        if (u != null) {
            // Manejar sesión y roles
            HttpSession sesion = req.getSession();
            sesion.setAttribute("usuario", u);
            int rol = u.getIdRol(); // campo generado
            switch (rol) {
                case 1 -> resp.sendRedirect(req.getContextPath() + "/jsp/admin/reservas.jsp");
                case 2 -> resp.sendRedirect(req.getContextPath() + "/jsp/mesero/reservas.jsp");
                default -> resp.sendRedirect(req.getContextPath() + "/jsp/cliente/reservas.jsp");
            }
        } else {
            req.setAttribute("error", "Credenciales incorrectas");
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        }
    }

}
