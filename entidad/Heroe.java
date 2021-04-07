package entidad;
import java.util.Random;

/**
 * Clase que modela al héroe
 * @author ADarkTeam
 */
public class Heroe extends Criatura{
    
    private String nombre;
    
    /**
     * Constructor vacío de la clase héroe
     * Inicializa al jugador con 10 puntos de vida y 10 puntos de ataque
     */
    
    public Heroe() {
        super();
    }

    /**
     * Método que permite al héroe (jugador) atacar a un monstruo.
     * Implementación del método atacar de la clase abstracta Criatura.
     * @param c La criatura (en este caso, el monstruo) que va atacar el jugador
     * @param factor El factor por el cual se va escalar su ataque (desde 0 a 100)
     */
    @Override
    public void atacar(Criatura c, int factor) {
        int danioHecho = (int)((factor*this.getAtaque())/100);
        c.modifyVida(-danioHecho);
    }
    
    /**
     * Getter del atributo nombre (nombre del jugador).
     * Si en el inicio del juego se ingresó una cadena vacía, el nombre del
     * jugador será "Héroe".
     * @return 
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Setter del atributo nombre del héroe
     * @param nombre el nuevo nombre que se desea para el héroe (jugador)
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
        
}