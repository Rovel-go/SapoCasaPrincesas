package sapoCasaPrincesas.registro_login.catalogo.dto;

// Uso este DTO para exponer solo los datos esenciales de la categoría
// y evitar enviar la entidad completa al frontend.
public record CategoriaServicioDTO(
        Long id,
        String categoria
) {}

