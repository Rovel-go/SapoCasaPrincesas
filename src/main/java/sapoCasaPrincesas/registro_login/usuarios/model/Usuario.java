package sapoCasaPrincesas.registro_login.usuarios.model;

public class Usuario {

    private Long id;
    private String nombre;
    private String apellidos;
    private String email;

    // Campo que recibe la contraseña en texto plano desde el formulario
    private String contrasena;

    // Campo que se guarda en la BD (contraseña cifrada con BCrypt)
    private String passwordHash;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Contraseña en texto plano (solo se usa al recibir datos del formulario)
    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
        // También la asignamos a passwordHash para que luego se cifre en el Service
        this.passwordHash = contrasena;
    }

    // Contraseña cifrada (se guarda en la BD)
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}












