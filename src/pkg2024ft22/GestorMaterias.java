package pkg2024ft22;
import java.util.Scanner;
import java.util.ArrayList;

public class GestorMaterias extends ModeloMateria {
    Scanner teclado = new Scanner(System.in);
    
    ArrayList<Materia> materias = new ArrayList<Materia>();
    
    public static void main(String[] args) {
        GestorMaterias gm = new GestorMaterias();
        gm.menu();
    }
    
    public void menu() {
        String op;
        System.out.println("===========MATERIAS============");
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
                case "0" -> System.out.println("SALIENDO DE MATERIAS...\n");
                default -> System.out.println("Opcion Invalida, Por favor seleccione una opcion valida.");    
            }
        } while(!"0".equals(op));
        
    }
    
    public void index() {
        System.out.println("-----Materias Existentes-----");
        this.materias = this.consulta();
        
        System.out.println("================================");
        System.out.println("| ID | Materia        | Unidad |");
        System.out.println("================================");
        for(Materia temp : this.materias) {
            System.out.format("| %2d | %-15s| %-6s |\n",
                    temp.getId(),
                    temp.getNombre(),
                    temp.getUnidad()
                    );     
        }
        System.out.println("================================");
    }
    
    public void create() {
        System.out.println("--------Crear Materia--------");
        this.index();
        this.crear();
    }
    
    public void update() {
        System.out.println("------Actualizar Materia-----");
        this.index();
        this.actualizar();
    }
        
    public void delete() {
        System.out.println("-------Eliminar Materia------");
        this.index();
        this.borrar();
    }
    
    public void search() {
        System.out.println("--------Buscar Materia-------");
        this.materias = this.buscar();
        
        System.out.println("=======================");
        System.out.println("| ID | Materia        |");
        System.out.println("=======================");
        for(Materia temp : this.materias) {
            System.out.format("| %2d | %-15s|\n",
                temp.getId(),
                temp.getNombre()
                );
        }
        System.out.println("=======================");
    }
    
}
