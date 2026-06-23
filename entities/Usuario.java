package entities;
import enums.Rol;

public class Usuario extends Base {
    private String nombre;
    private String apellido;
    private String mail;
    private String celular;
    private Rol rol;
    private Direccion direccion; // Relación 1 a 1 

    public Usuario(Long id, String nombre, String apellido, String mail, String celular, Rol rol, Direccion direccion) {
        super(id);
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.celular = celular;
        this.rol = rol;
        this.direccion = direccion;
    }

    public String getMail() { return mail; }

    @Override
    public String toString() {
        return "ID: " + getId() + " | " + nombre + " " + apellido + " | Mail: " + mail + " | Dir: " + direccion;
    }
}