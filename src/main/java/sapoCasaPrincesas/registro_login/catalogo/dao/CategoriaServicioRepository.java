package sapoCasaPrincesas.registro_login.catalogo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapoCasaPrincesas.registro_login.catalogo.model.CategoriaServicio;

import java.util.List;

@Repository
public interface CategoriaServicioRepository extends JpaRepository<CategoriaServicio, Long> {

    // Decidí usar búsqueda parcial e ignorando mayúsculas para permitir
    // que el frontend filtre categorías sin necesidad de coincidencias exactas.
    List<CategoriaServicio> findByCategoriaContainingIgnoreCase(String categoria);
}








