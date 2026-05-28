package una.bolsadeempleo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import una.bolsadeempleo.logic.Caracteristica;
import una.bolsadeempleo.logic.DTO.CaracteristicaDTO;
import una.bolsadeempleo.logic.DTO.NuevaCaracteristicaDTO;
import una.bolsadeempleo.logic.DTO.PasswordRequestDTO;
import una.bolsadeempleo.logic.DTO.UsuarioPendienteDTO;
import una.bolsadeempleo.logic.Service;
import una.bolsadeempleo.logic.Usuario;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    @Autowired
    private Service service;

    @GetMapping("/empresas-pendientes")
    public ResponseEntity<List<UsuarioPendienteDTO>> empresasPendientes() {
        System.out.println("Llamada GET a /api/admin/empresas-pendientes");
        try {
            List<UsuarioPendienteDTO> empresas = service.empresasPendientesDTO();
            return ResponseEntity.ok(empresas);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/empresas-pendientes")
    public ResponseEntity<String> aprobarEmpresa(@RequestBody PasswordRequestDTO request) {
        System.out.println("Llamada POST a /api/admin/empresas-pendientes con id: " + request.getId());
        try {
            Usuario u = new Usuario();
            u.setId(request.getId());
            u.setPasswordHash(request.getPassword());

            service.aprobarUsuario(u);
            return ResponseEntity.ok("Empresa aprobada");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al aprobar empresa");
        }
    }

    @GetMapping("/caracteristicas")
    public ResponseEntity<List<CaracteristicaDTO>> caracteristicas() {
        System.out.println("Llamada GET a /api/admin/caracteristicas");
        try {
            List<CaracteristicaDTO> caracteristicas = service.listarCaracteristicasDTO();
            return ResponseEntity.ok(caracteristicas);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/caracteristicas")
    public ResponseEntity<String> agregarCaracteristica(@RequestBody NuevaCaracteristicaDTO request) {
        System.out.println("Llamada POST a /api/admin/caracteristicas con nombre: " + request.getNombre() + " y idPadre: " + request.getIdPadre());
        try {
            Caracteristica nuevaCaracteristica = new Caracteristica();
            nuevaCaracteristica.setNombre(request.getNombre());
            int idPadre = request.getIdPadre();

            if (idPadre != 0) {
                Caracteristica padre = new Caracteristica();
                padre.setId(request.getIdPadre());
                nuevaCaracteristica.setIdPadre(padre);
            }

            service.saveCaracteristica(nuevaCaracteristica);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al agregar característica");
        }
        return ResponseEntity.ok("Característica agregada");
    }
}

