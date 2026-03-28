package una.bolsadeempleo.controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private PuestoRepository puestoRepository;

    @Autowired
    private HttpSession session;

    //INDEX PUESTOS
    @GetMapping("/")
    public String index(Model model) {

        var puestos = service.obtenerUltimosPuestos();

        model.addAttribute("puestos", puestos);

        return "index";
    }

    //buscar puestos
    @GetMapping("/puestos/buscar")
    public String mostrarBusqueda(Model model) {

        model.addAttribute("caracteristicas", service.getTodasCaracteristicas());
        model.addAttribute("puestos", new ArrayList<>()); // vacío al inicio

        return "buscar-puestos";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam(required = false) List<Integer> caracteristicas,
                         Model model) {

        var resultados = service.buscarPuestosPorCaracteristicas(caracteristicas);

        model.addAttribute("puestos", resultados);
        model.addAttribute("caracteristicas", service.getTodasCaracteristicas());

        return "buscar-puestos";
    }

    @GetMapping("empresa/registro-empresa")
    public String empresa() {
        return "registro-empresa";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //login
    @PostMapping("/login")
    public String procesarLogin(HttpServletRequest req) {
        if (req.getParameter("correo") == null ||
                (req.getParameter("password") == null)){
            return "login";
        }
/*
        if (req.getParameter("correo").equals("admin@admin.com") &&
                req.getParameter("password").equals("123")) {
            Usuario admin = new Usuario();
            admin.setCorreo("admin@admin.com");
            admin.setRol("ADMIN");
            req.getSession().setAttribute("usuario", admin);

            return "redirect:/admin/dashboard";
        }*/

        Usuario usuario = service.login(req.getParameter("correo"), req.getParameter("password"));
        if (usuario != null && Boolean.TRUE.equals(usuario.getAprobado())) {
            req.getSession().setAttribute("usuario", usuario);
            System.out.println("Usuario logueado: " + req.getParameter("correo"));
            System.out.println(usuario.getId());
            if (usuario.getRol().equals("EMPRESA")) {
                return "redirect:/empresa/empresa-dashboard";
            } else if (usuario.getRol().equals("OFERENTE")) {
                return "redirect:/oferente/oferente-dashboard";
            } else if (usuario.getRol().equals("ADMIN")) {
                return "redirect:/admin/admin-dashboard";
            }
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req) {
        req.getSession().invalidate(); // elimina la sesión
        return "redirect:/login";
    }

}
