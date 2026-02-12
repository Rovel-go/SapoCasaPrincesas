package sapoCasaPrincesas.registro_login.catalogo.service;

import org.springframework.stereotype.Service;
import sapoCasaPrincesas.registro_login.catalogo.dao.ColaboradorRepository;
import sapoCasaPrincesas.registro_login.catalogo.model.Colaborador;

import java.util.List;

@Service
public class ColaboradorService {

    private final ColaboradorRepository repo;

    public ColaboradorService(ColaboradorRepository repo) {
        this.repo = repo;
    }

    // Uso este método para obtener todos los colaboradores sin filtros,
    // ideal para cargar el catálogo completo en el frontend.
    public List<Colaborador> obtenerTodos() {
        return repo.findAll();
    }

    // Devuelvo null si no existe para que el controlador decida la respuesta HTTP.
    public Colaborador obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    // Permito búsqueda parcial por nombre para facilitar filtros dinámicos en el frontend.
    public List<Colaborador> buscarPorNombre(String nombre) {
        return repo.findByNombreContainingIgnoreCase(nombre);
    }

    // Creo un colaborador sin lógica adicional porque la validación se maneja en el controlador.
    public Colaborador crear(Colaborador c) {
        return repo.save(c);
    }

    // Actualizo solo los campos enviados para permitir actualizaciones parciales.
    public Colaborador actualizar(Long id, Colaborador datos) {
        Colaborador existente = repo.findById(id).orElse(null);
        if (existente == null) return null;

        if (datos.getNombre() != null) existente.setNombre(datos.getNombre());
        if (datos.getRol() != null) existente.setRol(datos.getRol());
        if (datos.getEspecialidad() != null) existente.setEspecialidad(datos.getEspecialidad());
        if (datos.getExperiencia() != null) existente.setExperiencia(datos.getExperiencia());
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




