package sapoCasaPrincesas.registro_login.usuarios.dto;

// Uso este DTO para exponer información pública del usuario sin incluir
// datos sensibles como el hash de la contraseña. Me sirve para responder
// al frontend y a Postman de forma segura y consistente.
public record UsuarioDTO(
        Long id,
        String nombre,
        String apellidos,
        String email,
        String rol
) {}



