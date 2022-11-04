/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud_mysql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Johana
 */
public class Producto implements IProducto{
    
    int codigo;
    String nombre;
    double precio;
    int codigo_fabricante;
    
    Conexion conn = new Conexion();
    Statement st;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCodigo_fabricante() {
        return codigo_fabricante;
    }

    public void setCodigo_fabricante(int codigo_fabricante) {
        this.codigo_fabricante = codigo_fabricante;
    }
    
    
    public void insertarProducto(JTextField nombre, JTextField precio, JTextField codigo_fab){
        
        setNombre(nombre.getText());
        setPrecio(Double.parseDouble(precio.getText()));
        setCodigo_fabricante(Integer.parseInt(codigo_fab.getText()));
        
        
        try {
            
            CallableStatement cs = conn.conexionDB().prepareCall("INSERT INTO producto(nombre, precio, codigo_fabricante) VALUES(?, ?, ?);");
            
            cs.setString(1, getNombre());
            cs.setDouble(2, getPrecio());
            cs.setInt(3, getCodigo_fabricante());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se inserto correctamente");
            
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "No se puedo insertar"+e.toString());
        }
        
    }
    
     public void listarProductos(JTable listarProductosEnTabla){
    
         DefaultTableModel tabla = new DefaultTableModel();    
         
         TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<TableModel>(tabla);
         
         listarProductosEnTabla.setRowSorter(ordenarTabla);
         
         tabla.addColumn("ID" );
         tabla.addColumn("Nombre");
         tabla.addColumn("Precio");
         tabla.addColumn("Codigo_fabricante");
         
         listarProductosEnTabla.setModel(tabla);
         String[] datos = new String[4];
         
         try {
             
             st = conn.conexionDB().createStatement();
         
             ResultSet rSet = st.executeQuery("SELECT * FROM producto;");
             
             while (rSet.next()) {           
                 
                 datos[0] = rSet.getString(1);
                 datos[1] = rSet.getString(2);
                 datos[2] = rSet.getString(3);
                 datos[3] = rSet.getString(4);
                 
                 tabla.addRow(datos);
                 
             }
             
             listarProductosEnTabla.setModel(tabla);
             
         } catch (Exception e) {
             
             JOptionPane.showMessageDialog(null, "No se puedo listar los registros de productos"+e.toString());
         }       
         
    }
     
     public void seleccionarProductoDeTabla(JTable listarProductosEnTabla, JTextField id, JTextField nombre, JTextField precio, JTextField id_fabricante){

         int fila = listarProductosEnTabla.getSelectedRow();
         
         if(fila >=0){
             
             id.setText(listarProductosEnTabla.getValueAt(fila, 0).toString());
             nombre.setText(listarProductosEnTabla.getValueAt(fila, 1).toString());
             precio.setText(listarProductosEnTabla.getValueAt(fila, 2).toString());
             id_fabricante.setText(listarProductosEnTabla.getValueAt(fila, 3).toString());
         
         }else{
         
             JOptionPane.showMessageDialog(null, "No se selecciono un registro de la tabla");
         
         }         
         
     }
     
     public void modificarProducto(JTextField id, JTextField nombre, JTextField precio, JTextField codigo_fab){
     
        setCodigo(Integer.parseInt(id.getText()));
        setNombre(nombre.getText());
        setPrecio(Double.parseDouble(precio.getText()));
        setCodigo_fabricante(Integer.parseInt(codigo_fab.getText()));
        
        
        
        try {
            
            CallableStatement cs = conn.conexionDB().prepareCall("UPDATE producto SET producto.nombre = ?, \n" +
                                                                                                 "producto.precio = ?, \n" +
                                                                                                 "producto.codigo_fabricante = ?\n" +
                                                                                                 "where producto.codigo = ?;");
            
            cs.setString(1, getNombre());
            cs.setDouble(2, getPrecio());
            cs.setInt(3, getCodigo_fabricante());
            cs.setInt(4, codigo);
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se Modifico correctamente");
            
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "No se puedo modificar, error "+e.toString());
        }
     }
     
     public void eliminarProducto(JTextField id){
     
         setCodigo(Integer.parseInt(id.getText()));
         
        try {
            
            CallableStatement cs = conn.conexionDB().prepareCall("DELETE FROM producto  WHERE producto.codigo = ?;");
            
            cs.setInt(1, getCodigo());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se Eliminó correctamente");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se puedo eliminar, error "+ex.toString());
        }
     
     }

    @Override
    public void seleccionarProductoDeTabla() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void elminarProducto(JTextField id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
