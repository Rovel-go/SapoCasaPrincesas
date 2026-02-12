package sapoCasaPrincesas.registro_login.catalogo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapoCasaPrincesas.registro_login.catalogo.model.Colaborador;
import java.util.List;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

    // Decidí permitir búsqueda parcial por nombre para que el frontend
    // pueda filtrar colaboradores sin necesidad de coincidencias exactas.
    List<Colaborador> findByNombreContainingIgnoreCase(String nombre);
}



