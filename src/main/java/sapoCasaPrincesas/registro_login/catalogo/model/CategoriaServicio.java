package sapoCasaPrincesas.registro_login.catalogo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorias_servicio")
public class CategoriaServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mantengo este campo obligatorio porque cada categoría debe tener un nombre único y claro.
    @Column(nullable = false)
    private String categoria;

    // Decidí ignorar esta lista en JSON para evitar ciclos infinitos y respuestas demasiado grandes.
    // Además, los subservicios se consultan desde su propio endpoint.
    @JsonIgnore
    @OneToMany(
            mappedBy = "categoria",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<SubServicio> subservicios = new ArrayList<>();

    public CategoriaServicio() {}

    // Uso este constructor para crear categorías rápidamente desde el servicio.
    public CategoriaServicio(String categoria) {
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<SubServicio> getSubservicios() {
        return subservicios;
    }

    public void setSubservicios(List<SubServicio> subservicios) {
        this.subservicios = subservicios;
    }

    // Agrego este método para mantener la relación bidireccional consistente.
    // Cuando agrego un subservicio, también actualizo su referencia a la categoría.
    public void agregarSubservicio(SubServicio sub) {
        subservicios.add(sub);
        sub.setCategoria(this);
    }
}
