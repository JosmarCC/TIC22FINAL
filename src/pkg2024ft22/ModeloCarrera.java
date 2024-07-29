package pkg2024ft22;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ModeloCarrera extends Conexion {

    Carrera carrera;
    Scanner teclado = new Scanner(System.in);
    ArrayList<Carrera> carteraCarrera = new ArrayList<Carrera>();

    public ArrayList consulta() {
        this.conectar();

        try {
            this.carteraCarrera.clear();
            this.ps = this.cnx.prepareStatement("SELECT * FROM carreras");
            this.rs = this.ps.executeQuery();

            while (rs.next()) {
                this.carrera = new Carrera(rs.getInt("id"), rs.getString("nombre"));
                this.carteraCarrera.add(carrera);
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return this.carteraCarrera;
    }

    public void crear() {
        this.conectar();

        try {
            this.carteraCarrera.clear();
            this.ps = this.cnx.prepareStatement("INSERT INTO carreras (nombre) VALUES (?)");
            System.out.println("Ingrese el nombre de la nueva carrera ");
            String nombreCarrera = teclado.next();
            System.out.println("");

            ps.setString(1, nombreCarrera);
            int filasCreadas = ps.executeUpdate();
            if (filasCreadas > 0) {
                System.out.println("La Carrera con nombre " + nombreCarrera + " se creo correctamente");
            } else {
                System.out.println("Lo sentimos, no se ha podido crear el Carrera " + nombreCarrera);
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    public void actualizar() {
        this.conectar();

        try {
            this.ps = this.cnx.prepareStatement("UPDATE carreras SET nombre = ? WHERE id = ?");
            System.out.println("Ingrese el ID del carrera a actualizar: ");
            int idCarrera = teclado.nextInt();
            ps.setInt(2, idCarrera);

            System.out.println("Ingrese el nuevo nombre de la carrera: ");
            String nuevoNombre = teclado.next();
            ps.setString(1, nuevoNombre);
            System.out.println("");

            int filasActualizadas = ps.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("La Carrera con ID " + idCarrera + " se actualizado correctamente");
            } else {
                System.out.println("No se encontro ninguna Carrera con el ID " + idCarrera);
            }

        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    public void borrar() {
        this.conectar();

        try {
            this.ps = this.cnx.prepareStatement("DELETE FROM carreras WHERE id = (?)");

            System.out.println("Seleccione el ID a eliminar: ");
            int idCarrera = teclado.nextInt();
            System.out.println("");

            ps.setInt(1, idCarrera);
            int filasEliminadas = this.ps.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("La Carrera con ID " + idCarrera + " se elimino correctamente");
            } else {
                System.out.println("No se encontro ninguna Carrera con el ID " + idCarrera);
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    public ArrayList buscar() {
        this.conectar();

        System.out.println("Escribe la carrea a buscar: ");
        String carreraBuscada = teclado.next();
        System.out.println("");

        try {
            this.carteraCarrera.clear();
            this.ps = this.cnx.prepareStatement("SELECT * FROM carreras WHERE nombre LIKE ?");
            ps.setString(1, carreraBuscada);
            this.rs = this.ps.executeQuery();

            boolean encontrado = false;

            while (this.rs.next()) {
                System.out.println("Coincidencias...");
                this.carrera = new Carrera(rs.getInt("id"), rs.getString("nombre"));
                this.carteraCarrera.add(carrera);
                encontrado = true;
            }

            if (encontrado == false) {
                System.out.println("Lo sentimos, No se encontro ninguna Carrera con el nombre: " + carreraBuscada);
                System.out.println("");
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return this.carteraCarrera;
    }
}
