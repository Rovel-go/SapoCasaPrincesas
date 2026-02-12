package sapoCasaPrincesas.registro_login.catalogo.service;

import org.springframework.stereotype.Service;
import sapoCasaPrincesas.registro_login.catalogo.dao.CategoriaServicioRepository;
import sapoCasaPrincesas.registro_login.catalogo.model.CategoriaServicio;

import java.util.List;

@Service
public class CategoriaServicioService {

    private final CategoriaServicioRepository repo;

    public CategoriaServicioService(CategoriaServicioRepository repo) {
        this.repo = repo;
    }

    // Uso este método para obtener todas las categorías sin filtros,
    // ideal para cargar el catálogo completo en el frontend.
    public List<CategoriaServicio> obtenerTodas() {
        return repo.findAll();
    }

    // Mantengo este nombre porque coincide con el controlador y permite
    // búsquedas parciales ignorando mayúsculas/minúsculas.
    public List<CategoriaServicio> obtenerPorNombre(String nombre) {
        return repo.findByCategoriaContainingIgnoreCase(nombre);
    }

    // Devuelvo null si no existe para que el controlador decida la respuesta HTTP.
    public CategoriaServicio obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    // Uso este método para crear nuevas categorías sin lógica adicional.
    public CategoriaServicio crear(CategoriaServicio categoria) {
        return repo.save(categoria);
    }

    // Actualizo solo los campos enviados para permitir actualizaciones parciales.
    public CategoriaServicio actualizar(Long id, CategoriaServicio datos) {
        CategoriaServicio existente = repo.findById(id).orElse(null);
        if (existente == null) return null;

        if (datos.getCategoria() != null) {
            existente.setCategoria(datos.getCategoria());
        }

        return repo.save(existente);
    }

    // Devuelvo un booleano para que el controlador decida si responde 404 o 200.
    public boolean eliminar(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}










