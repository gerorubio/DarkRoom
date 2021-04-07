package combate;

import darkroom.FinDelJuego;
import entidad.Criatura;
import entidad.Jugador;
import entidad.Monstruo;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import objetos.Pocion;
import darkroom.DarkRoom;
import sonidos.sonidos;

/**
 * Clase que modela el combate entre el héroe y un monstruo.
 * @author ADarkTeam
 */
public class Combate implements Galeria{
    
    Jugador hero;
    Monstruo bestia;    
    int ataque;
    AtaqueHeroe attack;
    boolean leTocaAlHeroe = true;
    
    Stage ventana;
    Scene juego;
    Scene combate;
    Pane root;
    
    Canvas lienzo = new Canvas(976,976);
    public GraphicsContext grafico = lienzo.getGraphicsContext2D();
    
    private ProgressBar barraAtaque;
    private ProgressBar vidaJugador;
    private Text strJugador;
    private Text strVidaJugador;
    private ProgressBar vidaBestia;
    private Text strBestia;
    private Text strVidaBestia;
    private Button btnPocion;
    private ToggleButton btn2;
    private Button salir;
    private StackPane rootSalida;
    private Text mensajeSalida;
    private Scene salida;
    private Stage ventanaSalida;
    FinDelJuego fdj;
    private final sonidos sonido1 = new sonidos();
    
    /**
     * Constructor del combate.
     * @param hero El jugador.
     * @param bestia El monstruo que se generó en la habitación.
     * @param ventana La ventana en la que el juego se encuentra.
     * @param juego La escena del juego, antes de que se inciara el combate.
     * @param fdj Fin del juego.
     */
    public Combate( Jugador hero, Monstruo bestia, Stage ventana, Scene juego, FinDelJuego fdj) {
        this.hero = hero;
        this.bestia = bestia;
        this.ventana = ventana;
        this.juego = juego;
        this.fdj = fdj;
    }
    
    /**
     * Método privado para inicializar las barras y otros detalles gráficos
     * relacionados a dichas barras.
     */
    private void initializeBars(){
        
        // Barra de ataque
        barraAtaque = new ProgressBar(0);
        barraAtaque.setLayoutX(200);
        barraAtaque.setLayoutY(700);
        barraAtaque.setPrefSize(550, 25);
        barraAtaque.setProgress(0);
        
        // Barra de la vida del héroe (jugador)
        vidaJugador = new ProgressBar( (double)this.hero.getVida()/this.hero.getMaxVida() );
        vidaJugador.setLayoutX(200);
        vidaJugador.setLayoutY(50);
        vidaJugador.setPrefSize(200, 25);
        
        // Texto con el nombre del jugador
        strJugador = new Text();
        strJugador.setLayoutX( 200 );
        strJugador.setLayoutY( 40 );
        strJugador.setText(hero.getNombre());
        strJugador.setFill(Color.WHITE);
        
        // Texto con los puntos de vida del jugador
        strVidaJugador = new Text();
        strVidaJugador.setLayoutX( 292 );
        strVidaJugador.setLayoutY( 65 );
        strVidaJugador.setText( "" + this.hero.getVida() );
        
        // Barra de vida del monstruo
        vidaBestia = new ProgressBar( (double)this.bestia.getVida()/this.bestia.getMaxVida() ); // Cambiar 100 por maxHealth
        vidaBestia.setLayoutX(550);
        vidaBestia.setLayoutY(50);
        vidaBestia.setPrefSize(200, 25);
        
        // Texto con el nombre del monstruo
        strBestia = new Text();
        strBestia.setLayoutX( 550 );
        strBestia.setLayoutY( 40 );
        strBestia.setText( bestia.getNombre() );
        strBestia.setFill(Color.WHITE);
        
        // Texto con los puntos de vida del monstruo
        strVidaBestia = new Text();
        strVidaBestia.setLayoutX( 638 );
        strVidaBestia.setLayoutY( 65 );
        strVidaBestia.setText( "" + this.bestia.getVida() );   
        
        rootSalida = new StackPane();
        salida = new Scene( rootSalida, 300, 100 );
        salir = new Button();
        salir.setText( "Salir del combate" );
        ventanaSalida = new Stage();
        ventanaSalida.setTitle("Combate terminado");
        ventanaSalida.setScene( salida );
        ventanaSalida.initModality(Modality.WINDOW_MODAL);
        mensajeSalida = new Text( 100, 50, "" );
        
    }
    
    /**
     * Método en el que se da inicio al combate.
     */
    public synchronized void beginBattle(sonidos sonido1){
        if(this.bestia.id == 41){
            sonido1.stop(2);
            this.sonido1.repro(1,1,1,1,1);
        }else{
        //this.sonido1.repro(1,1,1);
        }
        // Checamos estado actual.
        this.estadoActual();
        
        // Se inicializan las barras.
        initializeBars();
        
        btnPocion = new Button( "Tomar poción" );
        btnPocion.setLayoutX( 100 );
        btnPocion.setLayoutY( 750 );
        btnPocion.setOnAction( (ActionEvent event ) ->{
            
            event.consume();
            
            if( hero.getNumPociones() == 0 )
                btnPocion.setVisible( false );
            else
                btnPocion.setVisible( true );
            
            int prevLife = hero.getVida();
            hero.modifyVida( Pocion.getHealPoints() );
            hero.reduceItem( new Pocion() );
            System.out.println("Numero de pociones: " + hero.getNumPociones());
            UpdateProgressBar updateJ = new UpdateProgressBar( hero, vidaJugador, strVidaJugador, prevLife, hero.getVida(), hero, btn2, btnPocion );
            updateJ.start();
            int prevLifeJ = hero.getVida();
            // La bestia ataca.
            bestia.agredir((Criatura)hero);
            updateJ = new UpdateProgressBar( hero, vidaJugador, strVidaJugador, prevLifeJ, hero.getVida(), hero, btn2, btnPocion );
            updateJ.start();
            if( !hero.isAlive() ){
                mensajeSalida.setText( "¡Has muerto!");
                rootSalida.getChildren().add( mensajeSalida );
                StackPane.setMargin( mensajeSalida, new Insets( 25 ) );
                StackPane.setAlignment( mensajeSalida, Pos.TOP_CENTER );
                salir.setOnAction((ActionEvent eventoGameOver) -> {
//                    exitBattle();
                    // AGREGAR GAME OVER
                    ventanaSalida.close();
                    sonido1.stop(2);
                    gameOver();
                });
                rootSalida.getChildren().add( salir );
                StackPane.setAlignment( salir, Pos.BOTTOM_CENTER );
                StackPane.setMargin( salir, new Insets( 25 ) );
                salir.setVisible( true );
                ventanaSalida.show();
            }
            
        });
        
        // Configuración del boton atacar.
        btn2 = new ToggleButton( "Atacar" );
        btn2.setLayoutX(100);
        btn2.setLayoutY(700);
        btn2.setOnAction((ActionEvent event) -> {
            
            event.consume();
            
            btnPocion.setVisible( false );
            
            if( leTocaAlHeroe ){
                
                if( btn2.isSelected() ){
                    // Se inicia la carga (hilo)
                    System.out.println("Cargando el ataque...");
                    attack = new AtaqueHeroe( barraAtaque, btn2 );
                    attack.start();
                    
                }else{
                    // Se realiza el ataque
                    System.out.println("ATAQUE!");
                    attack.interrupt();
                    // Se recupera el valor del ataque.
                    int a = attack.valorAtaque;
                    // Guardamos la vida previa del monstruo para animación.
                    int prevLifeM = bestia.getVida();
                    // El heroe ataca
                    hero.atacar( bestia, a );
                    System.out.println("El ataque fue de "+ a);
                    // Se actualiza (anima) la barra de la bestia.
                    UpdateProgressBar updateM = new UpdateProgressBar( hero, vidaBestia, strVidaBestia, prevLifeM, bestia.getVida(), bestia, btn2, btnPocion );
                    updateM.start();
                    // La barra de ataque se queda vacía.
                    barraAtaque.setProgress(0);
                    if( !bestia.isAlive() ){
                        if (bestia.getId()==41)
                        {mensajeSalida.setText( "¡Has derrotado al Rey!");
                        rootSalida.getChildren().add( mensajeSalida );
                        StackPane.setAlignment( mensajeSalida, Pos.TOP_CENTER );
                        StackPane.setMargin( mensajeSalida, new Insets( 25 ) );
                        salir.setText("Terminar Juego");
                        salir.setOnAction((ActionEvent eventoSalir) -> {
                            exitBattle();
                            ventanaSalida.close();
                        });
                        
                        rootSalida.getChildren().add( salir );
                        salir.setVisible( true );
                        StackPane.setAlignment( salir, Pos.BOTTOM_CENTER );
                        StackPane.setMargin( salir, new Insets( 25 ) );
                        ventanaSalida.showAndWait();
                        this.terminar();
                            
                        }
                        else
                        {
                        mensajeSalida.setText( "¡Has derrotado al monstruo!");
                        rootSalida.getChildren().add( mensajeSalida );
                        StackPane.setAlignment( mensajeSalida, Pos.TOP_CENTER );
                        StackPane.setMargin( mensajeSalida, new Insets( 25 ) );
                        salir.setOnAction((ActionEvent eventoSalir) -> {
                            exitBattle();
                            ventanaSalida.close();
                        });
                        
                        rootSalida.getChildren().add( salir );
                        salir.setVisible( true );
                        StackPane.setAlignment( salir, Pos.BOTTOM_CENTER );
                        StackPane.setMargin( salir, new Insets( 25 ) );
                        ventanaSalida.showAndWait();
                            
                        }
                        
                    } else{
                        // Como el heroe ya atacó, el boton se vuelve invisible.
                        btn2.setVisible( false );
                        System.out.println( hero.getVida() );
                        leTocaAlHeroe = false;
                        // Se guarda la vida previa del heroe para animación.
                        int prevLifeJ = hero.getVida();
                        // La bestia ataca.
                        bestia.agredir((Criatura)hero);
                        UpdateProgressBar updateJ = new UpdateProgressBar( hero, vidaJugador, strVidaJugador, prevLifeJ, hero.getVida(), hero, btn2, btnPocion );
                        updateJ.start();
                        //Checamos si alguno de los dos combatientes murió.
                        if( !hero.isAlive() ){
                            mensajeSalida.setText( "¡Has muerto!");
                            rootSalida.getChildren().add( mensajeSalida );
                            StackPane.setAlignment( mensajeSalida, Pos.TOP_CENTER );
                            StackPane.setMargin( mensajeSalida, new Insets( 25 ) );
                            salir.setOnAction((ActionEvent eventoGameOver) -> {
//                                exitBattle();
                                // AGREGAR GAME OVER
                                ventanaSalida.close();
                                sonido1.stop(2);
                                gameOver();
                            });
                            rootSalida.getChildren().add( salir );
                            StackPane.setAlignment( salir, Pos.BOTTOM_CENTER );
                            StackPane.setMargin( salir, new Insets( 25 ) );
                            salir.setVisible( true );
                            ventanaSalida.show();
                            return;
                        }
                        // Si la batalla continúa, se vuelve a poner el botón del
                        // héroe para que vuelva atacar
                        //btn2.setVisible(true);
                        leTocaAlHeroe = true;
                    }
                }
                
            }
            
            //btnPocion.setVisible( true );
            
        });
        
        // Fondo
        System.out.println("Numero de monstruo:"+bestia.getId()+" Zona:"+bestia.getZona());
        int z=bestia.getZona(); //Consideramos los diferentes fondos y los centramos
        Image imgFondo = getWallpaper(bestia.getZona());
        if (z==1|| z==3)
        {
        // Dibujamos el fondo
        grafico.drawImage(imgFondo, 70, 0);           
        }
        else
        {
        grafico.drawImage(imgFondo,0, 0);
        }
        
        
        // Imagen de la bestia
        Image imgBestia = getMonster(bestia.getId());
        // Dibujamos la bestia
        int c = bestia.getId();  //Comparacion para tamaño de monstruos
        if ( c == 11 || c == 21 || c == 27 )
        {
            grafico.drawImage(imgBestia, 150, 50,675,650);
        }
        else
        {
            switch (c) {
                case 32:
                    grafico.drawImage(imgBestia, 150, 67,675,667);
                    break;
                case 41:
                    grafico.drawImage(imgBestia, 323, 280,388,400);
                    break;
                default:
                    grafico.drawImage(imgBestia, 310, 280,375,400);
                    break;
            }
        }
        
        // Panel donde se agrega todo
        root = new Pane();
        root.getChildren().add(lienzo);
        root.getChildren().add(btn2);
        root.getChildren().add(btnPocion);
        root.getChildren().add(barraAtaque);
        root.getChildren().add(vidaJugador);
        root.getChildren().add(vidaBestia);
        root.getChildren().add(strJugador);
        root.getChildren().add(strVidaJugador);
        root.getChildren().add(strBestia);
        root.getChildren().add(strVidaBestia);
        // Se crea la escena del combate
        this.combate = new Scene(root, 976, 976);
        // Se coloca la escena en la ventana
        this.ventana.setScene(this.combate);
        if( hero.getNumPociones() < 1 )
            btnPocion.setVisible( false );
    }
    
    /**
     * Método privado para finalizar un combate y regresar al juego.
     */
    private void exitBattle(){
        this.combate = null;
        this.ventana.setScene(this.juego);
        this.sonido1.stop(1,1,1);
        this.sonido1.stop(2);
        this.sonido1.stop(1,1,1,1,1 );
        
    }
    
    /**
     * Método privado para imprimir en consola las estadísticas actuales
     * de los combatientes (primero el héroe y después el monstruo).
     */
    private void estadoActual(){
        System.out.println(this.hero.toString()+"\n"+this.bestia.toString());
    }
    
    void gameOver(){
        this.sonido1.stop(2);
        this.sonido1.stop(1,1,1);
        this.sonido1.stop(1,1,1,1,1);
        this.sonido1.repro("a","b");
        Pane panel = new Pane();
        Image fondo = new Image("combate/fondos/gameOver.jpeg");
        Canvas canvas = new Canvas(976,976);
        this.grafico = canvas.getGraphicsContext2D();
        this.grafico.drawImage(fondo,0,0);
        Button btngo = new Button("OK");
        btngo.setLayoutX(470);
        btngo.setLayoutY(720);
        sonido1.stop(2);
        btngo.setOnAction((ActionEvent v) -> {
            System.out.println("Menu menu");
            fdj.start();
            exitBattle();
            new DarkRoom().reboot();
            //Regreso al menu
        });
        panel.getChildren().add(canvas);
        panel.getChildren().add(btngo);
        
        Scene estaEscena = new Scene(panel,976,976);
        this.ventana.setScene(estaEscena);
            
    }
    
    void terminar(){
        Pane panel = new Pane();
        Image fondo = new Image("combate/fondos/fin.jpg");
        Canvas canvas = new Canvas(976,976);
        this.grafico = canvas.getGraphicsContext2D();
        this.grafico.drawImage(fondo,0,0);
        Button btngo = new Button("Terminar Juego");
        btngo.setLayoutX(800);
        btngo.setLayoutY(700);
        btngo.setOnAction((ActionEvent v) -> {                             
            new DarkRoom().reboot();
        });
        panel.getChildren().add(canvas);
        panel.getChildren().add(btngo);
        
        Scene estaEscena = new Scene(panel,976,976);
        this.ventana.setScene(estaEscena);
            
    }
}
