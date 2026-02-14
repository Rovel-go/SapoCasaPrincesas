package sapoCasaPrincesas.registro_login.catalogo.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import sapoCasaPrincesas.registro_login.catalogo.dto.CategoriaServicioDTO;
import sapoCasaPrincesas.registro_login.catalogo.dto.SubServicioDTO;

import sapoCasaPrincesas.registro_login.catalogo.model.CategoriaServicio;
import sapoCasaPrincesas.registro_login.catalogo.model.SubServicio;

import sapoCasaPrincesas.registro_login.catalogo.service.CategoriaServicioService;
import sapoCasaPrincesas.registro_login.catalogo.service.SubServicioService;

import sapoCasaPrincesas.registro_login.catalogo.service.mapper.CategoriaServicioMapper;
import sapoCasaPrincesas.registro_login.catalogo.service.mapper.SubServicioMapper;

import sapoCasaPrincesas.registro_login.usuarios.dao.UsuarioRepository;
import sapoCasaPrincesas.registro_login.usuarios.model.Usuario;

import sapoCasaPrincesas.registro_login.config.AdminKeyValidator;

import java.util.List;

@RestController
@RequestMapping("/servicios")
@CrossOrigin(origins = "http://localhost:5173")
public class ServiciosController {

    private final CategoriaServicioService categoriaService;
    private final SubServicioService subServicioService;
    private final UsuarioRepository usuarioRepository;
    private final AdminKeyValidator adminKeyValidator;

    public ServiciosController(CategoriaServicioService categoriaService,
                               SubServicioService subServicioService,
                               UsuarioRepository usuarioRepository,
                               AdminKeyValidator adminKeyValidator) {
        this.categoriaService = categoriaService;
        this.subServicioService = subServicioService;
        this.usuarioRepository = usuarioRepository;
        this.adminKeyValidator = adminKeyValidator;
    }

    // ============================
    // GET — Público
    // ============================

    @GetMapping
    public ResponseEntity<List<CategoriaServicioDTO>> obtenerCategorias() {
        List<CategoriaServicioDTO> lista = categoriaService.obtenerTodas()
                .stream()
                .map(CategoriaServicioMapper::toDTO)
                .toList();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<?> obtenerCategoria(@PathVariable String categoria) {

        List<CategoriaServicioDTO> lista = categoriaService
                .obtenerPorNombre(categoria)
                .stream()
                .map(CategoriaServicioMapper::toDTO)
                .toList();

        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"mensaje\": \"Categoría inexistente\"}");
        }

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/subservicios/categoria/{idCategoria}")
    public ResponseEntity<?> obtenerSubserviciosPorCategoria(@PathVariable Long idCategoria) {

        List<SubServicioDTO> lista = subServicioService
                .obtenerPorCategoria(idCategoria)
                .stream()
                .map(SubServicioMapper::toDTO)
                .toList();

        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"mensaje\": \"No hay subservicios para esta categoría\"}");
        }

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/subservicio/{id}")
    public ResponseEntity<?> obtenerSubServicioPorId(@PathVariable Long id) {
        SubServicio sub = subServicioService.obtenerPorId(id);

        if (sub == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"mensaje\": \"Subservicio inexistente\"}");
        }

        return ResponseEntity.ok(SubServicioMapper.toDTO(sub));
    }

    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable String nombre) {

        List<SubServicioDTO> lista = subServicioService.buscarPorNombre(nombre)
                .stream()
                .map(SubServicioMapper::toDTO)
                .toList();

        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"mensaje\": \"Subservicio inexistente\"}");
        }

        return ResponseEntity.ok(lista);
    }

    // ============================
    // ADMIN — CRUD CATEGORÍAS
    // ============================

    @PostMapping("/categorias")
    public ResponseEntity<?> crearCategoria(
            @RequestHeader(value = "SAPO-ADMIN-KEY", required = false) String adminKey,
            @RequestBody CategoriaServicioDTO dto) {

        if (!adminKeyValidator.isValid(adminKey)) {
            return ResponseEntity.status(403).body("{\"mensaje\": \"Acceso denegado\"}");
        }

        try {
            CategoriaServicio nueva = new CategoriaServicio(dto.categoria());
            CategoriaServicio creada = categoriaService.crear(nueva);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(CategoriaServicioMapper.toDTO(creada));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"mensaje\": \"Error: categoría duplicada o inválida\"}");
        }
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<?> actualizarCategoria(
            @RequestHeader(value = "SAPO-ADMIN-KEY", required = false) String adminKey,
            @PathVariable Long id,
            @RequestBody CategoriaServicioDTO dto) {

        if (!adminKeyValidator.isValid(adminKey)) {
            return ResponseEntity.status(403).body("{\"mensaje\": \"Acceso denegado\"}");
        }

        CategoriaServicio datos = new CategoriaServicio(dto.categoria());
        CategoriaServicio actualizada = categoriaService.actualizar(id, datos);

        if (actualizada == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"mensaje\": \"Categoría no encontrada\"}");
        }

        return ResponseEntity.ok(CategoriaServicioMapper.toDTO(actualizada));
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<?> eliminarCategoria(
            @RequestHeader(value = "SAPO-ADMIN-KEY", required = false) String adminKey,
            @PathVariable Long id) {

        if (!adminKeyValidator.isValid(adminKey)) {
            return ResponseEntity.status(403).body("{\"mensaje\": \"Acceso denegado\"}");
        }

        boolean eliminado = categoriaService.eliminar(id);

        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"mensaje\": \"Categoría no encontrada\"}");
        }

        return ResponseEntity.ok("{\"mensaje\": \"Categoría eliminada correctamente\"}");
    }

    // ============================
    // ADMIN — CRUD SUBSERVICIOS
    // ============================

    @PostMapping("/subservicios")
    public ResponseEntity<?> crearSubServicio(
            @RequestHeader(value = "SAPO-ADMIN-KEY", required = false) String adminKey,
            @RequestParam Long categoriaId,
            @RequestBody SubServicioDTO dto) {

        if (!adminKeyValidator.isValid(adminKey)) {
            return ResponseEntity.status(403).body("{\"mensaje\": \"Acceso denegado\"}");
        }

        SubServicio nuevo = new SubServicio();
        nuevo.setNombre(dto.nombre());
        nuevo.setPrecio(dto.precio());

        SubServicio creado = subServicioService.crear(categoriaId, nuevo);

        if (creado == null) {
            return ResponseEntity.badRequest().body("{\"mensaje\": \"Categoría inexistente\"}");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(SubServicioMapper.toDTO(creado));
    }

    @PutMapping("/subservicios/{id}")
    public ResponseEntity<?> actualizarSubServicio(
            @RequestHeader(value = "SAPO-ADMIN-KEY", required = false) String adminKey,
            @PathVariable Long id,
            @RequestBody SubServicioDTO dto) {

        if (!adminKeyValidator.isValid(adminKey)) {
            return ResponseEntity.status(403).body("{\"mensaje\": \"Acceso denegado\"}");
        }

        SubServicio datos = new SubServicio();
        datos.setNombre(dto.nombre());
        datos.setPrecio(dto.precio());

        SubServicio actualizado = subServicioService.actualizar(id, datos);

        if (actualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"mensaje\": \"Subservicio no encontrado\"}");
        }

        return ResponseEntity.ok(SubServicioMapper.toDTO(actualizado));
    }

    @DeleteMapping("/subservicios/{id}")
    public ResponseEntity<?> eliminarSubServicio(
            @RequestHeader(value = "SAPO-ADMIN-KEY", required = false) String adminKey,
            @PathVariable Long id) {

        if (!adminKeyValidator.isValid(adminKey)) {
            return ResponseEntity.status(403).body("{\"mensaje\": \"Acceso denegado\"}");
        }

        boolean eliminado = subServicioService.eliminar(id);

        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"mensaje\": \"Subservicio no encontrado\"}");
        }

        return ResponseEntity.ok("{\"mensaje\": \"Subservicio eliminado correctamente\"}");
    }
}



