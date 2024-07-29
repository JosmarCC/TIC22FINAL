package pkg2024ft22;

public class Carrera {
    String nombre;
    int id;
    
    Carrera(int id, String nuevoNombre) {
        this.id = id;
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
    
    public void setId(int id) {
        this.id = id;
    }
}
