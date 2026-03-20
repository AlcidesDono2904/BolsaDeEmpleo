package una.bolsadeempleo.controller;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import una.bolsadeempleo.logic.Oferente;
import una.bolsadeempleo.logic.Puesto;
import una.bolsadeempleo.logic.Service;
import una.bolsadeempleo.logic.Usuario;
import una.bolsadeempleo.repository.PuestoRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private final Service service;

    public HomeController(Service service) {
        this.service = service;
    }

    //---------------------------PARTE PUBLICA-------------------------------------------
    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("titulo", "Bolsa de Empleo");
        return "index";
    }

    @Autowired
    private PuestoRepository puestoRepository;

    //INDEX PUESTOS
    @GetMapping("/Inicio")
    public String index(Model model) {

        var puestos = puestoRepository
                .findTop5ByTipoPublicacionAndActivoOrderByFechaPublicacionDesc("PUBLICO", true);

        model.addAttribute("puestos", puestos);

        return "index";
    }

    @GetMapping("/puestos/buscar")
    public String buscar(@RequestParam(required = false) String[] caracteristicas,
                         Model model) {

        List<Puesto> resultados = new ArrayList<>();

        if (caracteristicas != null && caracteristicas.length > 0) {
            for (String c : caracteristicas) {
                resultados.addAll(
                        puestoRepository.findByDescripcionContainingIgnoreCase(c)
                );
            }
        } else {
            resultados = puestoRepository.findAll();
        }
        model.addAttribute("puestos", resultados);
        return "buscar-puestos";
    }

    //@GetMapping("/puestos/buscar-por-caracteristicas")
    //public String buscarPuestos() {
      //  return "buscar-puestos";
    //}

    @GetMapping("empresa/registro-empresa")
    public String empresa() {
        return "registro-empresa";
    }

    @GetMapping("oferente/registro-oferente")
    public String oferente() {
        return "registro-oferente";
    }

    @PostMapping("oferente/registro-oferente")
    public String registroOferente(HttpServletRequest req, @ModelAttribute Oferente oferente, @ModelAttribute Usuario usuario) {
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(HttpServletRequest req) {
        if (req.getParameter("correo") == null ||
            (req.getParameter("password") == null)){
            return "login";
        }

        if (req.getParameter("correo").equals("admin@admin.com") &&
                req.getParameter("password").equals("123")) {

            return "redirect:/admin/dashboard";
        }

        Usuario usuario = service.findUsuarioByCorreoAndPassword(req.getParameter("correo"), req.getParameter("password"));
        if (usuario != null) {
            req.getSession().setAttribute("usuario", usuario);
            System.out.println("Usuario logueado: " + req.getParameter("correo"));
            System.out.println(usuario.getId());
            if (usuario.getRol().equals("EMPRESA")) {
                return "redirect:/empresa/dashboard";
            } else if (usuario.getRol().equals("OFERENTE")) {
                return "redirect:/oferente/dashboard";
            } else if (usuario.getRol().equals("ADMIN")) {
                return "redirect:/admin/dashboard";
            }
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req) {
        req.getSession().invalidate(); // elimina la sesión
        return "redirect:/login";
    }

    //--------------------------------------EMPRESA------------------------------------
    @GetMapping("/empresa/dashboard")
    public String dashboardEmpresa() { return "empresa/empresa-dashboard"; }

    @GetMapping("/empresa/puestos")
    public String puestosEmpresa() {
        return "empresa/puestos";
    }

    @GetMapping("/empresa/candidatos")
    public String candidatosEmpresa() { return "empresa/candidatos"; }

    @GetMapping("/empresa/candidatos/detalle")
    public String detalleCandidato() { return "empresa/detalle-candidato"; }

    @GetMapping("/empresa/publicar/puesto")
    public String publicarPuesto() { return "empresa/publicar-puesto"; }

    //-----------------------------OFERENTE--------------------------------------------
    @GetMapping("/oferente/dashboard")
    public String dashboardOferente() { return "oferente/oferente-dashboard"; }

    @GetMapping("/oferente/habilidades")
    public String dashboardHabilidades() { return "oferente/habilidades"; }

    //------------------------ADMINISTRADOR---------------------------------------------
    @GetMapping("/admin/dashboard")
    public String administrador() {
        return "admin/admin-dashboard";
    }

    @GetMapping("/admin/empresas/pendientes")
    public String empresasPendientes() {
        return "admin/empresas-pendientes";
    }

    @GetMapping("/admin/oferentes/pendientes")
    public String oferentesPendientes() {
        return "admin/oferentes-pendientes";
    }

    @GetMapping("/admin/caracteristicas")
    public String caracteristicas() {
        return "admin/caracteristicas";
    }
}
