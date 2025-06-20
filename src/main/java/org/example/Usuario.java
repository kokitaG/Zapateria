package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id_usuario;
    private String nombre_usuario;
    private String contrasena; // Considera usar un mecanismo más seguro para contraseñas (hashing) en un entorno real.

    // CONSTRUCTORES

    public Usuario() {
    }

    // Constructor completo para leer de la base de datos o instanciar un objeto existente
    public Usuario(int id_usuario, String nombre_usuario, String contrasena) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.contrasena = contrasena;
    }

    // Constructor para crear un nuevo usuario (sin ID, ya que es auto-incremental en la DB)
    public Usuario(String nombre_usuario, String contrasena) {
        this.nombre_usuario = nombre_usuario;
        this.contrasena = contrasena;
    }

    // Constructor para operaciones donde solo se necesita el ID (ej. eliminar)
    public Usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }


    // Getters (ya son públicos)

    public int getId_usuario() {
        return id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    // Setters (ya son públicos)

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }


    public static void crear(String nombre_usuario, String contrasena) throws SQLException {
        String sql = "INSERT INTO usuarios (nombre_usuario, contrasena) VALUES (?, ?)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre_usuario);
            ps.setString(2, contrasena);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuario '" + nombre_usuario + "' insertado exitosamente.");
            } else {
                System.out.println("No se pudo insertar el usuario '" + nombre_usuario + "'.");
            }
        }
    }


    public void modificar() throws SQLException {
        // Para modificar, el id_usuario de esta instancia debe estar establecido
        if (this.id_usuario == 0) {
            throw new SQLException("El ID del usuario no puede ser 0 al modificar.");
        }

        String sql = "UPDATE usuarios SET nombre_usuario = ?, contrasena = ? WHERE id_usuario = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, this.nombre_usuario);
            ps.setString(2, this.contrasena);
            ps.setInt(3, this.id_usuario);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuario con ID " + this.id_usuario + " modificado exitosamente.");
            } else {
                System.out.println("No se encontró el usuario con ID " + this.id_usuario + " para modificar o no hubo cambios.");
            }
        }
    }


    public static List<Usuario> listarTodos() throws SQLException {
        List<Usuario> listaUsuarios = new ArrayList<>();
        String sql = "SELECT id_usuario, nombre_usuario, contrasena FROM usuarios";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_usuario");
                String nombre = rs.getString("nombre_usuario");
                String contrasena = rs.getString("contrasena");

                listaUsuarios.add(new Usuario(id, nombre, contrasena));
            }
        }
        return listaUsuarios;
    }

    @Override
    public String toString() {
        return "Usuario {" +
                "ID=" + id_usuario +
                ", Nombre='" + nombre_usuario + '\'' +
                // Considera NO imprimir la contraseña en toString() por seguridad
                '}';
    }
}


