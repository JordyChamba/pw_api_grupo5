package pw_backend_api.interfaces;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pw_backend_api.application.MatriculaService;
import pw_backend_api.application.representation.MatriculaRepresentation;
import pw_backend_api.domain.Matricula;

import java.time.LocalDate;
import java.util.List;

@Path("/matriculas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MatriculaResource {

    @Inject
    MatriculaService matriculaService;

    @GET
    @Path("")
    @RolesAllowed("admin")
    public List<Matricula> listar() {
        return matriculaService.listarTodos();
    }

    @GET
    @Path("/cedula/{cedula}")
    @RolesAllowed("admin")
    public List<Matricula> buscarPorCedula(@PathParam("cedula") Integer cedula) {
        return matriculaService.buscarPorCedula(cedula);
    }

    @POST
    @RolesAllowed("admin")
    @Path("")
    public Response matricular(MatriculaRepresentation dto) {
        Matricula matricula = matriculaService.matricular(dto);
        return Response.status(Response.Status.CREATED).entity(matricula).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response cancelarMatricula(@PathParam("id") Integer id) {
        Matricula matricula = matriculaService.cancelarMatricula(id);
        return Response.ok(matricula).build();
    }

    @GET
    @Path("/reporte")
    @RolesAllowed("admin")
    public List<Matricula> reporte() {
        return matriculaService.listarTodos();
    }

}
