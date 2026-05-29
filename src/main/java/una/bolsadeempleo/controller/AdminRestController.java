package una.bolsadeempleo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import una.bolsadeempleo.logic.Caracteristica;
import una.bolsadeempleo.logic.DTO.*;
import una.bolsadeempleo.logic.Puesto;
import una.bolsadeempleo.logic.Service;
import una.bolsadeempleo.logic.Usuario;
import una.bolsadeempleo.util.PdfReportGenerator;

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

    @GetMapping("/oferentes-pendientes")
    public ResponseEntity<List<UsuarioPendienteDTO>> oferentesPendientes() {
        System.out.println("Llamada GET a /api/admin/oferentes-pendientes");
        try {
            List<UsuarioPendienteDTO> oferentes = service.oferentesPendientesDTO();
            return ResponseEntity.ok(oferentes);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/oferentes-pendientes")
    public ResponseEntity<String> aprobarOferente(@RequestBody PasswordRequestDTO request) {
        System.out.println("Llamada POST a /api/admin/oferentes-pendientes con id: " + request.getId());
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
    public ResponseEntity<String> agregarCaracteristica(@Valid @RequestBody NuevaCaracteristicaDTO request) {
        System.out.println("Llamada POST a /api/admin/caracteristicas con nombre: " + request.getNombre() + " y idPadre: " + request.getIdPadre());
        try {
            service.agregarCaracteristica(request);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al agregar característica");
        }
        return ResponseEntity.ok("Característica agregada");
    }

    @GetMapping("/reporte")
    public ResponseEntity<byte[]> generarReporte(
            @RequestParam int month,
            @RequestParam int year) {
        System.out.println("Llamada GET a /api/admin/reporte con mes: " + month + " y año: " + year);
        try {
            List<Puesto> puestos = service.listarPuestosPorMes(month, year);
            byte[] pdfBytes = PdfReportGenerator.generarReportePuestos(puestos, month, year);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=reporte.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}

