package pkg2024ft22;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorCuatrimestres extends ModeloCuatrimestre {
    Scanner teclado = new Scanner(System.in);
    ArrayList<Cuatrimestre> cuatrimestres = new ArrayList<Cuatrimestre>();
    
    public static void main(String[] args) {
        GestorCuatrimestres gc = new GestorCuatrimestres();
        gc.menu();
    }
    
    public void menu() {
        String op;
        System.out.println("========CUATRIMESTRES========");
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
                case "1" -> {System.out.println("*****************************"); index(); System.out.println("******************************\n");}
                case "2" -> {System.out.println("*****************************"); create(); System.out.println("*****************************\n");}
                case "3" -> {System.out.println("*****************************");; update(); System.out.println("*****************************\n");}
                case "4" -> {System.out.println("*****************************"); delete(); System.out.println("*****************************\n");}
                case "5" -> {System.out.println("*****************************"); search(); System.out.println("*****************************\n");}
                case "0" -> System.out.println("SALIENDO DE CUATRIMESTRES...\n");
                default -> System.out.println("Opcion Invalida, Por favor seleccione una opcion valida.\n");    
            }
        } while(!"0".equals(op));  
    }
    
    public void index() {     
        this.cuatrimestres = this.consulta();
        
        System.out.println("---Cuatrimestres Existentes---");
        System.out.println("=======================");
        System.out.println("| ID | Cuatrimestre   |");
        System.out.println("=======================");
        for(Cuatrimestre temp : this.cuatrimestres) {
            System.out.format("| %2d | %-15s|\n",
                    temp.getId(),
                    temp.getNombre()
                    );        
        }
        System.out.println("=======================");
    }
    
    public void create() {
        System.out.println("------Crear Cuatrimestre------");
        this.index();
        this.crear();
        
    }
    
    public void read() {}
    
    public void update() {
        System.out.println("---Actualizar Cuatrimestre---");
        this.index();
        this.actualizar();
       
    }
    
    public void delete() {
        System.out.println("----Eliminar Cuatrimestre----");
        this.index();
        this.borrar();
    }
    
    public void search() {
        System.out.println("-----Buscar Cuatrimestre-----");
        this.cuatrimestres = this.buscar();
        
        System.out.println("=======================");
        System.out.println("| ID | Cuatrimestre   |");
        System.out.println("=======================");
        for(Cuatrimestre temp : this.cuatrimestres) {
         System.out.format("| %2d | %-15s|\n",
                 temp.getId(),
                 temp.getNombre());   
        }
        System.out.println("=======================");
    }
}
