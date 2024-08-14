package pkg2024ft22;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ModeloAsignacion extends Conexion{
    Scanner teclado = new Scanner(System.in);
    Asignacion asignacion;
    ArrayList<Asignacion> carteraAsignacion = new ArrayList<Asignacion>();
    
    GestorGrupos gestorGrupos = new GestorGrupos();
    GestorMaterias gestorMaterias = new GestorMaterias();
    GestorProfesores gestorProfes = new GestorProfesores();
    
    public ArrayList consulta() {
        this.conectar();
        try {
            this.carteraAsignacion.clear();
            this.ps = this.cnx.prepareStatement("""
                                                SELECT asignaciones.id, grupos.nombre AS nombreGrupo, materias.nombre AS nombreMateria, profesores.nombre AS nombreProfesor
                                                FROM asignaciones
                                                INNER JOIN grupos ON asignaciones.id_grupo = grupos.id
                                                INNER JOIN materias ON asignaciones.id_materia = materias.id
                                                INNER JOIN profesores ON asignaciones.id_profe = profesores.id
                                                ORDER BY asignaciones.id ASC;
                                                """);
            this.rs = this.ps.executeQuery();

            while (rs.next()) {
                this.asignacion = new Asignacion(rs.getInt("id"), rs.getString("nombreGrupo"), rs.getString("nombreMateria"), rs.getString("nombreProfesor"));
                this.carteraAsignacion.add(asignacion);
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return this.carteraAsignacion;
    }
    
    public void crear() {
        this.conectar();
        try {
            this.ps = this.cnx.prepareStatement("INSERT INTO asignaciones (id_grupo, id_materia, id_profe) VALUES (?, ?, ?)");
            gestorGrupos.index();

            System.out.println("Ingrese el ID del Grupo a utilizar"); 
            int idGrupo = teclado.nextInt();
            this.ps.setInt(1, idGrupo);
            System.out.println("");

            gestorMaterias.index();
            System.out.println("Ingrese el ID de la Materia a utilizar");
            int idMateria = teclado.nextInt();           
            this.ps.setInt(2, idMateria);
            System.out.println("");
            
            gestorProfes.index();
            System.out.println("Ingrese el ID del Profesor a utilizar");
            int idProfe = teclado.nextInt();
            this.ps.setInt(3, idProfe);
            System.out.println("");
            
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
            this.ps = this.cnx.prepareStatement("UPDATE asignaciones SET id_grupo = ?, id_materia = ?, id_profe = ? WHERE id = ?");

            System.out.println("Ingrese el ID de la Asignacion a actualizar: ");
            int idActu = teclado.nextInt();
            System.out.println("");
            ps.setInt(4, idActu);

            gestorGrupos.index();
            System.out.println("Ingrese el ID del Grupo a utilizar");
            int idGrupo = teclado.nextInt();
            System.out.println("");
            this.ps.setInt(1, idGrupo);

            gestorMaterias.index();
            System.out.println("Ingrese el ID de la Materia a utilizar");
            int idMateria = teclado.nextInt();
            System.out.println("");
            this.ps.setInt(2, idMateria);
            
            gestorProfes.index();
            System.out.println("Ingrese el ID del Profesor a utilizar");
            int idProfe = teclado.nextInt();
            System.out.println("");
            this.ps.setInt(3, idProfe);

            int filasActualizadas = ps.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("La Asignacion con ID " + idActu + " se actualizo correctamente");
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
            this.ps = this.cnx.prepareStatement("DELETE FROM asignaciones WHERE id = (?)");

            System.out.println("Seleccione el ID a eliminar: ");
            int idAsig = teclado.nextInt();

            ps.setInt(1, idAsig);
            int filasEliminadas = this.ps.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("La Asignacion con ID " + idAsig + " se elimino correctamente");
            } else {
                System.out.println("No se encontro ningun Asignacion con el ID " + idAsig);
            }
            System.out.println("");
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }
    
     public ArrayList buscar() {
        this.conectar();

        try {
            this.carteraAsignacion.clear();
            System.out.println("""
                               Como desear buscar el valor:
                               1. Grupo
                               2. Materia
                               3. Profesor
                               """);
            int res = teclado.nextInt();

            if (res == 1) {
                this.ps = this.cnx.prepareStatement("""
                                                    SELECT asignaciones.id, grupos.nombre AS nombreGrupo, materias.nombre AS nombreMateria, profesores.nombre AS nombreProfesor
                                                    FROM asignaciones
                                                    INNER JOIN grupos ON asignaciones.id_grupo = grupos.id
                                                    INNER JOIN materias ON asignaciones.id_materia = materias.id
                                                    INNER JOIN profesores ON asignaciones.id_profe = profesores.id
                                                    WHERE grupos.nombre LIKE ?
                                                    ORDER BY asignaciones.id ASC;
                                                    """);

                System.out.println("Ingrese el Grupo a buscar: ");

                String nombreGrupo = teclado.next();
                ps.setString(1, "%" + nombreGrupo + "%");
                System.out.println("");

                this.rs = this.ps.executeQuery();
                
                System.out.println("Coincidencia...");
                while (rs.next()) {
                this.asignacion = new Asignacion(rs.getInt("id"), rs.getString("nombreGrupo"), rs.getString("nombreMateria"), rs.getString("nombreProfesor"));
                this.carteraAsignacion.add(asignacion);
                }
            } else if (res == 2) {
                                         this.ps = this.cnx.prepareStatement("""
                                                    SELECT asignaciones.id, grupos.nombre AS nombreGrupo, materias.nombre AS nombreMateria, profesores.nombre AS nombreProfesor
                                                    FROM asignaciones
                                                    INNER JOIN grupos ON asignaciones.id_grupo = grupos.id
                                                    INNER JOIN materias ON asignaciones.id_materia = materias.id
                                                    INNER JOIN profesores ON asignaciones.id_profe = profesores.id
                                                    WHERE materias.nombre LIKE ?
                                                    ORDER BY asignaciones.id ASC;
                                                    """);

                System.out.println("Ingrese la Materia a buscar: ");

                String nombrMateria = teclado.next();
                ps.setString(1, "%" + nombrMateria + "%");
                System.out.println("");

                this.rs = this.ps.executeQuery();
                
                System.out.println("Coincidencia...");
                while (rs.next()) {
                this.asignacion = new Asignacion(rs.getInt("id"), rs.getString("nombreGrupo"), rs.getString("nombreMateria"), rs.getString("nombreProfesor"));
                this.carteraAsignacion.add(asignacion);
                }
            } else if ( res == 3) {
            this.ps = this.cnx.prepareStatement("""
                                                    SELECT asignaciones.id, grupos.nombre AS nombreGrupo, materias.nombre AS nombreMateria, profesores.nombre AS nombreProfesor
                                                    FROM asignaciones
                                                    INNER JOIN grupos ON asignaciones.id_grupo = grupos.id
                                                    INNER JOIN materias ON asignaciones.id_materia = materias.id
                                                    INNER JOIN profesores ON asignaciones.id_profe = profesores.id
                                                    WHERE profesores.nombre LIKE ?
                                                    ORDER BY asignaciones.id ASC;
                                                    """);

                System.out.println("Ingrese el Profesor a buscar: ");

                String nombreProfe = teclado.next();
                ps.setString(1, "%" + nombreProfe + "%");
                System.out.println("");

                this.rs = this.ps.executeQuery();
                
                System.out.println("Coincidencia...");
                while (rs.next()) {
                this.asignacion = new Asignacion(rs.getInt("id"), rs.getString("nombreGrupo"), rs.getString("nombreMateria"), rs.getString("nombreProfesor"));
                this.carteraAsignacion.add(asignacion);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return this.carteraAsignacion;
    }
}
