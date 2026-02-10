package pw_backend_api.infraestructure;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import pw_backend_api.domain.Matricula;
import java.util.List;

@ApplicationScoped
public class MatriculaRepository implements PanacheRepository<Matricula> {

    public List<Matricula> findByEstudianteCedula(Integer cedula) {
        return find("estudiante.cedula", cedula).list();
    }
}
