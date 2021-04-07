/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda;

import entidad.Jugador;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import objetos.Lentes;
import objetos.Pico;
import objetos.Pocion;

/**
 *
 * @author Gerònimo
 */
public class Tienda{
    
    Jugador player;
    
    public Label espada = new Label("Mejorar espada");
    public Label vida = new Label("Cristal de vida");
    public Label pocionMejora = new Label("Estante para pociones");
    public Label pocion = new Label("Poción");
    public Label pico = new Label("Pico");
    public Label lentes = new Label("Lentes");
    
    public Button btnEspada;
    public Button btnVida;
    public Button btnPocionMejora;
    public Button btnPocion;
    public Button btnPico;
    public Button btnLentes;
    
    public Label lAtaque;
    public Label lVida;
    public Label lPocion;
    public Label lValPocion;
    public Label lPico;
    public Label lLentes;
    
    public Tienda(){}
    
    public void setTienda(Pane root, Jugador player, Label din){
        
        this.player = player;
        
        String p = ( player.hasPico() ) ? "Sí" : "No";
        String l = ( player.hasLentes() ) ? "Sí" : "No";
        
        lAtaque = new Label("Ataque: " + Integer.toString( player.getAtaque() ) );
        lVida = new Label("Vida: " + Integer.toString( player.getVida() ) );
        lPocion = new Label("Poción: " + Integer.toString( player.getNumPociones() ) );
        lValPocion = new Label(" Valor de Curación: " + Integer.toString( Pocion.getHealPoints() ) );
        lPico = new Label("Pico: " + p);
        lLentes = new Label("Lentes: " + l);

        lAtaque.setLayoutX(500);
        lVida.setLayoutX(500);
        lPocion.setLayoutX(500);
        lValPocion.setLayoutX(500);
        lPico.setLayoutX(500);
        lLentes.setLayoutX(500);

        lAtaque.setLayoutY(150);
        lVida.setLayoutY(200);
        lValPocion.setLayoutY(250);
        lPocion.setLayoutY(300);
        lPico.setLayoutY(350);
        lLentes.setLayoutY(400);

        root.getChildren().add(lAtaque);
        root.getChildren().add(lVida);
        root.getChildren().add(lPocion);
        root.getChildren().add(lValPocion);
        root.getChildren().add(lPico);
        root.getChildren().add(lLentes);
        
        String c = "Comprar";

        espada.setLayoutX(200);
        vida.setLayoutX(200);
        pocionMejora.setLayoutX(200);
        pocion.setLayoutX(200);
        pico.setLayoutX(200);
        lentes.setLayoutX(200);
        
        espada.setLayoutY(150);
        vida.setLayoutY(200);
        pocionMejora.setLayoutY(250);
        pocion.setLayoutY(300);
        pico.setLayoutY(350);
        lentes.setLayoutY(400);
        
        root.getChildren().add(espada);
        root.getChildren().add(vida);
        root.getChildren().add(pocionMejora);
        root.getChildren().add(pocion);
        root.getChildren().add(pico);
        root.getChildren().add(lentes);
        
        btnEspada = new Button();
        btnVida = new Button();
        btnPocionMejora = new Button();
        btnPocion = new Button();
        btnPico = new Button();
        btnLentes = new Button();

        btnEspada.setLayoutX(325);
        btnVida.setLayoutX(325);
        btnPocionMejora.setLayoutX(325);
        btnPocion.setLayoutX(325);
        btnPico.setLayoutX(325);
        btnLentes.setLayoutX(325);

        btnEspada.setLayoutY(150);
        btnVida.setLayoutY(200);
        btnPocionMejora.setLayoutY(250);
        btnPocion.setLayoutY(300);
        btnPico.setLayoutY(350);
        btnLentes.setLayoutY(400);

        btnEspada.setText(c + "  10 oro");
        btnVida.setText(c + "  15 oro");
        btnPocionMejora.setText(c + "  20 oro");
        btnPocion.setText(c + "  2 oro");
        btnPico.setText(c + "  5 oro");
        btnLentes.setText(c + "  7 oro");

        root.getChildren().add(btnEspada);
        root.getChildren().add(btnVida);
        root.getChildren().add(btnPocionMejora);
        root.getChildren().add(btnPocion);
        root.getChildren().add(btnPico);
        root.getChildren().add(btnLentes);
        
        btnEspada.setVisible(true);
        btnVida.setVisible(true);
        btnPocionMejora.setVisible(true);
        btnPocion.setVisible(true);
        btnPico.setVisible(true);
        btnLentes.setVisible(true);

        btnEspada.setOnAction((ActionEvent event) -> {
            if(player.din >= 10){
                player.din -= 10;
                player.modifyAtaque( 2 );
                lAtaque.setText("Ataque: " + Integer.toString( player.getAtaque() ));
                din.setText(Integer.toString(player.din));
            }
        });

        btnVida.setOnAction((ActionEvent event) -> {
            if(player.din >= 15){
                player.din -= 15;
                player.modifyMaxVida( 20 );
                lVida.setText("Vida: " + Integer.toString( player.getVida() ));
                din.setText(Integer.toString(player.din));
            }
        });

        btnPocionMejora.setOnAction((ActionEvent event) -> {
            if(player.din >= 20){
                player.din -= 20;
                Pocion.addHealPoints();
                lValPocion.setText("Valor de curación: " + Integer.toString( Pocion.getHealPoints() ) );
                din.setText(Integer.toString(player.din));
            }
        });

        btnPocion.setOnAction((ActionEvent event) -> {
            if( player.din >= 2 && player.getNumPociones() <= 10 ){
                player.din -= 2;
                player.addItem( new Pocion() );
                lPocion.setText("Poción: " + Integer.toString( player.getNumPociones()) );
                din.setText(Integer.toString(player.din));
            }
        });

        btnPico.setOnAction((ActionEvent event) -> {
            if(player.din >= 5 && !player.hasPico() ){
                player.din -= 5;
                player.addItem( new Pico() );
                player.usosPico = 7;
                lPico.setText("Pico: " + ( ( !player.hasPico() ) ? "No" : "Sí") );
                din.setText(Integer.toString(player.din));
            }
        });

        btnLentes.setOnAction((ActionEvent event) -> {
            if(player.din >= 7 && !player.hasLentes() ){
                player.din -= 7;
                player.addItem( new Lentes() );
                player.usosLentes = 2;
                lLentes.setText("Lentes: " + ( ( !player.hasLentes() ) ? "No" : "Sí") );
                din.setText(Integer.toString(player.din));
            }
        });
        
        this.tiendaOn();
    }
    
    public void tiendaOff(){
        espada.setVisible(false);
        vida.setVisible(false);
        pocionMejora.setVisible(false);
        pocion.setVisible(false);
        pico.setVisible(false);
        lentes.setVisible(false);
        
        btnEspada.setVisible(false);
        btnVida.setVisible(false);
        btnPocionMejora.setVisible(false);
        btnPocion.setVisible(false);
        btnPico.setVisible(false);
        btnLentes.setVisible(false);
        
        lAtaque.setVisible(false);
        lVida.setVisible(false);
        lPocion.setVisible(false);
        lValPocion.setVisible(false);
        lPico.setVisible(false);
        lLentes.setVisible(false);
    }
    
    public void tiendaOn(){
        
        String p = ( player.hasPico() ) ? "Sí" : "No";
        String l = ( player.hasLentes() ) ? "Sí" : "No";
        
        lAtaque.setText( "Ataque: " + Integer.toString( player.getAtaque() ) );
        lVida.setText("Vida: " + Integer.toString( player.getVida() ) );
        lPocion.setText("Poción: " + Integer.toString( player.getNumPociones() ) );
        lValPocion.setText(" Valor de Curación: " + Integer.toString( Pocion.getHealPoints() ) );
        lPico.setText("Pico: " + p );
        lLentes.setText("Lentes: " + l );
        
        espada.setVisible(true);
        vida.setVisible(true);
        pocionMejora.setVisible(true);
        pocion.setVisible(true);
        pico.setVisible(true);
        lentes.setVisible(true);
        
        btnEspada.setVisible(true);
        btnVida.setVisible(true);
        btnPocionMejora.setVisible(true);
        btnPocion.setVisible(true);
        btnPico.setVisible(true);
        btnLentes.setVisible(true);
        
        lAtaque.setVisible(true);
        lVida.setVisible(true);
        lPocion.setVisible(true);
        lValPocion.setVisible(true);
        lPico.setVisible(true);
        lLentes.setVisible(true);
        
    }
}
