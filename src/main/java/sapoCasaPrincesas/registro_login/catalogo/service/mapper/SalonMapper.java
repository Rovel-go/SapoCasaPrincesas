package sapoCasaPrincesas.registro_login.catalogo.service.mapper;

import sapoCasaPrincesas.registro_login.catalogo.dto.SalonDTO;
import sapoCasaPrincesas.registro_login.catalogo.model.Salon;

public class SalonMapper {

    // Uso este método para convertir la entidad a DTO y así evitar exponer
    // detalles internos del modelo que no necesito en las respuestas del catálogo.
    public static SalonDTO toDTO(Salon s) {
        if (s == null) return null;

        return new SalonDTO(
                s.getId(),
                s.getNombre(),
                s.getUbicacion(),
                s.getDireccion(),
                s.getDescripcion(),
                s.getFoto()
        );
    }
}
