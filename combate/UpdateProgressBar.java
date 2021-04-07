package combate;

import entidad.Criatura;
import entidad.Jugador;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;

/**
 * Clase que se usará para animar las barras de progreso por medio de hilos.
 * @author ADarkTeam
 */
public class UpdateProgressBar extends Thread {
    
    ProgressBar bar;
    Text strLife;
    int prevLife;
    int newLife;
    //boolean isFromMonster;
    Criatura c;
    ToggleButton btn2;
    Button btnPocion;
    Jugador hero;
    
    /**
     * Constructor de la clase.
     * @param hero El jugador.
     * @param bar La barra de vida que se actualizará (y animará)
     * @param strLife El texto que indica los puntos de vida que se actualizará
     * (y se animará)
     * @param prevLife Un número que representa cuanta vida tenía la criatura
     * antes de que se actualice (y se anime) la barra.
     * @param newLife Un número que representa cuanta vida tiene ahora la 
     * criatura, después de que se actualizó (y se animó).
     * @param c La criatura cuya barra de vida le pertenece.
     * @param btn2 El botón de dos estados que usa el jugador para atacar.
     * @param btnPocion El botón de usar poción.
     */
    public UpdateProgressBar( Jugador hero, ProgressBar bar, Text strLife, int prevLife, int newLife, Criatura c, ToggleButton btn2, Button btnPocion ) {
        this.bar = bar;
        this.strLife = strLife;
        this.prevLife = prevLife;
        this.newLife = newLife;
        this.c = c;
        this.btn2 = btn2;
        this.btnPocion = btnPocion;
        this.hero = hero;
    }
    
    /**
     * Implementación del método run para ejectuar el hilo.
     * Actualiza la barra de progreso, con la pequeña animación de que va
     * disminuyendo.
     */
    @Override
    public void run(){
            
        btn2.setVisible( false );
        btnPocion.setVisible( false );
        
        // posible polimorfismo
        if( c instanceof Jugador ){
            
            if( prevLife >= newLife ){
            
                try {
                    sleep(1500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AtaqueHeroe.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
        
        }
        
        if( prevLife > newLife ){
        
            for( int i = prevLife; i >= newLife; i-- ){
                bar.setProgress( (double)i/c.getMaxVida() );
                strLife.setText( "" + i );
                try {
                    sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AtaqueHeroe.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            
        }
        else{
            
            for( int i = prevLife; i <= newLife; i++ ){
                bar.setProgress( (double)i/c.getMaxVida() );
                strLife.setText( "" + i );
                try {
                    sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AtaqueHeroe.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            
        }
        
        if( c instanceof Jugador && prevLife < newLife ){
            
            try {
                sleep(1500);
            } catch (InterruptedException ex) {
                Logger.getLogger(AtaqueHeroe.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if( c instanceof Jugador && prevLife >= newLife ){
        
            btn2.setVisible( true );
            if( hero.getNumPociones() > 0 )
                btnPocion.setVisible( true );
            
        }
        
        System.out.println("Hilo barra terminado de " + c.toString() );
        
    }
    
}