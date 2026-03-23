package una.bolsadeempleo.logic;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import una.bolsadeempleo.repository.CaracteristicaRepository;
import una.bolsadeempleo.repository.EmpresaRepository;
import una.bolsadeempleo.repository.OferenteRepository;
import una.bolsadeempleo.repository.UsuarioRepository;

import java.util.List;

@org.springframework.stereotype.Service("service")
public class Service {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private OferenteRepository oferenteRepository;
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private CaracteristicaRepository caracteristicaRepository;

    // --- Usuario ---
    public Usuario saveUsuario(Usuario usuario) {
        String passwordPlano = usuario.getPasswordHash();
        String passwordHash = BCrypt.hashpw(passwordPlano, BCrypt.gensalt());

        usuario.setPasswordHash(passwordHash);

        return usuarioRepository.save(usuario);
    }

    public Usuario findUsuarioByCorreoAndPassword(String correo, String password) {
        return usuarioRepository.findByCorreoAndPasswordHash(correo, password);
    }

    public Usuario findUsuarioById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public void deleteUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public Iterable<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }

    // --- Oferente ---
    public List<Oferente> listarOferentesPendientes() {
        return oferenteRepository.findByIdUsuarioAprobadoFalse();
    }

    // --- Empresa ---


    // --- ADMIN ---
    public List<Empresa> listarEmpresasPendientes() {
        return empresaRepository.findByIdUsuarioAprobadoFalse();
    }

    public List<Caracteristica> listarCaracteristicas(Caracteristica caracteristica) {
        if (caracteristica.getId() == null) {
            return caracteristicaRepository.findAll();
        }
        return caracteristicaRepository.findByIdPadre(caracteristica);
    }

    public Caracteristica findCaracteristicaById(Integer id) {
        return caracteristicaRepository.findById(id).orElse(null);
    }

    public void saveCaracteristica(Caracteristica caracteristica) {
        caracteristicaRepository.save(caracteristica);
    }
}
