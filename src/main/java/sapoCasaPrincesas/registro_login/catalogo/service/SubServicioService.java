package sapoCasaPrincesas.registro_login.catalogo.service;

import org.springframework.stereotype.Service;
import sapoCasaPrincesas.registro_login.catalogo.dao.SubServicioRepository;
import sapoCasaPrincesas.registro_login.catalogo.dao.CategoriaServicioRepository;
import sapoCasaPrincesas.registro_login.catalogo.model.SubServicio;
import sapoCasaPrincesas.registro_login.catalogo.model.CategoriaServicio;

import java.util.List;

@Service
public class SubServicioService {

    private final SubServicioRepository repo;
    private final CategoriaServicioRepository categoriaRepo;

    public SubServicioService(SubServicioRepository repo, CategoriaServicioRepository categoriaRepo) {
        this.repo = repo;
        this.categoriaRepo = categoriaRepo;
    }

    // Uso este método para obtener todos los subservicios sin filtros,
    // ideal para cargar el catálogo completo en el frontend.
    public List<SubServicio> obtenerTodos() {
        return repo.findAll();
    }

    // Devuelvo null si no existe para que el controlador decida la respuesta HTTP.
    public SubServicio obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    // Permito búsqueda parcial por nombre para facilitar filtros dinámicos en el frontend.
    public List<SubServicio> buscarPorNombre(String nombre) {
        return repo.findByNombreContainingIgnoreCase(nombre);
    }

    // Creo un subservicio asociándolo directamente por ID de categoría.
    // Si la categoría no existe, devuelvo null para que el controlador responda 404.
    public SubServicio crear(Long categoriaId, SubServicio datos) {
        CategoriaServicio categoria = categoriaRepo.findById(categoriaId).orElse(null);
        if (categoria == null) return null;

        datos.setCategoria(categoria);
        return repo.save(datos);
    }

    // Variante de creación usando el nombre de la categoría.
    // Tomo la primera coincidencia porque la búsqueda es parcial.
    public SubServicio crear(String nombreCategoria, SubServicio datos) {
        List<CategoriaServicio> categorias = categoriaRepo.findByCategoriaContainingIgnoreCase(nombreCategoria);
        if (categorias.isEmpty()) return null;

        CategoriaServicio categoria = categorias.get(0);
        datos.setCategoria(categoria);

        return repo.save(datos);
    }

    // Actualizo solo los campos enviados para permitir actualizaciones parciales.
    public SubServicio actualizar(Long id, SubServicio datos) {
        SubServicio existente = repo.findById(id).orElse(null);
        if (existente == null) return null;

        if (datos.getNombre() != null) {
            existente.setNombre(datos.getNombre());
        }

        // Permití precio 0 porque algunos servicios pueden ser gratuitos o promocionales.
        if (datos.getPrecio() != null && datos.getPrecio() >= 0) {
            existente.setPrecio(datos.getPrecio());
        }

        return repo.save(existente);
    }

    // Devuelvo un booleano para que el controlador decida si responde 404 o 200.
    public boolean eliminar(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }

    // Uso este método para obtener todos los subservicios de una categoría específica.
    public List<SubServicio> obtenerPorCategoria(Long categoriaId) {
        return repo.findByCategoriaId(categoriaId);
    }
}
