package pkg2024ft22;

public class Grupo {
    int id;
    String nombre;
    String id_CC;
    String id_carrera;

    public String getId_CC() {
        return id_CC;
    }

    public void setId_CC(String id_CC) {
        this.id_CC = id_CC;
    }
    
    Grupo(int id, String nombre, String idCC, String idCarrera){
        this.id = id;
        this.nombre = nombre;
        this.id_CC = idCC;
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

    public String getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(String id_carrera) {
        this.id_carrera = id_carrera;
    }
    
  
}
