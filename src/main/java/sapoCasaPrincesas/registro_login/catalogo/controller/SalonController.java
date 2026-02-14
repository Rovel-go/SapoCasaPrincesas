package sapoCasaPrincesas.registro_login.catalogo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapoCasaPrincesas.registro_login.catalogo.dto.SalonDTO;
import sapoCasaPrincesas.registro_login.catalogo.model.Salon;
import sapoCasaPrincesas.registro_login.catalogo.service.SalonService;
import sapoCasaPrincesas.registro_login.catalogo.service.mapper.SalonMapper;
import sapoCasaPrincesas.registro_login.config.AdminKeyValidator;

import java.util.List;

@RestController
@RequestMapping("/salones")
@CrossOrigin(origins = "http://localhost:5173")
public class SalonController {

    private final SalonService service;
    private final AdminKeyValidator adminKeyValidator;

    public SalonController(SalonService service, AdminKeyValidator adminKeyValidator) {
        this.service = service;
        this.adminKeyValidator = adminKeyValidator;
    }

    // ============================
    // GET — Público
    // ============================

    @GetMapping
    public ResponseEntity<List<SalonDTO>> obtenerTodos() {
        List<SalonDTO> lista = service.obtenerTodos()
                .stream()
                .map(SalonMapper::toDTO)
                .toList();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Salon salon = service.obtenerPorId(id);

        if (salon == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"mensaje\": \"Salón inexistente\"}");
        }

        return ResponseEntity.ok(SalonMapper.toDTO(salon));
    }

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

    // ============================
    // POST — ADMIN
    // ============================

    @PostMapping
    public ResponseEntity<?> crear(
            @RequestHeader(value = "SAPO-ADMIN-KEY", required = false) String adminKey,
            @RequestBody SalonDTO dto) {

        if (!adminKeyValidator.isValid(adminKey)) {
            return ResponseEntity.status(403).body("{\"mensaje\": \"Acceso denegado\"}");
        }

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

    // ============================
    // PUT — ADMIN
    // ============================

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @RequestHeader(value = "SAPO-ADMIN-KEY", required = false) String adminKey,
            @PathVariable Long id,
            @RequestBody SalonDTO dto) {

        if (!adminKeyValidator.isValid(adminKey)) {
            return ResponseEntity.status(403).body("{\"mensaje\": \"Acceso denegado\"}");
        }

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

    // ============================
    // DELETE — ADMIN
    // ============================

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
                    .body("{\"mensaje\": \"Salón inexistente\"}");
        }

        return ResponseEntity.ok("{\"mensaje\": \"Salón eliminado correctamente\"}");
    }
}


