/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gato;
import java.util.*;

/**
 *
 * @author Andres Urquiza
 * 
 * 
 * clase jugador para crear a los jugadores que usaran la maquina gato
 */


public class Player {
    Scanner scanner = new Scanner(System.in);
    
    private String nombre = "";
    private int turno = 0;
    private int boletos = 0;
    private int racha = 0;
    
    /**
     * coloca el nombre del jugador
     * @param nombre nombre ingresado por el jugador,
     */
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    /**
     * alcanza el nombre del jugador para mostrarlo
     * @return nombre del jugador
     */
    
    public String getNombre(){
        return this.nombre;
    }
    
    /**
     * coloca el turno del jugador
     * @param turno turno elegido en la funcion generarOrden de la clase Tablero
     */
    
    public void setTurno(int turno){
        this.turno = turno;
    }
    
    /**
     * regresa el turno actual del jugador
     * @return turno del jugador
     */
    public int getTurno(){
        return this.turno;
    }
    
    /**
     * regresa la racha del jugador en caso de haber ganado mas de un juego
     * de manera consecutiva
     * @return racha del jugador
     */
    public int getRacha(){
        return this.racha;
    }
    
    /**
     * comprueba que el jugador gano y se le agregan los boletos correspondientes
     * se incrementa la racha y en caso de estar en racha recibe un bonus dependiendo
     * de la racha que tenga el jugador
     * @param boletos agrega los boletos que entrega la maquina
     */
    
    public void ganar(int boletos){
        this.racha ++;
        switch(this.racha){
            case 1:
                this.boletos+= boletos;
                break;
            case 3:
                System.out.println("Por tu racha de " + this.racha + " obtienes 2 boletos de bonus");
                this.boletos += boletos + 2;
                break;
            case 5:
                System.out.println("Por tu racha de " + this.racha + " obtienes 4 boletos de bonus");
                this.boletos += boletos + 4;
                break;
            case 7:
                System.out.println("Por tu racha de " + this.racha + " obtienes 7 boletos de bonus");
                this.boletos += boletos + 7;
                break;
            default:
                this.boletos += boletos;
                break;
        }
        
    }
    
    /**
     * comprueba si el jugador gano, en caso de llevar racha se coloca en 0 de nuevo
     * se agregan los boletos 
     * @param boletos boletos entregados por la maquina
     */
    
    public void perder(int boletos){
        this.racha = 0;
        this.boletos += boletos;
    }
    
    /**
     * entrega los boletos al jugador obtenidos durante todos sus juegos en la
     * maquina
     * @return boletos que tenga el jugador disponibles 
     */
    
    public int canjearBoletos(){
        int entregaBoletos = this.boletos;
        this.boletos = 0;
        return entregaBoletos;
    }
    
    /**
     * pide al usuario las coordenadas en el tablero donde quiere realizar su jugada
     * comprueba que la fila ingresada no exceda los indices del tablero
     * @return un arreglo con la fila en la primera posicion y en la siguiente
     * la columna
     */
    
    
    public int[] realizarJugada(){
        int[] jugada = new int[2];
        
        System.out.println("Turno de " + this.nombre);
        System.out.print("Ingrese la fila: ");
        int fila = scanner.nextInt();
        
        while(fila > 3 || fila < 0){
            System.out.print("Indice incorrecto, ingrese la fila de nuevo: ");
            fila = scanner.nextInt();
        }
        
        System.out.print("Ingrese la columna: ");
        int columna = scanner.nextInt();
        
        while(columna > 3 || columna < 0){
            System.out.print("Indice incorrecto, ingrese la columna de nuevo: ");
            columna = scanner.nextInt();
        }
        
        
        if(fila - 1 < 0 || columna - 1 < 0){
            jugada[0] = fila;
            jugada[1] = columna;
        } else{
            jugada[0] = fila-1;
            jugada[1] = columna-1;
        }
        
        return jugada;
        
    }
    
    
}
