package org.example;

import java.util.Date;

public class Clientes {

    int id_clientes;
    String nombre;
    String apellido;
    String email;
    String telefono;
    String direccion;
    Date fecha_registro;

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

    //Getter

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

    //Setter

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
}
