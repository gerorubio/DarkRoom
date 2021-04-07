/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import mapa.Cueva;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Random;
import javafx.scene.control.Button;
import objetos.Item;
import objetos.Lentes;
import objetos.Pico;
import objetos.Pocion;

/**
 *
 * @author Gerònimo
 */
public class Jugador extends Heroe implements Serializable{
    
    public int x, y;
    public int saveX, saveY;
    public int ent;
    public int din = 0;
    public char dir = 'd';
    public boolean isInCave = false;
    public Item[] inventario = new Item[3];
    //public int ataque = 10;
    private int pocion = 2;
    private boolean pico = false;
    public int usosPico = 0;
    private boolean lentes = false;
    public int usosLentes = 0;

    public Jugador(){
        super();
        this.x = 31;
        this.y = 31;
        this.ent = 0;
        this.inventario[0] = new Pocion();
        this.inventario[1] = new Pico();
        this.inventario[2] = new Lentes();
        this.vida = 10;
        this.ataque = 10;
    }
    
    public void moveLeft(Jugador player, GraphicsContext grafico){
        this.x = this.x - 1;
        this.dir = 'l';
        player.imprimirJugador( grafico, 'l' );
    }
    
    public void moveRight(Jugador player, GraphicsContext grafico){
        this.x = this.x + 1;
        this.dir = 'r';
        player.imprimirJugador( grafico, 'r' );
    }
    
    public void moveUp(Jugador player, GraphicsContext grafico){
        this.y = this.y - 1;
        this.dir = 'u';
        player.imprimirJugador( grafico, 'u' );
    }
    
    public void moveDown(Jugador player, GraphicsContext grafico){
        this.y = this.y + 1;
        this.dir = 'd';
        player.imprimirJugador( grafico, 'd' );
    }
    
    public void imprimirJugador( GraphicsContext grafico, char direccion ){
        
        int xpos = this.x;
        int ypos = this.y;
            
        Image mp;
                
        switch ( direccion ){
            
            case 'u':
                mp = new Image( "entidad/jugador/graphics/h_upidle.png" );
                grafico.drawImage( mp, xpos*16, ypos*16 );
                break;
                
            case 'd':
                mp = new Image( "entidad/jugador/graphics/h_downidle.png" );
                grafico.drawImage( mp, xpos*16, ypos*16 );
                break;
                
            case 'l':
                mp = new Image( "entidad/jugador/graphics/h_left1.png" );
                grafico.drawImage( mp, xpos*16, ypos*16 );
                break;
                
            case 'r':
                mp = new Image( "entidad/jugador/graphics/h_right1.png" );
                grafico.drawImage( mp, xpos*16, ypos*16 );
                break;
                
            // Idle, cuando se genera en el overworld.
            case 'i':
                mp = new Image( "entidad/jugador/graphics/h_downidle.png" );
                grafico.drawImage( mp, xpos*16, ypos*16 );
                break;
                
        }
        
    }

    public void verificarS(int[][] collisionador){
        Random rand = new Random();
        int  xpos = rand.nextInt(30);
        int  ypos = rand.nextInt(30);
          
        while(collisionador[xpos][ypos] == 1){
            xpos = rand.nextInt(30);
            ypos = rand.nextInt(30);
        }
        this.x = xpos;
        this.x = ypos;
    }

    public void saveCoord(){
        saveX = this.x;
        saveY = this.y;
    }
    
    public void mdin( int mas ){
        this.din = this.din + mas;
    }
    
    public void abrirC(Cueva a){
        Random rand = new Random();
        int xpos = a.rt(a.cuad, this.x, this.y);
        for (int i = 0; i<=xpos;i++){
        int mon = rand.nextInt(3);
        if(mon == 0){
            mon = 1;
        }
        this.mdin(mon);
        }
    }
    
    public void verificarC( Cueva a, Button btnac ){
       int xpos = a.contarVecinost( a.cuad, this.x, this.y );
        if ( xpos == 0 ){
            btnac.setVisible(false);
        }else{
            btnac.setVisible(true);
        }
    }
    
    public void addItem( Item item ){
        
        switch (item.getType()) {
            
            case "Lentes":
                if( !this.hasLentes() )
                    this.lentes = true;
                break;
                
            case "Pico":
                if( !this.hasPico() )
                    this.pico = true;
                break;
                
            case "Pocion":
                if( this.getNumPociones() <= 10 )
                    this.pocion++;
                break;
                
            default:
                break;
                
        }
    }
    
    public void reduceItem( Item item ){
        
        switch (item.getType()) {
            
            case "Lentes":
                if( !this.hasLentes() )
                    this.lentes = false;
                break;
                
            case "Pico":
                if( !this.hasPico() )
                    this.pico = false;
                break;
                
            case "Pocion":
                if( this.getNumPociones() > 0 )
                    this.pocion--;
                break;
                
            default:
                break;
                
        }
        
    }
    
    public boolean hasLentes(){
        return this.lentes;
    }
    
    public boolean hasPico(){
        return this.pico;
    }
    
    public int getNumPociones(){
        return this.pocion;
    }
    
    public void setPico(boolean pico) {
        this.pico = pico;
    }

    public void setLentes(boolean lentes) {
        this.lentes = lentes;
    }
}
