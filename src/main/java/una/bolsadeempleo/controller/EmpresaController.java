package una.bolsadeempleo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    //buscar candidato

    /*@GetMapping("/candidatos/{idPuesto}")
    public String buscarCandidatos(@PathVariable Integer idPuesto, Model model) {

        var candidatos = service.buscarCandidatosParaPuesto(idPuesto);
        var puesto = service.obtenerPuesto(idPuesto);

        model.addAttribute("puesto", puesto);
        model.addAttribute("candidatos", candidatos);

        return "empresa/candidatos";
    }*/

    @GetMapping("/candidatos/{idPuesto}/detalle/{idOferente}")
    public String verDetalle(@PathVariable Integer idPuesto,
                             @PathVariable Integer idOferente,
                             Model model) {

        var oferente = service.obtenerOferente(idOferente);

        model.addAttribute("oferente", oferente);
        model.addAttribute("habilidades", oferente.getOferenteHabilidads());

        return "empresa/detalle-candidato";
    }
}