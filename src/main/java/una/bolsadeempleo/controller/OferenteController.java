package una.bolsadeempleo.controller;

import com.lowagie.text.Document;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import una.bolsadeempleo.logic.*;
import una.bolsadeempleo.logic.services.NacionalidadService;
import una.bolsadeempleo.repository.OferenteRepository;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Controller
@RequestMapping("/oferente")
public class OferenteController {
    @Autowired
    private Service service;
    @Autowired
    private HttpSession session;
    @Autowired
    private OferenteRepository oferenteRepository;
    @Autowired
    private NacionalidadService nacionalidadService;

    @GetMapping("/oferente-dashboard")
    public String dashboard() {
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login";
        }

        return "/oferente/oferente-dashboard";
    }

    @GetMapping("/registro-oferente")
    public String mostrarFormulario(Model model) {
        model.addAttribute("nacionalidades", nacionalidadService.listarNacionalidades());
        return "/registro-oferente";
    }

    @PostMapping("/guardar")
    public String guardarOferente(@RequestParam String correo,
                                  @RequestParam String identificacion,
                                  @RequestParam String nombre,
                                  @RequestParam String apellido,
                                  @RequestParam String telefono,
                                  @RequestParam String residencia,
                                  @RequestParam String nacionalidad) {

        service.guardarOferente(correo, null, identificacion, nombre, apellido, telefono, residencia, nacionalidad);
        return "redirect:/login";
    }

    //mostrar habilidades
    @GetMapping("/habilidades")
    public String habilidades(Model model, @RequestParam(required = false) Integer actualId) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login";
        }

        Oferente of = oferenteRepository.findByIdUsuarioId(usuario.getId());

        // Obtener habilidades actuales del oferente
        model.addAttribute("habilidades", service.getHabilidadesDeOferente(of.getId()));

        // Si se proporciona actualId, buscar esa característica
        if (actualId != null) {
            Caracteristica caracteristicaSeleccionada = service.findCaracteristicaById(actualId);

            if (caracteristicaSeleccionada != null) {
                // Obtener el árbol de padres (ruta desde raíz hasta esta característica)
                model.addAttribute("caracteristicaArbol", caracteristicaSeleccionada.listarPadres());

                // Obtener las subcategorías de la característica seleccionada
                model.addAttribute("caracteristicas",
                    service.listarCaracteristicas(caracteristicaSeleccionada));

                // Mostrar la característica seleccionada
                model.addAttribute("caracteristicaSeleccionada", caracteristicaSeleccionada);
            }
        } else {
            // Si no hay actualId, mostrar solo las raíces
            Caracteristica raiz = new Caracteristica();
            model.addAttribute("caracteristicas", service.listarCaracteristicas(raiz));
            model.addAttribute("caracteristicaSeleccionada", null);
        }

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

    //cv
    @GetMapping("/cv")
    public void generarCv(HttpServletResponse response) throws Exception {

        Usuario u = (Usuario) session.getAttribute("usuario");
        if (u == null) {
            response.sendRedirect("/login");
            return;
        }

        Oferente oferente = oferenteRepository.findByIdUsuarioId(u.getId());

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=cv.pdf");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        document.add(new Paragraph("CURRICULUM VITAE"));
        document.add(new Paragraph("---------------------------"));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Nombre: "
                + oferente.getNombre() + " " + oferente.getApellido()));

        document.add(new Paragraph("Correo: "
                + oferente.getIdUsuario().getCorreo()));
        document.add(new Paragraph("Teléfono: " + oferente.getTelefono()));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Habilidades"));
        document.add(new Paragraph("---------------------------"));
        document.add(new Paragraph(" "));

        for (OferenteHabilidad h : oferente.getOferenteHabilidads()) {
            document.add(new Paragraph(
                    "- " + h.getIdCaracteristica().getNombre() +
                            " (Nivel: " + h.getNivel() + ")"
            ));
        }

        document.close();
    }
}