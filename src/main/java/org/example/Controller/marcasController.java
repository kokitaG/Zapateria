package org.example.Controller;

import org.example.Marcas; // Importa la clase Marcas
import java.util.List;
import java.sql.SQLException;

public class marcasController {


    public List<Marcas> listarTodas() throws SQLException {
        // Llama al método estático listarTodas de la clase Marcas
        return Marcas.listarTodas();
    }


    public void crearMarca(String nombre_marca) throws SQLException {
        // Llama al método estático crear de la clase Marcas
        Marcas.crear(nombre_marca);
    }


    public void modificarMarca(int id_marca, String nuevo_nombre_marca) throws SQLException {
        // Para el método de instancia 'modificar', necesitamos crear un objeto Marcas
        // con los datos a actualizar y el ID de la marca.
        Marcas marcaParaModificar = new Marcas(id_marca, nuevo_nombre_marca);

        marcaParaModificar.modificar(); // Llama al método de instancia modificar
    }
}