package sapoCasaPrincesas.registro_login.catalogo.dto;

// Uso este DTO para enviar al frontend solo la información necesaria del salón,
// evitando exponer la entidad completa y manteniendo el catálogo más liviano.
public record SalonDTO(
        Long id,
        String nombre,
        String ubicacion,
        String direccion,
        String descripcion,
        String foto
) {}

