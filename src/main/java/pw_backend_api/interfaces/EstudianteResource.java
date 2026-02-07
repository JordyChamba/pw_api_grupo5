package pw_backend_api.interfaces;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pw_backend_api.application.EstudianteService;
import pw_backend_api.application.representation.EstudianteRepresentation;

@Path("/estudiantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstudianteResource {
    @Inject
    private EstudianteService estudianteService;

    @GET
    public Response listarTodos() {
        return Response.ok(estudianteService.listarTodos()).build();
    }

    @GET
    @Path("/{id}")
    public Response listarPorId(@PathParam("id") Integer id) {
        return Response.ok(estudianteService.listarPorId(id)).build();
    }

    @POST
    @Path("")
    public Response insertarEstudiante(EstudianteRepresentation er) {
        return Response.status(Response.Status.CREATED)
                .entity(estudianteService.insertarEstudiante(er))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminarEstudiante(@PathParam("id") Integer id) {
        estudianteService.eliminarEstudiante(id);
        return Response.noContent().build();
    }
}