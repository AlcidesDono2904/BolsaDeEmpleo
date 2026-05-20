package una.bolsadeempleo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import una.bolsadeempleo.logic.Puesto;
import una.bolsadeempleo.logic.Service;

import java.util.List;

@RestController
@RequestMapping("/api/empresa")
@CrossOrigin(origins = "http://localhost:5173")
public class PuestoRestController {

    @Autowired
    private Service service;

    @GetMapping("/puestos")
    public List<Puesto> verMisPuestos(
            Authentication auth
    ) {

        return service.obtenerPuestosPorEmpresaCorreo(
                auth.getName()
        );
    }
}