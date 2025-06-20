package org.example.Controller;

import org.example.Productos; // Importa la clase Productos
import java.util.List;
import java.sql.SQLException;

public class productosController {


    public List<Productos> listarTodos() throws SQLException {
        // Llama al método estático listarTodos de la clase Productos
        return Productos.listarTodos();
    }


    public void crearProducto(int id_marca, String nombre_producto, String descripcion, String color, String talla, String SKU, int cntidad_stock, double precio_venta) throws SQLException {
        // Llama al método estático crear de la clase Productos
        Productos.crear(id_marca, nombre_producto, descripcion, color, talla, SKU, cntidad_stock, precio_venta);
    }


    public void modificarProducto(int id_producto, int id_marca, String nombre_producto, String descripcion, String color, String talla, String SKU, int cntidad_stock, double precio_venta) throws SQLException {
        // Para el método de instancia 'modificar', necesitamos crear un objeto Productos
        // con los datos a actualizar y el ID del producto.
        Productos productoParaModificar = new Productos(id_marca, id_producto, nombre_producto, descripcion, color, talla, SKU, cntidad_stock, precio_venta);
        // El ID del producto ya está establecido en el constructor anterior.

        productoParaModificar.modificar(); // Llama al método de instancia modificar
    }
}