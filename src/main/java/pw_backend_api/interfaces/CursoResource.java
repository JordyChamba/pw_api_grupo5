package pw_backend_api.interfaces;

import java.util.List;

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
public class CursoResource {
    @Inject
    CursoService cursoService;

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarCurso(CursoRepresentation cursoRe){
           this.cursoService.crearcurso(cursoRe);
        return Response.status(201).entity(cursoRe).build();
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CursoRepresentation> mostrarCursos(){
        List<CursoRepresentation> listCursos = this.cursoService.listarTodos();
        return listCursos;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CursoRepresentation buscarPorId(@PathParam("id") Integer id){
        return this.cursoService.buscarPorId(id);
    }

    @PUT
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CursoRepresentation actualizarCurso(@PathParam("id") Integer id, CursoRepresentation cursoRe){
        return this.cursoService.actualizarCurso(id, cursoRe);
    }

    @DELETE
    @Path("")
    public void borrarCurso(@PathParam("id")Integer id){
        this.cursoService.eliminarCurso(id);
    }


}
