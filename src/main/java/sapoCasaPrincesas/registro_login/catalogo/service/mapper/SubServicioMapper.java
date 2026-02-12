package sapoCasaPrincesas.registro_login.catalogo.service.mapper;

import sapoCasaPrincesas.registro_login.catalogo.dto.SubServicioDTO;
import sapoCasaPrincesas.registro_login.catalogo.model.SubServicio;

public class SubServicioMapper {

    // Uso este método para exponer solo la información necesaria del subservicio.
    // También envío el ID de la categoría en lugar de la entidad completa
    // para mantener el DTO liviano y evitar ciclos o datos innecesarios.
    public static SubServicioDTO toDTO(SubServicio s) {
        return new SubServicioDTO(
                s.getId(),
                s.getNombre(),
                s.getPrecio(),
                s.getCategoria() != null ? s.getCategoria().getId() : null
        );
    }
}

