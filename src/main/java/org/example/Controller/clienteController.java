// In clienteController.java
package org.example.Controller;

import org.example.Clientes; // <--- This import is crucial
import java.util.List;
import java.sql.SQLException;

public class clienteController {

    public List<Clientes> listarTodos() throws SQLException {
        return Clientes.listarTodos(); // <--- Correct static call
    }

    public void crearCliente(String nombre, String apellido, String email, String telefono, String direccion) throws SQLException {
        Clientes.crear(nombre, apellido, email, telefono, direccion); // <--- Correct static call
    }

    public void modificarCliente(int id_clientes, String nombre, String apellido, String email, String telefono, String direccion) throws SQLException {
        Clientes cliente = new Clientes(id_clientes, nombre, apellido, email, telefono, direccion, null);
        cliente.modificar();
    }
}