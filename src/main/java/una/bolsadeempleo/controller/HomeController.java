package una.bolsadeempleo.controller;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import una.bolsadeempleo.logic.Oferente;
import una.bolsadeempleo.logic.Service;
import una.bolsadeempleo.logic.Usuario;

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

    @GetMapping("/puestos/buscar-por-caracteristicas")
    public String buscarPuestos() {
        return "buscar-puestos";
    }

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
        Usuario usuario = service.findUsuarioByCorreoAndPassword(req.getParameter("correo"), req.getParameter("password"));
        if (usuario != null) {
            req.getSession().setAttribute("usuario", usuario);
            System.out.println("Usuario logueado: " + req.getParameter("correo"));
            System.out.println(usuario.getId());

            return "redirect:/empresa/dashboard";
        }
        return "login";
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
    @GetMapping("/admin")
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
