package email;

import org.apache.commons.lang3.RandomStringUtils;
import usuarios.UsuariosDAO;
import usuarios.UsuariosDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Email", value = "/Email")
public class Email extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("new-acc") != null) {
            String from, message, name;
            String to = "admin@hardwarestore.it";
            String subject = "Solicitud de Cuenta nueva";

            from = request.getParameter("email");
            name = request.getParameter("name");
            message = request.getParameter("message");

            String fullMessage = "<h1>El usuario <u>" + name + "</u> realizó una solicitud de nueva cuenta con sa siguiente información:</h1><br>Nombre: " + name + "<br>Correo: " + from + "<br>Mensaje: " + message;

            Mailer.sendEmail("localhost", "25", from, to, subject, fullMessage);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

        if (request.getParameter("recover") != null) {
            UsuariosDAO usuariosDAO = new UsuariosDAO();
            PassRecoveryDAO passRecoveryDAO = new PassRecoveryDAO();
            String email = request.getParameter("email");

            UsuariosDTO usuariosDTO = usuariosDAO.searchByEmail(email);
            System.out.println(usuariosDTO);

            if (usuariosDTO != null) {
                String code = RandomStringUtils.randomAlphabetic(6);
                if (passRecoveryDAO.insertPassReco(usuariosDTO.getCedula(), code)) {
                    String message = "<h1>Solicitud de recuperación de contraseña.</h1><br><p>Se ha registrado una solicitud de recuperación de contraseña. " +
                            "Por favor introduzca el siguiente código para continuar</p><br>&emsp;<h2>" + code + "</h2><br>Si usted no realizó la solicitud, omita este mensaje.<br>" +
                            "*El código expira en 10 minutos";
                    Mailer.sendEmail("localhost", "25", "noreply@hardwarestore.it", email, "Recuperación de contraseña", message);
                } else {
                    request.setAttribute("error", "Error en la operación");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
            request.setAttribute("email", email);
            request.getRequestDispatcher("recover.jsp").forward(request, response);
        }

        if (request.getParameter("reset") != null) {
            PassRecoveryDAO passRecoveryDAO = new PassRecoveryDAO();
            UsuariosDAO usuariosDAO = new UsuariosDAO();
            String email = request.getParameter("email");
            String code = request.getParameter("code");
            String password = request.getParameter("new_pass");
            UsuariosDTO usuariosDTO = passRecoveryDAO.validateCode(email, code);
            if (usuariosDTO != null) {
                if (usuariosDAO.changePassword(password, usuariosDTO.getCedula())) {
                    request.setAttribute("success", "Contraseña reestablecida.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
            request.setAttribute("error", "Datos invalidos");
            request.getRequestDispatcher("recover.jsp").forward(request, response);
        }
    }
}
