package sapoCasaPrincesas.registro_login.usuarios.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sapoCasaPrincesas.registro_login.usuarios.model.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UsuarioDao {

    private final JdbcTemplate jdbcTemplate;

    public UsuarioDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Usuario> usuarioMapper = new RowMapper<Usuario>() {
        @Override
        public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
            Usuario u = new Usuario();
            u.setId(rs.getLong("id"));
            u.setNombre(rs.getString("nombre"));
            u.setApellidos(rs.getString("apellidos"));
            u.setEmail(rs.getString("email"));
            u.setPasswordHash(rs.getString("password_hash"));
            return u;
        }
    };

    public List<Usuario> obtenerTodos() {
        String sql = "SELECT id, nombre, apellidos, email, password_hash FROM usuarios";
        return jdbcTemplate.query(sql, usuarioMapper);
    }

    public Usuario obtenerPorId(Long id) {
        String sql = "SELECT id, nombre, apellidos, email, password_hash FROM usuarios WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, usuarioMapper, id);
    }

    public int crear(Usuario usuario) {
        try {
            String sql = "INSERT INTO usuarios (nombre, apellidos, email, password_hash) VALUES (?, ?, ?, ?)";
            return jdbcTemplate.update(sql,
                    usuario.getNombre(),
                    usuario.getApellidos(),
                    usuario.getEmail(),
                    usuario.getPasswordHash());
        } catch (Exception e) {
            System.out.println("ERROR en UsuarioDao.crear: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    public int actualizar(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre = ?, apellidos = ?, email = ?, password_hash = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                usuario.getNombre(),
                usuario.getApellidos(),
                usuario.getEmail(),
                usuario.getPasswordHash(),
                usuario.getId());
    }

    public int eliminar(Long id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}







