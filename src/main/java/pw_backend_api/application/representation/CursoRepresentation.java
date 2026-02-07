package pw_backend_api.application.representation;

public class CursoRepresentation {
    public Integer id;
    public String CodigoCurso;
    public String nombre;
    public String creditos;
    public String descripcion;
    public Integer cupos;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCodigoCurso() {
        return CodigoCurso;
    }
    public void setCodigoCurso(String codigoCurso) {
        CodigoCurso = codigoCurso;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCreditos() {
        return creditos;
    }
    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Integer getCupos() {
        return cupos;
    }
    public void setCupos(Integer cupos) {
        this.cupos = cupos;
    }
    

}
