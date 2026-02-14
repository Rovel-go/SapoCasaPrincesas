package sapoCasaPrincesas.registro_login.catalogo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapoCasaPrincesas.registro_login.catalogo.dto.ColaboradorDTO;
import sapoCasaPrincesas.registro_login.catalogo.model.Colaborador;
import sapoCasaPrincesas.registro_login.catalogo.service.ColaboradorService;
import sapoCasaPrincesas.registro_login.catalogo.service.mapper.ColaboradorMapper;
import sapoCasaPrincesas.registro_login.config.AdminKeyValidator;

import java.util.List;

@RestController
@RequestMapping("/colaboradores")
@CrossOrigin(origins = "http://localhost:5173")
public class ColaboradoresController {

    private final ColaboradorService service;
    private final AdminKeyValidator adminKeyValidator;

    public ColaboradoresController(ColaboradorService service, AdminKeyValidator adminKeyValidator) {
        this.service = service;
        this.adminKeyValidator = adminKeyValidator;
    }

    // GET — Público
    @GetMapping
    public ResponseEntity<List<ColaboradorDTO>> obtenerTodos() {
        List<ColaboradorDTO> lista = service.obtenerTodos()
                .stream()
                .map(ColaboradorMapper::toDTO)
                .toList();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Colaborador col = service.obtenerPorId(id);

        if (col == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"mensaje\": \"Colaborador inexistente\"}");
        }

        return ResponseEntity.ok(ColaboradorMapper.toDTO(col));
    }

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

    // POST — ADMIN
    @PostMapping
    public ResponseEntity<?> crear(
            @RequestHeader(value = "SAPO-ADMIN-KEY", required = false) String adminKey,
            @RequestBody ColaboradorDTO dto) {

        if (!adminKeyValidator.isValid(adminKey)) {
            return ResponseEntity.status(403).body("{\"mensaje\": \"Acceso denegado\"}");
        }

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

    // PUT — ADMIN
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @RequestHeader(value = "SAPO-ADMIN-KEY", required = false) String adminKey,
            @PathVariable Long id,
            @RequestBody ColaboradorDTO dto) {

        if (!adminKeyValidator.isValid(adminKey)) {
            return ResponseEntity.status(403).body("{\"mensaje\": \"Acceso denegado\"}");
        }

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

    // DELETE — ADMIN
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(
            @RequestHeader(value = "SAPO-ADMIN-KEY", required = false) String adminKey,
            @PathVariable Long id) {

        if (!adminKeyValidator.isValid(adminKey)) {
            return ResponseEntity.status(403).body("{\"mensaje\": \"Acceso denegado\"}");
        }

        boolean eliminado = service.eliminar(id);

        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"mensaje\": \"Colaborador inexistente\"}");
        }

        return ResponseEntity.ok("{\"mensaje\": \"Colaborador eliminado correctamente\"}");
    }
}



