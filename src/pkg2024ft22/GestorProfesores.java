package pkg2024ft22;
import java.util.Scanner;
import java.util.ArrayList;

public class GestorProfesores extends ModeloProfesor{
    Scanner teclado = new Scanner(System.in);
    ArrayList<Profesor> profesores = new ArrayList<Profesor>();
    
    public static void main(String[] args) {
        GestorProfesores gp = new GestorProfesores();
        gp.menu();   
    }
    
    public void menu() {
        String op;
        System.out.println("===========PROFESORES============");
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
                case "0" -> System.out.println("SALIENDO DE PROFESORES...\n");
                default -> System.out.println("Opcion Invalida, Por favor seleccione una opcion valida.");    
            }
        } while(!"0".equals(op));
        
    }

    public void index() { 
        System.out.println("------Profesores Existentes------");
        this.profesores = this.consulta();
        
        System.out.println("=======================");
        System.out.println("| ID | Profesor       |");
        System.out.println("=======================");
        for(Profesor temp : this.profesores) {
            System.out.format("| %2d | %-15s|\n",
                    temp.getId(),
                    temp.getNombre()
                    );     
        }
        System.out.println("=======================");
    }
    
    public void create() {  
        System.out.println("---------Crear Profesor---------");
        this.index();
        this.crear();
    }
    
    public void update() {
        System.out.println("-------Actualizar Profesor------");
        this.index();
        this.actualizar();
    }
        
    public void delete() {
        System.out.println("---Eliminar Profesor---");
        this.index();
        this.borrar();
    }
    
    public void search() {
        System.out.println("---------Buscar Profesor--------");
        this.profesores = this.buscar();
        
        System.out.println("=======================");
        System.out.println("| ID | Profesor       |");
        System.out.println("=======================");
        for(Profesor temp : this.profesores) {
            System.out.format("| %2d | %-15s|\n",
                 temp.getId(),
                 temp.getNombre());   
                   
        }
        System.out.println("=======================");
    }
   
}
