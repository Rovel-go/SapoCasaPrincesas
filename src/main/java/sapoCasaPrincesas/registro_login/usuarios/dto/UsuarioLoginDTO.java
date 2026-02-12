package sapoCasaPrincesas.registro_login.usuarios.dto;

// Uso este DTO exclusivamente para manejar el inicio de sesión.
// Decidí mantenerlo minimalista porque solo necesito el email y la contraseña
// para validar las credenciales sin exponer información adicional del usuario.
public record UsuarioLoginDTO(
        String email,
        String contrasena
) {}




