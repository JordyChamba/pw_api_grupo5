package pw_backend_api.application;

import java.time.LocalDate;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import pw_backend_api.application.representation.MatriculaRepresentation;
import pw_backend_api.domain.Curso;
import pw_backend_api.domain.Estudiante;
import pw_backend_api.domain.Matricula;
import pw_backend_api.infraestructure.CursoRepository;
import pw_backend_api.infraestructure.EstudianteRepository;
import pw_backend_api.infraestructure.MatriculaRepository;

@ApplicationScoped
public class MatriculaService {

    @Inject
    MatriculaRepository matriculaRepository;

    @Inject
    EstudianteRepository estudianteRepository;

    @Inject
    CursoRepository cursoRepository;

    public List<Matricula> listarTodos() {
        return matriculaRepository.listAll();
    }

    public List<Matricula> buscarPorCedula(Integer cedula) {
        return matriculaRepository.findByEstudianteCedula(cedula);
    }

    @Transactional
    public Matricula matricular(MatriculaRepresentation dto) {
        if (dto.estudianteId == null || dto.cursoId == null) {
            throw new WebApplicationException("Estudiante y Curso son requeridos", Response.Status.BAD_REQUEST);
        }

        Estudiante estudiante = estudianteRepository.findById(Long.valueOf(dto.estudianteId));
        Curso curso = cursoRepository.findById(Long.valueOf(dto.cursoId));

        if (estudiante == null || curso == null) {
            throw new WebApplicationException("Estudiante o Curso no encontrado", Response.Status.NOT_FOUND);
        }

        Matricula matricula = new Matricula();
        matricula.estudiante = estudiante;
        matricula.curso = curso;
        matricula.fecha = LocalDate.now();
        matricula.estado = "ACTIVA";

        matriculaRepository.persist(matricula);

        return matricula;
    }

    @Transactional
    public Matricula cancelarMatricula(Integer id) {
        Matricula matricula = matriculaRepository.findById(id.longValue());

        if (matricula == null) {
            throw new WebApplicationException("Matrícula no encontrada", Response.Status.NOT_FOUND);
        }

        matricula.estado = "ANULADA";
        // No need to persist explicitly if managed, but good for clarity in some
        // contexts.
        // With Panache and @Transactional, modifying entity is enough.

        return matricula;
    }
}
