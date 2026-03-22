package una.bolsadeempleo.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import una.bolsadeempleo.logic.Empresa;
import una.bolsadeempleo.logic.Service;
import una.bolsadeempleo.logic.Usuario;

@Controller
public class AdminController {

    @Autowired
    private Service service;

    @Autowired
    private HttpSession session;

    private String validarAdmin() {
        /**
         * Valida si el usuario es ADMIN*
         * @return null si es ADMIN
         *         "redirect:/login" a login si no hay usuario logueado.
         *         "redirect:/" si el usuario no es ADMIN.
         */

        Usuario user = (Usuario) session.getAttribute("usuario");
        if (user == null) {
            return "redirect:/login";
        }
        if (!user.getRol().equals("ADMIN") || !user.getAprobado()) {
            return "redirect:/";
        }

        return null;
    }

    @GetMapping("/admin/admin-dashboard")
    public String dashboard() {
        String redireccion = validarAdmin();
        if (redireccion != null) {
            return redireccion;
        }
        return "/admin/admin-dashboard";
    }

    @GetMapping("/admin/empresas-pendientes")
    public String usuarios(Model model) {
        String redireccion = validarAdmin();
        if (redireccion != null) {
            return redireccion;
        }
        model.addAttribute("lista", service.listarEmpresasPendientes());
        model.addAttribute("urlAccion", "/admin/aprobar-usuario");

        return "/admin/empresas-pendientes";
    }

    @PostMapping("/admin/aprobar-usuario")
    public String editarEmpresa(@ModelAttribute Usuario usuario) {
        String redireccion = validarAdmin();
        if (redireccion != null) {
            return redireccion;
        }
        System.out.println("ID a aprobar: " + usuario.getId());
        usuario = service.findUsuarioById(usuario.getId());
        usuario.setAprobado(true);
        service.saveUsuario(usuario);
        return "redirect:/admin/empresas-pendientes";
    }

    @GetMapping("/admin/generar-clave")
    public String mostrarGenerarClave(@RequestParam Integer id, Model model) {
        String redireccion = validarAdmin();
        if (redireccion != null) {
            return redireccion;
        }
        Usuario usuario = service.findUsuarioById(id);
        model.addAttribute("usuario", usuario);
        return "/admin/generar-clave";
    }
}
