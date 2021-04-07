/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darkroom;

import entidad.Jugador;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import mapa.Mapa;

/**
 *
 * @author DanteBM
 */
public class FinDelJuego extends Thread{
    Mapa ow;
    Button btnIniciar;
    Button btnContinuar;
    Button btnSalir;
    Button a;
    Button b;
    Button c;
    GraphicsContext g;
    Label labelDin;

    public FinDelJuego( Mapa ow,Button btnIniciar, Button btnContinuar, 
            Button btnSalir, Button a, Button b, Button c, GraphicsContext g, Label din ) {
        this.ow = ow;
        this.btnIniciar = btnIniciar;
        this.btnContinuar = btnContinuar;
        this.btnSalir = btnSalir;
        this.a = a;
        this.b = b;
        this.c = c;
        this.g = g;
        this.labelDin = din;
    }
    
    @Override
    public void run(){
        System.out.println("Iniciamos");
        g.clearRect(0, 0, 976, 976);
        btnIniciar.setVisible(true);
        btnContinuar.setVisible(true);
        btnSalir.setVisible(true);
        ow = null;
        labelDin.setVisible( false );
        if(a != null)
            a.setVisible(false);
        if(b != null)
            b.setVisible(false);
        if(c != null)
            c.setVisible(false);
    }
    
    
}
