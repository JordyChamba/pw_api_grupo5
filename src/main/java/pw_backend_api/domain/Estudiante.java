package pw_backend_api.domain;

import java.time.LocalDate;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import java.util.List;
import jakarta.json.bind.annotation.JsonbTransient;

@Entity
@Table(name = "estudiante")
@SequenceGenerator(name = "estudiante_seq", sequenceName = "estudiante_secuencia", allocationSize = 1)
public class Estudiante extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estudiante_seq")
    public Integer id;
    public String nombre;
    public String apellido;
    public String carrera;
    public LocalDate fechaNacimiento;
    public String telefono;

    @JsonbTransient
    @OneToMany(mappedBy = "estudiante")
    public List<Matricula> matriculas;

}
