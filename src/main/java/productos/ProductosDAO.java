package productos;

import Conexion.Conexion;
import clientes.ClientesDTO;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductosDAO {

    Conexion conexion = new Conexion();
    Connection con = conexion.connect();
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    public ProductosDTO searchProducto (int codigo_producto) {

        ProductosDTO producto =  null;

        try {
            String query = "Select * from Productos where codigo_producto = ?";
            statement = con.prepareStatement(query);
            statement.setInt(1, codigo_producto);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                producto = new ProductosDTO(Integer.parseInt(resultSet.getString(1)),
                        Double.parseDouble(resultSet.getString(2)),
                        Double.parseDouble(resultSet.getString(5)),
                        Double.parseDouble(resultSet.getString(6)),
                        resultSet.getString(3),
                        resultSet.getString(4));
            }


        }catch (SQLException e) {
            System.out.println(e);
//            JOptionPane.showMessageDialog(null, "Carga fallida del archivo" + e);
        }
        return producto;
    }

    public boolean updateProducto (ProductosDTO producto) {
        boolean result = false;

        try {
            String query = "update Productos set nombre_producto = ?," +
                    "precio_venta = ? where codigo_producto = ?";
            statement = con.prepareStatement(query);
            statement.setString(1, producto.getNombre_producto());
            statement.setDouble(2, producto.getPrecio_venta());
            statement.setInt(3, producto.getCodigo_producto());
            result = statement.executeUpdate() > 0;

        }catch (SQLException e) {
            System.out.println(e);
//            JOptionPane.showMessageDialog(null, "error" + e);
        }
        return result;
    }

    public boolean uploadProducto(String URL) {

        boolean result = false;

        try {
//            String query1 = "DELETE FROM Productos";
//            statement = con.prepareStatement(query1);
//            statement.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Borrado");

            String query = "load data infile '"+URL+"' replace into table productos fields terminated by ','" +
                    " lines terminated by '\\n'";
            System.out.println(query);

            statement = con.prepareStatement(query);
            result = statement.executeUpdate() > 0;

        }catch(SQLException ex) {
            System.out.println("productosDAO"+ex);
//            JOptionPane.showMessageDialog(null,"Error al registrar los productos: "+ ex);
        }
        return result;
    }
    public ArrayList<ProductosDTO> cargarProductos() {
        ProductosDTO producto = null;
        ArrayList<ProductosDTO> listaProductos = new ArrayList<>();

        try {
            String query = "select * from Productos ";
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                producto = new ProductosDTO(Integer.parseInt(resultSet.getString(1)),
                        Double.parseDouble(resultSet.getString(2)),
                        Double.parseDouble(resultSet.getString(5)),
                        Double.parseDouble(resultSet.getString(6)),
                        resultSet.getString(3),
                        resultSet.getString(4));
                listaProductos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error al cargar clientes" + e);
        }
        return listaProductos;
    }
    public boolean uploadCSV(String csv) throws FileNotFoundException {
        boolean result = false;

        BufferedReader reader= new BufferedReader(new FileReader(csv));
        String line = null;
        try{
            String query = "replace into Productos values(?,?,?,?,?,?)";
            statement = con.prepareStatement(query);
            while ((line = reader.readLine())!= null) {
                String[] parts = line.split(",");
                int codigo = Integer.parseInt(parts[0]);
                Double iva = Double.parseDouble(parts[1]);
                String nit = parts[2];
                String nombre = parts[3];
                Double compra = Double.parseDouble(parts[4]);
                Double venta = Double.parseDouble(parts[5]);

                statement.setInt(1,codigo);
                statement.setDouble(2,iva);
                statement.setString(3,nit);
                statement.setString(4,nombre);
                statement.setDouble(5,compra);
                statement.setDouble(6,venta);
                statement.addBatch();
            }

             result = statement.executeBatch().length > 0;
            reader.close();

        }catch (Exception e){
            System.out.println(e);
        }
        return result;
    }
}
