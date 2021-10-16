package usuarios;

import Conexion.Conexion;
import clientes.ClientesDTO;
import utils.PasswordCrypto;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class UsuariosDAO {
    Conexion conexion = new Conexion();
    Connection con = conexion.connect();
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    public boolean insertUser(UsuariosDTO usuario){
       boolean result = false;
       try{
           String query = "insert into Usuarios values(?,?,?,?,?)";
           statement = con.prepareStatement(query);
           statement.setInt(1,usuario.getCedula());
           statement.setString(2, usuario.getEmail());
           statement.setString(3, usuario.getName());
           statement.setString(4, usuario.getPassword());
           statement.setString(5, usuario.getUser());
           result = statement.executeUpdate() > 0;

       }catch(SQLException e){
           System.out.println(e);
       }
       return result;
   }

    public UsuariosDTO searchUser(int cedula) {
        UsuariosDTO user = null;
        try {
            String query = "select * from Usuarios where cedula_usuario=?";
            statement = con.prepareStatement(query);
            statement.setInt(1,cedula);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                user = new UsuariosDTO(Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(5),
                        resultSet.getString(4),
                        resultSet.getString(3),
                        resultSet.getString(2));
            }
        }catch (SQLException e){
            System.out.println(e);
//            JOptionPane.showMessageDialog(null, "Error"+e);
        }
        return user;
    }

    public UsuariosDTO searchByEmail(String email){
        UsuariosDTO user = null;
        try {
            String query = "select * from Usuarios where email_usuario=?";
            statement = con.prepareStatement(query);
            statement.setString(1,email);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                user = new UsuariosDTO(Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(5),
                        resultSet.getString(4),
                        resultSet.getString(3),
                        resultSet.getString(2));
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        return user;
    }
    public boolean updateUser(UsuariosDTO usuariosDTO) {
        boolean result = false;
        try{
            String query = "update Usuarios set email_usuario=?, nombre_usuario=?, password=?," +
                    "usuario=? where cedula_usuario=?";
            statement = con.prepareStatement(query);
            statement.setString(1, usuariosDTO.getEmail());
            statement.setString(2, usuariosDTO.getName());
            statement.setString(3, usuariosDTO.getPassword());
            statement.setString(4, usuariosDTO.getUser());
            statement.setInt(5,usuariosDTO.getCedula());
            result = statement.executeUpdate() > 0;

        }catch (SQLException e){
//            JOptionPane.showMessageDialog(null, "Error"+e);
            System.out.println(e);
        }
        return result;
    }

    public boolean deleteUser(int cedula) {
        boolean result = false;
        try{
            String query = "delete FROM Usuarios WHERE cedula_usuario=?";
            statement= con.prepareStatement(query);
            statement.setInt(1, cedula);
            result = statement.executeUpdate() > 0;
        }catch (SQLException e){
            System.out.println(e);
//            JOptionPane.showMessageDialog(null, "Error"+e);
        }
        return result;
    }

    public ArrayList<UsuariosDTO> allUsers () {

        UsuariosDTO usuariosDTO = null;
        ArrayList<UsuariosDTO> listaUsuarios = new ArrayList<>();

        try {
            String query = "select * FROM Usuarios";
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                usuariosDTO = new UsuariosDTO();
                usuariosDTO.setCedula(resultSet.getInt(1));
                usuariosDTO.setEmail(resultSet.getString(2));
                usuariosDTO.setName(resultSet.getString(3));
                usuariosDTO.setUser(resultSet.getString(5));
                listaUsuarios.add(usuariosDTO);
            }
            System.out.println(listaUsuarios);
        }catch (SQLException e) {
            System.out.println(e);

        }return listaUsuarios;
    }

    public boolean changePassword(String password, int cedula){
        boolean result = false;
        String hashedPassword = PasswordCrypto.hashPassword(password);

        try {
            String query = "update Usuarios set password=? where cedula_usuario=?";
            statement = con.prepareStatement(query);
            statement.setString(1, hashedPassword);
            statement.setInt(2,cedula);

            result = statement.executeUpdate()>0;

        }catch (SQLException e){
            System.out.println(e);
        }
       return result;
    }

}
