package clientes;

import Conexion.Conexion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientesDAO {
    Conexion conexion = new Conexion();
    Connection con = conexion.connect();
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    public boolean insertclient(ClientesDTO clients) {
        boolean result = false;
        try {
            String query = "insert into Clientes values(?,?,?,?,?)";
            statement = con.prepareStatement(query);
            statement.setInt(1, clients.getCedula());
            statement.setString(2, clients.getAdress());
            statement.setString(3, clients.getEmail());
            statement.setString(4, clients.getName());
            statement.setString(5, clients.getPhone());
            result = statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e);
//            JOptionPane.showMessageDialog(null, "Error" + e);
        }
        return result;
    }

    public ClientesDTO searchCliente(int cedula) {
        ClientesDTO cliente = null;
        try {
            String query = "select * from Clientes where cedula_cliente=?";
            statement = con.prepareStatement(query);
            statement.setInt(1, cedula);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cliente = new ClientesDTO(Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5));
            }
        } catch (SQLException e) {
            System.out.println(e);
//            JOptionPane.showMessageDialog(null, "Error" + e);
        }
        return cliente;
    }

    public boolean updateClient(ClientesDTO clientsDTO) {
        boolean result = false;
        try {
            String query = "update Clientes set direcion_cliente=?, email_cliente=?, nombre_cliente=?," +
                    "telefono_clientes=? where cedula_cliente=?";
            statement = con.prepareStatement(query);
            statement.setString(1, clientsDTO.getAdress());
            statement.setString(2, clientsDTO.getEmail());
            statement.setString(3, clientsDTO.getName());
            statement.setString(4, clientsDTO.getPhone());
            statement.setInt(5, clientsDTO.getCedula());
            result = statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e);
//            JOptionPane.showMessageDialog(null, "Error" + e);
        }
        return result;
    }


    public boolean deleteclient(int cedula) {
        boolean result = false;
        try {
            String query = "delete FROM Clientes WHERE cedula_cliente=?";
            statement = con.prepareStatement(query);
            statement.setInt(1, cedula);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e);
//            JOptionPane.showMessageDialog(null, "Error" + e);
        }
        return result;
    }

    public ArrayList<ClientesDTO> cargarClientes() {
        ClientesDTO clienteC = null;
        ArrayList<ClientesDTO> listaClientes = new ArrayList<>();

        try {
            String query = "select cedula_cliente,nombre_cliente from Clientes ";
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                clienteC = new ClientesDTO();
                clienteC.setCedula(Integer.parseInt(resultSet.getString(1)));
                clienteC.setName(resultSet.getString(2));
                listaClientes.add(clienteC);
            }
        } catch (SQLException e) {
            System.out.println(e);
//            JOptionPane.showMessageDialog(null, "Error al cargar clientes" + e);
        }
        return listaClientes;
    }

    public ArrayList<ClientesDTO> allClients () {

        ClientesDTO clientesDTO = null;
        ArrayList<ClientesDTO> listaclientes = new ArrayList<>();

        try {
            String query = "select * FROM Clientes";
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                clientesDTO = new ClientesDTO();
                clientesDTO.setCedula(resultSet.getInt(1));
                clientesDTO.setEmail(resultSet.getString(3));
                clientesDTO.setName(resultSet.getString(4));
                clientesDTO.setAdress(resultSet.getString(2));
                clientesDTO.setPhone(resultSet.getString(5));
                listaclientes.add(clientesDTO);
            }
            System.out.println(listaclientes);
        }catch (SQLException e) {
            System.out.println(e);

        }return listaclientes;
    }
}
