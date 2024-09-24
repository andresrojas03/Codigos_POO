/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package principal_poo06;

import java.util.*;
import gato.Tablero;
import gato.Player;



/**
 *
 * @author Andres Urquiza
 */
public class Principal_POO06 {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while(true){
            Tablero tablero = new Tablero();

            Player jugador1 = new Player();
            Player jugador2 = new Player();
            String continuar = " ";
            while(!continuar.equalsIgnoreCase("c")){
                
                System.out.println("Bienvenido a la maquina de gato. Presione C para continuar, presione S para terminar la maquina");
                continuar = scanner.nextLine();
                if(continuar.equalsIgnoreCase("s")){
                    System.exit(0);
                }
            }
            
            
            System.out.print("Ingrese el nombre del jugador 1: ");
            String nombre1 = scanner.nextLine();
            System.out.print("Ingrese el nombre del jugador 2: ");
            String nombre2 = scanner.nextLine();

            jugador1.setNombre(nombre1);
            jugador2.setNombre(nombre2);

            if(!tablero.jugar(jugador1, jugador2)){
                
                System.out.println("1.Reiniciar Maquina 2. Ver ultimos juegos 3.Agregar Boletos 4.Comprobar boletos");
                System.out.print("Su seleccion: ");
                
                int sel = scanner.nextInt();
                scanner.nextLine();
                
                switch(sel){
                    case 1:
                        continue;
                    case 2:
                        tablero.verJuegos();
                        break;
                    case 3:
                        tablero.agregarBoletos();
                        break;
                    case 4:
                        tablero.comprobarBoletos();
                    default:
                        System.out.println("Opcion invalida, intentelo de nuevo");
                        break;
                }
                
            }
            
        }
        
    }
   
    
}
