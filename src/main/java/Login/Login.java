package Login;

import Conexion.Conexion;
import usuarios.UsuariosDTO;
import utils.PasswordCrypto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/Login")
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("login-acc") != null) {
            String user, password, hashedPassword;
            user = request.getParameter("user"); // obtener atributos del formulario
            password = request.getParameter("password");

            if (user.equals("admininicial") && password.equals("admin123456")) { //usuario hardcodeado
                UsuariosDTO admininicial = new UsuariosDTO();
                admininicial.setName("Admininicial");
                admininicial.setCedula(0);
                request.getSession().setAttribute("CURRENT_USER", admininicial);
                request.getRequestDispatcher("WEB-INF/views/users.jsp").forward(request, response);
            } else {
                hashedPassword = PasswordCrypto.hashPassword(password);

                Conexion conn = new Conexion();
                Connection con = conn.connect();
                try {
                    UsuariosDTO logedInUser = UserSession.login(con, user, hashedPassword);
                    if (logedInUser != null) {
                        request.getSession().setAttribute("CURRENT_USER", logedInUser); //se crea un atributo de sesión con el usuario que ingresó
                        con.close();
                        request.getRequestDispatcher("WEB-INF/views/users.jsp").forward(request, response);
                    } else {
                        request.setAttribute("error", "Usuario o contraseña incorrectos.");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
//                        response.sendRedirect("login.jsp?mensaje=Usuario blaba");
                    }
                } catch (SQLException e) {
                    request.setAttribute("error", "Error: " + e);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
        }
        if (request.getParameter("new-acc") != null) {
            request.getRequestDispatcher("new_acc.jsp").forward(request, response);
        }
    }
}
