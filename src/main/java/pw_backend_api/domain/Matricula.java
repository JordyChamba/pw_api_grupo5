package pw_backend_api.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="matricula")
@SequenceGenerator(name = "seq_matri", sequenceName ="seq_matri", allocationSize = 1 )
public class Matricula extends PanacheEntityBase{


}
