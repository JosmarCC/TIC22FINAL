package pkg2024ft22;

public class Alumno {
    int id;
    String nombre;
    String id_grupo;
    String estatus;
    
    Alumno (int id, String nombre, String idGrupo, String est) {
        this.id = id;
        this.nombre = nombre;
        this.id_grupo = idGrupo;
        this.estatus = est;
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
    
    
    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    } 
}