package org.example.Controller;

import org.example.Proveedores; // Importa la clase Proveedores
import java.util.List;
import java.sql.SQLException;

public class proveedoresController {


    public List<Proveedores> listarTodos() throws SQLException {

        return Proveedores.listarTodos();
    }


    public void crearProveedor(String nombre_proveedor, String contacto, String telefono, String email, String direccion) throws SQLException {

        Proveedores.crear(nombre_proveedor, contacto, telefono, email, direccion);
    }


    public void modificarProveedor(int id_proveedor, String nombre_proveedor, String contacto, String telefono, String email, String direccion) throws SQLException {

        Proveedores proveedorParaModificar = new Proveedores(id_proveedor, nombre_proveedor, contacto, telefono, email, direccion);


        proveedorParaModificar.modificar();
    }
}
