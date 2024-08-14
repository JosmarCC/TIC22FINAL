package pkg2024ft22;

import java.util.ArrayList;
import java.util.Scanner;

public class GestorAlumnos extends ModeloAlumno{
    
    Scanner teclado = new Scanner(System.in);
    ArrayList<Alumno> alumno = new ArrayList<Alumno>();
    GestorGrupos ggp = new GestorGrupos();
    
    public static void main(String[] args) {
        GestorAlumnos gestorCC = new GestorAlumnos();
        gestorCC.menu();
    }
    
    public void menu() {
        String op;
        System.out.println("=====ALUMNOS=====");
        do {
            System.out.print("""
                            Seleccione la opcion deseada: 
                            1. Index
                            2. Create  
                            3. Update
                            4. Delete
                            5. Search
                            0. Exit
                            """);
            op = teclado.next();
            System.out.println("");
            
            switch(op) {
                case "1" -> {System.out.println("*****************************"); index(); System.out.println("*****************************\n");}
                case "2" -> {System.out.println("*****************************"); create(); System.out.println("*****************************\n");}
                case "3" -> {System.out.println("*****************************"); update(); System.out.println("*****************************\n");}
                case "4" -> {System.out.println("*****************************"); delete(); System.out.println("*****************************\n");}
                case "5" -> {System.out.println("*****************************"); search(); System.out.println("*****************************\n");}
                case "0" -> System.out.println("SALIENDO DE ALUMNOS...\n");

                default -> System.out.println("Opcion Invalida, Por favor seleccione una opcion valida.\n");    
            }
        } while(!"0".equals(op));  
    }
    
    public void index() { 
        System.out.println("--Alumnos Existentes--");
        
        this.alumno = this.consulta();
        System.out.println("===============================================================");
        System.out.println("| ID | Nombre              | Grupo          | Estatus         |");
        System.out.println("===============================================================");
        for(Alumno temp : this.alumno) {
            System.out.format("| %2d | %-20s| %-15s| %-15s |\n",
                temp.getId(),
                temp.getNombre(),
                temp.getId_grupo(),
                temp.getEstatus());
        }
        System.out.println("===============================================================");
    }
    
    public void create() {
        System.out.println("--Crear Alumno--");
        this.crear();
    
    }
    
    public void update() {
        System.out.println("--Actualizar Alumno--");
        this.index();
        this.actualizar();
    }
    
    public void delete() {
        System.out.println("---Eliminar Alumno---");
        this.index();
        this.borrar();
       
    }
    
    public void search() {
        System.out.println("--Buscar Alumno--");
        
        this.alumno = this.buscar();
        System.out.println("===============================================================");
        System.out.println("| ID | Nombre              | Grupo          | Estatus         |");
        System.out.println("===============================================================");
        for(Alumno temp : this.alumno) {
            System.out.format("| %2d | %-20s| %-15s| %-15s |\n",
                temp.getId(),
                temp.getNombre(),
                temp.getId_grupo(),
                temp.getEstatus());
        }
        System.out.println("===============================================================");
    
    }
}
