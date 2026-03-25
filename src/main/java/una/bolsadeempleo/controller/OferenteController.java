package una.bolsadeempleo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import una.bolsadeempleo.logic.Oferente;
import una.bolsadeempleo.logic.Service;
import una.bolsadeempleo.logic.Usuario;
import una.bolsadeempleo.repository.OferenteRepository;
import una.bolsadeempleo.repository.UsuarioRepository;

@Controller
@RequestMapping("/oferente")
public class OferenteController {
    @Autowired
    private Service service;

    @Autowired
    private HttpSession session;

    @Autowired
    private OferenteRepository oferenteRepository;

    @GetMapping("/registro")
    public String mostrarFormulario() {
        return "/registro-oferente";
    }

    @PostMapping("/guardar")
    public String guardarOferente(@RequestParam String correo,
                                  @RequestParam String password,
                                  @RequestParam String identificacion,
                                  @RequestParam String nombre,
                                  @RequestParam String apellido,
                                  @RequestParam String telefono,
                                  @RequestParam String residencia) {

        service.guardarOferente(correo, password, identificacion, nombre, apellido, telefono, residencia);

        return "redirect:/login";
    }

    //mostrar habilidades
    @GetMapping("/habilidades")
    public String habilidades(Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        Oferente of = oferenteRepository.findByIdUsuarioId(usuario.getId());

        model.addAttribute("habilidades", service.getHabilidadesDeOferente(of.getId()));
        model.addAttribute("caracteristicas", service.getTodasCaracteristicas());

        return "oferente/habilidades";
    }

    //Guardar habilidad
    @PostMapping("/habilidades/agregar")
    public String agregar(@RequestParam Integer idCaracteristica,
                          @RequestParam Integer nivel) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login";
        }

        service.agregarHabilidad(usuario.getId(), idCaracteristica, nivel);

        return "redirect:/oferente/habilidades?ok=true";
    }
}