package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// La clase Productos ya es pública, ¡bien!
public class Productos extends Marcas {
    private int id_producto;
    private String nombre_producto;
    private String descripcion;
    private String color;
    private String Talla; // Cuidado con la capitalización, por convención Java sería 'talla'
    private String SKU;
    private int cntidad_stock; // Cuidado con el typo, debería ser 'cantidad_stock'
    private double precio_venta;

    // Constructores

    public Productos() {
        super();
    }

    // Constructor completo que incluye id_marca para la superclase
    public Productos(int id_marca, int id_producto, String nombre_producto, String descripcion, String color, String talla, String SKU, int cntidad_stock, double precio_venta) {
        super(id_marca); // Llama al constructor de Marcas para establecer el id_marca
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.descripcion = descripcion;
        this.color = color;
        this.Talla = talla;
        this.SKU = SKU;
        this.cntidad_stock = cntidad_stock;
        this.precio_venta = precio_venta;
    }

    // Constructor para operaciones donde solo se necesita el ID del producto
    public Productos(int id_producto) {
        this.id_producto = id_producto;
    }

    // Constructor para el método estático 'crear'
    public Productos(int id_marca, String nombre_producto, String descripcion, String color, String talla, String SKU, int cntidad_stock, double precio_venta) {
        super(id_marca);
        this.nombre_producto = nombre_producto;
        this.descripcion = descripcion;
        this.color = color;
        this.Talla = talla;
        this.SKU = SKU;
        this.cntidad_stock = cntidad_stock;
        this.precio_venta = precio_venta;
    }


    // Getters

    public int getId_producto() {
        return id_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getColor() {
        return color;
    }

    public String getTalla() {
        return Talla;
    }

    public String getSKU() {
        return SKU;
    }

    public int getCntidad_stock() {
        return cntidad_stock;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    // Setter

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setTalla(String talla) {
        Talla = talla;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public void setCntidad_stock(int cntidad_stock) {
        this.cntidad_stock = cntidad_stock;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }


    public static void crear(int id_marca, String nombre_producto, String descripcion, String color, String talla, String SKU, int cntidad_stock, double precio_venta) throws SQLException {
        if (id_marca == 0) { // Validar id_marca directamente
            throw new SQLException("El ID de la marca no puede ser 0 al crear un producto.");
        }

        // Asegúrate de que el nombre de la columna en tu DB sea 'cantidad_stock' si corregiste el typo
        String sql = "INSERT INTO productos (nombre_producto, descripcion, color, talla, SKU, cantidad_stock, precio_venta, id_marca) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre_producto);
            ps.setString(2, descripcion);
            ps.setString(3, color);
            ps.setString(4, talla); // Usamos 'talla' como parámetro
            ps.setString(5, SKU);
            ps.setInt(6, cntidad_stock);
            ps.setDouble(7, precio_venta);
            ps.setInt(8, id_marca);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Producto '" + nombre_producto + "' insertado exitosamente con marca ID " + id_marca + ".");
            } else {
                System.out.println("No se pudo insertar el producto '" + nombre_producto + "'.");
            }
        }
    }


    public void modificar() throws SQLException {
        if (this.id_producto == 0) {
            throw new SQLException("El ID del producto no puede ser 0 al modificar.");
        }

        String sql = "UPDATE productos SET nombre_producto = ?, descripcion = ?, color = ?, talla = ?, SKU = ?, cantidad_stock = ?, precio_venta = ?, id_marca = ? WHERE id_producto = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, this.nombre_producto);
            ps.setString(2, this.descripcion);
            ps.setString(3, this.color);
            ps.setString(4, this.Talla);
            ps.setString(5, this.SKU);
            ps.setInt(6, this.cntidad_stock);
            ps.setDouble(7, this.precio_venta);
            ps.setInt(8, super.getId_marca()); // Usa el id_marca heredado de la clase Marcas
            ps.setInt(9, this.id_producto);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Producto con ID " + this.id_producto + " modificado exitosamente.");
            } else {
                System.out.println("No se encontró el producto con ID " + this.id_producto + " para modificar o no hubo cambios.");
            }
        }
    }


    public static List<Productos> listarTodos() throws SQLException { // <-- RENOMBRADO Y ES STATIC
        List<Productos> listaProductos = new ArrayList<>();
        // Un JOIN es necesario para obtener el nombre de la marca si no lo almacenas en Productos
        String sql = "SELECT p.id_producto, p.nombre_producto, p.descripcion, p.color, p.talla, p.SKU, p.cantidad_stock, p.precio_venta, p.id_marca, m.nombre_marca " +
                "FROM productos p JOIN marcas m ON p.id_marca = m.id_marca";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Productos producto = new Productos();
                producto.setId_producto(rs.getInt("id_producto"));
                producto.setNombre_producto(rs.getString("nombre_producto"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setColor(rs.getString("color"));
                producto.setTalla(rs.getString("talla"));
                producto.setSKU(rs.getString("SKU"));
                producto.setCntidad_stock(rs.getInt("cantidad_stock"));
                producto.setPrecio_venta(rs.getDouble("precio_venta"));
                // Establece el id_marca y nombre_marca del objeto Marcas padre
                producto.setId_marca(rs.getInt("id_marca"));
                producto.setNombre_marca(rs.getString("nombre_marca")); // Asigna el nombre de la marca

                listaProductos.add(producto);
            }
        }
        return listaProductos;
    }

    @Override
    public String toString() {
        return "Producto {" +
                "ID=" + id_producto +
                ", Nombre='" + nombre_producto + '\'' +
                ", Descripción='" + descripcion + '\'' +
                ", Color='" + color + '\'' +
                ", Talla='" + Talla + '\'' +
                ", SKU='" + SKU + '\'' +
                ", Stock=" + cntidad_stock +
                ", Precio Venta=" + precio_venta +
                ", Marca [ID=" + super.getId_marca() + ", Nombre='" + super.getNombre_marca() + "']" + // Muestra la marca
                '}';
    }
}