package pkg2024ft22;

public class Asignacion {
    int id;
    String id_grupo;
    String id_materia;
    String id_profesor;

    Asignacion (int id, String idGrupo, String idMateria, String idProfesor) {
        this.id = id;
        this.id_grupo = idGrupo;
        this.id_materia = idMateria;
        this.id_profesor = idProfesor;  
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(String id_grupo) {
        this.id_grupo = id_grupo;
    }

    public String getId_materia() {
        return id_materia;
    }

    public void setId_materia(String id_materia) {
        this.id_materia = id_materia;
    }

    public String getId_profesor() {
        return id_profesor;
    }

    public void setId_profesor(String id_profesor) {
        this.id_profesor = id_profesor;
    }
    
}
