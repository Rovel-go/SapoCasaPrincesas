package sapoCasaPrincesas.registro_login.usuarios.dto;

// Uso este DTO para recibir los datos necesarios en el proceso de registro.
// Decidí mantenerlo minimalista porque solo necesito esta información para
// crear un usuario nuevo sin exponer campos internos del modelo.
public record UsuarioRegistroDTO(
        String nombre,
        String apellidos,
        String email,
        String contrasena
) {}




