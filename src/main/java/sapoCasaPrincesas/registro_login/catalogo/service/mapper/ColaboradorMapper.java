package sapoCasaPrincesas.registro_login.catalogo.service.mapper;

import sapoCasaPrincesas.registro_login.catalogo.dto.ColaboradorDTO;
import sapoCasaPrincesas.registro_login.catalogo.model.Colaborador;

public class ColaboradorMapper {

    // Uso este método para exponer solo la información necesaria del colaborador,
    // evitando enviar la entidad completa y manteniendo el catálogo más liviano.
    public static ColaboradorDTO toDTO(Colaborador c) {
        return new ColaboradorDTO(
                c.getId(),
                c.getNombre(),
                c.getRol(),
                c.getEspecialidad(),
                c.getExperiencia(),
                c.getFoto()
        );
    }
}


