package sapoCasaPrincesas.registro_login.catalogo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapoCasaPrincesas.registro_login.catalogo.dto.ColaboradorDTO;
import sapoCasaPrincesas.registro_login.catalogo.model.Colaborador;
import sapoCasaPrincesas.registro_login.catalogo.service.ColaboradorService;
import sapoCasaPrincesas.registro_login.catalogo.service.mapper.ColaboradorMapper;

import java.util.List;

@RestController
@RequestMapping("/colaboradores")
@CrossOrigin(origins = "http://localhost:5173")
public class ColaboradoresController {

    private final ColaboradorService service;

    public ColaboradoresController(ColaboradorService service) {
        this.service = service;
    }

    // Uso este endpoint para listar todos los colaboradores visibles en el catálogo.
    @GetMapping
    public ResponseEntity<List<ColaboradorDTO>> obtenerTodos() {
        List<ColaboradorDTO> lista = service.obtenerTodos()
                .stream()
                .map(ColaboradorMapper::toDTO)
                .toList();

        return ResponseEntity.ok(lista);
    }

    // Devuelvo 404 si el colaborador no existe para mantener respuestas claras al frontend.
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Colaborador col = service.obtenerPorId(id);

        if (col == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"mensaje\": \"Colaborador inexistente\"}");
        }

        return ResponseEntity.ok(ColaboradorMapper.toDTO(col));
    }

    // Permito búsqueda parcial por nombre para facilitar filtros dinámicos en el cliente.
    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable String nombre) {

        List<ColaboradorDTO> lista = service.buscarPorNombre(nombre)
                .stream()
                .map(ColaboradorMapper::toDTO)
                .toList();

        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"mensaje\": \"Colaborador inexistente\"}");
        }

        return ResponseEntity.ok(lista);
    }

    // Creo un colaborador a partir del DTO para evitar exponer la entidad directamente.
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody ColaboradorDTO dto) {

        Colaborador nuevo = new Colaborador(
                null,
                dto.nombre(),
                dto.rol(),
                dto.especialidad(),
                dto.experiencia(),
                dto.foto()
        );

        Colaborador guardado = service.crear(nuevo);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ColaboradorMapper.toDTO(guardado));
    }

    // Actualizo solo los campos enviados en el DTO para permitir modificaciones parciales.
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody ColaboradorDTO dto) {

        Colaborador datos = new Colaborador(
                null,
                dto.nombre(),
                dto.rol(),
                dto.especialidad(),
                dto.experiencia(),
                dto.foto()
        );

        Colaborador actualizado = service.actualizar(id, datos);

        if (actualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"mensaje\": \"Colaborador inexistente\"}");
        }

        return ResponseEntity.ok(ColaboradorMapper.toDTO(actualizado));
    }

    // Devuelvo un mensaje claro para que el frontend pueda mostrar confirmación al usuario.
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {

        boolean eliminado = service.eliminar(id);

        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"mensaje\": \"Colaborador inexistente\"}");
        }

        return ResponseEntity.ok("{\"mensaje\": \"Colaborador eliminado correctamente\"}");
    }
}

