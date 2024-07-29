package pkg2024ft22;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ModeloCiclo extends Conexion {
    Ciclo ciclo;
    Scanner teclado = new Scanner(System.in);
    ArrayList<Ciclo> carteraCiclos = new ArrayList<Ciclo>();
            
    public ArrayList consulta() {
        this.conectar();
        
        try {
            this.carteraCiclos.clear();
            this.ps = this.cnx.prepareStatement("SELECT * FROM ciclos");
            this.rs = this.ps.executeQuery();
                 
            while (rs.next()) {
                this.ciclo = new Ciclo(rs.getInt("id"), rs.getString("nombre"));
                this.carteraCiclos.add(ciclo);
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }  
        return this.carteraCiclos;
    }
    
    public void crear() {
        this.conectar();
   
        try {
            this.carteraCiclos.clear();
            this.ps = this.cnx.prepareStatement("INSERT INTO ciclos (nombre) VALUES (?)");
            System.out.println("Ingrese el nombre del nuevo Ciclo: ");
            String nombreCiclo = teclado.next();
            
            ps.setString(1, nombreCiclo);
            int filasCreadas = ps.executeUpdate(); 
            if (filasCreadas > 0) {
                System.out.println("El Ciclo con nombre " + nombreCiclo + " se creo correctamente");
            } else {
                System.out.println("Lo sentimos, no se ha podido crear el Ciclo " + nombreCiclo);
            }
            System.out.println("");    
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        } 
    }
    
    public void actualizar() {
        this.conectar();
        
        try {
            this.ps = this.cnx.prepareStatement("UPDATE ciclos SET nombre = ? WHERE id = ?");
            System.out.println("Ingrese el ID del ciclo a actualizar: ");
            int idCiclo = teclado.nextInt();    
            ps.setInt(2, idCiclo);
            
            System.out.println("Ingrese el nuevo nombre del Ciclo: ");
            String nuevoNombre = teclado.next();
            ps.setString(1, nuevoNombre);
            
            int filasActualizadas = ps.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("El Ciclo con ID " + idCiclo + " se actualizo correctamente");
            } else {
                System.out.println("No se encontro ningun Ciclo con el ID " + idCiclo);
            } 
            System.out.println("");
            
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }    
    
    public void borrar() {
        this.conectar();
        
        try {
            this.ps = this.cnx.prepareStatement("DELETE FROM ciclos WHERE id = ?");
            
            System.out.println("Seleccione el ID a eliminar: ");
            int idCiclo = teclado.nextInt();
            
            ps.setInt(1, idCiclo);
            int filasEliminadas = this.ps.executeUpdate();
    
            if (filasEliminadas > 0) {
                System.out.println("El Ciclo con ID " + idCiclo + " se elimino correctamente");
            } else {
                System.out.println("No se encontro ningun Ciclo con el ID " + idCiclo);
            }
            System.out.println("");
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        } 
    }
    
    public ArrayList buscar() {
        this.conectar();
        
        try {
            this.carteraCiclos.clear();
            this.ps = this.cnx.prepareStatement("SELECT * FROM ciclos WHERE nombre LIKE ?");
            
            System.out.println("Escribe el Ciclo a buscar: ");
            String cicloBuscado = teclado.next();
            System.out.println("");
            ps.setString(1, "%" + cicloBuscado + "%");
            this.rs = this.ps.executeQuery();
            
            boolean encontrado = false;
            
            System.out.println("Coincidencias...");
            while(this.rs.next()) {
               
                this.ciclo = new Ciclo(rs.getInt("id"), rs.getString("nombre"));
                this.carteraCiclos.add(ciclo);
                encontrado = true;  
            }
            
            if (encontrado == false) {
                System.out.println("Lo sentimos, No se encontro ningun Ciclo con el nombre: " + cicloBuscado);
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }  
        return this.carteraCiclos;            
    }
}
