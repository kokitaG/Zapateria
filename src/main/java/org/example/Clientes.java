package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp; // Import necesario para getTimestamp en ResultSet

public class Clientes {

    private int id_clientes; // Atributos privados
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String direccion;
    private Date fecha_registro;

    //Constructores

    public Clientes() {
    }

    public Clientes(int id_clientes, String nombre, String apellido, String email, String telefono, String direccion, Date fecha_registro) {
        this.id_clientes = id_clientes;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fecha_registro = fecha_registro;
    }

    public Clientes(int id_clientes) {
        this.id_clientes = id_clientes;
    }

    // Constructor añadido para facilitar la creación de objetos Clientes cuando se listan
    // No se usa para el método estático 'crear', pero es útil para otros escenarios
    public Clientes(String nombre, String apellido, String email, String telefono, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fecha_registro = null; // La DB podría poner NOW()
    }


    // Getter

    public int getId_clientes() {
        return id_clientes;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    // Setter

    public void setId_clientes(int id_clientes) {
        this.id_clientes = id_clientes;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }


    // ======================================
    // Métodos CRUD
    // ======================================

    /**
     * Inserta un nuevo cliente en la base de datos.
     * Este método es STATIC para ser llamado directamente desde el controlador.
     */
    public static void crear(String nombre, String apellido, String email, String telefono, String direccion) throws SQLException {
        String sql = "INSERT INTO clientes (nombre, apellido, email, telefono, direccion, fecha_registro) VALUES (?, ?, ?, ?, ?, NOW())";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);    // Usamos los parámetros del método
            ps.setString(2, apellido);
            ps.setString(3, email);
            ps.setString(4, telefono);
            ps.setString(5, direccion);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cliente '" + nombre + " " + apellido + "' insertado exitosamente.");
            } else {
                System.out.println("No se pudo insertar el cliente '" + nombre + " " + apellido + "'.");
            }
        }
    }

    /**
     * Modifica un cliente existente en la base de datos.
     * Este método NO es estático porque opera sobre la instancia actual del cliente.
     */
    public void modificar() throws SQLException {
        if (this.id_clientes == 0) {
            throw new SQLException("El ID del cliente no puede ser 0 al modificar. Asigne el ID del cliente a la instancia.");
        }
        String sql = "UPDATE clientes SET nombre = ?, apellido = ?, email = ?, telefono = ?, direccion = ? WHERE id_clientes = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, this.nombre);
            ps.setString(2, this.apellido);
            ps.setString(3, this.email);
            ps.setString(4, this.telefono);
            ps.setString(5, this.direccion);
            ps.setInt(6, this.id_clientes);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cliente con ID " + this.id_clientes + " modificado exitosamente.");
            } else {
                System.out.println("No se encontró el cliente con ID " + this.id_clientes + " para modificar o no hubo cambios.");
            }
        }
    }

    /**
     * Lista todos los clientes de la base de datos.
     * Este método es STATIC para ser llamado directamente desde el controlador.
     * Se renombró de 'list' a 'listarTodos' para mayor claridad y evitar conflictos.
     * @return Una lista de objetos Clientes.
     */
    public static List<Clientes> listarTodos() throws SQLException {
        List<Clientes> listaClientes = new ArrayList<>();
        String sql = "SELECT id_clientes, nombre, apellido, email, telefono, direccion, fecha_registro FROM clientes";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_clientes");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                String direccion = rs.getString("direccion");
                Date fecha = rs.getTimestamp("fecha_registro"); // Usar getTimestamp para leer fechas de la DB

                // Se crea una nueva instancia de Clientes con los datos recuperados
                listaClientes.add(new Clientes(id, nombre, apellido, email, telefono, direccion, fecha));
            }
        }
        return listaClientes;
    }

    // Sobrescribir toString() para una mejor representación del objeto
    @Override
    public String toString() {
        return "Cliente {" +
                "ID=" + id_clientes +
                ", Nombre='" + nombre + '\'' +
                ", Apellido='" + apellido + '\'' +
                ", Email='" + email + '\'' +
                ", Teléfono='" + telefono + '\'' +
                ", Dirección='" + direccion + '\'' +
                ", Fecha Registro=" + fecha_registro +
                '}';
    }
}