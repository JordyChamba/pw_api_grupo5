package pw_backend_api.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "matricula")
@SequenceGenerator(name = "seq_matri", sequenceName = "seq_matri", allocationSize = 1)
public class Matricula extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_matri")
    public Integer id;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    public Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    public Curso curso;

    public LocalDate fecha;
}
