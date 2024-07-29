package pkg2024ft22;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        GestorCC gestorCC = new GestorCC();
        GestorCarreras gestorCarreras = new GestorCarreras();
        GestorMaterias gestorMaterias = new GestorMaterias();
        GestorProfesores gestorProfesores = new GestorProfesores();

        OUTER:
        do {
            System.out.println("""
                                Seleccione la clase a modificar:
                                1. Ciclos
                                2. Cuatrimestres
                                3. Ciclo_Cuatrimestre
                                4. Carreras
                                5. Materias
                                6. Profesores
                                0. Salir
                                """);
            int res = teclado.nextInt();
            switch (res) {
                case 3 ->
                    gestorCC.menu();
                case 4 ->
                    gestorCarreras.menu();
                case 5 ->
                    gestorMaterias.menu();
                case 6 ->
                    gestorProfesores.menu();
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