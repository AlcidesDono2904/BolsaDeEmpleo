package una.bolsadeempleo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import una.bolsadeempleo.logic.Oferente;
import una.bolsadeempleo.logic.Usuario;
import una.bolsadeempleo.repository.OferenteRepository;
import una.bolsadeempleo.repository.UsuarioRepository;

@Controller
@RequestMapping("/oferente")
public class OferenteController {

    @Autowired
    private OferenteRepository oferenteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/registro")
    public String mostrarFormulario() {
        return "/registro-oferente";
    }

    @PostMapping("/guardar")
    public String guardarOferente(HttpServletRequest req) {


        Usuario usuario = new Usuario();
        usuario.setCorreo(req.getParameter("correo"));
        usuario.setPasswordHash(req.getParameter("password"));
        usuario.setRol("OFERENTE");
        usuario.setAprobado(true);

        usuarioRepository.save(usuario);

        Oferente oferente = new Oferente();
        oferente.setIdentificacion(req.getParameter("identificacion"));
        oferente.setNombre(req.getParameter("nombre"));
        oferente.setApellido(req.getParameter("apellido"));
        oferente.setTelefono(req.getParameter("telefono"));
        oferente.setResidencia(req.getParameter("residencia"));

        // relación
        oferente.setIdUsuario(usuario);

        // nacionalidad va con excel

        oferenteRepository.save(oferente);

        return "redirect:/login";
    }
}