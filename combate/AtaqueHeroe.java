package combate;

import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;

/**
 * Clase que se usará para el ataque de héroe por medio de hilos.
 * @author ADarkTeam
 */
public class AtaqueHeroe extends Thread{
    
    ProgressBar ataque;
    ToggleButton btn;
    int valorAtaque;
    
    /**
     * Constructor de la clase
     * @param ataque La barra de ataque
     * @param btn  El botón de dos estados que usará el jugador para atacar
     */
    public AtaqueHeroe(ProgressBar ataque, ToggleButton btn) {
        this.ataque = ataque;
        this.btn = btn;
        
    }
    
    /**
     * Este metodo se ejecutará cuando el hilo asociado al objeto de esta clase
     * utilize su método start.
     */
    @Override
    public void run(){
        valorAtaque = 0;
        int step = 1;
        while( true ){
            if(valorAtaque+step>100 || valorAtaque+step < 0)
                step=-step;
            valorAtaque += step;
            ataque.setProgress((double)valorAtaque/100);
            try {
                sleep(5);
            } catch (InterruptedException ex) {
                return;
            }
        }
    }
    
}
