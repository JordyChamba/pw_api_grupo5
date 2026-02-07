package pw_backend_api.interfaces;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pw_backend_api.application.CursoService;
import pw_backend_api.application.representation.CursoRepresentation;

@Path("/cursos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CursoResource {

    @Inject
    CursoService cursoService;

    @GET
    @Path("")
    public Response mostrarCursos() {
        return Response.ok(cursoService.listarTodos()).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Integer id) {
        return Response.ok(cursoService.buscarPorId(id)).build();
    }

    @POST
    @Path("")
    public Response guardarCurso(CursoRepresentation cursoRe) {
        CursoRepresentation creado = cursoService.crearCurso(cursoRe);

        return Response.status(Response.Status.CREATED)
                .entity(creado)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizarCurso(@PathParam("id") Integer id,
            CursoRepresentation cursoRe) {

        return Response.ok(cursoService.actualizarCurso(id, cursoRe)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response borrarCurso(@PathParam("id") Integer id) {
        cursoService.eliminarCurso(id);
        return Response.noContent().build();
    }
}
