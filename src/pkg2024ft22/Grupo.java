package pkg2024ft22;

public class Grupo {
    int id;
    String nombre;
    int id_ciclo;
    String id_carrera;
    
    Grupo(int id, String nombre, int idCiclo, String idCarrera){
        this.id = id;
        this.nombre = nombre;
        this.id_ciclo = idCiclo;
        this.id_carrera = idCarrera;
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

    public int getId_ciclo() {
        return id_ciclo;
    }

    public void setId_ciclo(int id_ciclo) {
        this.id_ciclo = id_ciclo;
    }

    public String getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(String id_carrera) {
        this.id_carrera = id_carrera;
    }
    
  
}
