package org.example.Controller;

import org.example.Usuario; // Importa la clase Usuario
import java.util.List;
import java.sql.SQLException;

public class usuarioController {

    public List<Usuario> listarTodos() throws SQLException {
        return Usuario.listarTodos();
    }

    public void crearUsuario(String nombre_usuario, String contrasena) throws SQLException {
        Usuario.crear(nombre_usuario, contrasena);
    }


    public void modificarUsuario(int id_usuario, String nombre_usuario, String contrasena) throws SQLException {
        Usuario usuarioParaModificar = new Usuario(id_usuario, nombre_usuario, contrasena);
        usuarioParaModificar.modificar();
    }
}