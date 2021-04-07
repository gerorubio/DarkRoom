package entidad;

/**
 * Clase que modela a un monstruo del juego.
 * @author ADarkTeam
 */
public class Monstruo extends Criatura{
    String nombre = "";
    int tipo; //10,20,30 (1,2,3)
    public int zona; //1-4
    public int id;

    /**
     * Constructor (sin argumentos) de la clase monstruo.
     * Los valores de monstruo serán los del constructor vacío de la clase
     * Criatura (10 de vida y 10 de ataque)
     */ 
    public Monstruo() {
        super();
    }
    /**
     * Constructor del monstruo.
     * De acuerdo a un identificador único, se establecen las propiedades
     * del monstruo
     * @param id El identificador único del monstruo
     */
    public Monstruo(int id) {
        this.id = id;
        //int hp, at;
        switch(id){
            case 11:
                this.setVida(20);
                this.setAtaque(3);
                this.setNombre("Slimer");
                this.zona = 1;
                break;
            case 12:
                this.setVida(25);
                this.setAtaque(2);
                this.setNombre("Celldos");
                this.zona = 1;
                break;
            case 21:
                this.setVida(55);
                this.setAtaque(6);
                this.setNombre("Verdugo");
                this.zona = 2;
                break;
            case 22:
                this.setVida(70);
                this.setAtaque(5);
                this.setNombre("Devil-Bat");
                this.zona = 2;
                break;
            case 23:
                this.setVida(45);
                this.setAtaque(8);
                this.setNombre("Hybrid");
                this.zona = 2;
                break;
            case 24:
                this.id=27;
                this.setVida(60);
                this.setAtaque(7);
                this.setNombre("Taurus");
                this.zona = 2;
                break;
            case 34:
                this.id=27;
                this.setVida(60);
                this.setAtaque(7);
                this.setNombre("Taurus");
                this.zona = 3;
                break;
            case 31:
                this.setVida(70);
                this.setAtaque(16);
                this.setNombre("Bat-Cyclops");
                this.zona = 3;
                break;
            case 32:
                this.setVida(48);
                this.setAtaque(22);
                this.setNombre("Fallen Knight");
                this.zona = 3;
                break;
            case 33:
                this.setVida(80);
                this.setAtaque(13);
                this.setNombre("Spooky");
                this.zona = 3;
                break;
            case 41:
                this.setVida(450);
                this.setAtaque(25);
                this.setNombre("Dark King");
                this.zona = 4;
                break;
        }
        this.tipo = id/10;
        this.setMaxVida(this.getVida());
        if(this.nombre.equals(""))
            this.setNombre("Monstruo");
    }

    /**
     * Método que permite a un monstruo atacar (a un jugador).
     * Este método no se usa directamente, ya que se necesita de un factor de 
     * escala. Se debe usar agredir.
     * @see agredir
     * @param c El heroe (jugador) que está atacando
     * @param factor El escalar con el que el heroe será atacado
     */
    void atacar(Criatura c, Float factor) {
        int danioHecho = (int)((factor*this.getAtaque()));
        System.out.println("Daño hecho por criatura: "+danioHecho);
        c.modifyVida(-danioHecho);
    }
    
    /**
     * Método que permite a un monstruo atacar al jugador.
     * Se crea el factor de escala (del 0 al 1) y se le pasa al método de atacar
     * @param c El heroe (jugador) que está atacando
     */
    public void agredir(Criatura c){
        /*try {
            Thread.sleep( 1000 );                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }*/
        Float factor = new java.util.Random().nextFloat();
        //System.out.println("Factor :"+factor);
        this.atacar(c,factor);
    }
    /**
     * @deprecated
     * Método que permite a un monstruo atacar (a un jugador)
     * @param c El heroe (jugador) que está atacando
     * @param factor El escalar con el que el heroe será atacado
     */
    @Override
    void atacar(Criatura c, int factor) {
        //
    }
    /**
     * Getter del atributo zona
     * @return zona del monstruo (del 1 al 4)
     */
    public int getZona() {
        return zona;
    }
    /**
     * Getter del atributo id
     * @return id el identificador único del monstruo
     */
    public int getId() {
        return id;
    }
    /**
     * Getter del atributo nombre
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Setter del atributo nombre
     * @param nombre el nuevo nombre que se desea para el monstruo
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
        	
}