package sapoCasaPrincesas.registro_login.catalogo.dto;

// Uso este DTO para enviar al frontend solo la información necesaria del colaborador,
// evitando exponer la entidad completa y manteniendo el catálogo más liviano.
public record ColaboradorDTO(
        Long id,
        String nombre,
        String rol,
        String especialidad,
        String experiencia,
        String foto
) {}
