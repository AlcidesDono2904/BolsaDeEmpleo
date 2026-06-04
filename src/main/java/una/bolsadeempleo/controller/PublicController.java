package una.bolsadeempleo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import una.bolsadeempleo.logic.DTO.PuestoDTO;
import una.bolsadeempleo.logic.Puesto;
import una.bolsadeempleo.logic.Service;
import una.bolsadeempleo.logic.services.CambioService;
import una.bolsadeempleo.logic.services.NacionalidadService;
import java.util.List;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/public")
@CrossOrigin(origins = "http://localhost:5173")
public class PublicController {
    @Autowired
    private Service service;
    @Autowired
    private CambioService cambioService;
    @Autowired
    private NacionalidadService nacionalidadService;

    @GetMapping("/ultimosPuestos")
    public ResponseEntity<List<PuestoDTO>> ultimosPuestos() {

        return ResponseEntity.ok(service.obtenerUltimosPuestosDTO());

    }

    @GetMapping("/nacionalidades")
    public ResponseEntity<List<String>> nacionalidades() {

        return ResponseEntity.ok(
                nacionalidadService.listarNacionalidades()
        );
    }

    @PostMapping("/registro-oferente")
    public ResponseEntity<Void> registrarOferente(
            @RequestBody Map<String, String> body
    ) {

        service.guardarOferente(
                body.get("correo"),
                null,
                body.get("identificacion"),
                body.get("nombre"),
                body.get("apellido"),
                body.get("telefono"),
                body.get("residencia"),
                body.get("nacionalidad")
        );

        return ResponseEntity.ok().build();
    }

}
