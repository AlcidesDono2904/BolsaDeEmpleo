package una.bolsadeempleo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import una.bolsadeempleo.logic.CandidatoResultado;
import una.bolsadeempleo.logic.Service;
import una.bolsadeempleo.logic.Usuario;

import java.util.List;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    private Service service;
    @Autowired
    private HttpSession session;

    private String validarEmpresa() {
        /**
         * Valida si el usuario es EMPRESA*
         * @return null si es EMPRESA
         *         "redirect:/login" a login si no hay usuario logueado.
         *         "redirect:/" si el usuario no es EMPRESA.
         */

        Usuario user = (Usuario) session.getAttribute("usuario");
        if (user == null) {
            return "redirect:/login";
        }
        if (!user.getRol().equals("EMPRESA") || !user.getAprobado()) {
            return "redirect:/";
        }

        return null;
    }
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

    @GetMapping("/candidatos/detalle/{idOferente}")
    public String verDetalle(@PathVariable Integer idOferente,
                             Model model) {
        String redireccion = validarEmpresa();
        if (redireccion != null) {
            return redireccion;
        }

        var oferente = service.obtenerOferente(idOferente);

        model.addAttribute("oferente", oferente);
        model.addAttribute("habilidades", oferente.getOferenteHabilidads());

        return "empresa/detalle-candidato";
    }

    @GetMapping("/empresa-dashboard")
    public String dashboard(@RequestParam(required = false) String ok) {
            String redireccion = validarEmpresa();
            if (redireccion != null) {
                return redireccion;
            }
        return "/empresa/empresa-dashboard";
    }

    @GetMapping("/candidatos/buscar")
    public String buscarCandidatos(Model model, @RequestParam Integer idPuesto) {
        String redireccion = validarEmpresa();
        if (redireccion != null) {
            return redireccion;
        }
        model.addAttribute("puesto", service.findPuesto(idPuesto));
        List<CandidatoResultado> candidatos = service.listarOferentesCandidatos(idPuesto);
        System.out.println("Candidatos encontrados para el puesto ID " + idPuesto + ":");
        candidatos.forEach(candidato -> {
            System.out.println("Candidato: " + candidato.getOferente().getNombre() + ", Compatibilidad: " + candidato.getPorcentajeCompatibilidad());
        });
        model.addAttribute("candidatos", service.listarOferentesCandidatos(idPuesto));

        return "empresa/candidatos/buscar";
    }
}