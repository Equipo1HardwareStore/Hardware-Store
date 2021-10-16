package usuarios;

import utils.PasswordCrypto;
import utils.SessionChecker;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.swing.*;
import java.io.IOException;

@WebServlet(name = "Usuarios", value = "/Usuarios")
public class Usuarios extends SessionChecker {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validate(request, response);

        UsuariosDAO usuariosDAO = new UsuariosDAO();

        if (request.getParameter("create") != null) {
            int cedula;
            String user, name, password, email, hashedPassword;
            cedula = Integer.parseInt(request.getParameter("cedula"));
            user = request.getParameter("user");
            name = request.getParameter("name");
            password = request.getParameter("password");
            email = request.getParameter("email");
            hashedPassword = PasswordCrypto.hashPassword(password);


            UsuariosDTO usuariosDTO = new UsuariosDTO(cedula, user, hashedPassword, name, email);
            if (usuariosDAO.insertUser(usuariosDTO)) {
                request.setAttribute("RESULT", "success-msg");
                request.setAttribute("MESSAGE", "Usuario agregado con éxito.");
            } else {
                request.setAttribute("RESULT", "error-msg");
                request.setAttribute("MESSAGE", "Error al crear el usuario.");
            }
            request.getRequestDispatcher("WEB-INF/views/users.jsp").forward(request, response);
        }

        if (request.getParameter("search") != null) {
            int cedula;
            cedula = Integer.parseInt(request.getParameter("cedula"));
            UsuariosDTO usuario = usuariosDAO.searchUser(cedula);
            if (usuario != null) {
                request.setAttribute("USER", usuario);

            } else {
                request.setAttribute("RESULT", "error-msg");
                request.setAttribute("MESSAGE", "Usuario no encontrado");
            }
            request.getRequestDispatcher("WEB-INF/views/users.jsp").forward(request, response);

        }
        if (request.getParameter("update") != null) {
            int cedula;
            String email, name, password, user, hashedPassword;
            cedula = Integer.parseInt(request.getParameter("cedula"));
            email = request.getParameter("email");
            name = request.getParameter("name");
            password = request.getParameter("password");
            if (password.equals("")){
                hashedPassword = usuariosDAO.searchUser(cedula).getPassword();
            }else {
                hashedPassword = PasswordCrypto.hashPassword(password);
            }
            user = request.getParameter("user");
            UsuariosDTO usuariosDTO = new UsuariosDTO(cedula, user, hashedPassword, name, email);
            if (usuariosDAO.updateUser(usuariosDTO)) {
                request.setAttribute("RESULT", "success-msg");
                request.setAttribute("MESSAGE", "Usuario modificado con éxito.");
            } else {
                request.setAttribute("RESULT", "error-msg");
                request.setAttribute("MESSAGE", "Error al modificar el usuario.");
            }
            request.getRequestDispatcher("WEB-INF/views/users.jsp").forward(request, response);

        }
        if (request.getParameter("delete") != null) {
            int cedula;
            cedula = Integer.parseInt(request.getParameter("cedula"));
//            int accept = JOptionPane.showInternalConfirmDialog(null,"¿Eliminar cliente con cedula: ?"+cedula);
//            if (accept == 0){
            if (usuariosDAO.deleteUser(cedula)) {
                request.setAttribute("RESULT", "success-msg");
                request.setAttribute("MESSAGE", "Usuario eliminado.");
            } else {
                request.setAttribute("RESULT", "error-msg");
                request.setAttribute("MESSAGE", "Error al eliminar el usuario.");
            }
//            }
            request.getRequestDispatcher("WEB-INF/views/users.jsp").forward(request, response);
        }
    }
}
