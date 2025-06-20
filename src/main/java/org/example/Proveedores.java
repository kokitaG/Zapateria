package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Proveedores {

    private int id_proveedor;
    private String nombre_proveedor;
    private String contacto;
    private String telefono;
    private String email;
    private String direccion;

    //Constructores

    public Proveedores() {
    }

    // Constructor completo (se usa para leer de la DB o para instanciar en general)
    public Proveedores(int id_proveedor, String nombre_proveedor, String contacto, String telefono, String email, String direccion) {
        this.id_proveedor = id_proveedor;
        this.nombre_proveedor = nombre_proveedor;
        this.contacto = contacto;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }

    // Constructor para operaciones donde solo se necesita el ID del proveedor (modificar/eliminar por ID)
    public Proveedores(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    // Constructor para el método estático 'crear' (sin id_proveedor, ya que es auto-incremental)
    public Proveedores(String nombre_proveedor, String contacto, String telefono, String email, String direccion) {
        this.nombre_proveedor = nombre_proveedor;
        this.contacto = contacto;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }


    //Getter (ya son públicos)

    public int getId_proveedor() {
        return id_proveedor;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public String getContacto() {
        return contacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getDireccion() {
        return direccion;
    }

    //Setter (ya son públicos)

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public static void crear(String nombre_proveedor, String contacto, String telefono, String email, String direccion) throws SQLException {
        String sql = "INSERT INTO proveedores (nombre_proveedor, contacto, telefono, email, direccion) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre_proveedor);
            ps.setString(2, contacto);
            ps.setString(3, telefono);
            ps.setString(4, email);
            ps.setString(5, direccion);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Proveedor '" + nombre_proveedor + "' insertado exitosamente.");
            } else {
                System.out.println("No se pudo insertar el proveedor '" + nombre_proveedor + "'.");
            }
        }
    }



    public void modificar() throws SQLException {
        // Para modificar, el id_proveedor de esta instancia debe estar establecido
        if (this.id_proveedor == 0) {
            throw new SQLException("El ID del proveedor no puede ser 0 al modificar.");
        }

        String sql = "UPDATE proveedores SET nombre_proveedor = ?, contacto = ?, telefono = ?, email = ?, direccion = ? WHERE id_proveedor = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, this.nombre_proveedor);
            ps.setString(2, this.contacto);
            ps.setString(3, this.telefono);
            ps.setString(4, this.email);
            ps.setString(5, this.direccion);
            ps.setInt(6, this.id_proveedor);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Proveedor con ID " + this.id_proveedor + " modificado exitosamente.");
            } else {
                System.out.println("No se encontró el proveedor con ID " + this.id_proveedor + " para modificar o no hubo cambios.");
            }
        }
    }



    public static List<Proveedores> listarTodos() throws SQLException { // <-- RENOMBRADO DE 'list()'
        List<Proveedores> listaProveedores = new ArrayList<>();
        String sql = "SELECT id_proveedor, nombre_proveedor, contacto, telefono, email, direccion FROM proveedores";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_proveedor");
                String nombre = rs.getString("nombre_proveedor");
                String contacto = rs.getString("contacto");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
                String direccion = rs.getString("direccion");

                listaProveedores.add(new Proveedores(id, nombre, contacto, telefono, email, direccion));
            }
        }
        return listaProveedores;
    }


    @Override
    public String toString() {
        return "Proveedor {" +
                "ID=" + id_proveedor +
                ", Nombre='" + nombre_proveedor + '\'' +
                ", Contacto='" + contacto + '\'' +
                ", Teléfono='" + telefono + '\'' +
                ", Email='" + email + '\'' +
                ", Dirección='" + direccion + '\'' +
                '}';
    }
}