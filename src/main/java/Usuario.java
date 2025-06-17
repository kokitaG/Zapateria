//CLASE

public class Usuario {
    private int id_usuario;
    private String nombre_usuario;
    private String contrasena;

    //CONSTRUCTORES

    public Usuario() {
    };

    public Usuario(int id_usuario, String nombre_usuario, String contrasena) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.contrasena = contrasena;
    };

    //Getter

    public int getId_usuario() {
        return id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    //Setter


    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}





