package pw_backend_api.application.representation;

import java.time.LocalDate;

public class MatriculaRepresentation {
    public Integer id;
    public Integer estudianteId;
    public Integer cursoId;
    public LocalDate fecha;
    // Optional: Add names for easier reading in responses if used for output
    public String nombreEstudiante;
    public String nombreCurso;
}
