package org.example.Controller;

import org.example.Compras;
import java.util.Date;
import java.util.List;
import java.sql.SQLException;

public class ComprasController {

    public List<Compras> listarTodas() throws SQLException {
        return Compras.listarTodas();
    }

    public void crearCompra(int id_proveedor, Date fecha_compra, double monto_total_compra, String estado_compra, String detalles_items) throws SQLException {
        Compras.crear(id_proveedor, fecha_compra, monto_total_compra, estado_compra, detalles_items);
    }

    public void modificarCompra(int id_compra, int id_proveedor, Date fecha_compra, double monto_total_compra, String estado_compra, String detalles_items) throws SQLException {

        Compras compraParaModificar = new Compras(id_proveedor, fecha_compra, monto_total_compra, estado_compra, detalles_items);
        compraParaModificar.setId_compra(id_compra); // Establecemos el ID de la compra a modificar.

        compraParaModificar.modificar();
    }
}