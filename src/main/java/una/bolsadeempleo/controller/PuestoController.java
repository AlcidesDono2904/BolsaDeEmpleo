package una.bolsadeempleo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import una.bolsadeempleo.logic.Puesto;
import una.bolsadeempleo.repository.PuestoRepository;

import java.math.BigDecimal;

@Controller
@RequestMapping("/puesto")
public class PuestoController {

    @Autowired
    private PuestoRepository puestoRepository;

    @PostMapping("/guardar")
    public String guardarPuesto(HttpServletRequest req) {

        Puesto puesto = new Puesto();
        puesto.setDescripcion(req.getParameter("descripcion"));
        puesto.setSalarioUsd(new BigDecimal(req.getParameter("salario")));
        puesto.setTipoPublicacion(req.getParameter("tipo"));
        puesto.setActivo(true);

        // luego  la empresa desde sesión

        puestoRepository.save(puesto);

        return "redirect:/empresa/dashboard";
    }
}