package una.bolsadeempleo.logic;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import una.bolsadeempleo.repository.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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

    public void aprobarUsuario(Usuario u) {
        String password = u.getPasswordHash();
        Usuario usuario = usuarioRepository.findById(u.getId()).orElse(null);
        if (usuario != null) {
            usuario.setAprobado(true);
            String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
            usuario.setPasswordHash(passwordHash);
            usuarioRepository.save(usuario);
        }
    }

    public Usuario login(String correo, String password) {
        Usuario usuario = usuarioRepository.findByCorreo(correo);
        if (usuario != null && BCrypt.checkpw(password, usuario.getPasswordHash())) {
            System.out.println("Login exitoso para usuario: " + correo);
            return usuario;
        }
        System.out.println("Login fallido para usuario: " + correo);
        return null;
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

    // --- Oferente ---
    private double calcularCompatibilidad(Oferente oferente, Puesto puesto) {
        // Obtener características requeridas del puesto
        Set<PuestoCaracteristica> caracteristicasPuesto = puestoCaracteristicaRepository
                .findByIdPuestoId(puesto.getId());

        if (caracteristicasPuesto.isEmpty()) {
            return 0.0;
        }

        // Obtener habilidades del oferente
        List<OferenteHabilidad> habilidadesOferente = oferenteHabilidadRepository
                .findByIdOferenteId(oferente.getId());

        // Crear mapa de habilidades del oferente para búsqueda rápida
        Map<Integer, Integer> mapaHabilidadesOferente = new HashMap<>();
        for (OferenteHabilidad oh : habilidadesOferente) {
            mapaHabilidadesOferente.put(oh.getIdCaracteristica().getId(), oh.getNivel());
        }

        // Construcción de vectores para distancia coseno
        double productoPunto = 0.0;
        double magnitudPuesto = 0.0;
        double magnitudOferente = 0.0;

        // Iterar sobre características del puesto
        for (PuestoCaracteristica pc : caracteristicasPuesto) {
            Integer idCaracteristica = pc.getIdCaracteristica().getId();
            Integer nivelRequerido = pc.getNivelRequerido();

            Integer nivelOferente = mapaHabilidadesOferente.getOrDefault(idCaracteristica, 0);

            productoPunto += nivelRequerido * nivelOferente;

            magnitudPuesto += nivelRequerido * nivelRequerido;
            magnitudOferente += nivelOferente * nivelOferente;
        }

        // Distancia coseno = (A·B) / (||A|| × ||B||)
        if (magnitudPuesto == 0.0 || magnitudOferente == 0.0) {
            return 0.0;
        }
        return productoPunto / (Math.sqrt(magnitudPuesto) * Math.sqrt(magnitudOferente));
    }

    public List<CandidatoResultado> listarOferentesCandidatos(Integer idPuesto) {
        Puesto puesto = puestoRepository.findById(idPuesto).orElse(null);
        if (puesto == null) {
            return new ArrayList<>();
        }

        Set<PuestoCaracteristica> caracteristicasPuesto = puestoCaracteristicaRepository
                .findByIdPuestoId(idPuesto);
        if (caracteristicasPuesto.isEmpty()) {
            return new ArrayList<>();
        }


        List<Oferente> todosOferentes = oferenteRepository.findByIdUsuarioAprobadoTrue();
        List<CandidatoResultado> candidatos = new ArrayList<>();

        //
        for (Oferente oferente : todosOferentes) {
            // Obtener habilidades del oferente
            List<OferenteHabilidad> habilidadesOferente = oferenteHabilidadRepository
                    .findByIdOferenteId(oferente.getId());
            System.out.println("Evaluando oferente: " + oferente.getNombre() + " " + oferente.getApellido() +
                               " con habilidades: " + habilidadesOferente.size());
            if (habilidadesOferente.isEmpty()) {
                continue; // Saltar oferentes sin habilidades
            }

            // Crear mapa de habilidades del oferente
            Map<Integer, Integer> mapaHabilidades = new HashMap<>();
            for (OferenteHabilidad oh : habilidadesOferente) {
                mapaHabilidades.put(oh.getIdCaracteristica().getId(), oh.getNivel());
            }

            // Contar requisitos alcanzados (nivel_oferente >= nivel_requerido)
            int requisitosAlcanzados = 0;
            for (PuestoCaracteristica pc : caracteristicasPuesto) {
                Integer idCaracteristica = pc.getIdCaracteristica().getId();
                Integer nivelRequerido = pc.getNivelRequerido();
                Integer nivelOferente = mapaHabilidades.getOrDefault(idCaracteristica, 0);

                if (nivelOferente >= nivelRequerido) {
                    requisitosAlcanzados++;
                }
            }

            // Filtrar solo incluir si cumple al menos con una característica
            if (requisitosAlcanzados > 0) {
                double compatibilidad = calcularCompatibilidad(oferente, puesto);
                double porcentaje = compatibilidad * 100.0; // Convertir a porcentaje

                CandidatoResultado resultado = new CandidatoResultado();
                resultado.setOferente(oferente);
                resultado.setPorcentajeCompatibilidad(porcentaje);
                resultado.setRequisitosAlcanzados(requisitosAlcanzados);

                candidatos.add(resultado);
            }
        }

        // Ordenar por porcentaje de compatibilidad descendente
        candidatos.sort((a, b) -> Double.compare(b.getPorcentajeCompatibilidad(),
                                                  a.getPorcentajeCompatibilidad()));

        return candidatos;
    }

    public List<Oferente> listarOferentesPendientes() {
        return oferenteRepository.findByIdUsuarioAprobadoFalse();
    }

    public void guardarOferente(String correo, String password,
                                String identificacion, String nombre,
                                String apellido, String telefono,
                                String residencia, String nacionalidad) {

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
        oferente.setNacionalidad(nacionalidad);

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

    // --- Empresa ---
    public List<Empresa> listarEmpresasPendientes() {
        return empresaRepository.findByIdUsuarioAprobadoFalse();
    }
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

    // --- Caracteristica ---
    public List<Caracteristica> getTodasCaracteristicas() {
        return caracteristicaRepository.findAll();
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

    // --- Puesto ---
    public List<Puesto> listarPuestosPorMes(Integer mes, Integer anio) {
        List<Puesto> todosPuestos = puestoRepository.findAll();

        return todosPuestos.stream()
            .filter(p -> {
                if (p.getFechaPublicacion() == null) return false;
                java.time.YearMonth ym = java.time.YearMonth.from(
                    p.getFechaPublicacion().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime()
                );
                return ym.getYear() == anio && ym.getMonthValue() == mes;
            })
            .sorted((a, b) -> a.getIdEmpresa().getNombre().compareTo(b.getIdEmpresa().getNombre()))
            .collect(Collectors.toList());
    }
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

    public Puesto findPuesto(Integer idPuesto) {
        return puestoRepository.findById(idPuesto).orElse(null);
    }
}
