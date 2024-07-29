package pkg2024ft22;

public class Alumno {
    int id;
    String nombre;
    String id_grupo;
    String descripcion;
    
    Alumno (int id, String nombre, String idGrupo, String des) {
        this.id = id;
        this.nombre = nombre;
        this.id_grupo = idGrupo;
        this.descripcion = des;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(String id_grupo) {
        this.id_grupo = id_grupo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
