package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

// HACER LA CLASE MARCAS PÚBLICA
public class Marcas { // <-- AÑADIDO 'public' aquí
    private int id_marca;
    private String nombre_marca;

    // Constructores
    public Marcas() {
    }

    public Marcas(int id_marca, String nombre_marca) {
        this.id_marca = id_marca;
        this.nombre_marca = nombre_marca;
    }

    public Marcas(int id_marca) {
        this.id_marca = id_marca;
    }

    // Constructor para el método estático 'crear'
    public Marcas(String nombre_marca) {
        this.nombre_marca = nombre_marca;
    }

    // Getters
    public int getId_marca() {
        return id_marca;
    }

    public String getNombre_marca() {
        return nombre_marca;
    }

    // Setters
    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public void setNombre_marca(String nombre_marca) {
        this.nombre_marca = nombre_marca;
    }


    public static void crear(String nombre_marca) throws SQLException {
        String sql = "INSERT INTO marcas (nombre_marca) VALUES (?)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre_marca); // Usamos el parámetro del método

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Marca '" + nombre_marca + "' insertada exitosamente.");
            } else {
                System.out.println("No se pudo insertar la marca '" + nombre_marca + "'.");
            }
        }
    }


    public void modificar() throws SQLException {
        if (this.id_marca == 0) {
            throw new SQLException("El ID de la marca no puede ser 0 al modificar.");
        }
        String sql = "UPDATE marcas SET nombre_marca = ? WHERE id_marca = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, this.nombre_marca); // Nuevo nombre de la marca (desde la instancia)
            ps.setInt(2, this.id_marca);     // ID de la marca a modificar (desde la instancia)

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Marca con ID " + this.id_marca + " modificada a '" + this.nombre_marca + "' exitosamente.");
            } else {
                System.out.println("No se encontró la marca con ID " + this.id_marca + " para modificar o no hubo cambios.");
            }
        }
    }


    public static List<Marcas> listarTodas() throws SQLException { // <-- RENOMBRADO Y ES STATIC
        List<Marcas> listaMarcas = new ArrayList<>();
        String sql = "SELECT id_marca, nombre_marca FROM marcas";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_marca");
                String nombre = rs.getString("nombre_marca");
                listaMarcas.add(new Marcas(id, nombre)); // Usa el constructor con ID y nombre
            }
        }
        return listaMarcas;
    }

    // Sobrescribir toString() para una mejor representación del objeto
    @Override
    public String toString() {
        return "Marca [ID: " + id_marca + ", Nombre: " + nombre_marca + "]";
    }
}