package pw_backend_api.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import pw_backend_api.application.representation.EstudianteRepresentation;
import pw_backend_api.domain.Estudiante;
import pw_backend_api.infraestructure.EstudianteRepository;
import pw_backend_api.infraestructure.MatriculaRepository;

@ApplicationScoped
public class EstudianteService {
    @Inject
    public EstudianteRepository estudianteRepository;

    @Inject
    MatriculaRepository matriculaRepository;

    public EstudianteRepresentation mapperToER(Estudiante estudiante) {
        EstudianteRepresentation er = new EstudianteRepresentation();
        er.id = estudiante.id;
        er.nombre = estudiante.nombre;
        er.apellido = estudiante.apellido;
        er.carrera = estudiante.carrera;
        er.fechaNacimiento = estudiante.fechaNacimiento;
        er.telefono = estudiante.telefono;
        er.cedula = estudiante.cedula;

        er.links = new java.util.ArrayList<>();
        er.links.add(new EstudianteRepresentation.Link("self", "http://localhost:8081/estudiantes/" + estudiante.id));
        er.links.add(new EstudianteRepresentation.Link("parent", "http://localhost:8081/estudiantes"));

        return er;
    }

    public Estudiante mapperToE(EstudianteRepresentation er) {
        Estudiante estudiante = new Estudiante();
        estudiante.id = er.id;
        estudiante.nombre = er.nombre;
        estudiante.apellido = er.apellido;
        estudiante.carrera = er.carrera;
        estudiante.fechaNacimiento = er.fechaNacimiento;
        estudiante.telefono = er.telefono;
        estudiante.cedula = er.cedula;

        return estudiante;
    }

    public List<EstudianteRepresentation> listarTodos() {
        return estudianteRepository.findAll().list().stream().map(this::mapperToER).toList();
    }

    public EstudianteRepresentation listarPorId(Integer id) {
        Estudiante estudiante = this.estudianteRepository.findById(id.longValue());
        return this.mapperToER(estudiante);
    }

    public EstudianteRepresentation buscarPorCedula(Integer cedula) {
        Estudiante estudiante = estudianteRepository.findByCedula(cedula);
        if (estudiante == null) {
            throw new WebApplicationException("Estudiante no encontrado con cédula: " + cedula,
                    Response.Status.NOT_FOUND);
        }
        return mapperToER(estudiante);
    }

    @Transactional
    public EstudianteRepresentation insertarEstudiante(EstudianteRepresentation er) {
        Estudiante estudiante = mapperToE(er);

        estudianteRepository.persist(estudiante);

        return mapperToER(estudiante);
    }

    @Transactional
    public EstudianteRepresentation actualizarEstudiante(Integer id, EstudianteRepresentation er) {
        Estudiante estudiante = this.estudianteRepository.findById(id.longValue());
        estudiante.nombre = er.nombre;
        estudiante.apellido = er.apellido;
        estudiante.carrera = er.carrera;
        estudiante.fechaNacimiento = er.fechaNacimiento;
        estudiante.telefono = er.telefono;
        estudiante.cedula = er.cedula;

        estudianteRepository.persist(estudiante);

        return mapperToER(estudiante);
    }

    @Transactional
    public EstudianteRepresentation parche(Integer id, EstudianteRepresentation er) {
        Estudiante estudiante = this.estudianteRepository.findById(id.longValue());
        if (er.nombre != null)
            estudiante.nombre = er.nombre;
        if (er.apellido != null)
            estudiante.apellido = er.apellido;
        if (er.carrera != null)
            estudiante.carrera = er.carrera;
        if (er.fechaNacimiento != null)
            estudiante.fechaNacimiento = er.fechaNacimiento;
        if (er.telefono != null)
            estudiante.telefono = er.telefono;
        if (er.cedula != null)
            estudiante.cedula = er.cedula;

        estudianteRepository.persist(estudiante);

        return mapperToER(estudiante);
    }

    @Transactional
    public void eliminarEstudiante(Integer id) {
        if (matriculaRepository.count("estudiante.id", id) > 0) {
            throw new jakarta.ws.rs.WebApplicationException(
                    "No se puede eliminar el estudiante porque tiene matrículas asociadas.",
                    jakarta.ws.rs.core.Response.Status.CONFLICT);
        }
        this.estudianteRepository.deleteById(id.longValue());
    }
}
