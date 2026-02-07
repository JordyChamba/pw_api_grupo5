package pw_backend_api.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import pw_backend_api.application.representation.EstudianteRepresentation;
import pw_backend_api.domain.Estudiante;
import pw_backend_api.infraestructure.EstudianteRepository;

@ApplicationScoped
public class EstudianteService {
    @Inject
    public EstudianteRepository estudianteRepository;

    public EstudianteRepresentation mapperToER(Estudiante estudiante) {
        EstudianteRepresentation er = new EstudianteRepresentation();
        er.id = estudiante.id;
        er.nombre = estudiante.nombre;
        er.apellido = estudiante.apellido;
        er.carrera = estudiante.carrera;
        er.fechaNacimiento = estudiante.fechaNacimiento;
        er.telefono = estudiante.telefono;

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

        return estudiante;
    }

    public List<EstudianteRepresentation> listarTodos() {
        return estudianteRepository.findAll().list().stream().map(this::mapperToER).toList();
    }

    public EstudianteRepresentation listarPorId(Integer id) {
        Estudiante estudiante = this.estudianteRepository.findById(id.longValue());
        return this.mapperToER(estudiante);
    }

    @Transactional
    public EstudianteRepresentation insertarEstudiante(EstudianteRepresentation er) {
        Estudiante estudiante = mapperToE(er);

        estudianteRepository.persist(estudiante);

        return mapperToER(estudiante);
    }

    @Transactional
    public void eliminarEstudiante(Integer id) {
        this.estudianteRepository.deleteById(id.longValue());
    }
}
