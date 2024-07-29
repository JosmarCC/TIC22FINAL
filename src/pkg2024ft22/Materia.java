package pkg2024ft22;

public class Materia {
    int id;
    String nombre;
    int unidad;
    
    Materia (int id, String nombre, int unidad) {
        this.id = id;
        this.nombre = nombre;
        this.unidad = unidad;
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

    public int getUnidad() {
        return unidad;
    }

    public void setUnidad(int unidad) {
        this.unidad = unidad;
    }
    
    
}
