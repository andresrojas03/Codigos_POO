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
 */


public class Tablero {
    
    public char[][] tablero = new char[3][3];
    public Player[] listaJugadores = new Player[2];
    public ArrayList<String> juegos = new ArrayList<>();
    private int boletos = 100;
    protected int conteoJuegos = 0;
    
    private String adminUser = "Cr4zydukK";
    private String adminPsswrd = "A1b!C2d@eF3g4h#";
    
    Scanner scanner = new Scanner(System.in);
    
    public void agregarBoletos(){
        System.out.println("Inicie sesion para continuar");
        System.out.print("Usuario: ");
        String user = scanner.nextLine();
        System.out.print("Contrasena: ");
        String psswrd = scanner.nextLine();
        
        if(user.equals(adminUser) && psswrd.equals(adminPsswrd)){
            System.out.print("Bienvenido, ingrese la cantidad de boletos que desea recargar: ");
            int recargaBoletos = scanner.nextInt();
            this.boletos += recargaBoletos;
        }
        else{
            System.out.println("Usuario o contrasena incorrectos");
        }
    }
    
    public void comprobarBoletos(){
        System.out.println("Inicie sesion para continuar");
        System.out.print("Usuario: ");
        String user = scanner.nextLine();
        System.out.print("Contrasena: ");
        String psswrd = scanner.nextLine();
        
        if(user.equals(adminUser) && psswrd.equals(adminPsswrd)){
            System.out.print("Bienvenido, la cantidad de boletos en la maquina es: " + this.boletos);
        } else{
            System.out.println("Usuario o contrasena incorrectos");
        }
    }
    
    public boolean jugar(Player jugador1, Player jugador2){
        
        this.boletos -= 6;
        int turno = 2;
        this.conteoJuegos ++;
        boolean empate = false;
        
        
        this.generarTablero();
        this.generarOrden(jugador1, jugador2);
        this.mostrarTablero();
        
        while(!this.terminarJuego()){

                turno = (turno %2)+1;
                
                while(true){
                    
                    int [] jugada = this.listaJugadores[turno-1].realizarJugada();
                    if(this.actualizarTablero(turno, jugada)){
                        break;
                    }
                }
                if(this.tableroLleno() && !this.terminarJuego()){
                    System.out.println("Empate, no hay boletos para este juego");
                    this.juegos.add("Juego " + conteoJuegos + ".Empate");
                    empate = true;
                    break;
                }
        }
        
        if(!empate){
            int boletosGanador = 5;
            int boletosPerdedor = 1;

            if(turno - 1 == 0){    

                
                System.out.println("Ha ganado el jugador: " + this.listaJugadores[0].getNombre());
                this.listaJugadores[0].ganar(boletosGanador);
                this.listaJugadores[1].perder(boletosPerdedor);
                juegos.add("Juego " + this.conteoJuegos + ".Ganador: " + this.listaJugadores[0]);
                System.out.println("El jugador " + this.listaJugadores[0].getNombre() + " lleva una racha de: " + this.listaJugadores[0].getRacha());
            }

            if(turno -1 == 1){

                
                System.out.println("Ha ganado el jugador: " + this.listaJugadores[1].getNombre());
                this.listaJugadores[1].ganar(boletosGanador);
                this.listaJugadores[0].perder(boletosPerdedor);
                juegos.add("Juego " + this.conteoJuegos +".Ganador: " + this.listaJugadores[1]);
                System.out.println("El jugador " + this.listaJugadores[1].getNombre() + " lleva una racha de: " + this.listaJugadores[1].getRacha());

            }
            
        }
        
        
        
        System.out.print("Desea volver a jugar? [S/N]: ");
        String volverAJugar = scanner.nextLine(); 
        
        if(volverAJugar.equalsIgnoreCase("s")){
            return jugar(jugador1, jugador2);
        } else{
            System.out.println("Canjeando boletos de jugador 1 y jugador 2");
            System.out.println("El jugador " + this.listaJugadores[0].getNombre() + " ha conseguido " + this.listaJugadores[0].canjearBoletos() + " boletos" );
            System.out.println("El jugador " + this.listaJugadores[1].getNombre() + " ha conseguido " + this.listaJugadores[1].canjearBoletos() + " boletos");
            return false;
        }
        
    }
    
    
    public void generarTablero(){
        System.out.println("Generando tablero.....");
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                this.tablero[i][j] = ' ';
            }
        }
        System.out.println("Tablero generado");
    }
    
    public void mostrarTablero(){
        System.out.println("#### Juego " + this.conteoJuegos + " #####");
        System.out.println("1." + listaJugadores[0].getNombre() + "    " + "2." + listaJugadores[1].getNombre());
        
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(this.tablero[i][j]);
                if(j < 2) System.out.print(" | ");
            }
            System.out.println();
            if(i < 2) System.out.println("----------");
        }
    }
    
    public boolean actualizarTablero(int turno, int[] coordenadas){
       int fila = coordenadas[0];
       int columna = coordenadas[1];
       
       while(tablero[fila][columna] != ' '){
           System.out.println("Jugada invalida, espacio ya ocupado");
           this.mostrarTablero();
           return false;
           
       }
       if(turno == 1){
           this.tablero[fila][columna] = 'x';
           
       }
       
       if(turno == 2){
           this.tablero[fila][columna] = 'o';
       }
       
       this.mostrarTablero();
       return true;
       
    }
    
    public boolean tableroLleno(){
        for(int i = 0; i< 3; i++){
            for(int j = 0;j < 3; j++){
                //comprueba si hay casillas disponibles
                if(this.tablero[i][j] == ' '){
                    return false;
                }
            }
        }
        return true;
    }
    
    public void verJuegos(){
        for(String j: this.juegos){
            System.out.println(j);
        }
    }
    
    
    
    public void generarOrden(Player jugador1, Player jugador2){
        
        Random random = new Random();
        int turno = random.nextInt(0, 1);
        
        if(turno == 0){
        
            System.out.println("Empieza el jugador: " + jugador1.getNombre());
            this.listaJugadores[0] = jugador1;
            
            this.listaJugadores[1] = jugador2;
        }
        
        if(turno == 1){
            System.out.println("Empieza el jugador: " + jugador2.getNombre());
            this.listaJugadores[0] = jugador2;
            this.listaJugadores[1] = jugador1;
        }    
    }
    
    public boolean terminarJuego(){
        return this.comprobarHorizontal() || this.comprobarVertical() || this.comprobarDiagonal();
    }
    
    public boolean comprobarHorizontal(){
        
        for(int j = 0; j < 3; j++){
            
            if(this.tablero[j][0] != ' ' && this.tablero[j][0] == this.tablero[j][1] && this.tablero[j][1] == this.tablero[j][2]){
                return true;
            }
        }
        return false;
    
    }
    
    public boolean comprobarVertical(){
        
        for(int i = 0; i < 3; i++){
            
            if(this.tablero [0][i] != ' ' && this.tablero[0][i] == this.tablero[1][i] && this.tablero[1][i] == this.tablero[2][i]){
                return true;
            }
        }
        return false;
    }
    
    public boolean comprobarDiagonal(){
        
        if(this.tablero [0][0] != ' ' && this.tablero[0][0] == this.tablero[1][1] && this.tablero[1][1] == this.tablero[2][2]){
            return true;
        } else if(this.tablero[0][2] != ' ' && this.tablero[0][2] == this.tablero[1][1] && this.tablero[1][1] == this.tablero[2][0]){
            return true;
        }
        return false;
    }
    
}
