package pkg2024ft22;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ModeloCuatrimestre extends Conexion{
    Cuatrimestre cuatrimestre;
    Scanner teclado = new Scanner(System.in);
    ArrayList<Cuatrimestre> carteraCuatrimestre = new ArrayList<Cuatrimestre>();
    
    public ArrayList consulta() {
        this.conectar();
        
        try {
            this.carteraCuatrimestre.clear();
            this.ps = this.cnx.prepareStatement("SELECT * FROM cuatrimestres");
            this.rs = this.ps.executeQuery();
            
            while (rs.next()) {
                this.cuatrimestre = new Cuatrimestre(rs.getInt("id"), rs.getString("nombre"));
                this.carteraCuatrimestre.add(cuatrimestre);
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }  
        return this.carteraCuatrimestre;
    }
    
    public void crear() {
        this.conectar();
        
        try {
            this.carteraCuatrimestre.clear();
            this.ps = this.cnx.prepareStatement("INSERT INTO cuatrimestres (nombre) VALUES (?)");
            System.out.println("Ingrese el nombre del nuevo Cuatrimestre: ");
            String nombreCuatri = teclado.next();      
            System.out.println("");
            
            ps.setString(1, nombreCuatri);
            int filasCreadas = ps.executeUpdate(); 
            if (filasCreadas > 0) {
                System.out.println("El Cuatrimestre con nombre " + nombreCuatri + " se creo correctamente");
            } else {
                System.out.println("Lo sentimos, no se ha podido crear el Cuatrimestre " + nombreCuatri);
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        } 
    }
    
    public void actualizar() {
        this.conectar();
        
        try {
            this.ps = this.cnx.prepareStatement("UPDATE cuatrimestres SET nombre = ? WHERE id = ?");
            System.out.println("Ingrese el ID del cuatrimestre a actualizar: ");
            int idCuatri = teclado.nextInt();    
            ps.setInt(2, idCuatri);
            
            System.out.println("Ingrese el nuevo nombre del Cuatrimestres: ");
            String nuevoNombre = teclado.next();
            ps.setString(1, nuevoNombre);
            System.out.println("");
            
            
            int filasActualizadas = ps.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("El Cuatrimestres con ID " + idCuatri + " se actualizado correctamente");
            } else {
                System.out.println("No se encontro ningun Cuatrimestres con el ID " + idCuatri);
            }   
            
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }    
    
    public void borrar() {
        this.conectar();
        
        try {
            this.ps = this.cnx.prepareStatement("DELETE FROM cuatrimestres WHERE id = (?)");
            
            System.out.println("Seleccione el ID a eliminar: ");
            int idCuatri = teclado.nextInt();
            System.out.println("");
            
            ps.setInt(1, idCuatri);
            int filasEliminadas = this.ps.executeUpdate();
    
            if (filasEliminadas > 0) {
                System.out.println("El Cuatrimestre con ID " + idCuatri + " se elimino correctamente");
            } else {
                System.out.println("No se encontro ningun Cuatrimestre con el ID " + idCuatri);
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        } 
    }
    
    public ArrayList buscar() {
        this.conectar();  
               
        try {
            this.carteraCuatrimestre.clear();
            this.ps = this.cnx.prepareStatement("SELECT * FROM cuatrimestres WHERE nombre LIKE ?");
           
            System.out.println("Escribe el Cuatrimestre a buscar: ");
            String cuatriBuscado = teclado.next();
            ps.setString(1, "%" + cuatriBuscado + "%");  
            System.out.println("");
            this.rs = this.ps.executeQuery();
            
            boolean encontrado = false;
            
            while(this.rs.next()) {
                System.out.println("Coincidencias...");
                this.cuatrimestre = new Cuatrimestre(rs.getInt("id"), rs.getString("nombre"));
                this.carteraCuatrimestre.add(cuatrimestre);
                encontrado = true;  
            }
            
            
            if (encontrado == false) {
                System.out.println("Lo sentimos, No se encontro ningun Cuatrimestre con el nombre: " + cuatriBuscado);
                System.out.println("");
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }  
        return this.carteraCuatrimestre;            
    }
}

