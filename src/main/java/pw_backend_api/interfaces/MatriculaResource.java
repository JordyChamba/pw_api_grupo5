package pw_backend_api.interfaces;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pw_backend_api.application.representation.MatriculaRepresentation;
import pw_backend_api.domain.Curso;
import pw_backend_api.domain.Estudiante;
import pw_backend_api.domain.Matricula;
import pw_backend_api.infraestructure.CursoRepository;
import pw_backend_api.infraestructure.EstudianteRepository;

import java.time.LocalDate;
import java.util.List;

@Path("/matriculas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MatriculaResource {

    @Inject
    EstudianteRepository estudianteRepository;

    @Inject
    CursoRepository cursoRepository;

    @GET
    public List<Matricula> listar() {
        return Matricula.listAll();
    }

    @POST
    @Transactional
    public Response matricular(MatriculaRepresentation dto) {
        if (dto.estudianteId == null || dto.cursoId == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Estudiante y Curso son requeridos").build();
        }

        Estudiante estudiante = estudianteRepository.findById(Long.valueOf(dto.estudianteId));
        Curso curso = cursoRepository.findById(Long.valueOf(dto.cursoId));

        if (estudiante == null || curso == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Estudiante o Curso no encontrado").build();
        }

        Matricula matricula = new Matricula();
        matricula.estudiante = estudiante;
        matricula.curso = curso;
        matricula.fecha = LocalDate.now();

        matricula.persist();

        return Response.status(Response.Status.CREATED).entity(matricula).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response cancelarMatricula(@PathParam("id") Integer id) {
        boolean deleted = Matricula.deleteById(id);

        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/reporte")
    public List<Matricula> reporte() {
        return Matricula.listAll();
    }

}
