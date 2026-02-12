package sapoCasaPrincesas.registro_login.catalogo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "subservicios")
public class SubServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mantengo este campo simple porque solo necesito el nombre visible del subservicio.
    private String nombre;

    // Decidí usar Double en lugar de double para permitir valores nulos
    // y evitar errores cuando el precio no se envía en el DTO.
    private Double precio;

    // Relación muchos-a-uno: cada subservicio pertenece a una categoría.
    // Uso JoinColumn para mantener la FK explícita en la base de datos.
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaServicio categoria;

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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public CategoriaServicio getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaServicio categoria) {
        this.categoria = categoria;
    }
}

