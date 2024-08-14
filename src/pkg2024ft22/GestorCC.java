package pkg2024ft22;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorCC extends ModeloCC {
    Scanner teclado = new Scanner(System.in);
    ArrayList<CC> cc = new ArrayList<CC>();
        
    public static void main(String[] args) {
        GestorCC gestorCC = new GestorCC();
        gestorCC.menu();
    }
    
    public void menu() {
        String op;
        System.out.println("=====CICLO_CUATRIMESTRE=====");
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
                            6. Ciclos
                            7. Cuatrimestres                             
                            """);
            op = teclado.next();
            System.out.println("");
            
            switch(op) {
                case "1" -> {System.out.println("*****************************"); index(); System.out.println("*****************************\n");}
                case "2" -> {System.out.println("*****************************"); create(); System.out.println("*****************************\n");}
                case "3" -> {System.out.println("*****************************"); update(); System.out.println("*****************************\n");}
                case "4" -> {System.out.println("*****************************"); delete(); System.out.println("*****************************\n");}
                case "5" -> {System.out.println("*****************************"); search(); System.out.println("*****************************\n");}
                case "0" -> System.out.println("SALIENDO DE CICLO_CUATRIMESTRE...\n");
                case "6" -> gestorCiclos.menu();
                case "7" -> gestorCuatris.menu();
                default -> System.out.println("Opcion Invalida, Por favor seleccione una opcion valida.\n");    
            }
        } while(!"0".equals(op));  
    }
    
    public void index() { 
        System.out.println("--Ciclo_Cuatrimestre Existentes--");
        
        this.cc = this.consulta();
        System.out.println("========================================");
        System.out.println("| ID | Ciclo          | Cuatrimestre   |");
        System.out.println("========================================");
        for(CC temp : this.cc) {
            System.out.format("| %2d | %-15s| %-15s|\n",
                temp.getId(),
                temp.getNombre(),
                temp.getCuatri()
                );
        }
        System.out.println("========================================");
    }
    
    public void create() {
        System.out.println("--Crear Ciclo_Cuatrimestre--");
        this.crear();
    
    }
    
    public void delete() {
        System.out.println("---Eliminar Ciclo_Cuatrimestre---");
        this.index();
        this.borrar();
       
    }
    
    public void search() {
        System.out.println("--Buscar Ciclo_Cuatrimestre--");
        
        this.cc = this.buscar();
        System.out.println("========================================");
        System.out.println("| ID | Ciclo          | Cuatrimestre   |");
        System.out.println("========================================");
        for(CC temp : this.cc) {
            System.out.format("| %2d | %-15s| %-15s|\n",
                temp.getId(),
                temp.getNombre(),
                temp.getCuatri()
                );
        }
        System.out.println("========================================");
    
    }
    
    public void update() {
        System.out.println("--Actualizar Ciclo_Cuatrimestre--");
        this.index();
        this.actualizar();
    }   
}
