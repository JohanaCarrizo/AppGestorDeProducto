/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud_mysql;

import javax.swing.*;

/**
 *
 * @author Johana
 */
interface IProducto {
    
    void insertarProducto(JTextField nombre, JTextField precio, JTextField codigo_fab);
    
    void listarProductos(JTable listarProductosEnTabla);
    
    void seleccionarProductoDeTabla();
    
    void modificarProducto(JTextField id, JTextField nombre, JTextField precio, JTextField codigo_fab);
    
    void elminarProducto(JTextField id);
    
}
