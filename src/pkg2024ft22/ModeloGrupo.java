package pkg2024ft22;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ModeloGrupo extends Conexion {
    Scanner teclado = new Scanner(System.in);
    Grupo grupo;
    
    ArrayList<Grupo> carteraGrupos = new ArrayList<Grupo>();
    GestorCarreras gestorCarrera = new GestorCarreras();
    GestorCC gestorCC = new GestorCC();
    
    public ArrayList consulta() {
        this.conectar();
        try {
            this.carteraGrupos.clear();
            this.ps = this.cnx.prepareStatement("""
                                                SELECT grupos.id, grupos.nombre, grupos.id_ciclo_cuatri, carreras.nombre AS nombreCarrera
                                                FROM grupos
                                                INNER JOIN carreras ON grupos.id_carrera = carreras.id
                                                ORDER BY grupos.id ASC;
                                                """);
            this.rs = this.ps.executeQuery();

            while (rs.next()) {
                this.grupo = new Grupo(rs.getInt("id"), rs.getString("nombre"), rs.getInt("id_ciclo_cuatri"), rs.getString("nombreCarrera"));
                this.carteraGrupos.add(grupo);
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return this.carteraGrupos;
    }
    
    public void crear() {
        this.conectar();
        try {
            this.ps = this.cnx.prepareStatement("INSERT INTO grupos (nombre, id_ciclo_cuatri, id_carrera) VALUES (?, ?, ?)");

            System.out.println("Ingrese el nombre del nuevo Grupo ");
            String nombreGrupo = teclado.next();
            this.ps.setString(1, nombreGrupo);
            System.out.println("");

            gestorCC.index();
            System.out.println("Ingrese el ID del Ciclo_Cuatrimestre a utilizar");
            int idCC = teclado.nextInt();
            this.ps.setInt(2, idCC);
            System.out.println("");
              
            gestorCarrera.index();
            System.out.println("Ingrese el ID de la Carrera a utilizar"); 
            int idCarrera = teclado.nextInt();
            System.out.println("");
            this.ps.setInt(3, idCarrera);

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
            this.ps = this.cnx.prepareStatement("UPDATE grupos SET nombre = ?, id_ciclo_cuatri = ?, id_carrera = ? WHERE id = ?");

            System.out.println("Ingrese el ID del Grupo a actualizar: ");
            int idActu = teclado.nextInt();
            ps.setInt(4, idActu);
            System.out.println("");

            System.out.println("Ingrese el nombre del Grupo ");
            String nombre = teclado.next();
            this.ps.setString(1, nombre);
            System.out.println("");

            gestorCC.index();
            System.out.println("Ingrese el ID del Ciclo_Cuatrimestre a utilizar");
            int idCC = teclado.nextInt();
            this.ps.setInt(2, idCC);
            System.out.println("");
            
            gestorCarrera.index();
            System.out.println("Ingrese el ID de la Carrera a utilizar");
            int idCarrera = teclado.nextInt();
            this.ps.setInt(3, idCarrera);

            int filasActualizadas = ps.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("El Grupo con ID " + idActu + " se actualizo correctamente");
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
            this.ps = this.cnx.prepareStatement("DELETE FROM grupos WHERE id = (?)");

            System.out.println("Seleccione el ID a eliminar: ");
            int idGrupo = teclado.nextInt();

            ps.setInt(1, idGrupo);
            int filasEliminadas = this.ps.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("El Grupo con ID " + idGrupo + " se elimino correctamente");
            } else {
                System.out.println("No se encontro ningun Grupo con el ID " + idGrupo);
            }
            System.out.println("");
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }
    
        public ArrayList buscar() {
        this.conectar();

        try {
            this.carteraGrupos.clear();
            System.out.println("Escribe el nombre del Grupo a buscar");
            String res = teclado.next();

            this.ps = this.cnx.prepareStatement("""
                                                SELECT grupos.id, grupos.nombre, grupos.id_ciclo_cuatri, carreras.nombre AS nombreCarrera
                                                FROM grupos
                                                INNER JOIN carreras ON grupos.id_carrera = carreras.id
                                                WHERE grupos.nombre LIKE ?
                                                ORDER BY grupos.id ASC;
                                                """);
            ps.setString(1, "%" + res + "%");
            System.out.println("");
            
            this.rs = this.ps.executeQuery();
            System.out.println("Coincidencias...");
            while (rs.next()) {
                this.grupo = new Grupo(rs.getInt("id"), rs.getString("nombre"), rs.getInt("id_ciclo_cuatri"), rs.getString("nombreCarrera"));
                this.carteraGrupos.add(grupo);
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return this.carteraGrupos;
    }
}
