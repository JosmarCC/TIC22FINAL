package pkg2024ft22;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ModeloMateria extends Conexion {
  
    Materia materia;
    Scanner teclado = new Scanner(System.in);

    ArrayList<Materia> carteraMaterias = new ArrayList<Materia>();
    
    public ArrayList consulta() {
        this.conectar();

        try {
            this.carteraMaterias.clear();
            this.ps = this.cnx.prepareStatement("SELECT * FROM materias");
            this.rs = this.ps.executeQuery();

            while (rs.next()) {
                this.materia = new Materia(rs.getInt("id"), rs.getString("nombre"));
                this.carteraMaterias.add(materia);
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return this.carteraMaterias;
    }
    
    public void crear() {
        this.conectar();

        try {
            this.carteraMaterias.clear();
            this.ps = this.cnx.prepareStatement("INSERT INTO materias (nombre) VALUES (?)");

            System.out.println("Ingrese el nombre de la nueva Materia: ");
            String nombreMateria = teclado.next();
            ps.setString(1, nombreMateria);

            int filasCreadas = ps.executeUpdate();
            if (filasCreadas > 0) {
                System.out.println("La Materia con nombre " + nombreMateria + " se creo correctamente");
            } else {
                System.out.println("Lo sentimos, no se ha podido crear el Materia " + nombreMateria);
            }
            System.out.println("");
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }
    
    public void actualizar() {
        this.conectar();

        try {
            this.ps = this.cnx.prepareStatement("UPDATE materias SET nombre = (?) WHERE id = (?)");

            System.out.println("Ingrese el ID de la Materia a actualizar: ");
            int idMateria = teclado.nextInt();
            ps.setInt(2, idMateria);

            System.out.println("Ingrese el nuevo nombre de la Materia: ");
            String nuevoNombre = teclado.next();
            ps.setString(1, nuevoNombre);            

            int filasActualizadas = ps.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("La Materia con ID " + idMateria + " se actualizo correctamente");
            } else {
                System.out.println("No se encontro ninguna Materia con el ID " + idMateria);
            }
            System.out.println("");

        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }
    
    public void borrar() {
        this.conectar();

        try {
            this.ps = this.cnx.prepareStatement("DELETE FROM materias WHERE id = (?)");

            System.out.println("Seleccione el ID a eliminar: ");
            int idMateria = teclado.nextInt();
            ps.setInt(1, idMateria);

            int filasEliminadas = this.ps.executeUpdate();
            if (filasEliminadas > 0) {
                System.out.println("La Materia con ID " + idMateria + " se elimino correctamente");
            } else {
                System.out.println("No se encontro ninguna Materia con el ID " + idMateria);
            }
            System.out.println("");
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }
    
    public ArrayList buscar() {
        this.conectar();

        try {
            this.carteraMaterias.clear();
            this.ps = this.cnx.prepareStatement("SELECT * FROM materias WHERE nombre LIKE ?");

            System.out.println("Escribe la Materia a buscar: ");
            String materiaBuscada = teclado.next();
            ps.setString(1, "%" + materiaBuscada + "%");
            System.out.println("");

            this.rs = this.ps.executeQuery();
            boolean encontrado = false;
            
            System.out.println("Coincidencias...");
            while (this.rs.next()) {    
                this.materia = new Materia(rs.getInt("id"), rs.getString("nombre"));
                this.carteraMaterias.add(materia);
                encontrado = true;
            }

            if (encontrado == false) {
                System.out.println("Lo sentimos, No se encontro ninguna Materia con el nombre: " + materiaBuscada);
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return this.carteraMaterias;
    }
}
