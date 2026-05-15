package una.bolsadeempleo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import una.bolsadeempleo.logic.*;
import una.bolsadeempleo.util.PdfReportGenerator;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private Service service;

    @Autowired
    private SesionUsuarioBean sesion;

    @GetMapping("/admin/admin-dashboard")
    public String dashboard() {
        if (!sesion.hasAdminPermissions()) {
            return "redirect:/login";
        }
        return "/admin/admin-dashboard";
    }

    @GetMapping("/admin/empresas-pendientes")
    public String empresasPendientes(Model model) {
        if (!sesion.hasAdminPermissions()) {
            return "redirect:/login";
        }
        model.addAttribute("lista", service.listarEmpresasPendientes());

        return "/admin/empresas-pendientes";
    }

    @GetMapping("/admin/oferentes-pendientes")
    public String oferentesPendientes(Model model) {
        if (!sesion.hasAdminPermissions()) {
            return "redirect:/login";
        }
        model.addAttribute("lista", service.listarOferentesPendientes());

        return "/admin/oferentes-pendientes";
    }

    @PostMapping("/admin/aprobar-usuario")
    public String aprobarUsuario(@ModelAttribute Usuario usuario) {
        if (!sesion.hasAdminPermissions()) {
            return "redirect:/login";
        }
        service.aprobarUsuario(usuario);
        return "redirect:/admin/admin-dashboard";
    }

    @GetMapping("/admin/generar-clave")
    public String mostrarGenerarClave(@RequestParam Integer id, Model model) {
        if (!sesion.hasAdminPermissions()) {
            return "redirect:/login";
        }
        Usuario usuario = service.findUsuarioById(id);
        model.addAttribute("usuario", usuario);
        return "/admin/generar-clave";
    }

    @GetMapping("/admin/caracteristicas")
    public String mostrarCaracteristicas(Model model, @RequestParam(required = false) Integer idPadre) {
        if (!sesion.hasAdminPermissions()) {
            return "redirect:/login";
        }
        Caracteristica caracteristica = new Caracteristica();
        if (idPadre != null) {

            caracteristica = service.findCaracteristicaById(idPadre);
            var lista = caracteristica.listarPadres();
            model.addAttribute("caracteristicaSeleccionada", caracteristica);
            model.addAttribute("caracteristicaArbol", lista);
            for (Caracteristica c : lista) {
                System.out.println("ID: " + c.getId() + " Nombre: " + c.getNombre());
            }
        }else {
            model.addAttribute("caracteristicaArbol", null);
        }

        model.addAttribute("caracteristicas", service.listarCaracteristicas(caracteristica));
        return "/admin/caracteristicas";
    }

    @PostMapping("/admin/agregarCaracteristica")
    public String agregarCaracteristica(@Valid @ModelAttribute Caracteristica caracteristica) {
        if (!sesion.hasAdminPermissions()) {
            return "redirect:/login";
        }
        service.saveCaracteristica(caracteristica);
        return "redirect:/admin/caracteristicas?idPadre=" + (caracteristica.getIdPadre() != null ? caracteristica.getIdPadre().getId() : "");
    }

    @GetMapping("/admin/reportes-puestos")
    public String mostrarReportes() {
        if (!sesion.hasAdminPermissions()) {
            return "redirect:/login";
        }
        return "/admin/reportes-puestos";
    }

    @PostMapping("/admin/descargar-reporte")
    public String descargarReporte(@RequestParam Integer mes, @RequestParam Integer anio,
                                  HttpServletResponse response) {
        if (!sesion.hasAdminPermissions()) {
            return "redirect:/login";
        }

        try {
            List<Puesto> puestos = service.listarPuestosPorMes(mes, anio);
            byte[] pdfBytes = PdfReportGenerator.generarReportePuestos(puestos, mes, anio);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=reporte_puestos_" + mes + "_" + anio + ".pdf");
            response.setContentLength(pdfBytes.length);
            response.getOutputStream().write(pdfBytes);
            response.getOutputStream().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/reportes-puestos";
    }
}
