package una.bolsadeempleo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import una.bolsadeempleo.logic.Service;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private Service service;

        @GetMapping("/empresas")
        public String verEmpresas(Model model) {
            var empresas = service.getEmpresasPendientes();
            model.addAttribute("empresas", service.getEmpresasPendientes());

            return "admin/empresas-pendientes"; // tu html
        }

    @GetMapping("/oferentes")
    public String verOferentes(Model model) {
        var oferentes = service.getOferentesPendientes();
        model.addAttribute("oferentes", service.getOferentesPendientes());
        return "admin/oferentes-pendientes"; // tu html
    }

    // Aprobar usuario
    @PostMapping("/aprobar")
    public String aprobar(@RequestParam Integer id) {

        service.aprobarUsuario(id);

        return "redirect:/admin/dashboard";
    }

    //GUARDA CARARCTERISTICA
    @PostMapping("/caracteristicas/guardar")
    public String guardarCaracteristica(@RequestParam String nombre,
                                        @RequestParam(required = false) Integer padreId) {

        service.guardarCaracteristica(nombre, padreId);

        return "redirect:/admin/caracteristicas";
    }

    @GetMapping("/caracteristicas")
    public String verCaracteristicas(Model model) {
        model.addAttribute("caracteristicas", service.getTodasCaracteristicas());
        return "admin/caracteristicas";
    }
}
