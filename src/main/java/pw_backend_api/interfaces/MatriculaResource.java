package pw_backend_api.interfaces;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pw_backend_api.application.MatriculaService;
import pw_backend_api.application.representation.MatriculaRepresentation;
import pw_backend_api.domain.Matricula;

import java.util.List;

import io.quarkus.security.Authenticated;

@Path("/matriculas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class MatriculaResource {

    @Inject
    MatriculaService matriculaService;

    @GET
    @Path("")
    public List<Matricula> listar() {
        return matriculaService.listarTodos();
    }

    @GET
    @Path("/cedula/{cedula}")
    public List<Matricula> buscarPorCedula(@PathParam("cedula") Integer cedula) {
        return matriculaService.buscarPorCedula(cedula);
    }

    @POST
    @Path("")
    public Response matricular(MatriculaRepresentation dto) {
        Matricula matricula = matriculaService.matricular(dto);
        return Response.status(Response.Status.CREATED).entity(matricula).build();
    }

    @DELETE
    @Path("/{id}")
    public Response cancelarMatricula(@PathParam("id") Integer id) {
        Matricula matricula = matriculaService.cancelarMatricula(id);
        return Response.ok(matricula).build();
    }

    @GET
    @Path("/reporte")
    public List<Matricula> reporte() {
        return matriculaService.listarTodos();
    }

}
