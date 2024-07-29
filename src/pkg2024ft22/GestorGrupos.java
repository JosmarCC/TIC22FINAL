package pkg2024ft22;

import java.util.ArrayList;
import java.util.Scanner;

public class GestorGrupos extends ModeloGrupo {
    
    Scanner teclado = new Scanner(System.in);
    ArrayList<Grupo> grupos = new ArrayList<Grupo>();
    
    public static void main(String[] args) {
        GestorGrupos gg = new GestorGrupos();
        gg.menu();
    }
    
    public void menu() {
        String op;
        System.out.println("===========Grupos============");
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
                case "1" -> {System.out.println(""); index(); System.out.println("\n");}
                case "2" -> {System.out.println(""); create(); System.out.println("\n");}
                case "3" -> {System.out.println(""); update(); System.out.println("\n");}
                case "4" -> {System.out.println(""); delete(); System.out.println("\n");}
                case "5" -> {System.out.println(""); search(); System.out.println("\n");}
                case "0" -> System.out.println("SALIENDO DE GRUPOS...\n");
                default -> System.out.println("Opcion Invalida, Por favor seleccione una opcion valida.");    
            }
        } while(!"0".equals(op));
        
    }
    
    public void index() {
        
        System.out.println("------Grupos Existentes------");
        this.grupos = this.consulta();
        
        System.out.println("===============================================");
        System.out.println("| ID | Grupo          | ID CC    | Carrera    |");
        System.out.println("===============================================");
        for(Grupo temp : this.grupos) {
            System.out.format("| %2d | %-15s| %-8s | %-10s |\n",
                    temp.getId(),
                    temp.getNombre(),
                    temp.getId_ciclo(),
                    temp.getId_carrera()
                    );     
        }
        System.out.println("===============================================");
    }
    
    public void create() {
        
        System.out.println("---------Crear Grupo---------");
        this.index();
        this.crear();
    }
    
    public void update() {
        System.out.println("-------Actualizar Grupo------");
        this.index();
        this.actualizar();
    }
    
    public void delete() {
        System.out.println("---Eliminar Grupo---");
        this.index();
        this.borrar();
    }
    
    public void search() {
        System.out.println("---------Buscar Grupo--------");
        this.grupos = this.buscar();
        
        System.out.println("===============================================");
        System.out.println("| ID | Grupo          | ID CC    | Carrera    |");
        System.out.println("===============================================");
        for(Grupo temp : this.grupos) {
            System.out.format("| %2d | %-15s| %-8s | %-10s |\n",
                    temp.getId(),
                    temp.getNombre(),
                    temp.getId_ciclo(),
                    temp.getId_carrera()
                    );     
        }
        System.out.println("===============================================");
    }
}
