package una.bolsadeempleo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import una.bolsadeempleo.logic.DTO.PuestoDTO;
import una.bolsadeempleo.logic.Puesto;
import una.bolsadeempleo.logic.Service;
import una.bolsadeempleo.logic.services.CambioService;

import java.util.List;

@RestController
@RequestMapping("/api/public")
public class PublicController {
    @Autowired
    private Service service;
    @Autowired
    private CambioService cambioService;

    @GetMapping("/ultimosPuestos")
    public ResponseEntity<List<PuestoDTO>> ultimosPuestos() {

        return ResponseEntity.ok(service.obtenerUltimosPuestosDTO());

    }

}
