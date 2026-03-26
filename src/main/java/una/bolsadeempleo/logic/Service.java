package una.bolsadeempleo.logic;

import org.springframework.beans.factory.annotation.Autowired;
import una.bolsadeempleo.repository.*;

import java.math.BigDecimal;
import java.util.*;

@org.springframework.stereotype.Service("service")
public class Service {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private OferenteRepository oferenteRepository;
    @Autowired
    private PuestoRepository puestoRepository;
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private CaracteristicaRepository caracteristicaRepository;
    @Autowired
    private PuestoCaracteristicaRepository puestoCaracteristicaRepository;
    @Autowired
    private OferenteHabilidadRepository oferenteHabilidadRepository;

    // --- Usuario ---
    public Usuario saveUsuario(Usuario usuario) {
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

    // --- INDEX ---
    public List<Puesto> obtenerUltimosPuestos() {
        return puestoRepository
                .findTop5ByTipoPublicacionAndActivoOrderByFechaPublicacionDesc("PUBLICO", true);
    }

    public List<Puesto> buscarPuestos(String[] caracteristicasIds) {

        if (caracteristicasIds == null || caracteristicasIds.length == 0) {
            return puestoRepository.findAll();
        }

        List<Integer> ids = Arrays.stream(caracteristicasIds)
                .map(Integer::valueOf)
                .toList();

        return puestoRepository.findDistinctByPuestoCaracteristicas_IdCaracteristica_IdIn(ids);
    }

    public List<Puesto> buscarPuestosPorCaracteristicas(List<Integer> ids) {

        if (ids == null || ids.isEmpty()) {
            return puestoRepository.findAll();
        }

        return puestoRepository.findByCaracteristicasIds(ids);
    }

    // --- Admin ---
    public List<Usuario> getEmpresasPendientes() {
        var lista = usuarioRepository.findAll();

        for (Usuario u : lista) {
            System.out.println("ROL: " + u.getRol() + " | APROBADO: " + u.getAprobado());
        }
        return usuarioRepository.findByRolAndAprobado("EMPRESA", false);
    }

    public List<Usuario> getOferentesPendientes(){
        var lista = usuarioRepository.findAll();

        for (Usuario u : lista) {
            System.out.println("ROL: " + u.getRol() + " | APROBADO: " + u.getAprobado());
        }
        return usuarioRepository.findByRolAndAprobado("OFERENTE", false);
    }

    public void aprobarUsuario(Integer id) {
        Usuario u = usuarioRepository.findById(id).orElse(null);
        if (u != null) {
            u.setAprobado(true);
            usuarioRepository.save(u);
        }
    }

    // --- Empresa ---

    public void guardarEmpresa(String correo, String password,
                               String nombre, String localizacion,
                               String telefono, String descripcion) {

        // crear usuario
        Usuario usuario = new Usuario();
        usuario.setCorreo(correo);
        usuario.setPasswordHash(password);
        usuario.setRol("EMPRESA");
        usuario.setAprobado(false);

        usuarioRepository.save(usuario);

        // crear empresa
        Empresa empresa = new Empresa();
        empresa.setNombre(nombre);
        empresa.setLocalizacion(localizacion);
        empresa.setTelefono(telefono);
        empresa.setDescripcion(descripcion);

        // relación
        empresa.setIdUsuario(usuario);

        empresaRepository.save(empresa);
    }

    // ------ Oferente ------

    public void guardarOferente(String correo, String password,
                                String identificacion, String nombre,
                                String apellido, String telefono,
                                String residencia) {

        // crear usuario
        Usuario usuario = new Usuario();
        usuario.setCorreo(correo);
        usuario.setPasswordHash(password);
        usuario.setRol("OFERENTE");
        usuario.setAprobado(false);

        usuarioRepository.save(usuario);

        // crear oferente
        Oferente oferente = new Oferente();
        oferente.setIdentificacion(identificacion);
        oferente.setNombre(nombre);
        oferente.setApellido(apellido);
        oferente.setTelefono(telefono);
        oferente.setResidencia(residencia);

        // relación
        oferente.setIdUsuario(usuario);

        oferenteRepository.save(oferente);
    }

    public List<OferenteHabilidad> getHabilidadesDeOferente(Integer idOferente) {
        return oferenteHabilidadRepository.findByIdOferenteId(idOferente);
    }

    public void agregarHabilidad(Integer usuarioId, Integer idCaracteristica, Integer nivel) {

        // 1. Buscar el oferente real basado en el usuario que está logueado
        Oferente oferente = oferenteRepository.findByIdUsuarioId(usuarioId);

        if (oferente == null) {
            throw new RuntimeException("No existe oferente asociado al usuario " + usuarioId);
        }

        // 2. Buscar la característica seleccionada
        Caracteristica c = caracteristicaRepository.findById(idCaracteristica)
                .orElseThrow(() -> new RuntimeException("Característica no encontrada"));

        // 3. Crear el ID compuesto
        OferenteHabilidadId ohId = new OferenteHabilidadId();
        ohId.setIdOferente(oferente.getId());         // este SÍ es el correcto
        ohId.setIdCaracteristica(c.getId());

        // 4. Crear la habilidad
        OferenteHabilidad oh = new OferenteHabilidad();
        oh.setId(ohId);
        oh.setIdOferente(oferente);
        oh.setIdCaracteristica(c);
        oh.setNivel(nivel);

        // 5. Guardar
        oferenteHabilidadRepository.save(oh);
    }

    //------ Puestos ------
    public void guardarPuesto(Integer usuarioId, String descripcion, String salario, String tipo,
                              List<Integer> caracteristicasIds) {

        Empresa empresa = empresaRepository.findByIdUsuarioId(usuarioId);

        Puesto puesto = new Puesto();
        puesto.setDescripcion(descripcion);
        puesto.setSalarioUsd(new BigDecimal(salario));
        puesto.setTipoPublicacion(tipo);
        puesto.setActivo(true);
        puesto.setIdEmpresa(empresa);

        puestoRepository.save(puesto);


        if (caracteristicasIds != null) {
            for (Integer idCar : caracteristicasIds) {

                Caracteristica c = caracteristicaRepository.findById(idCar).orElse(null);

                if (c != null) {
                    PuestoCaracteristica pc = new PuestoCaracteristica();

                    PuestoCaracteristicaId id = new PuestoCaracteristicaId();
                    id.setIdPuesto(puesto.getId());
                    id.setIdCaracteristica(c.getId());

                    pc.setId(id);

                    pc.setIdPuesto(puesto);
                    pc.setIdCaracteristica(c);

                    pc.setNivelRequerido(1);

                    puestoCaracteristicaRepository.save(pc);
                }
            }
        }
    }

    public List<Puesto> obtenerPuestosPorEmpresa(Integer usuarioId) {

        Empresa empresa = empresaRepository.findByIdUsuarioId(usuarioId);

        return puestoRepository.findByIdEmpresa(empresa);
    }

    public void desactivarPuesto(Integer idPuesto, Integer idUsuario) {

        Puesto puesto = puestoRepository.findById(idPuesto).orElse(null);
        if (puesto == null) return;

        // validar que el puesto pertenece a la empresa logueada
        if (!puesto.getIdEmpresa().getIdUsuario().getId().equals(idUsuario)) {
            return; // no permitir desactivar
        }

        puesto.setActivo(false);
        puestoRepository.save(puesto);
    }

    // --- Carcateristicas ---
    public void guardarCaracteristica(String nombre, Integer padreId) {

        Caracteristica c = new Caracteristica();
        c.setNombre(nombre);

        if (padreId != null) {
            Caracteristica padre = caracteristicaRepository.findById(padreId).orElse(null);
            c.setIdPadre(padre);
        }

        caracteristicaRepository.save(c);
        System.out.println(caracteristicaRepository.findAll());
    }

    public List<Caracteristica> getTodasCaracteristicas() {
        return caracteristicaRepository.findAll();
    }
}
