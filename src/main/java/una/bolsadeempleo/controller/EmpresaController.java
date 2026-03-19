package una.bolsadeempleo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import una.bolsadeempleo.logic.Empresa;
import una.bolsadeempleo.logic.Usuario;
import una.bolsadeempleo.repository.EmpresaRepository;
import una.bolsadeempleo.repository.UsuarioRepository;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Mostrar formulario
    @GetMapping("/registro")
    public String mostrarFormulario() {
        return "empresa/registro-empresa";
    }

    // Guardar en BD
    @PostMapping("/guardar")
    public String guardarEmpresa(HttpServletRequest req) {

        // aqui creamos usuario
        Usuario usuario = new Usuario();
        usuario.setCorreo(req.getParameter("correo"));
        usuario.setPasswordHash(req.getParameter("password")); // luego se encripta
        usuario.setRol("EMPRESA");
        usuario.setAprobado(false);

        usuarioRepository.save(usuario);

        // aqui empresa
        Empresa empresa = new Empresa();
        empresa.setNombre(req.getParameter("nombre"));
        empresa.setLocalizacion(req.getParameter("localizacion"));
        empresa.setTelefono(req.getParameter("telefono"));
        empresa.setDescripcion(req.getParameter("descripcion"));

        // relacionar usuario
        empresa.setIdUsuario(usuario);

        empresaRepository.save(empresa);

        return "redirect:/login";
    }
}