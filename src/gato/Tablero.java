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

/**
 *
 * Clase tablero, donde se maneja la logica del juego y de la maquina
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
    
    
    /**
     * agrega los boletos a la maquina siempre y cuando sea administrador
     */
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
    
    /**
     * comprueba los boletos que hay en la maquina siempre y cuando sea el 
     * administrador
     */
    
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
    
    
    /**
     * funcion que se encarga de llevar el desarrollo del juego
     * y de entregar los boletos en caso de que no se quiera seguir jugando
     * 
     * @param jugador1 instancia del jugador 1
     * @param jugador2 instancia del jugador 2
     * @return false si no se quiere seguir jugando, llamada recursiva si se quiere jugar de nuevo
     * 
     */
    
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
    
    /**
     * Genera el tablero de juego, una matriz 3x3
     */
    
    
    public void generarTablero(){
        System.out.println("Generando tablero.....");
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                this.tablero[i][j] = ' ';
            }
        }
        System.out.println("Tablero generado");
    }
    
    /**
     * muestra en pantalla el tablero generado con las modificaciones que se le
     * hagan en cada turno
     */
    
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
    
    /**
     * Se encarga de actualizar y colocar el tiro de cada uno de los jugadores
     * @param turno indica el turno de la persona para saber que simbolo poner
     * @param coordenadas las coordenadas del tiro del jugador en el tablero
     * @return true si se realizo una jugada, en caso contrario return false
     */
    
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
    
    /**
     * comprueba si hay casillas disponibles para realizar jugadas
     * @return false si hay casillas disponibles, return true en caso contrario
     * 
     */
    
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
    
    /**
     * muestra una lista con el ganador de los juegos almacenados en la maquina
     * desde su ejecucion
     */
    
    public void verJuegos(){
        for(String j: this.juegos){
            System.out.println(j);
        }
    }
    
    /**
     * Genera quien va primero en la lista de turnos
     * @param jugador1 instancia del jugador1
     * @param jugador2 instancia del jugador2
     */
    
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
    
    
    /**
     * comprueba si hay una jugada ganadora en el tablero
     * @return false en caso de que ninguna de las comprobaciones se cumpla
     * true en caso de que se haya hehco una jugada valida para el juego
     */
    public boolean terminarJuego(){
        return this.comprobarHorizontal() || this.comprobarVertical() || this.comprobarDiagonal();
    }
    
    /**
     * comprueba las filas del tablero para ver si hay un
     * tres en raya
     * @return true si hay tres figuras iguales de manera consecutiva horizontalmente
     */
    
    public boolean comprobarHorizontal(){
        
        for(int j = 0; j < 3; j++){
            
            if(this.tablero[j][0] != ' ' && this.tablero[j][0] == this.tablero[j][1] && this.tablero[j][1] == this.tablero[j][2]){
                return true;
            }
        }
        return false;
    
    }
    
    /**
     * comprueba las columnas del tablero para ver si hay tres en raya
     * @return true si hay tres figuras iguales de manera consecutiva verticalmente
     */
    
    public boolean comprobarVertical(){
        
        for(int i = 0; i < 3; i++){
            
            if(this.tablero [0][i] != ' ' && this.tablero[0][i] == this.tablero[1][i] && this.tablero[1][i] == this.tablero[2][i]){
                return true;
            }
        }
        return false;
    }
    
    /**
     * comprueba las diagonales del tablero para ver si hay tres en raya
     * @return true si hay tres figuras iguales en forma diagonal
     */
    
    public boolean comprobarDiagonal(){
        
        if(this.tablero [0][0] != ' ' && this.tablero[0][0] == this.tablero[1][1] && this.tablero[1][1] == this.tablero[2][2]){
            return true;
        } else if(this.tablero[0][2] != ' ' && this.tablero[0][2] == this.tablero[1][1] && this.tablero[1][1] == this.tablero[2][0]){
            return true;
        }
        return false;
    }
    
}
