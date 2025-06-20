package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// HACER LA CLASE COMPRAS PÚBLICA
public class Compras extends Proveedores { // <-- AÑADIDO 'public' aquí
    private int id_compra;
    private Date fecha_compra;
    private double monto_total_compra;
    private String estado_compra;
    private String detalles_items;

    //Constructores (ya son públicos implícitamente si la clase es pública)

    public Compras() {
        super();
    }

    public Compras(int id_compra) {
        this.id_compra = id_compra;
    }

    public Compras(int id_proveedor, Date fecha_compra, double monto_total_compra, String estado_compra, String detalles_items) {
        super(id_proveedor);
        this.fecha_compra = fecha_compra;
        this.monto_total_compra = monto_total_compra;
        this.estado_compra = estado_compra;
        this.detalles_items = detalles_items;
    }

    public Compras(int id_proveedor, String nombre_proveedor, String contacto, String telefono, String email, String direccion,
                   int id_compra, Date fecha_compra, double monto_total_compra, String estado_compra, String detalles_items) {
        super(id_proveedor, nombre_proveedor, contacto, telefono, email, direccion);
        this.id_compra = id_compra;
        this.fecha_compra = fecha_compra;
        this.monto_total_compra = monto_total_compra;
        this.estado_compra = estado_compra;
        this.detalles_items = detalles_items;
    }


    //Getters (ya son públicos)

    public int getId_compra() {
        return id_compra;
    }

    public Date getFecha_compra() {
        return fecha_compra;
    }

    public double getMonto_total_compra() {
        return monto_total_compra;
    }

    public String getEstado_compra() {
        return estado_compra;
    }

    public String getDetalles_items() {
        return detalles_items;
    }

    //Setters (ya son públicos)

    public void setId_compra(int id_compra) { // <-- ASEGURARSE DE QUE ES PÚBLICO
        this.id_compra = id_compra;
    }

    public void setFecha_compra(Date fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public void setMonto_total_compra(double monto_total_compra) {
        this.monto_total_compra = monto_total_compra;
    }

    public void setEstado_compra(String estado_compra) {
        this.estado_compra = estado_compra;
    }

    public void setDetalles_items(String detalles_items) {
        this.detalles_items = detalles_items;
    }

    // Métodos CRUD (ya son públicos, pero lo recalco)

    public static void crear(int id_proveedor, Date fecha_compra, double monto_total_compra, String estado_compra, String detalles_items) throws SQLException {
        if (id_proveedor == 0) {
            throw new SQLException("El ID del proveedor no puede ser 0 al crear una compra. Asigne el ID del proveedor.");
        }

        String sql = "INSERT INTO compras (id_proveedor, fecha_compra, monto_total_compra, estado_compra, detalles_items) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id_proveedor);
            ps.setTimestamp(2, new Timestamp(fecha_compra.getTime()));
            ps.setDouble(3, monto_total_compra);
            ps.setString(4, estado_compra);
            ps.setString(5, detalles_items);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Compra para proveedor ID " + id_proveedor + " insertada exitosamente.");
            } else {
                System.out.println("No se pudo insertar la compra para proveedor ID " + id_proveedor + ".");
            }
        }
    }

    public void modificar() throws SQLException { // <-- ASEGURARSE DE QUE ES PÚBLICO
        if (this.id_compra == 0) {
            throw new SQLException("El ID de la compra no puede ser 0 al modificar.");
        }
        String sql = "UPDATE compras SET fecha_compra = ?, monto_total_compra = ?, estado_compra = ?, detalles_items = ? WHERE id_compra = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setTimestamp(1, new Timestamp(this.fecha_compra.getTime()));
            ps.setDouble(2, this.monto_total_compra);
            ps.setString(3, this.estado_compra);
            ps.setString(4, this.detalles_items);
            ps.setInt(5, this.id_compra);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Compra con ID " + this.id_compra + " modificada exitosamente.");
            } else {
                System.out.println("No se encontró la compra con ID " + this.id_compra + " para modificar o no hubo cambios.");
            }
        }
    }

    public static List<Compras> listarTodas() throws SQLException { // <-- ASEGURARSE DE QUE ES PÚBLICO
        List<Compras> listaCompras = new ArrayList<>();
        String sql = "SELECT co.id_compra, co.fecha_compra, co.monto_total_compra, co.estado_compra, co.detalles_items, " +
                "pr.id_proveedor, pr.nombre_proveedor, pr.contacto, pr.telefono, pr.email, pr.direccion " +
                "FROM compras co JOIN proveedores pr ON co.id_proveedor = pr.id_proveedor " +
                "ORDER BY co.fecha_compra DESC";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idCompra = rs.getInt("id_compra");
                Date fechaCompra = rs.getTimestamp("fecha_compra");
                double montoTotalCompra = rs.getDouble("monto_total_compra");
                String estadoCompra = rs.getString("estado_compra");
                String detallesItems = rs.getString("detalles_items");

                int idProveedor = rs.getInt("id_proveedor");
                String nombreProveedor = rs.getString("nombre_proveedor");
                String contactoProveedor = rs.getString("contacto");
                String telefonoProveedor = rs.getString("telefono");
                String emailProveedor = rs.getString("email");
                String direccionProveedor = rs.getString("direccion");

                Compras compra = new Compras(idProveedor, nombreProveedor, contactoProveedor, telefonoProveedor,
                        emailProveedor, direccionProveedor, idCompra, fechaCompra,
                        montoTotalCompra, estadoCompra, detallesItems);
                listaCompras.add(compra);
            }
        }
        return listaCompras;
    }

    @Override
    public String toString() {
        return "Compra {" +
                "ID=" + id_compra +
                ", Fecha Compra=" + fecha_compra +
                ", Monto Total=" + monto_total_compra +
                ", Estado='" + estado_compra + '\'' +
                ", Detalles Items='" + detalles_items + '\'' +
                "} " + super.toString();
    }
}
