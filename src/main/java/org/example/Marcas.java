package org.example;

public class Marcas {
    int id_marca;
    String nombre_marca;

    //Constructores


    public Marcas(int id_marca) {
    }

    public Marcas() {
        this.id_marca = id_marca;
        this.nombre_marca = nombre_marca;
    }

    //Getter

    public int getId_marca() {
        return id_marca;
    }

    public String getNombre_marca() {
        return nombre_marca;
    }

    //Setter

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public void setNombre_marca(String nombre_marca) {
        this.nombre_marca = nombre_marca;
    }

}
