package una.bolsadeempleo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import una.bolsadeempleo.logic.*;
import una.bolsadeempleo.logic.DTO.PuestoDTO;
import una.bolsadeempleo.repository.UsuarioRepository;
import java.util.Map;

import java.util.List;

@RestController
@RequestMapping("/api/empresa")
@CrossOrigin(origins = "http://localhost:5173")
public class PuestoRestController {

    @Autowired
    private Service service;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/puestos")
    public ResponseEntity<List<PuestoDTO>> obtenerPuestosEmpresa() {

        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        String correo = auth.getName();

        return ResponseEntity.ok(
                service.obtenerPuestosPorEmpresaDTO(correo)
        );
    }

    @GetMapping("/caracteristicas")
    public List<Caracteristica> listarCaracteristicas() {

        return service.listarCaracteristicas(
                new Caracteristica()
        );
    }

    @PostMapping("/guardar-puesto")
    public void guardarPuesto(
            @RequestBody Map<String, Object> body,
            Authentication auth
    ) {

        Usuario usuario =
                usuarioRepository.findByCorreo(
                        auth.getName()
                );

        List<Integer> caracteristicas =
                (List<Integer>) body.get("caracteristicas");

        service.guardarPuesto(
                usuario.getId(),
                (String) body.get("descripcion"),
                (String) body.get("salario"),
                (String) body.get("tipo"),
                caracteristicas
        );
    }

    @PutMapping("/puestos/desactivar/{id}")
    public void desactivarPuesto(@PathVariable Integer id,
                                 Authentication authentication) {

        String correo = authentication.getName();

        Usuario usuario = usuarioRepository.findByCorreo(correo);

        service.desactivarPuesto(id, usuario.getId());
    }

    @GetMapping("/candidatos/{idPuesto}")
    public List<CandidatoResultado> candidatos(
            @PathVariable Integer idPuesto
    ) {
        return service.listarOferentesCandidatos(idPuesto);
    }
}