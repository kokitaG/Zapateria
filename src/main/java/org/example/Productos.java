package org.example;
class Productos extends Marcas {

    //public class Productos {

        int id_producto;
        String nombre_producto;
        String descripcion;
        String color;
        String Talla;
        String SKU;
        int cntidad_stock;
        double precio_venta;

        //Constructores


        public Productos() {
            super();
        }

    public Productos(int id_marca, int id_producto, String nombre_producto, String descripcion, String color, String talla, String SKU, int cntidad_stock, double precio_venta) {
        super(id_marca);
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.descripcion = descripcion;
        this.color = color;
        Talla = talla;
        this.SKU = SKU;
        this.cntidad_stock = cntidad_stock;
        this.precio_venta = precio_venta;
    }
//Getter

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


//Setter


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
}


