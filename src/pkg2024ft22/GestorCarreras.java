package pkg2024ft22;

import java.util.Scanner;
import java.util.ArrayList;

public class GestorCarreras extends ModeloCarrera{
    Scanner teclado = new Scanner(System.in);
    ArrayList<Carrera> carreras = new ArrayList<Carrera>();
    
    public static void main(String[] args) {
        GestorCarreras gca = new GestorCarreras();
        gca.menu();
       
    }
    
    public void menu() {
        String op;
        System.out.println("===========Carreras============");
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
                case "0" -> System.out.println("SALIENDO DE CARRERAS...\n");
                default -> System.out.println("Opcion Invalida, Por favor seleccione una opcion valida.");    
            }
        } while(!"0".equals(op));
        
    }
    
    public void index() {
        
        System.out.println("------Carreras Existentes------");
        this.carreras = this.consulta();
        
        System.out.println("=======================");
        System.out.println("| ID | Carrera        |");
        System.out.println("=======================");
        for(Carrera temp : this.carreras) {
            System.out.format("| %2d | %-15s|\n",
                    temp.getId(),
                    temp.getNombre()
                    );     
        }
        System.out.println("=======================");
    }
    
    public void create() {
        
        System.out.println("---------Crear Carrera---------");
        this.index();
        this.crear();
    }
    
    public void update() {
        System.out.println("-------Actualizar Carrera------");
        this.index();
        this.actualizar();
    }
        
    public void delete() {
        System.out.println("---Eliminar Carrera---");
        this.index();
        this.borrar();
    }
    
    public void search() {
        System.out.println("---------Buscar Carrera--------");
        this.carreras = this.buscar();
        
        System.out.println("=======================");
        System.out.println("| ID | Carrera        |");
        System.out.println("=======================");
        for(Carrera temp : this.carreras) {
            System.out.format("| %2d | %-15s|\n",
                 temp.getId(),
                 temp.getNombre());   
            System.out.println("=======================");       
        }
    }
}
