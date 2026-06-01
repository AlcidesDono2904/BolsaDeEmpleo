package una.bolsadeempleo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import una.bolsadeempleo.logic.DTO.CaracteristicaDTO;
import una.bolsadeempleo.logic.DTO.OferenteHabilidadDTO;
import una.bolsadeempleo.logic.DTO.OferenteHabilidadNuevaDTO;
import una.bolsadeempleo.logic.Service;

import java.util.List;

@RestController
@RequestMapping("/api/oferentes")
public class OferenteRestController {
    @Autowired
    private Service service;

    @GetMapping("/caracteristicas")
    public ResponseEntity<List<CaracteristicaDTO>> listarCaracteristicas() {
        System.out.println("Llamada GET a /api/oferentes/caracteristicas");
        List<CaracteristicaDTO> caracteristicas = service.listarCaracteristicasDTO();
        return ResponseEntity.ok(caracteristicas);
    }

    @GetMapping("/mis-caracteristicas")
    public ResponseEntity<List<OferenteHabilidadDTO>> listarMisCaracteristicas(Authentication auth) {
        System.out.println("Llamada GET a /api/oferentes/mis-caracteristicas");
        try {
            String correo = auth.getName();
            List<OferenteHabilidadDTO> caracteristicas = service.listarCaracteristicasOferenteDTO(correo);
            return ResponseEntity.ok(caracteristicas);
        } catch (Exception e) {
            System.out.println("Error al obtener características del oferente: " + e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/habilidades/agregar")
    public ResponseEntity<String> agregarCaracteristica(@RequestBody OferenteHabilidadNuevaDTO nuevaCaracteristica, Authentication auth ) {
        System.out.println("Llamada POST a /api/oferentes/caracteristicas/agregar con caracteristicaId: " + nuevaCaracteristica.getIdCaracteristica() + " y nivel: " + nuevaCaracteristica.getNivel());
        try {
            String correo = auth.getName();
            int idUsuario = service.obtenerIdUsuarioPorCorreo(correo);
            service.agregarHabilidad(idUsuario, nuevaCaracteristica.getIdCaracteristica(), nuevaCaracteristica.getNivel());
            return ResponseEntity.ok("Característica agregada exitosamente");
        } catch (Exception e) {
            System.out.println("Error al agregar característica al oferente: " + e.getMessage());
            return ResponseEntity.status(500).body("Error al agregar característica");
        }
    }
}
