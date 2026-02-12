package sapoCasaPrincesas.registro_login.catalogo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapoCasaPrincesas.registro_login.catalogo.model.Salon;
import java.util.List;

@Repository
public interface SalonRepository extends JpaRepository<Salon, Long> {

    // Decidí permitir búsqueda parcial por nombre para que el frontend
    // pueda filtrar salones sin exigir coincidencias exactas.
    List<Salon> findByNombreContainingIgnoreCase(String nombre);
}

