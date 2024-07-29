package pkg2024ft22;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ModeloAlumno extends Conexion {
    
    Scanner teclado = new Scanner(System.in);
    Alumno alumno;
    ArrayList<Alumno> carteraAlumnos = new ArrayList<Alumno>();
    
    GestorGrupos gestorGrupo = new GestorGrupos();
    
    public ArrayList consulta() {
        this.conectar();
        try {
            this.carteraAlumnos.clear();
            this.ps = this.cnx.prepareStatement("""
                                                SELECT alumnos.id, alumnos.nombre, grupos.nombre AS nombreGrupo, alumnos.descripcion
                                                FROM alumnos
                                                INNER JOIN grupos ON alumnos.id_grupo = grupos.id
                                                ORDER BY alumnos.id ASC;
                                                """);
            this.rs = this.ps.executeQuery();

            while (rs.next()) {
                this.alumno = new Alumno(rs.getInt("id"), rs.getString("nombre"), rs.getString("nombreGrupo"), rs.getString("descripcion"));
                this.carteraAlumnos.add(alumno);
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return this.carteraAlumnos;
    }
    
    public void crear() {
        this.conectar();
        try {
            this.ps = this.cnx.prepareStatement("INSERT INTO alumnos (nombre, id_grupo, descripcion) VALUES (?, ?, ?)");
            

            System.out.println("Ingrese el nombre del nuevo Alumno");
            String nombre = teclado.next();
            this.ps.setString(1, nombre);
            System.out.println("");

            gestorGrupo.index();
            System.out.println("Ingrese el ID del grupo a utilizar");
            int idGrupo = teclado.nextInt();
            this.ps.setInt(2, idGrupo);
            
            System.out.println("Agregre una breve descripcion al Alumno");
            String des = teclado.next();
            this.ps.setString(3, des);  
            
            int filasCreadas = ps.executeUpdate();
            if (filasCreadas > 0) {
                System.out.println("se creo correctamente");
            } else {
                System.out.println("Lo sentimos");
            }

        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }
    
    public void actualizar() {
        this.conectar();

        try {
            this.ps = this.cnx.prepareStatement("UPDATE alumnos SET nombre = ?, id_grupo = ?, descripcion = ? WHERE id = ?");

            System.out.println("Ingrese el ID del Alumno a actualizar: ");
            int idActu = teclado.nextInt();
            System.out.println("");
            ps.setInt(4, idActu);

            System.out.println("Ingrese el nuevo nombre del Alumno");
            String nombre = teclado.next();
            System.out.println("");
            this.ps.setString(1, nombre);

            gestorGrupo.index();
            System.out.println("Ingrese el ID del Grupo a utilizar");
            int idGrupo = teclado.nextInt();
            System.out.println("");
            this.ps.setInt(2, idGrupo);
            
            System.out.println("Ingrese la nueva Descripcion");
            String des = teclado.next();
            this.ps.setString(3, des);

            int filasActualizadas = ps.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("El Alumno con ID " + idActu + " se actualizo correctamente");
            } else {
                System.out.println("Lo sentimos, no se pudo actualizar");
            }

        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }
    
    public void borrar() {
        this.conectar();

        try {
            this.ps = this.cnx.prepareStatement("DELETE FROM alumnos WHERE id = (?)");

            System.out.println("Seleccione el ID a eliminar: ");
            int idAlu = teclado.nextInt();

            ps.setInt(1, idAlu);
            int filasEliminadas = this.ps.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("El Alumno con ID " + idAlu + " se elimino correctamente");
            } else {
                System.out.println("No se encontro ningun Alumno con el ID " + idAlu);
            }
            System.out.println("");
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }
    
    public ArrayList buscar() {
        this.conectar();

        try {
            this.carteraAlumnos.clear();
             this.ps = this.cnx.prepareStatement("""
                                                SELECT alumnos.id, alumnos.nombre, grupos.nombre AS nombreGrupo, alumnos.descripcion
                                                FROM alumnos
                                                INNER JOIN grupos ON alumnos.id_grupo = grupos.id
                                                WHERE alumnos.nombre LIKE ?
                                                ORDER BY alumnos.id ASC;
                                                """);
            
            System.out.println("Ingrese el alumno a buscar");
            String nombreAlu = teclado.next();
            ps.setString(1, "%" + nombreAlu + "%");
            System.out.println("");
            
            this.rs = this.ps.executeQuery();
            System.out.println("Coincidencias...");
            while (rs.next()) {
                this.alumno = new Alumno(rs.getInt("id"), rs.getString("nombre"), rs.getString("nombreGrupo"), rs.getString("descripcion"));
                this.carteraAlumnos.add(alumno);
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return this.carteraAlumnos;
    }
        
}
