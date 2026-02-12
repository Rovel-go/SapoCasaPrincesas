package sapoCasaPrincesas.registro_login.catalogo.dto;

// Uso este DTO para exponer solo los datos esenciales del subservicio,
// incluyendo el ID de la categoría para evitar enviar la entidad completa.
public record SubServicioDTO(
        Long id,
        String nombre,
        Double precio,
        Long categoriaId
) {}
