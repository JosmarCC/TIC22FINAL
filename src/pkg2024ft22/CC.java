package pkg2024ft22;


public class CC {
    int id;
    String nombre;
    String cuatri;
    
    public CC(int id, String nombreCiclo, String nombreCuatri) {
        this.id = id;
        this.nombre = nombreCiclo;
        this.cuatri = nombreCuatri;
                
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

    public String getCuatri() {
        return cuatri;
    }

    public void setCuatri(String cuatri) {
        this.cuatri = cuatri;
    }

}

