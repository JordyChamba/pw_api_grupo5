package pw_backend_api.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import pw_backend_api.application.representation.CursoRepresentation;
import pw_backend_api.domain.Curso;
import pw_backend_api.infraestructure.CursoRepository;
import pw_backend_api.infraestructure.MatriculaRepository;

@ApplicationScoped
public class CursoService {
    @Inject
    CursoRepository cursoRepository;

    @Inject
    MatriculaRepository matriculaRepository;

    public CursoRepresentation mapperToRep(Curso curso) {
        CursoRepresentation curs = new CursoRepresentation();
        curs.id = curso.id;
        curs.codigoCurso = curso.codigoCurso;
        curs.nombre = curso.nombre;
        curs.descripcion = curso.descripcion;
        curs.creditos = curso.creditos;
        curs.cupos = curso.cupos;
        return curs;
    }

    public Curso mapperToCur(CursoRepresentation cursoR) {
        Curso curs = new Curso();
        curs.id = cursoR.id;
        curs.codigoCurso = cursoR.codigoCurso;
        curs.nombre = cursoR.nombre;
        curs.descripcion = cursoR.descripcion;
        curs.creditos = cursoR.creditos;
        curs.cupos = cursoR.cupos;
        return curs;
    }

    public List<CursoRepresentation> listarTodos() {
        return cursoRepository.findAll().list().stream().map(this::mapperToRep).toList();
    }

    public CursoRepresentation buscarPorId(Integer id) {
        Curso curso = this.cursoRepository.findById(id.longValue());
        return this.mapperToRep(curso);
    }

    @Transactional
    public CursoRepresentation crearCurso(CursoRepresentation cr) {
        Curso curso2 = mapperToCur(cr);

        cursoRepository.persist(curso2);

        return mapperToRep(curso2);
    }

    @Transactional
    public CursoRepresentation actualizarCurso(Integer id, CursoRepresentation cursoR) {
        Curso curs = this.cursoRepository.findById(id.longValue());
        if (curs == null) {
            return null;
        }
        curs.codigoCurso = cursoR.codigoCurso;
        curs.nombre = cursoR.nombre;
        curs.descripcion = cursoR.descripcion;
        curs.creditos = cursoR.creditos;
        curs.cupos = cursoR.cupos;
        return this.mapperToRep(curs);
    }

    @Transactional
    public CursoRepresentation parcheCurso(Integer id, CursoRepresentation cursoR) {
        Curso curs = this.cursoRepository.findById(id.longValue());
        if (curs == null) {
            return null;
        }
        if (cursoR.codigoCurso != null)
            curs.codigoCurso = cursoR.codigoCurso;
        if (cursoR.nombre != null)
            curs.nombre = cursoR.nombre;
        if (cursoR.descripcion != null)
            curs.descripcion = cursoR.descripcion;
        if (cursoR.creditos != null)
            curs.creditos = cursoR.creditos;
        if (cursoR.cupos != null)
            curs.cupos = cursoR.cupos;
        return this.mapperToRep(curs);
    }

    @Transactional
    public void eliminarCurso(Integer id) {
        if (matriculaRepository.count("curso.id", id) > 0) {
            throw new jakarta.ws.rs.WebApplicationException(
                    "No se puede eliminar el curso porque tiene matrículas asociadas.",
                    jakarta.ws.rs.core.Response.Status.CONFLICT);
        }
        this.cursoRepository.deleteById(id.longValue());
    }

}
