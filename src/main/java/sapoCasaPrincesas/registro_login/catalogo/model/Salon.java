package sapoCasaPrincesas.registro_login.catalogo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "salones")
public class Salon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mantengo el nombre simple porque es el identificador visible del salón.
    private String nombre;

    // Uso este campo para indicar la ciudad o zona general donde está ubicado el salón.
    private String ubicacion;

    // Guardo la dirección exacta para mostrarla en el catálogo o en detalles del salón.
    private String direccion;

    // Agregué este campo para que coincida con la estructura real en MySQL
    // y así evitar inconsistencias entre el modelo y la base de datos.
    private String descripcion;

    // Decidí almacenar solo la URL de la foto para simplificar la evidencia
    // y evitar manejar archivos binarios.
    private String foto;

    // Constructor vacío requerido por JPA.
    public Salon() {}

    // Constructor completo para facilitar la creación manual desde el servicio o pruebas.
    public Salon(Long id, String nombre, String descripcion, String ubicacion, String direccion, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.foto = foto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}

