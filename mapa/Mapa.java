/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Gerònimo
 */
public class Mapa implements Serializable {
    public int cuad[][];
    public int filas;
    public int columnas;
    public ArrayList<Cueva> arrayCave;
    public int limitCave = 10;
    public int numCaveZ1 = 0;
    public int numCaveZ2 = 0;
    public int numCaveZ3 = 0;
    public int numCaveZ4 = 0;
    public int tope = 0;
    
    public Mapa(){}
    
    public Mapa(int _filas, int _columnas){
        this.columnas = _columnas;
        this.filas = _filas;
        this.arrayCave = new ArrayList<>();
    }
    
    public void generarMapa(){
        Random rand = new Random();
        int probZonas = 30;
        int [][] matriz = new int [this.filas][this.columnas];
        
        //Generacion Overworld
        
        for(int i = 0; i < this.filas; i++){
            for(int j = 0; j < this.columnas; j++){
                if(i >= 0 && i <= 60 && j >= 0 && j <= 60){
                    matriz[i][j] = 3;
                }

                if(i >= 15 && i <= 45 && j >= 15 && j <= 45 ){
                	matriz[i][j] =2;
                }
                if(i >=  25 && i <= 35 && j >= 25 && j <= 35){
                	matriz[i][j] = 1;
                }
            }
        }
        
        //Reduccion de limites bruscos
        
        for(int i = 0; i < this.filas; i++){
            for(int j = 0; j < this.columnas; j++){
                if(((i >= 0 && i <= 3) || (i >= 57 && i <= 60))){
                    if(rand.nextInt(100) < probZonas){
                        matriz[i][j] = 4;
                    }
                }
                if(((j >= 0 && j <= 3) || (j >= 57 && j <= 60))){
                    if(rand.nextInt(100) < probZonas){
                        matriz[i][j] = 4;
                    }
                }
                
                if(((i >= 13 && i <= 17) || (i >= 43 && i <= 47)) && ((j >= 13) && (j <= 47))){
                    if(rand.nextInt(100) < probZonas){
                        matriz[i][j] = 3;
                    }else {
                        matriz[i][j] = 2;
                    }
                }
                if(((j >= 13 && j <= 17) || (j >= 43 && j <= 47)) && ((i >= 13) && (i <= 47))){
                    if(rand.nextInt(100) < probZonas){
                        matriz[i][j] = 3;
                    }else {
                        matriz[i][j] = 2;
                    }
                }
                
                if(((i >= 23 && i <= 28) || (i >= 33 && i <= 38)) && ((j >= 23) && (j <= 38))){
                    if(rand.nextInt(100) < probZonas){
                        matriz[i][j] = 2;
                    }else {
                        matriz[i][j] = 1;
                    }
                }
                if(((j >= 23 && j <= 28) || (j >= 33 && j <= 38)) && ((i >= 23) && (i <= 38))){
                    if(rand.nextInt(100) < probZonas - 10){
                        matriz[i][j] = 2;
                    }else {
                        matriz[i][j] = 1;
                    }
                }
            }
        }
        
        //Generacion Cuevas en Overworld
        double probCueva = 100.0;
        double auCueva = .025;
        
        Cueva cave;
        
        for(int i = 0; i < this.filas; i++){
            for(int j = 0; j < this.columnas; j++){
                if(rand.nextInt(100) < probCueva){
                    switch(matriz[i][j]){
                        case 1:
                            if(numCaveZ1 < limitCave){
                                System.out.println("Cueva" + numCaveZ1 + "zona 1");
                                cave = new Cueva(31, 31, i, j);
                                cave.zona = matriz[i][j];
                                arrayCave.add(cave);
                                cave.crear(cave);
                                numCaveZ1++;
                                tope++;
                                matriz[i][j] = 5;
                                auCueva = 2;
                            }
                            break;
                        
                        case 2:
                            if(numCaveZ2 < limitCave + 20){
                                System.out.println("Cueva" + numCaveZ2 + "zona 2");
                                cave = new Cueva(31, 31, i, j);
                                cave.zona = matriz[i][j];
                                arrayCave.add(cave);
                                cave.crear(cave);
                                numCaveZ2++;
                                tope++;
                                matriz[i][j] = 5;
                                auCueva = .033;
                            }
                            break;
                            
                        case 3:
                            if(numCaveZ3 < limitCave + 20){
                                System.out.println("Cueva" + numCaveZ3 + "zona 3");
                                cave = new Cueva(31, 31, i, j);
                                cave.zona = matriz[i][j];
                                arrayCave.add(cave);
                                cave.crear(cave);
                                numCaveZ3++;
                                tope++;
                                matriz[i][j] = 5;
                                auCueva = .014;
                            }
                            break;
                        
                        case 4:
                            if(numCaveZ4 < 1){
                                System.out.println("Cueva" + numCaveZ4 + "zona 4");
                                cave = new Cueva(31, 31, i, j);
                                cave.zona = matriz[i][j];
                                arrayCave.add(cave);
                                cave.crear(cave);
                                numCaveZ4++;
                                tope++;
                                matriz[i][j] = 5;
                            }
                            break;
                            
                        default:
                            System.out.print("");
                    }
                    
                    probCueva = 0;
                }else{
                    probCueva = probCueva + auCueva;
                }
            }
        }
        this.cuad = matriz;
    }
    
    public void imprimirMapa(int cuad[][], GraphicsContext grafico){
        Image m1 = new Image("mapa/tiles/tile1.png");
        Image m2 = new Image("mapa/tiles/tile2.png");
        Image m3 = new Image("mapa/tiles/tile3.png");
        Image m4 = new Image("mapa/tiles/tile4.png");
        Image m5 = new Image("mapa/tiles/tile5.png");
        for(int x = 0; x < this.filas; x++){
            for(int y = 0; y < this.columnas; y++){
                if(cuad[x][y] == 1)
                    grafico.drawImage(m1, x*16, y*16);
                if(cuad[x][y] == 2)
                    grafico.drawImage(m2, x*16, y*16);
                if(cuad[x][y] == 3)
                    grafico.drawImage(m3, x*16, y*16);
                if(cuad[x][y] == 4)
                    grafico.drawImage(m4, x*16, y*16);
                if(cuad[x][y] == 5)
                    grafico.drawImage(m5, x*16, y*16);
            }
        }
    }    
}
