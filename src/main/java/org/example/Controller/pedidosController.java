package org.example.Controller;

import org.example.Pedidos;
import java.util.Date;
import java.util.List;
import java.sql.SQLException;

public class pedidosController {

    public List<Pedidos> listarTodos() throws SQLException {

        return Pedidos.listarTodosPedidos();
    }

    public void crearPedido(int id_clientes, Date fecha_pedido, double monto_total, String estado, String detalle_items) throws SQLException {
        Pedidos.crear(id_clientes, fecha_pedido, monto_total, estado, detalle_items);
    }

    public void modificarPedido(int id_pedido, int id_clientes, Date fecha_pedido, double monto_total, String estado, String detalle_items) throws SQLException {
        Pedidos pedidoParaModificar = new Pedidos(id_clientes, fecha_pedido, monto_total, estado, detalle_items);
        pedidoParaModificar.setId_pedido(id_pedido);

        pedidoParaModificar.modificar();
    }
}