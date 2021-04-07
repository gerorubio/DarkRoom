/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonidos;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 *
 * @author LOLO
 */
public class sonidos {

    Media cancion1 = new Media(sonidos.class.getResource("overworld.mp3").toString());
    MediaPlayer mediaPlayer = new MediaPlayer(cancion1);
    Media cancion2 = new Media(sonidos.class.getResource("cave.mp3").toString());
    MediaPlayer mediaPlayer2 = new MediaPlayer(cancion2);
    Media sound = new Media(sonidos.class.getResource("money.mp3").toString());
    MediaPlayer mediaPlayer3 = new MediaPlayer(sound);
    Media cancion3 = new Media(sonidos.class.getResource("main.mp3").toString());
    MediaPlayer mediaPlayer4 = new MediaPlayer(cancion3);
    Media cancion4 = new Media(sonidos.class.getResource("batalla.mp3").toString());
    MediaPlayer mediaPlayer5 = new MediaPlayer(cancion4);
    Media cancion5 = new Media(sonidos.class.getResource("tienda.mp3").toString());
    MediaPlayer mediaPlayer6 = new MediaPlayer(cancion5);
    Media cancion6 = new Media(sonidos.class.getResource("darking.mp3").toString());
    MediaPlayer mediaPlayer7 = new MediaPlayer(cancion6);
    Media cancion7 = new Media(sonidos.class.getResource("stage2.mp3").toString());
    MediaPlayer mediaPlayer8 = new MediaPlayer(cancion7);
    Media cancion8 = new Media(sonidos.class.getResource("stage3.mp3").toString());
    MediaPlayer mediaPlayer9 = new MediaPlayer(cancion8);
    Media cancion9 = new Media(sonidos.class.getResource("stage4.mp3").toString());
    MediaPlayer mediaPlayer10 = new MediaPlayer(cancion9);
    Media sonido2 = new Media(sonidos.class.getResource("gameover.mp3").toString());
    MediaPlayer mediaPlayer11 = new MediaPlayer(sonido2);
    
    public sonidos() {
    }
    
    public void repro(){
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.seek(Duration.ZERO);
        });
        mediaPlayer.play();
    }
    
    public void repro(int a){
        if(a == 1){
        mediaPlayer2.setOnEndOfMedia(() -> {
            mediaPlayer2.seek(Duration.ZERO);
        });
        mediaPlayer2.play();
    }
        if(a == 2){
            mediaPlayer8.setOnEndOfMedia(() -> {
            mediaPlayer8.seek(Duration.ZERO);
        });
        mediaPlayer8.play();
    }
        if(a == 3){
            mediaPlayer9.setOnEndOfMedia(() -> {
            mediaPlayer9.seek(Duration.ZERO);
        });
        mediaPlayer9.play();
    }
        if(a == 4){
            mediaPlayer10.setOnEndOfMedia(() -> {
            mediaPlayer10.seek(Duration.ZERO);
        });
        mediaPlayer10.play();
    }
    }
    
    
    public void repro(String a){
        mediaPlayer3.play();
        mediaPlayer3.setOnEndOfMedia(() -> {
        mediaPlayer3.stop();
        });     
    }
    public void repro(String a,String b){
        mediaPlayer11.play();
        mediaPlayer11.setOnEndOfMedia(() -> {
        mediaPlayer11.stop();
        });     
    }
    
    public void repro(int a,int b){
        mediaPlayer4.setOnEndOfMedia(() -> {
            mediaPlayer4.seek(Duration.ZERO);
        });
        mediaPlayer4.play();
    }
    
    public void repro(int a,int b,int c){
        mediaPlayer5.setOnEndOfMedia(() -> {
            mediaPlayer5.seek(Duration.ZERO);
        });
        mediaPlayer5.play();
    }
    public void repro(int a,int b,int c,int d){
        mediaPlayer6.setOnEndOfMedia(() -> {
            mediaPlayer6.seek(Duration.ZERO);
        });
        mediaPlayer6.play();
    }
    public void repro(int a,int b,int c,int d,int e){
        mediaPlayer7.setOnEndOfMedia(() -> {
            mediaPlayer7.seek(Duration.ZERO);
        });
        mediaPlayer7.play();
    }
    
    public void stop(){
        mediaPlayer.stop();
    }
    public void stop(int a){
        mediaPlayer2.stop();
        mediaPlayer8.stop();
        mediaPlayer9.stop();
        mediaPlayer10.stop();
        mediaPlayer4.stop();
        mediaPlayer5.stop();
        mediaPlayer6.stop();
        mediaPlayer7.stop();
    }
    public void stop(int a,int b){
        mediaPlayer4.stop();
    }
    public void stop(int a,int b,int c){
        mediaPlayer5.stop();
    }
    public void stop(int a,int b,int c,int d){
        mediaPlayer6.stop();
    }
    public void stop(int a,int b,int c,int d,int e){
        mediaPlayer7.stop();
    }
        
}
