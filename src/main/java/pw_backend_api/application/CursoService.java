package pw_backend_api.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import pw_backend_api.application.representation.CursoRepresentation;
import pw_backend_api.domain.Curso;
import pw_backend_api.infraestructure.CursoRepository;


@ApplicationScoped
public class CursoService {
    @Inject
    CursoRepository cursoRepository;

    @Transactional
    public void crearcurso(CursoRepresentation curso) {
        this.cursoRepository.persist(this.mapperToCur(curso));
    }

    public List<CursoRepresentation> listarTodos(){
        List<CursoRepresentation> rep = new ArrayList<>();
        for(Curso cur: this.cursoRepository.listAll()){
            rep.add(this.mapperToRep(cur));
        }
        return rep;
    }

    public CursoRepresentation buscarPorId(Integer id ){
        return this.mapperToRep(this.cursoRepository.findById(id.longValue()));
    }

    public CursoRepresentation actualizarCurso(Integer id, CursoRepresentation cursoR){
        Curso curs= this.cursoRepository.findById(id.longValue());
        if(curs == null){
            return null;
        }
        curs.CodigoCurso = cursoR.CodigoCurso;
        curs.nombre = cursoR.nombre;
        curs.descripcion = cursoR.descripcion;
        curs.creditos = cursoR.creditos;
        curs.cupos = cursoR.cupos;
        return this.mapperToRep(curs);
    }

    @Transactional
    public void eliminarCurso(Integer id){
        this.cursoRepository.deleteById(id.longValue());
    }

    private CursoRepresentation mapperToRep(Curso curso) {
        CursoRepresentation curs = new CursoRepresentation();
        curs.id = curso.id;
        curs.CodigoCurso = curso.CodigoCurso;
        curs.nombre = curso.nombre;
        curs.descripcion = curso.descripcion;
        curs.creditos = curso.creditos;
        curs.cupos = curso.cupos;
        return curs;
    }

    private Curso mapperToCur(CursoRepresentation cursoR) {
        Curso curs = new Curso();
        curs.id = cursoR.id;
        curs.CodigoCurso = cursoR.CodigoCurso;
        curs.nombre = cursoR.nombre;
        curs.descripcion = cursoR.descripcion;
        curs.creditos = cursoR.creditos;
        curs.cupos = cursoR.cupos;
        return curs;
    }

}
