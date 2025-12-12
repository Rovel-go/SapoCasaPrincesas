package sapoCasaPrincesas.registro_login.usuarios.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sapoCasaPrincesas.registro_login.usuarios.dao.UsuarioDao;
import sapoCasaPrincesas.registro_login.usuarios.model.Usuario;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioDao usuarioDao;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioDao usuarioDao, PasswordEncoder passwordEncoder) {
        this.usuarioDao = usuarioDao;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> obtenerTodos() {
        return usuarioDao.obtenerTodos();
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioDao.obtenerPorId(id);
    }

    public boolean crear(Usuario usuario) {
        try {
            // Validaciones básicas
            if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
                System.out.println("❌ ERROR: El email está vacío.");
                return false;
            }

            if (usuario.getPasswordHash() == null || usuario.getPasswordHash().trim().isEmpty()) {
                System.out.println("❌ ERROR: La contraseña está vacía.");
                return false;
            }

            // Verificar si el email ya existe
            List<Usuario> existentes = usuarioDao.obtenerTodos();
            for (Usuario u : existentes) {
                if (u.getEmail().equalsIgnoreCase(usuario.getEmail())) {
                    System.out.println("❌ ERROR: El email ya está registrado: " + usuario.getEmail());
                    return false;
                }
            }

            // Cifrar la contraseña
            String hash = passwordEncoder.encode(usuario.getPasswordHash());
            usuario.setPasswordHash(hash);

            // Intentar guardar en la BD
            int resultado = usuarioDao.crear(usuario);
            if (resultado > 0) {
                System.out.println("✅ Usuario creado correctamente: " + usuario.getEmail());
                return true;
            } else {
                System.out.println("❌ ERROR: Falló la inserción en la BD.");
                return false;
            }

        } catch (Exception e) {
            System.out.println("❌ ERROR inesperado al crear usuario:");
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Usuario usuario) {
        try {
            if (usuario.getId() == null) {
                System.out.println("❌ ERROR: No se puede actualizar sin ID.");
                return false;
            }

            if (usuario.getPasswordHash() == null || usuario.getPasswordHash().trim().isEmpty()) {
                System.out.println("❌ ERROR: La contraseña está vacía.");
                return false;
            }

            String hash = passwordEncoder.encode(usuario.getPasswordHash());
            usuario.setPasswordHash(hash);

            int resultado = usuarioDao.actualizar(usuario);
            if (resultado > 0) {
                System.out.println("✅ Usuario actualizado correctamente: " + usuario.getEmail());
                return true;
            } else {
                System.out.println("❌ ERROR: No se encontró usuario con ID " + usuario.getId());
                return false;
            }

        } catch (Exception e) {
            System.out.println("❌ ERROR al actualizar usuario:");
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(Long id) {
        try {
            int resultado = usuarioDao.eliminar(id);
            if (resultado > 0) {
                System.out.println("✅ Usuario eliminado con ID: " + id);
                return true;
            } else {
                System.out.println("❌ ERROR: No se encontró usuario con ID " + id);
                return false;
            }
        } catch (Exception e) {
            System.out.println("❌ ERROR al eliminar usuario con ID: " + id);
            e.printStackTrace();
            return false;
        }
    }

    public Usuario login(String email, String rawPassword) {
        try {
            List<Usuario> usuarios = usuarioDao.obtenerTodos();
            for (Usuario u : usuarios) {
                if (u.getEmail().equalsIgnoreCase(email) &&
                        passwordEncoder.matches(rawPassword, u.getPasswordHash())) {
                    System.out.println("✅ Login exitoso para: " + email);
                    return u;
                }
            }
            System.out.println("❌ Login fallido para: " + email);
            return null;
        } catch (Exception e) {
            System.out.println("❌ ERROR en login:");
            e.printStackTrace();
            return null;
        }
    }
}

















