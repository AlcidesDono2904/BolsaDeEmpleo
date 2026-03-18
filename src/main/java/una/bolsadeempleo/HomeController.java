package una.bolsadeempleo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
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

    @GetMapping("/login")
    public String login() {
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
