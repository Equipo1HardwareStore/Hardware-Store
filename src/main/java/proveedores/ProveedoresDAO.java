package proveedores;

import Conexion.Conexion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProveedoresDAO {
    Conexion conexion = new Conexion();
    Connection con = conexion.connect();
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    public ProveedoresDTO searchProveedor(String nit) {
       ProveedoresDTO proveedor = null;

       try{
           String query = "select * from Proveedores where nitproveedor=?";
           statement = con.prepareStatement(query);
           statement.setString(1,nit);
           resultSet = statement.executeQuery();
           while(resultSet.next()){
              proveedor = new ProveedoresDTO(resultSet.getString(1),
                      resultSet.getString(4),
                      resultSet.getString(3),
                      resultSet.getString(5),
                      resultSet.getString(2)
                      );
           }
       }catch (SQLException e){
           System.out.println(e);
//           JOptionPane.showMessageDialog(null, "Error"+e);
       }
       return proveedor;
    }

    public boolean insertProvider(ProveedoresDTO prveedor){
        boolean result = false;
        try {
            String query = "insert into Proveedores values(?,?,?,?,?)";
            statement = con.prepareStatement(query);
            statement.setString(1,prveedor.getNit());
            statement.setString(2,prveedor.getCity());
            statement.setString(3,prveedor.getAddress());
            statement.setString(4,prveedor.getName());
            statement.setString(5,prveedor.getPhone());
            result = statement.executeUpdate() > 0;
        }catch (SQLException e){
            System.out.println(e);
//            JOptionPane.showMessageDialog(null, "Error"+e);
        }
        return result;
    }

    public boolean updateProvider(ProveedoresDTO proveedor) {
        boolean result = false;
        try{
            String query = "update Proveedores set ciudad_proveedor=?, direccion_proveedor=?, nombre_proveedor=?, telefono_proveedor=? where nitproveedor=?";
            statement = con.prepareStatement(query);
            statement.setString(1, proveedor.getCity());
            statement.setString(2, proveedor.getAddress());
            statement.setString(3, proveedor.getName());
            statement.setString(4, proveedor.getPhone());
            statement.setString(5, proveedor.getNit());
            result = statement.executeUpdate() > 0;
        }catch(SQLException e){
            System.out.println(e);
//            JOptionPane.showMessageDialog(null, "Error"+e);
        }
        return result;
    }

    public boolean deleteProvider(String nit) {
        boolean result = false;
        try {
            String query = "delete from Proveedores where nitproveedor=?";
            statement = con.prepareStatement(query);
            statement.setString(1,nit);
            result = statement.executeUpdate() > 0;
        }catch (SQLException e){
            System.out.println(e);
//            JOptionPane.showMessageDialog(null, "Error"+e);
        }
        return result;
    }
}
