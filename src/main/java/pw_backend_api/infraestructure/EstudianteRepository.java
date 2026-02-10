package pw_backend_api.infraestructure;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import pw_backend_api.domain.Estudiante;

@ApplicationScoped
public class EstudianteRepository implements PanacheRepository<Estudiante> {

    public Estudiante findByCedula(Integer cedula) {
        return find("cedula", cedula).firstResult();
    }
}
