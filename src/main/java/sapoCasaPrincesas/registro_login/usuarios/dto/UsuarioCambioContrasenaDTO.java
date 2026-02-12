package sapoCasaPrincesas.registro_login.usuarios.dto;

// Uso este DTO exclusivamente para manejar el cambio de contraseña.
// Decidí mantenerlo simple porque solo necesito estos tres datos
// y no quiero exponer información adicional del usuario.
public record UsuarioCambioContrasenaDTO(
        String email,
        String actual,
        String nueva
) {}




