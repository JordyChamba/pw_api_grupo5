package pw_backend_api.domain;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="curso")
@SequenceGenerator(name = "seq_curso",sequenceName = "seq_curso" ,allocationSize = 1)
public class Curso extends PanacheEntityBase {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "seq_producto")
    public Integer id;
    public String CodigoCurso;
    public String nombre;
    public String creditos;
    public String descripcion;
    public Integer cupos;

    @OneToMany(mappedBy = "curso")
    public List<Matricula> matriculas;


}
