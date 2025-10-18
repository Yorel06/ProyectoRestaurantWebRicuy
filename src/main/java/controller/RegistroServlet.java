
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


@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {

  
        protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Crear objeto Usuario (clase generada por el cliente SOAP)
        Usuario u = new Usuario();
        u.setNombreCompleto(req.getParameter("nombre"));
        u.setCorreo(req.getParameter("correo"));
        u.setContrasena(req.getParameter("contrasena"));
        // Nota: idRol no se envía; el microservicio asigna rol=Cliente (3) en DAO

        // Crear el Service y obtener el puerto
        UsuarioService_Service service = new UsuarioService_Service();
        UsuarioService port = service.getUsuarioServicePort(); // o getUsuariosService() según el nombre

        // Invocar operación registrarUsuario (típicamente boolean)
        boolean ok = port.registrarUsuario(u);

        if (ok) {
            resp.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
        } else {
            req.setAttribute("error", "Error al registrar usuario");
            req.getRequestDispatcher("/jsp/registro.jsp").forward(req, resp);
        }
    }
    
}
