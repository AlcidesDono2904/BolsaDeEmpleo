package una.bolsadeempleo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import una.bolsadeempleo.logic.Nacionalidad;
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


        return "redirect:index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(HttpServletRequest req) {
        if (req.getAttribute("usuario") != null &&
            (req.getAttribute("password") != null)){
            return "login";
        }

        if (service.findUsuarioByCorreoAndPassword(req.getParameter("correo"), req.getParameter("password")) != null) {
            req.getSession().setAttribute("usuario", req.getAttribute("usuario").toString());
            System.out.println("Usuario logueado: " + req.getAttribute("usuario").toString());
            return "redirect:/index";
        }
        return "login";
    }



    //--------------------------------------EMPRESA------------------------------------
    @GetMapping("/empresa")
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
    @GetMapping("/oferente")
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
