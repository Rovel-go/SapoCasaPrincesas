package sapoCasaPrincesas.registro_login.catalogo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "colaboradores")
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mantengo este campo simple porque solo necesito el nombre público del colaborador.
    private String nombre;

    // Uso este campo para identificar el rol dentro del salón (ej: estilista, maquilladora).
    private String rol;

    // Guardo la especialidad para mostrarla en el catálogo sin necesidad de cálculos adicionales.
    private String especialidad;

    // Este campo me permite describir la experiencia del colaborador de forma libre.
    private String experiencia;

    // Decidí almacenar la URL de la foto en lugar del archivo para simplificar la evidencia.
    private String foto;

    // Constructor vacío requerido por JPA.
    public Colaborador() {}

    // Constructor completo para facilitar la creación manual desde el servicio o pruebas.
    public Colaborador(Long id, String nombre, String rol, String especialidad, String experiencia, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
        this.especialidad = especialidad;
        this.experiencia = experiencia;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}

