package una.bolsadeempleo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import una.bolsadeempleo.logic.Service;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    private Service service;

    // Mostrar formulario
    @GetMapping("/registro")
    public String mostrarFormulario() {
        return "/registro-empresa";
    }

    // Guardar en BD
    @PostMapping("/guardar")
    public String guardarEmpresa(@RequestParam String correo,
                                 @RequestParam String password,
                                 @RequestParam String nombre,
                                 @RequestParam String localizacion,
                                 @RequestParam String telefono,
                                 @RequestParam String descripcion) {

        service.guardarEmpresa(correo, password, nombre, localizacion, telefono, descripcion);

        return "redirect:/login";
    }
}