package una.bolsadeempleo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import una.bolsadeempleo.logic.*;
import una.bolsadeempleo.logic.DTO.*;
import una.bolsadeempleo.repository.UsuarioRepository;
import java.util.ArrayList;
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
    public List<CaracteristicaDTO> listarCaracteristicas() {
        return service.listarCaracteristicasDTO();
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

    @PutMapping("/desactivar/{id}")
    public void desactivarPuesto(@PathVariable Integer id,
                                 Authentication authentication) {

        String correo = authentication.getName();

        Usuario usuario = usuarioRepository.findByCorreo(correo);

        service.desactivarPuesto(id, usuario.getId());
    }

    @GetMapping("/candidatos/{idPuesto}")
    public List<CandidatoDTO> candidatos(
            @PathVariable Integer idPuesto
    ) {
        return service.listarOferentesCandidatosDTO(idPuesto);
    }

    @GetMapping("/candidatos/detalle/{idOferente}")
    public OferenteDetalleDTO detalleCandidato(
            @PathVariable Integer idOferente
    ) {

        Oferente o = service.obtenerOferente(idOferente);

        OferenteDetalleDTO dto = new OferenteDetalleDTO();

        dto.setId(o.getId());
        dto.setNombre(o.getNombre());
        dto.setApellido(o.getApellido());
        dto.setIdentificacion(o.getIdentificacion());
        dto.setTelefono(o.getTelefono());
        dto.setResidencia(o.getResidencia());
        dto.setCorreo(o.getIdUsuario().getCorreo());

        List<HabilidadDTO> habilidades = new ArrayList<>();

        for (OferenteHabilidad h : o.getOferenteHabilidads()) {

            HabilidadDTO habilidadDTO = new HabilidadDTO();

            habilidadDTO.setNombre(
                    h.getIdCaracteristica().getNombre()
            );

            habilidadDTO.setNivel(
                    h.getNivel()
            );

            habilidades.add(habilidadDTO);
        }

        dto.setHabilidades(habilidades);

        return dto;
    }
}