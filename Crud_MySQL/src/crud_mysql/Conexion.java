/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud_mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Johana
 */
public class Conexion {
    
    Connection conexion = null;
    
    
    String usuario = "root";
    String pass = "";
    String db = "tienda";
    String ip = "localhost";
    String puerto = "3306";
    
    String url = "jdbc:mysql://"+ip+":"+puerto+"/"+db;
    
    public Connection conexionDB(){
    
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, pass);
            //JOptionPane.showMessageDialog(null, "La conexion se realiazada con exito");
            
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error al conectarse con la base de dato"+e.toString());
        }
    
        return conexion;
    }
}
