package sapoCasaPrincesas.registro_login.usuarios.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // He decidido exponer este campo como "nombre" en JSON para mantener consistencia con el frontend.
    @JsonProperty("nombre")
    @Column(nullable = false)
    private String nombre;

    // Mantengo "apellidos" obligatorio porque lo uso para mostrar el nombre completo del usuario.
    @JsonProperty("apellidos")
    @Column(nullable = false)
    private String apellidos;

    // El email es único porque lo utilizo como identificador principal del usuario.
    @JsonProperty("email")
    @Column(nullable = false, unique = true)
    private String email;

    // Oculto el hash de la contraseña para evitar exponer información sensible en las respuestas JSON.
    @JsonIgnore
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    // Este campo solo lo uso para recibir la contraseña en texto plano al registrar o cambiar contraseña.
    // No se guarda en la base de datos.
    @Transient
    @JsonProperty("contrasena")
    private String contrasena;

    // El rol me permite diferenciar permisos (admin / cliente) dentro del sistema.
    @Column(nullable = false)
    private String rol;

    public Usuario() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}