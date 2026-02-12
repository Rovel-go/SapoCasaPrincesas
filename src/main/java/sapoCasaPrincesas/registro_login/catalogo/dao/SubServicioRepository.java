package sapoCasaPrincesas.registro_login.catalogo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapoCasaPrincesas.registro_login.catalogo.model.SubServicio;

import java.util.List;

@Repository
public interface SubServicioRepository extends JpaRepository<SubServicio, Long> {

    // Permito búsqueda parcial por nombre para que el frontend pueda filtrar
    // subservicios sin exigir coincidencias exactas.
    List<SubServicio> findByNombreContainingIgnoreCase(String nombre);

    // Uso este método para obtener todos los subservicios asociados a una categoría
    // sin necesidad de cargar la entidad completa de CategoriaServicio.
    List<SubServicio> findByCategoriaId(Long categoriaId);
}




