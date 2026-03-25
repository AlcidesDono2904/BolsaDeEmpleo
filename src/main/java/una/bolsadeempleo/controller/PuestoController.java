package una.bolsadeempleo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import una.bolsadeempleo.logic.Caracteristica;
import una.bolsadeempleo.logic.Usuario;
import una.bolsadeempleo.logic.Service;

import java.util.List;


@Controller
@RequestMapping("/empresa")
public class PuestoController {

    @Autowired
    private HttpSession session;

    @Autowired
    private Service service;

    // Mostrar formulario
    @GetMapping("/publicar/puesto")
    public String mostrarForm(Model model) {
        model.addAttribute("caracteristicas", service.listarCaracteristicas(new Caracteristica()));
        return "empresa/publicar-puesto";
    }

    // Guardar puesto
    @PostMapping("/guardar-puesto")
    public String guardarPuesto(@RequestParam String descripcion,
                                @RequestParam String salario,
                                @RequestParam String tipo,
                                @RequestParam(required = false) List<Integer> caracteristicas) {

        // obtener usuario logueado
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login";
        }

        service.guardarPuesto(usuario.getId(), descripcion, salario, tipo, caracteristicas);

        return "redirect:/empresa/dashboard?ok=true";
    }

    //mostrar puestos
    @GetMapping("/puestos")
    public String verMisPuestos(Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login";
        }

        var puestos = service.obtenerPuestosPorEmpresa(usuario.getId());

        model.addAttribute("puestos", puestos);

        return "empresa/puestos";
    }
}