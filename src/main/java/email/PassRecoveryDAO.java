package email;

import Conexion.Conexion;
import usuarios.UsuariosDTO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PassRecoveryDAO {
    Conexion conexion = new Conexion();
    Connection con = conexion.connect();
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    public boolean insertPassReco(int cedula, String code) {
        boolean result = false;
        try {
            String query = "insert into pass_recovery (cedula_usuario, codigo) values(?,?)";
            statement = con.prepareStatement(query);
            statement.setInt(1, cedula);
            statement.setString(2, code);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e);
//            JOptionPane.showMessageDialog(null, "Error" + e);
        }
        return result;
    }

    public UsuariosDTO validateCode(String email, String code) {
        UsuariosDTO usuariosDTO = null;
        try {
            String query = "select p.id, u.cedula_usuario, u.email_usuario, p.codigo, p.creado from Usuarios as u " +
                    "inner join pass_recovery as p on u.cedula_usuario = p.cedula_usuario " +
                    "where u.email_usuario=? and p.codigo=? and p.creado > date_sub(now(), interval 10 minute) " +
                    "order by p.id desc limit 1";
            statement = con.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, code);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                usuariosDTO = new UsuariosDTO();
                usuariosDTO.setCedula(resultSet.getInt(2));
                usuariosDTO.setEmail(resultSet.getString(3));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return usuariosDTO;
    }

}
