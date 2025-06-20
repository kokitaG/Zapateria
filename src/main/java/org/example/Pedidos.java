package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedidos extends Clientes {

    private int id_pedido;
    private Date fecha_pedido;
    private double monto_total;
    private String estado;
    private String detalle_items;

    //Constructores

    public Pedidos() {
        super();
    }

    public Pedidos(int id_clientes, String nombre, String apellido, String email, String telefono, String direccion, Date fecha_registro,
                   int id_pedido, Date fecha_pedido, double monto_total, String estado, String detalle_items) {
        super(id_clientes, nombre, apellido, email, telefono, direccion, fecha_registro);
        this.id_pedido = id_pedido;
        this.fecha_pedido = fecha_pedido;
        this.monto_total = monto_total;
        this.estado = estado;
        this.detalle_items = detalle_items;
    }

    public Pedidos(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Pedidos(int id_clientes, Date fecha_pedido, double monto_total, String estado, String detalle_items) {
        super(id_clientes);
        this.fecha_pedido = fecha_pedido;
        this.monto_total = monto_total;
        this.estado = estado;
        this.detalle_items = detalle_items;
    }


    //Getters

    public int getId_pedido() {
        return id_pedido;
    }

    public Date getFecha_pedido() {
        return fecha_pedido;
    }

    public double getMonto_total() {
        return monto_total;
    }

    public String getEstado() {
        return estado;
    }

    public String getDetalle_items() {
        return detalle_items;
    }

    //Setters

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public void setFecha_pedido(Date fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public void setMonto_total(double monto_total) {
        this.monto_total = monto_total;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setDetalle_items(String detalle_items) {
        this.detalle_items = detalle_items;
    }


    public static void crear(int id_clientes, Date fecha_pedido, double monto_total, String estado, String detalle_items) throws SQLException {
        if (id_clientes == 0) {
            throw new SQLException("El ID del cliente no puede ser 0 al crear un pedido. Asigne el ID del cliente al pedido.");
        }

        String sql = "INSERT INTO pedidos (id_clientes, fecha_pedido, monto_total, estado, detalle_items) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id_clientes);
            ps.setTimestamp(2, new Timestamp(fecha_pedido.getTime()));
            ps.setDouble(3, monto_total);
            ps.setString(4, estado);
            ps.setString(5, detalle_items);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Pedido para cliente ID " + id_clientes + " insertado exitosamente.");
            } else {
                System.out.println("No se pudo insertar el pedido para cliente ID " + id_clientes + ".");
            }
        }
    }


    public void modificar() throws SQLException {
        if (this.id_pedido == 0) {
            throw new SQLException("El ID del pedido no puede ser 0 al modificar.");
        }

        String sql = "UPDATE pedidos SET fecha_pedido = ?, monto_total = ?, estado = ?, detalle_items = ? WHERE id_pedido = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setTimestamp(1, new Timestamp(this.fecha_pedido.getTime()));
            ps.setDouble(2, this.monto_total);
            ps.setString(3, this.estado);
            ps.setString(4, this.detalle_items);
            ps.setInt(5, this.id_pedido);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Pedido con ID " + this.id_pedido + " modificado exitosamente.");
            } else {
                System.out.println("No se encontró el pedido con ID " + this.id_pedido + " para modificar o no hubo cambios.");
            }
        }
    }

    /**
     * Lista todos los pedidos de la base de datos, incluyendo información del cliente asociado.
     * RENOMBRADO para evitar conflicto con Clientes.listarTodos().
     * @return Una lista de objetos Pedidos.
     */
    public static List<Pedidos> listarTodosPedidos() throws SQLException { // <--- CAMBIO DE NOMBRE AQUÍ
        List<Pedidos> listaPedidos = new ArrayList<>();
        String sql = "SELECT p.id_pedido, p.fecha_pedido, p.monto_total, p.estado, p.detalle_items, " +
                "c.id_clientes, c.nombre, c.apellido, c.email, c.telefono, c.direccion, c.fecha_registro " +
                "FROM pedidos p JOIN clientes c ON p.id_clientes = c.id_clientes " +
                "ORDER BY p.fecha_pedido DESC";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idPedido = rs.getInt("id_pedido");
                Date fechaPedido = rs.getTimestamp("fecha_pedido");
                double montoTotal = rs.getDouble("monto_total");
                String estado = rs.getString("estado");
                String detalleItems = rs.getString("detalle_items");

                int idCliente = rs.getInt("id_clientes");
                String nombreCliente = rs.getString("nombre");
                String apellidoCliente = rs.getString("apellido");
                String emailCliente = rs.getString("email");
                String telefonoCliente = rs.getString("telefono");
                String direccionCliente = rs.getString("direccion");
                Date fechaRegistroCliente = rs.getTimestamp("fecha_registro");

                Pedidos pedido = new Pedidos(idCliente, nombreCliente, apellidoCliente, emailCliente,
                        telefonoCliente, direccionCliente, fechaRegistroCliente,
                        idPedido, fechaPedido, montoTotal, estado, detalleItems);
                listaPedidos.add(pedido);
            }
        }
        return listaPedidos;
    }

    @Override
    public String toString() {
        return "Pedido {" +
                "ID=" + id_pedido +
                ", Fecha Pedido=" + fecha_pedido +
                ", Monto Total=" + monto_total +
                ", Estado='" + estado + '\'' +
                ", Detalle Items='" + detalle_items + '\'' +
                "} " + super.toString();
    }
}