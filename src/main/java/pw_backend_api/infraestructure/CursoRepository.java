package pw_backend_api.infraestructure;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import pw_backend_api.domain.Curso;

@ApplicationScoped
public class CursoRepository implements PanacheRepository<Curso> {

    public Curso findByCodigo(String codigo) {
        return find("codigoCurso", codigo).firstResult();
    }
}
