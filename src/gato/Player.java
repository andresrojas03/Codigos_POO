/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gato;
import java.util.*;

/**
 *
 * @author Andres Urquiza
 */
public class Player {
    Scanner scanner = new Scanner(System.in);
    
    private String nombre = "";
    private int turno = 0;
    private int boletos = 0;
    private int racha = 0;
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public void setTurno(int turno){
        this.turno = turno;
    }
    
    public int getTurno(){
        return this.turno;
    }
    
    public int getRacha(){
        return this.racha;
    }
    
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
    
    public void perder(int boletos){
        this.racha = 0;
        this.boletos += boletos;
    }
    
    public int canjearBoletos(){
        int entregaBoletos = this.boletos;
        this.boletos = 0;
        return entregaBoletos;
    }
    
    
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
