package Conexion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private final String BD = "grupo_8_equipo1";
//    prestamosvf.czo3ixoe3xoe.us-east-1.rds.amazonaws.com
    private final String URL = "jdbc:mariadb://prestamosvf.czo3ixoe3xoe.us-east-1.rds.amazonaws.com/"+ BD;
//    private final String URL = "jdbc:mysql://localhost:3306/"+ BD;
    private final String USER = "admin";
    private final String PASS = "Ciclo3_admi123";

    Connection con = null;

    public Connection connect(){
        try{

            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(URL,USER,PASS);
        } catch(Exception e){
            System.out.println(e);
//            JOptionPane.showMessageDialog(null, "Error"+e);
        }
        return con;
    }
}
