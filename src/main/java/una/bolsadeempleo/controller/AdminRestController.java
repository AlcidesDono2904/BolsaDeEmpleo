package una.bolsadeempleo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import una.bolsadeempleo.logic.DTO.UsuarioPendienteDTO;
import una.bolsadeempleo.logic.Service;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    @Autowired
    private Service service;

    @GetMapping("/empresasPendientes")
    public ResponseEntity<List<UsuarioPendienteDTO>> empresasPendientes() {
        System.out.println("Llamada a /api/admin/empresasPendientes");
        try {
            List<UsuarioPendienteDTO> empresas = service.empresasPendientesDTO();
            return ResponseEntity.ok(empresas);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}

