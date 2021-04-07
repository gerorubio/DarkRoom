package darkroom;

import combate.Combate;
import entidad.Jugador;
import entidad.Monstruo;
import sonidos.sonidos;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import mapa.Cueva;
import mapa.Mapa;
import objetos.Lentes;
import objetos.Pico;
import tienda.Tienda;
import archivos.archivo;
import java.io.File;
import javafx.scene.image.Image;


/**
 *
 * @author Gerònimo
 */
public class DarkRoom extends Application {
    
    private Mapa overworld;
    public Jugador player;
    private Pane root;
    private GraphicsContext grafico;
    private Scene scene;
    static private Stage stage;
    private Cueva cave;
    private Canvas lienzo;
    private Button btnac;
    public Button btnInicio;
    public Button btnContinuar;
    public Button btnSalir;
    private Button btnEntrarCueva;
    private Button btnSalirCueva;
    private Button btnTienda;
    private Button btnSalirT;
    public Button btnLentes;
    public Button btnPico;
    private Button creditos;
    private final Label din = new Label();
    private Tienda tienda;
    private static sonidos sonido1 = new sonidos();
    public Cueva cavePlaying;
    public int cred; 
    
    public FinDelJuego fdj;
    
    @Override
    public void start(Stage primaryStage) {
        
        stage = primaryStage;
        root = new Pane();
        scene = new Scene(root, 976, 976);
        lienzo = new Canvas(976, 976);
        root.getChildren().add(lienzo);
        grafico = lienzo.getGraphicsContext2D();
        
        btnInicio = new Button();
        btnContinuar = new Button();
        btnSalir = new Button();
        creditos=new Button();
        
        this.sonido1.stop();
        this.sonido1.stop(2);
        this.sonido1.repro(1,1);
        
        btnInicio.setText("Iniciar Partida");
        btnContinuar.setText("Cargar Partida");
        btnSalir.setText("Salir");
        creditos.setText("Creditos");
       
        btnInicio.setLayoutX(450);
        btnContinuar.setLayoutX(450);
        btnSalir.setLayoutX(475);
        creditos.setLayoutX(465);
       
        btnInicio.setLayoutY(340);
        btnContinuar.setLayoutY(440);
        btnSalir.setLayoutY(540);
        creditos.setLayoutY(640);
        
        Image creditosim =new Image("darkroom/fondos/Menu.png");
        grafico.drawImage(creditosim, 0, 0);    //Ponemos fondo del menú
        
        root.getChildren().add(btnInicio);
        root.getChildren().add(btnContinuar);
        root.getChildren().add(btnSalir);
        root.getChildren().add(creditos);     
        
        
        lienzo.requestFocus();
        
        lienzo.setOnKeyPressed( (KeyEvent event) -> {
            
            switch (event.getCode()) {
                
                case W:
                    
                    if( player.isInCave )
                        moveUpCave();
                    else
                        moveUpOW();
                    break;
                    
                case S:
                    
                    if( player.isInCave )
                        moveDownCave();
                    else
                        moveDownOW();
                    break;
                    
                case A:
                    
                    if( player.isInCave )
                        moveLeftCave();
                    else
                        moveLeftOW();
                    break;
                    
                case D:
                    if( player.isInCave )
                        moveRightCave();
                    else
                        moveRightOW();
                    break;
                    
                case SPACE:
                    
                    if( btnac.isVisible() )
                        openChest();
                    else if( btnEntrarCueva.isVisible() )
                        enterCave();
                    else if( btnSalirCueva.isVisible() )
                        leaveCave();
                    break;               
            }
            
        });
        
        btnInicio.setOnAction((ActionEvent event) -> {
            menuOff(btnInicio, btnContinuar, btnSalir,creditos);
            player = new Jugador();
            askForName(primaryStage);
            din.setText( player.din + "" );
            din.setVisible( true );
            overworld = new Mapa(61, 61);
            overworld.generarMapa();
            jugar(primaryStage);
            this.sonido1.stop(1,1);
            this.sonido1.stop(2);
            this.sonido1.repro();
        });
        
        btnContinuar.setOnAction((ActionEvent event) -> {
            System.out.println("Cargar Partida");
            this.load(primaryStage);
            din.setVisible( true );
        });
        
        btnSalir.setOnAction((ActionEvent event) -> {
            System.out.println("Salir");
            primaryStage.close();
        });
        
        primaryStage.setTitle("Dark Room");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        creditos.setOnAction((ActionEvent event) -> {
        this.cred=0;
        Pane panel = new Pane();
        Image fondo = new Image("darkroom/fondos/creditos_1.jpg");
        Canvas canvas = new Canvas(976,976);
        this.grafico = canvas.getGraphicsContext2D();
        this.grafico.drawImage(fondo,0,0);
        Button btnsig = new Button("Siguiente");
        btnsig.setLayoutX(800);
        btnsig.setLayoutY(700);
        
        btnsig.setOnAction((ActionEvent v) -> {  
        if(this.cred==0)
        {           
            Image fondo2 = new Image("darkroom/fondos/creditos_2.jpg");
            this.grafico = canvas.getGraphicsContext2D();
            this.grafico.drawImage(fondo2,0,0);
            this.cred=1;
            fdj.start();         
        }
        else{
            this.start(this.stage);
        }
       
        });
        panel.getChildren().add(canvas);
        panel.getChildren().add(btnsig);
        
        
        Scene estaEscena = new Scene(panel,976,976);
        this.stage.setScene(estaEscena);
        cred=0;
        
        });
    }
    
    private void jugar(Stage primaryStage){
        
        btnac = new Button();
        tienda = new Tienda();
        Button btnGuardar = new Button();
        
        btnEntrarCueva = new Button();
        btnSalirCueva = new Button();
        btnTienda = new Button();
        btnSalirT = new Button();
        btnLentes = new Button();
        btnPico = new Button();
        
        root.getChildren().add(btnEntrarCueva);
        root.getChildren().add(btnSalirCueva);
        root.getChildren().add(btnTienda);
        root.getChildren().add(btnSalirT);
        root.getChildren().add(btnLentes);
        root.getChildren().add(btnPico);
        root.getChildren().add(btnGuardar);
        
        overworld.imprimirMapa( overworld.cuad, grafico );
        player.imprimirJugador( grafico, 'i' );
        lienzo.requestFocus();
        
        btnac.setText("Abrir cofre");
        btnac.setLayoutX(900);
        btnac.setLayoutY(350);
        din.setLayoutX(500);
        din.setLayoutY(0);
        din.setVisible( true );
        
        tienda.setTienda(root, player, din);
        tienda.tiendaOff();

        root.getChildren().add(btnac);
        root.getChildren().add(din);
        btnac.setVisible(false);
        
        fdj = new FinDelJuego( overworld, btnInicio, btnContinuar, btnSalir, btnLentes, btnPico, btnac, grafico, din );
        
        btnac.setOnAction((ActionEvent event) -> {
            openChest();
        });
        
        btnEntrarCueva.setText("Entrar a la cueva");
        btnGuardar.setText("Guardar Partida");
        btnEntrarCueva.setLayoutX(90);
        btnEntrarCueva.setLayoutY(90);
        btnEntrarCueva.setVisible(false);
        
        btnEntrarCueva.setOnAction((ActionEvent event) -> {
            enterCave();
        });
        
        btnSalirCueva.setText("Salir de la cueva");
        btnSalirCueva.setLayoutX(90);
        btnSalirCueva.setLayoutY(90);
        btnSalirCueva.setVisible(false);
        
        btnSalirCueva.setOnAction((ActionEvent event) -> {
            leaveCave();
        });
        
        btnTienda.setText("Tienda");
        btnTienda.setLayoutX(580);
        btnTienda.setLayoutY(0);
        btnSalirT.setLayoutX(580);
        btnGuardar.setLayoutX(660);
        btnGuardar.setLayoutY(0);
        btnSalirT.setLayoutY(0);
        btnSalirT.setText("Salir");
        btnSalirT.setVisible(false);
        
        btnTienda.setVisible(true);
        btnTienda.setOnAction((ActionEvent event) -> {
            grafico.clearRect( 0, 0, 976, 976 );
            player.saveCoord();
            btnTienda.setVisible(false);
            btnSalirT.setVisible(true);
            grafico.clearRect(0, 0, 976, 976);
            this.sonido1.stop();
            this.sonido1.repro(1,1,1,1);
            tienda.tiendaOn();
        });
        
        btnSalirT.setOnAction((ActionEvent event) ->{
            player.x = player.saveX;
            player.y = player.saveY;
            overworld.imprimirMapa( overworld.cuad, grafico );
            player.imprimirJugador( grafico, 'i' );
            btnSalirT.setVisible(false);
            btnTienda.setVisible(true);
            tienda.tiendaOff();
            this.sonido1.stop(1,1,1,1);
            this.sonido1.repro();
            lienzo.requestFocus();
        });
        
        btnLentes.setLayoutX(700);
        btnLentes.setLayoutY(300);
        btnLentes.setVisible(false);
        btnLentes.setText("Lentes");
        Lentes lentes = new Lentes();
        
        btnLentes.setOnAction((ActionEvent event) ->{
            if(player.hasLentes()){
                lentes.usarL(cavePlaying);
                player.usosLentes--;
                if(player.usosLentes <= 0){
                    player.setLentes(false);
                }
                cavePlaying.imprimirCuad(cave.cuad, grafico);
                player.imprimirJugador(grafico, 'i');
                lienzo.requestFocus();
            }else{
                lienzo.requestFocus();
            }
        });
        
        btnPico.setLayoutX(700);
        btnPico.setLayoutY(400);
        btnPico.setVisible(false);
        btnPico.setText("Pico");
        
        btnPico.setOnAction((ActionEvent event) ->{
            if(player.hasPico()){
                char direccion;
                direccion = player.dir;
                switch(direccion){
                    case 'u':
                        if(cavePlaying.cuadC[player.x][player.y - 1] == 1){
                            cavePlaying.cuadC[player.x][player.y - 1] = 0;
                            cavePlaying.cuad[player.x][player.y - 1] = 1;
                            player.usosPico--;
                            if(player.usosPico <= 0){
                                player.setPico(false);
                            }
                            cavePlaying.imprimirCuad(cave.cuad, grafico);
                            player.imprimirJugador(grafico, 'u');
                            lienzo.requestFocus();
                            break;
                        }else{
                            cavePlaying.imprimirCuad(cave.cuad, grafico);
                            player.imprimirJugador(grafico, 'u');
                            lienzo.requestFocus();
                            break;
                        }
                    case 'd':
                        if(cavePlaying.cuadC[player.x][player.y + 1] == 1){
                            cavePlaying.cuadC[player.x][player.y + 1] = 0;
                            cavePlaying.cuad[player.x][player.y + 1] = 1;
                            player.usosPico--;
                            if(player.usosPico <= 0){
                                player.setPico(false);
                            }
                            cavePlaying.imprimirCuad(cave.cuad, grafico);
                            player.imprimirJugador(grafico, 'd');
                            lienzo.requestFocus();
                            break;
                        }else{
                            cavePlaying.imprimirCuad(cave.cuad, grafico);
                            player.imprimirJugador(grafico, 'u');
                            lienzo.requestFocus();
                            break;
                        }
                    case 'r':
                        if(cavePlaying.cuadC[player.x + 1][player.y] == 1){
                            cavePlaying.cuadC[player.x + 1][player.y] = 0;
                            cavePlaying.cuad[player.x + 1][player.y] = 1;
                            player.usosPico--;
                            if(player.usosPico <= 0){
                                player.setPico(false);
                            }
                            cavePlaying.imprimirCuad(cave.cuad, grafico);
                            player.imprimirJugador(grafico, 'r');
                            lienzo.requestFocus();
                            break;
                        }else{
                            cavePlaying.imprimirCuad(cave.cuad, grafico);
                            player.imprimirJugador(grafico, 'u');
                            lienzo.requestFocus();
                            break;
                        }
                    case 'l':
                        if(cavePlaying.cuadC[player.x - 1][player.y] == 1){
                            cavePlaying.cuadC[player.x - 1][player.y] = 0;
                            cavePlaying.cuad[player.x - 1][player.y] = 1;
                            player.usosPico--;
                            if(player.usosPico <= 0){
                                player.setPico(false);
                            }
                            cavePlaying.imprimirCuad(cave.cuad, grafico);
                            player.imprimirJugador(grafico, 'l');
                            lienzo.requestFocus();
                            break;
                        }else{
                            cavePlaying.imprimirCuad(cave.cuad, grafico);
                            player.imprimirJugador(grafico, 'u');
                            lienzo.requestFocus();
                            break;
                        }
                }
            }else{
                lienzo.requestFocus();
            }
        });
        
    btnGuardar.setOnAction((ActionEvent event) -> {
            this.save();
        });
    }
    
    private void menuOff(Button a, Button b, Button c,Button d){
        
        a.setVisible(false);
        b.setVisible(false);
        c.setVisible(false);
        d.setVisible(false);
        
    }
    
    private boolean verificarCueva(){
        // 5 representa que hay una cueva
        return overworld.cuad[player.x][player.y] == 5;
    }
    
    private Cueva jugarCueva( int x, int y ){
        cavePlaying = new Cueva(1,1,1,1);
        int _x, _y;
        for(int i = 0; i < overworld.arrayCave.size(); i++){
            cavePlaying = overworld.arrayCave.get(i);
            if(cavePlaying.x == x && cavePlaying.y == y){
                System.out.println("Se encontro cueva");
                cavePlaying.imprimirCuad( cavePlaying.cuad, grafico );
                player.x = cavePlaying.xSpawn;
                player.y = cavePlaying.ySpawn;
                player.imprimirJugador( grafico, 'i' );
                return cavePlaying;
            }
        }
        
        return cavePlaying;
    }
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private void moveUpOW(){
        if(player.y != 0){
            player.moveUp(player, grafico);
            if( verificarCueva() == true ){
                btnEntrarCueva.setVisible(true);
            }else{
                btnEntrarCueva.setVisible(false);
            }
            overworld.imprimirMapa( overworld.cuad, grafico );
            player.imprimirJugador( grafico, 'u' );
            lienzo.requestFocus();
        }else{
            lienzo.requestFocus();
        }
        
    }
    
    private void moveDownOW(){
        if(player.y != 60){
            player.moveDown(player, grafico);
            if( verificarCueva() == true ){
                btnEntrarCueva.setVisible(true);
            }else{
                btnEntrarCueva.setVisible(false);
            }
            overworld.imprimirMapa( overworld.cuad, grafico );
            player.imprimirJugador( grafico, 'd' );
            lienzo.requestFocus();
        }else{
            lienzo.requestFocus();
        }
    }
    
    private void moveLeftOW(){
        if(player.x != 0){
            player.moveLeft( player, grafico );
            if( verificarCueva() == true ){
                btnEntrarCueva.setVisible(true);
            }else{
                btnEntrarCueva.setVisible(false);
            }
            overworld.imprimirMapa( overworld.cuad, grafico );
            player.imprimirJugador( grafico, 'l' );
            lienzo.requestFocus();
        }else{
            lienzo.requestFocus();
        }
    }
    
    private void moveRightOW(){
        if(player.x != 60){
            player.moveRight( player, grafico );
            if( verificarCueva() == true ){
                btnEntrarCueva.setVisible(true);
            }else{
                btnEntrarCueva.setVisible(false);
            }
            overworld.imprimirMapa( overworld.cuad, grafico );
            player.imprimirJugador( grafico, 'r' );
            lienzo.requestFocus();
        }else{
            lienzo.requestFocus();
        }
    }
    
    private void moveUpCave(){
        if(player.y != 0){
            if( cave.cuadC[player.x][player.y - 1] == 0 ){
                grafico.clearRect( 0, 0, 976, 976 );
                cave.reveal( cave.cuadl,player.x, player.y );
                cave.imprimirCuad( cave.cuad, grafico );
                player.moveUp( player, grafico );
                player.verificarC( cave, btnac );
                int id = cave.combatir( player.x, player.y );
                System.out.println(id);
                if(id != 0){
                    Combate battle = new Combate(player, new Monstruo(id), stage, scene,fdj);
                    battle.beginBattle(this.sonido1);
                }
                if( cave.cuad[player.x][player.y] == 9 ){
                    btnSalirCueva.setVisible(true);
                }else{
                    btnSalirCueva.setVisible(false);
                }
            }
            lienzo.requestFocus();
        }else{
            lienzo.requestFocus();
        }
    }
    
    private void moveDownCave(){
        if(player.y != 30){
            if( cave.cuadC[player.x][player.y + 1] == 0 ){
                grafico.clearRect( 0, 0, 976, 976 );
                cave.reveal( cave.cuadl,player.x, player.y );
                cave.imprimirCuad( cave.cuad, grafico );
                player.moveDown( player,grafico );
                player.verificarC( cave,btnac );
                int id = cave.combatir( player.x, player.y );
                if(id != 0){
                    Combate battle = new Combate(player,new Monstruo(id), stage, scene,fdj);
                    battle.beginBattle(this.sonido1);
                }
                if( cave.cuad[player.x][player.y] == 9 ){
                    btnSalirCueva.setVisible(true);
                }else{
                    btnSalirCueva.setVisible(false);
                }
            }
            lienzo.requestFocus();
        }else{
            lienzo.requestFocus();
        }
    }
    
    private void moveLeftCave(){
        if(player.x != 0){
            if( cave.cuadC[player.x - 1][player.y] == 0 ){
                grafico.clearRect( 0, 0, 976, 976 );
                cave.reveal( cave.cuadl,player.x, player.y );
                cave.imprimirCuad( cave.cuad, grafico );
                player.moveLeft( player, grafico );
                player.verificarC( cave, btnac );
                int id = cave.combatir( player.x, player.y );
                if(id != 0){
                    Combate battle = new Combate(player,new Monstruo(id), stage, scene,fdj);
                    battle.beginBattle(this.sonido1);
                }
                if( cave.cuad[player.x][player.y] == 9 ){
                    btnSalirCueva.setVisible(true);
                }else{
                    btnSalirCueva.setVisible(false);
                }
            }
            lienzo.requestFocus();
        }else{
            lienzo.requestFocus();
        }
    }
    
    private void moveRightCave(){
        if(player.x != 30){
            if( cave.cuadC[player.x+1][player.y] == 0 ){
                grafico.clearRect( 0, 0, 976, 976 );
                cave.reveal( cave.cuadl,player.x, player.y );
                cave.imprimirCuad( cave.cuad, grafico );
                player.moveRight( player, grafico );
                player.verificarC( cave, btnac );
                int id = cave.combatir( player.x, player.y );
                if(id != 0){
                    Combate battle = new Combate(player,new Monstruo(id), stage, scene,fdj);;
                    battle.beginBattle(this.sonido1);
                }
                if( cave.cuad[player.x][player.y] == 9 ){
                btnSalirCueva.setVisible(true);
                }else{
                    btnSalirCueva.setVisible(false);
                }
            }
            lienzo.requestFocus();
        }else{
            lienzo.requestFocus();
        }
    }
    
    private void openChest(){
        
        player.abrirC(cave);
        cave.imprimirCuad(cave.cuad, grafico);
        player.imprimirJugador( grafico, player.dir );
        btnac.setVisible(false);
        din.setText(Integer.toString(player.din));
        lienzo.requestFocus();
        this.sonido1.repro("aa");
        
    }
    
    private void enterCave(){
        
        btnTienda.setVisible(false);
        btnEntrarCueva.setVisible(false);
        btnLentes.setVisible(true);
        btnPico.setVisible(true);
        grafico.clearRect( 0, 0, 976, 976 );
        player.saveCoord();
        cave = jugarCueva( player.x, player.y );
        cave.reveal( cave.cuadl,player.x, player.y );
        cave.imprimirCuad( cave.cuad, grafico );
        player.imprimirJugador( grafico, 'i' );
        player.isInCave = true;
        lienzo.requestFocus();
        this.sonido1.stop();
        this.sonido1.repro(cave.zona);
        
    }
    
    private void leaveCave(){
        
        btnTienda.setVisible(true);
        btnLentes.setVisible(false);
        btnPico.setVisible(false);
        player.x = player.saveX;
        player.y = player.saveY;
        overworld.imprimirMapa( overworld.cuad, grafico );
        player.imprimirJugador( grafico, 'i' );
        btnSalirCueva.setVisible(false);
        player.isInCave = false;
        lienzo.requestFocus();
        this.sonido1.stop(2);
        this.sonido1.repro();
        
    }
    
    private void askForName(Stage escenario){
        
        Canvas elCanvas = new Canvas(976,976);
        GraphicsContext g = elCanvas.getGraphicsContext2D();
        g.setFill(Color.BLACK);
        g.fillRect(0, 0, 976, 976);
        
        Text texto = new Text();
        texto.setText("Ingresa un nombre");
        texto.setLayoutX(250);
        texto.setLayoutY(400);
        texto.setFill(Color.WHITE);
        
        TextField campo = new TextField();
        campo.setLayoutX(400);
        campo.setLayoutY(380);
        campo.setPrefSize(150, 20);
        
        Button confirmar = new Button("Iniciar");
        confirmar.setLayoutX(600);
        confirmar.setLayoutY(380);
        
        Pane panel = new Pane();
        panel.getChildren().add(elCanvas);
        panel.getChildren().add(texto);
        panel.getChildren().add(campo);
        panel.getChildren().add(confirmar);
        
        Scene escenaNombre = new Scene(panel,976,976);
        escenario.setScene(escenaNombre);
        
        confirmar.setOnAction((ActionEvent event) -> {
            String entrada = campo.getText();
            if(entrada.equals("")){
                player.setNombre("Héroe");
            }else{
                player.setNombre(entrada);
            }
            escenario.setScene(scene);            
        });
        
    }
    
    public void load(Stage primaryStage){
        System.out.println("Cargando partida...");
        File f = new File("./f1.txt");
        if(f.exists() && !f.isDirectory()) { 
            menuOff(btnInicio, btnContinuar, btnSalir,creditos);
            archivo arch = new archivo();
            archivo arch2 = arch.cargar();
            menuOff(btnInicio, btnContinuar, btnSalir,creditos);
            player = new Jugador();
            overworld = new Mapa(61, 61);
            overworld.generarMapa();
            this.overworld = arch2.Mt;
            this.player = arch2.Pt;
            this.din.setText( "" + player.din );
            this.din.setVisible( true );
            jugar(primaryStage);
            this.sonido1.stop(2);
            this.sonido1.stop(1,1);
            this.sonido1.repro();
        }else{
             System.out.println("Cargado fallido, no se encontró archivo...");
        }

    }
    
     public void save(){
        System.out.println("Guardando partida...");
        archivo arch = new archivo();
        arch.Mt = this.overworld;
        arch.Pt = this.player;
        arch.guardar();
        lienzo.requestFocus();
    }
     
     public void reboot()
    {
       this.start(this.stage);
    }
    
    
}