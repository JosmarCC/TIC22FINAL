package pkg2024ft22;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ModeloCC extends Conexion {

    Scanner teclado = new Scanner(System.in);
    CC cc;
    ArrayList<CC> carteraCC = new ArrayList<CC>();

    GestorCiclos gestorCiclos = new GestorCiclos();
    GestorCuatrimestres gestorCuatris = new GestorCuatrimestres();

    public ArrayList consulta() {
        this.conectar();
        try {
            this.carteraCC.clear();
            this.ps = this.cnx.prepareStatement("""
                                                SELECT ciclo_cuatri.id, ciclos.nombre AS nombreCiclo, cuatrimestres.nombre AS nombreCuatri
                                                FROM ciclo_cuatri
                                                INNER JOIN ciclos ON ciclo_cuatri.id_ciclo = ciclos.id
                                                INNER JOIN cuatrimestres ON ciclo_cuatri.id_cuatri = cuatrimestres.id
                                                ORDER BY ciclo_cuatri.id ASC;
                                                """);
            this.rs = this.ps.executeQuery();

            while (rs.next()) {
                this.cc = new CC(rs.getInt("id"), rs.getString("nombreCiclo"), rs.getString("nombreCuatri"));
                this.carteraCC.add(cc);
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return this.carteraCC;
    }

    public void crear() {
        this.conectar();
        try {
            this.ps = this.cnx.prepareStatement("INSERT INTO ciclo_cuatri (id_ciclo, id_cuatri) VALUES (?, ?)");
            gestorCiclos.index();

            System.out.println("Ingrese el ID del Ciclo a utilizar");
            int idCiclo = teclado.nextInt();
            this.ps.setInt(1, idCiclo);
            System.out.println("");

            gestorCuatris.index();
            System.out.println("Ingrese el ID del Cuatrimestre a utilizar");
            int idCuatri = teclado.nextInt();
            System.out.println("");

            this.ps.setInt(2, idCuatri);

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
            this.ps = this.cnx.prepareStatement("UPDATE ciclo_cuatri SET id_ciclo = ?, id_cuatri = ? WHERE id = ?");

            System.out.println("Ingrese el ID del Ciclo_Cuatrimestre a actualizar: ");
            int idActu = teclado.nextInt();
            System.out.println("");
            ps.setInt(3, idActu);

            gestorCiclos.index();
            System.out.println("Ingrese el ID del Ciclo a utilizar");
            int idCiclo = teclado.nextInt();
            System.out.println("");
            this.ps.setInt(1, idCiclo);

            gestorCuatris.index();
            System.out.println("Ingrese el ID del Cuatrimestre a utilizar");
            int idCuatri = teclado.nextInt();
            System.out.println("");
            this.ps.setInt(2, idCuatri);

            int filasActualizadas = ps.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("El Ciclo_Cuatrimestre con ID " + idCuatri + " se actualizo correctamente");
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
            this.ps = this.cnx.prepareStatement("DELETE FROM ciclo_cuatri WHERE id = (?)");

            System.out.println("Seleccione el ID a eliminar: ");
            int idCicloCuatri = teclado.nextInt();

            ps.setInt(1, idCicloCuatri);
            int filasEliminadas = this.ps.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("El Ciclo_cuatrimestre con ID " + idCicloCuatri + " se elimino correctamente");
            } else {
                System.out.println("No se encontro ningun Ciclo_cuatrimestre con el ID " + idCicloCuatri);
            }
            System.out.println("");
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    public ArrayList buscar() {
        this.conectar();

        try {
            this.carteraCC.clear();
            System.out.println("""
                               Como desear buscar el valor:
                               1. Ciclo
                               2. Cuatrimestre
                               """);
            int res = teclado.nextInt();

            if (res == 1) {
                this.ps = this.cnx.prepareStatement("""
                                                    SELECT ciclo_cuatri.id, ciclos.nombre AS nombreCiclo, cuatrimestres.nombre AS nombreCuatri
                                                    FROM ciclo_cuatri
                                                    INNER JOIN ciclos ON ciclo_cuatri.id_ciclo = ciclos.id
                                                    INNER JOIN cuatrimestres ON ciclo_cuatri.id_cuatri = cuatrimestres.id
                                                    WHERE ciclos.nombre LIKE ?
                                                    ORDER BY ciclo_cuatri.id ASC;
                                                    """);

                System.out.println("Ingrese el Ciclo a buscar: ");

                String nombreCiclo = teclado.next();
                ps.setString(1, "%" + nombreCiclo + "%");
                System.out.println("");

                this.rs = this.ps.executeQuery();
                System.out.println("Coincidencia...");
                while (rs.next()) {
                    this.cc = new CC(rs.getInt("id"), rs.getString("nombreCiclo"), rs.getString("nombreCuatri"));
                    this.carteraCC.add(cc);
                }

            } else if (res == 2) {
                this.ps = this.cnx.prepareStatement("""
                                                    SELECT ciclo_cuatri.id, ciclos.nombre AS nombreCiclo, cuatrimestres.nombre AS nombreCuatri
                                                    FROM ciclo_cuatri
                                                    INNER JOIN ciclos ON ciclo_cuatri.id_ciclo = ciclos.id
                                                    INNER JOIN cuatrimestres ON ciclo_cuatri.id_cuatri = cuatrimestres.id
                                                    WHERE cuatrimestres.nombre LIKE ?
                                                    ORDER BY ciclo_cuatri.id ASC;
                                                    """);

                System.out.println("Ingrese el Cuatrimestre a buscar: ");
                String nombreCuatri = teclado.next().toUpperCase();
                System.out.println("");
                ps.setString(1, "%" + nombreCuatri + "%");
                this.rs = this.ps.executeQuery();

                while (rs.next()) {
                    this.cc = new CC(rs.getInt("id"), rs.getString("nombreCiclo"), rs.getString("nombreCuatri"));
                    this.carteraCC.add(cc);
                }
                System.out.println("========================================");

            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return this.carteraCC;
    }
}
