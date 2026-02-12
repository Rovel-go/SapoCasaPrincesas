package sapoCasaPrincesas.registro_login.catalogo.service.mapper;

import sapoCasaPrincesas.registro_login.catalogo.dto.CategoriaServicioDTO;
import sapoCasaPrincesas.registro_login.catalogo.model.CategoriaServicio;

public class CategoriaServicioMapper {

    // Uso este método para convertir la entidad a DTO y así evitar exponer
    // relaciones internas como la lista de subservicios.
    public static CategoriaServicioDTO toDTO(CategoriaServicio c) {
        return new CategoriaServicioDTO(
                c.getId(),
                c.getCategoria()
        );
    }
}



