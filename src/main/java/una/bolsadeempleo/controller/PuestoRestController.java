package una.bolsadeempleo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import una.bolsadeempleo.logic.Caracteristica;
import una.bolsadeempleo.logic.Puesto;
import una.bolsadeempleo.logic.Service;
import una.bolsadeempleo.logic.Usuario;
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
    public List<Puesto> verMisPuestos(
            Authentication auth
    ) {

        return service.obtenerPuestosPorEmpresaCorreo(
                auth.getName()
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
}