package pkg2024ft22;

public class Cuatrimestre {
    String nombre;
    int id;
    
    Cuatrimestre(int id, String nuevoNombre) {
        this. id = id;
        this.nombre = nuevoNombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    } 
    
    public int getId() {
        return id;
    }
    
    public void serId(int id) {
        this.id = id;
    }
}
