package entidad;

/**
 * Clase que modelará a una criatura de juego. Es abstracta porque se pretende
 * hacer clases más complejas a partir de esta.
 * @author ADarkTeam
 */
abstract public class Criatura{
    
    public int vida;
    public int maxVida;
    public int ataque;
    
    /**
     * Constructor vacío de la clase criatura.
     * Crea una nueva criatura con sus atributos iniciales igual a 10
     */
    public Criatura() {
       this.vida = 10;
       this.maxVida = 10;
       this.ataque = 10;
    }
    
    /**
     * Constructor con argumentos de la clase criatura.
     * Se crea una nueva criatura con los atributos deseados.
     * @param vida Los puntos de vida deseados de la criatura.
     * @param ataque Los puntos de ataque deseados de la criatura.
     */
    public Criatura(int vida, int ataque) {
        this.vida = vida;
        this.maxVida = vida;
        this.ataque = ataque;
    }
    
    /**
     * Método para saber si una criatura sigue viva.
     * Si sus puntos de vida son mayor que cero, se dice que está viva.
     * Si la criatura tiene cero puntos de vida, se dice que murió
     * @return Un booleano que representa si una criatura sigue viva
     */
    public boolean isAlive(){
        return this.vida > 0;
    }
    
    /**
     * Método para modificar vida (no confundir con setVida).
     * Este método recibe una cierta cantidad de puntos de vida y los agrega
     * a los puntos de vida actual de la criatura. También se puede quitar 
     * puntos de vida si el argumento es negativo (menor que cero).
     * @param hp Los puntos de vida que se agregarán (o se restarán si es que es
     * menor a cero)
     */
    public void modifyVida(int hp){
        // Se suma la vida actual con los puntos a recuperar
        int nuevaVida = this.vida + hp;
        if(nuevaVida > this.maxVida)
            this.vida = this.maxVida;
        else if(nuevaVida < 0)
            this.vida = 0;
        else
            this.vida = nuevaVida;
    }
    
    /**
     * Método para modificar ataque (no confundir con setAtaque).
     * Este método recibe una cierta cantidad de puntos de ataque y los agrega
     * a los puntos de ataque actual de la criatura. También se puede quitar 
     * puntos de ataque si el argumento es negativo (menor que cero).
     * @param ad Los puntos de ataque que se agregarán (o se restarán si es que es
     * menor a cero)
     */
    public void modifyAtaque(int ad){
        int nuevoAtaque = this.ataque + ad;
        this.ataque = nuevoAtaque;
    }
    
    /**
     * Método para modificar nmv (no confundir con setMaxVida).
     * Este método recibe una cierta la cantidad de puntos de vida y los agrega
     * a los puntos de maxVida actual de la criatura. También se puede quitar 
     * puntos de vida máxima si el argumento es negativo (menor que cero).
     * @param nmv Los puntos de maxVida que se agregarán (o se restarán si es que es
     * menor a cero)
     */
    public void modifyMaxVida(int nmv){
        int nuevaMaxVida = this.maxVida + nmv;
        this.maxVida = nuevaMaxVida;
    }
    
    /**
     * Método abstracto para atacar
     * @param c La criatura que será atacada
     * @param factor El factor que escala el ataque
     */
    abstract void atacar(Criatura c, int factor);
    
    /**
     * Getter del atributo vida
     * @return vida La vida actual de la criatura
     */
    public int getVida() {
        return vida;
    }  
    
    /**
     * Setter del atributo vida. No confundir con modifyVida.
     * Este método recibe unos puntos de vida y los asigna a la criatura.
     * @param vida Los nuevos puntos de vida
     */
    public void setVida(int vida) {
        this.vida = vida;
    }
    
    /**
     * Getter del atributo ataque
     * @return ataque Los puntos de ataque
     */
    public int getAtaque() {
        return ataque;
    }
    
    /**
     * Setter del atributo ataque. No confundir con modifyAtaque.
     * Este método recibe unos puntos de ataque y los asigna a la criatura.
     * @param ataque Los puntos de ataque
     */
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
    
    /**
     * Getter del atributo maxVida
     * @return maxVida
     */
    public int getMaxVida() {
        return maxVida;
    }
    
    /**
     * Setter del atributo maxVida
     * @param maxVida Los nuevos puntos de vida máxima.
     */
    public void setMaxVida(int maxVida) {
        this.maxVida = maxVida;
    }
    
    /**
    * Representación textual de una criatura.
    * @return Una cadena con los datos de la criatura en ceustión.
    */
    @Override
    public String toString() {
        return "Criatura{" + "vida=" + vida + ", ataque=" + ataque + '}';
    }
}