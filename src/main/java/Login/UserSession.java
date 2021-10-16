package Login;

import usuarios.UsuariosDTO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSession {
    public static UsuariosDTO login(Connection conn, String user, String password) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        UsuariosDTO userSession = null;
        try {
            String query = "SELECT * from Usuarios where usuario=? and password=?";
            statement = conn.prepareStatement(query);
            statement.setString(1, user);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                userSession = new UsuariosDTO(Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(5),
                        resultSet.getString(4),
                        resultSet.getString(3),
                        resultSet.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e);
//            JOptionPane.showMessageDialog(null, "Error" + e);
        }
        conn.close();
        return userSession;

    }
}
