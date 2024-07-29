package pkg2024ft22;

import java.util.ArrayList;
import java.util.Scanner;

public class GestorAsignaciones extends ModeloAsignacion {
    Scanner teclado = new Scanner(System.in);
    ArrayList<Asignacion> asignacion = new ArrayList<Asignacion>();
    
    GestorProfesores gestorProfes = new GestorProfesores();
    GestorMaterias gestorMaterias = new GestorMaterias();
    
    public static void main(String[] args) {
        GestorAsignaciones gestorAsignaciones = new GestorAsignaciones();
        gestorAsignaciones.menu();
    }
    
    public void menu() {
        String op;
        System.out.println("=====ASIGNACIONES=====");
        do {
            System.out.print("""
                               Seleccione la opcion deseada: 
                               1. Index
                               2. Create  
                               3. Update
                               4. Delete
                               5. Search
                               0. Exit
                               *****************************
                               6. Profesores
                               7. Materias
                               """);
            op = teclado.next();
            System.out.println("");
            
            switch(op) {
                case "1" -> {System.out.println("*****************************"); index(); System.out.println("*****************************\n");}
                case "2" -> {System.out.println("*****************************"); create(); System.out.println("*****************************\n");}
                case "3" -> {System.out.println("*****************************"); update(); System.out.println("*****************************\n");}
                case "4" -> {System.out.println("*****************************"); delete(); System.out.println("*****************************\n");}
                case "5" -> {System.out.println("*****************************"); search(); System.out.println("*****************************\n");}
                case "0" -> System.out.println("SALIENDO DE ASIGNACIONES...\n");
                case "6" -> gestorProfes.menu();
                case "7" -> gestorMaterias.menu();
                default -> System.out.println("Opcion Invalida, Por favor seleccione una opcion valida.\n");    
            }
        } while(!"0".equals(op));  
    }
    
    public void index() { 
        System.out.println("--Asignaciones Existentes--");
        this.asignacion = this.consulta();
        
        System.out.println("=========================================================");
        System.out.println("| ID | Grupo          | Materia        | Profesor       |");
        System.out.println("=========================================================");
        for(Asignacion temp : this.asignacion) {
            System.out.format("| %2d | %-15s| %-15s| %-15s|\n",
                temp.getId(),
                temp.getId_grupo(),
                temp.getId_materia(),
                temp.getId_profesor()
                ); 
        }
        System.out.println("=========================================================");
    }
    
    public void create() {
        System.out.println("--Crear Asignacion--");
        this.crear();
    
    }
    
    public void update() {
        System.out.println("--Actualizar Asignacion--");
        this.index();
        this.actualizar();
    }
    
    public void delete() {
        System.out.println("---Eliminar Asignacion---");
        this.index();
        this.borrar();
       
    }
    
        public void search() {
        System.out.println("--Buscar Asignacion--");
        
        this.asignacion = this.buscar();
        System.out.println("=========================================================");
        System.out.println("| ID | Grupo          | Materia        | Profesor       |");
        System.out.println("=========================================================");
        for(Asignacion temp : this.asignacion) {
            System.out.format("| %2d | %-15s| %-15s| %-15s|\n",
                temp.getId(),
                temp.getId_grupo(),
                temp.getId_materia(),
                temp.getId_profesor()
                ); 
        }
        System.out.println("=========================================================");
    
    }
}
