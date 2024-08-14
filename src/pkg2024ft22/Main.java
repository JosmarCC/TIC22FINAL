    package pkg2024ft22;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        GestorCC gestorCC = new GestorCC();
        GestorGrupos gestorGrupos = new GestorGrupos();
        GestorAsignaciones gestorAsignaciones = new GestorAsignaciones(); 
        GestorAlumnos gestorAlumnos = new GestorAlumnos();
        OUTER:
        do {
            System.out.println("""
                                Seleccione la clase a modificar:
                                1. Ciclo_Cuatrimestre
                                2. Grupos
                                3. Asignaciones
                                4. Alumnos
                                0. Salir
                                """);
            int res = teclado.nextInt();
            switch (res) {
                case 1 ->gestorCC.menu();
                case 2 -> gestorGrupos.menu();
                case 3 -> gestorAsignaciones.menu();
                case 4 -> gestorAlumnos.menu();
                case 0 -> {
                    System.out.println("---SALIENDO...---");
                    break OUTER;
                }
                default ->
                    System.out.println("Por favor seleccione una opcion valida");
            }
        } while (true);
    }
}