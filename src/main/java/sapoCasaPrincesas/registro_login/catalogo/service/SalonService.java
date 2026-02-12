package sapoCasaPrincesas.registro_login.catalogo.service;

import org.springframework.stereotype.Service;
import sapoCasaPrincesas.registro_login.catalogo.dao.SalonRepository;
import sapoCasaPrincesas.registro_login.catalogo.model.Salon;

import java.util.List;

@Service
public class SalonService {

    private final SalonRepository repo;

    public SalonService(SalonRepository repo) {
        this.repo = repo;
    }

    // Uso este método para obtener todos los salones sin filtros,
    // ideal para cargar el catálogo completo en el frontend.
    public List<Salon> obtenerTodos() {
        return repo.findAll();
    }

    // Devuelvo null si no existe para que el controlador decida la respuesta HTTP.
    public Salon obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    // Permito búsqueda parcial por nombre para facilitar filtros dinámicos en el frontend.
    public List<Salon> buscarPorNombre(String nombre) {
        return repo.findByNombreContainingIgnoreCase(nombre);
    }

    // Creo un salón sin lógica adicional porque la validación se maneja en el controlador.
    public Salon crear(Salon salon) {
        return repo.save(salon);
    }

    // Actualizo solo los campos enviados para permitir actualizaciones parciales.
    public Salon actualizar(Long id, Salon datos) {
        Salon existente = repo.findById(id).orElse(null);
        if (existente == null) return null;

        if (datos.getNombre() != null) existente.setNombre(datos.getNombre());
        if (datos.getDescripcion() != null) existente.setDescripcion(datos.getDescripcion());
        if (datos.getUbicacion() != null) existente.setUbicacion(datos.getUbicacion());
        if (datos.getFoto() != null) existente.setFoto(datos.getFoto());

        return repo.save(existente);
    }

    // Devuelvo un booleano para que el controlador decida si responde 404 o 200.
    public boolean eliminar(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}
