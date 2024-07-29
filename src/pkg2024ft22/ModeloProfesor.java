package pkg2024ft22;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ModeloProfesor extends Conexion {

    Profesor profesor;
    Scanner teclado = new Scanner(System.in);

    ArrayList<Profesor> carteraProfesores = new ArrayList<Profesor>();

    public ArrayList consulta() {
        this.conectar();

        try {
            this.carteraProfesores.clear();
            this.ps = this.cnx.prepareStatement("SELECT * FROM profesores");
            this.rs = this.ps.executeQuery();

            while (rs.next()) {
                this.profesor = new Profesor(rs.getInt("id"), rs.getString("nombre"));
                this.carteraProfesores.add(profesor);
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return this.carteraProfesores;
    }

    public void crear() {
        this.conectar();

        try {
            this.carteraProfesores.clear();
            this.ps = this.cnx.prepareStatement("INSERT INTO profesores (nombre) VALUES (?)");

            System.out.println("Ingrese el nombre del nuevo Profesor: ");
            String nombreProfesor = teclado.next();
            ps.setString(1, nombreProfesor);

            int filasCreadas = ps.executeUpdate();
            if (filasCreadas > 0) {
                System.out.println("El Profesor con nombre " + nombreProfesor + " se creo correctamente");
            } else {
                System.out.println("Lo sentimos, no se ha podido crear el Profesor " + nombreProfesor);
            }
            System.out.println("");
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    public void actualizar() {
        this.conectar();

        try {
            this.ps = this.cnx.prepareStatement("UPDATE profesores SET nombre = ? WHERE id = ?");

            System.out.println("Ingrese el ID del Profesor a actualizar: ");
            int idProfesor = teclado.nextInt();
            ps.setInt(2, idProfesor);

            System.out.println("Ingrese el nuevo nombre del Profesor: ");
            String nuevoNombre = teclado.next();
            ps.setString(1, nuevoNombre);

            int filasActualizadas = ps.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("El Profesor con ID " + idProfesor + " se actualizo correctamente");
            } else {
                System.out.println("No se encontro ningun Profesor con el ID " + idProfesor);
            }
            System.out.println("");

        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    public void borrar() {
        this.conectar();

        try {
            this.ps = this.cnx.prepareStatement("DELETE FROM profesores WHERE id = ?");

            System.out.println("Seleccione el ID a eliminar: ");
            int idProfesor = teclado.nextInt();
            ps.setInt(1, idProfesor);

            int filasEliminadas = this.ps.executeUpdate();
            if (filasEliminadas > 0) {
                System.out.println("El Profesor con ID " + idProfesor + " se elimino correctamente");
            } else {
                System.out.println("No se encontro ningun Profesor con el ID " + idProfesor);
            }
            System.out.println("");
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    public ArrayList buscar() {
        this.conectar();

        try {
            this.carteraProfesores.clear();
            this.ps = this.cnx.prepareStatement("SELECT * FROM profesores WHERE nombre LIKE ?");

            System.out.println("Escribe el Profesor a buscar: ");
            String profesorBuscado = teclado.next();
            ps.setString(1, "%" + profesorBuscado + "%");
            System.out.println("");

            this.rs = this.ps.executeQuery();
            boolean encontrado = false;

            while (this.rs.next()) {
                System.out.println("Coincidencias...");
                this.profesor = new Profesor(rs.getInt("id"), rs.getString("nombre"));
                this.carteraProfesores.add(profesor);
                encontrado = true;
            }

            if (encontrado == false) {
                System.out.println("Lo sentimos, No se encontro ningun Profesor con el nombre: " + profesorBuscado);
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return this.carteraProfesores;
    }

}
