package sapoCasaPrincesas.registro_login.catalogo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapoCasaPrincesas.registro_login.catalogo.dto.SalonDTO;
import sapoCasaPrincesas.registro_login.catalogo.model.Salon;
import sapoCasaPrincesas.registro_login.catalogo.service.SalonService;
import sapoCasaPrincesas.registro_login.catalogo.service.mapper.SalonMapper;

import java.util.List;

@RestController
@RequestMapping("/salones")
@CrossOrigin(origins = "http://localhost:5173")
public class SalonController {

    private final SalonService service;

    public SalonController(SalonService service) {
        this.service = service;
    }

    // Uso este endpoint para listar todos los salones visibles en el catálogo.
    @GetMapping
    public ResponseEntity<List<SalonDTO>> obtenerTodos() {
        List<SalonDTO> lista = service.obtenerTodos()
                .stream()
                .map(SalonMapper::toDTO)
                .toList();

        return ResponseEntity.ok(lista);
    }

    // Devuelvo 404 si el salón no existe para mantener respuestas claras al frontend.
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Salon salon = service.obtenerPorId(id);

        if (salon == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"mensaje\": \"Salón inexistente\"}");
        }

        return ResponseEntity.ok(SalonMapper.toDTO(salon));
    }

    // Permito búsqueda parcial por nombre para facilitar filtros dinámicos en el cliente.
    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable String nombre) {

        List<SalonDTO> lista = service.buscarPorNombre(nombre)
                .stream()
                .map(SalonMapper::toDTO)
                .toList();

        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"mensaje\": \"Salón inexistente\"}");
        }

        return ResponseEntity.ok(lista);
    }

    // Creo un salón a partir del DTO para evitar exponer la entidad directamente.
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody SalonDTO dto) {

        Salon nuevo = new Salon(
                null,
                dto.nombre(),
                dto.descripcion(),
                dto.ubicacion(),
                dto.direccion(),
                dto.foto()
        );

        Salon guardado = service.crear(nuevo);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(SalonMapper.toDTO(guardado));
    }

    // Actualizo solo los campos enviados en el DTO para permitir modificaciones parciales.
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody SalonDTO dto) {

        Salon datos = new Salon(
                null,
                dto.nombre(),
                dto.descripcion(),
                dto.ubicacion(),
                dto.direccion(),
                dto.foto()
        );

        Salon actualizado = service.actualizar(id, datos);

        if (actualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"mensaje\": \"Salón inexistente\"}");
        }

        return ResponseEntity.ok(SalonMapper.toDTO(actualizado));
    }

    // Devuelvo un mensaje claro para que el frontend pueda mostrar confirmación al usuario.
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {

        boolean eliminado = service.eliminar(id);

        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"mensaje\": \"Salón inexistente\"}");
        }

        return ResponseEntity.ok("{\"mensaje\": \"Salón eliminado correctamente\"}");
    }
}

