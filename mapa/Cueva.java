/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa;

import entidad.Jugador;
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Gerònimo
 */
public class Cueva extends Mapa{
    
    public int cuadC [][];
    public int cuadl[][];
    public int limiteN = 4;
    public int limiteM = 3;
    public int contadorCombates=0;
    public int x, y;
    public int zona;
    public int xSpawn, ySpawn;
    
    public Cueva(int _columnas, int _filas, int _x, int _y) {
        super(_columnas, _filas);
        this.x = _x;
        this.y = _y;
    }

    public Cueva() {
        
    }
    
    public int[][] getCuad() {
        return cuad;
    }

    public void setCuad(int[][] cuad) {
        this.cuad = cuad;
    }
    
    public Cueva crear(Cueva cave){
        Jugador player = new Jugador();
        player.x = 13;
        player.y = 13;
        cave.generarCueva();
        for(int i = 0; i < 5; i++){
            cave.cuad = cave.simularPaso(cave.cuad);
        }
        cave.ponerPolvo(cave.cuad);
        cave.ponerTesoro(cave.cuad);
        cave.generarColisiones(cave.cuad);
        cave.obs(cave.cuad);
        
        for(int i = 6; i <= 10; i ++){
            for(int j = 9; j <= 13; j ++){
                cave.cuadC[i][j] = 0;
                cave.cuad[i][j] = 1;
            }
        }
        
        
        cave.xSpawn = 9;
        cave.ySpawn = 10;
        cave.cuad[8][11] = 9;
        
        player.verificarS(cave.cuadC);
        
        
        return cave;
        
        
    }
    
    public void generarCueva(){
        Random rand = new Random();
        int[][] multi = new int[this.filas][this.columnas];
        int prob1 = 40;
    
        for(int xf=0; xf<this.filas; xf++){
            for(int yf=0; yf<this.columnas; yf++){
                if(rand.nextInt(100) < prob1){
                    multi[xf][yf] = 2;
                }else{
                    multi[xf][yf] = 1;
                }
            }
        }
        for(int xf=0; xf<this.filas; xf++){
            for(int yf=0; yf<this.columnas; yf++){
                if(rand.nextInt(100) < prob1){
                    multi[xf][yf] = 2;
                }else{
                    multi[xf][yf] = 1;
                }
            }
        }
        this.cuad = multi;
    }
    
    public int[][] simularPaso(int[][] vMap){
        int[][] nMap = new int[this.filas][this.columnas];
        for(int xf=0; xf<vMap.length; xf++){
            for(int yf=0; yf<vMap[0].length; yf++){
                int nbs = contarVecinosVivos(vMap, xf, yf);
                if(vMap[xf][yf] == 2){
                    if(nbs < limiteM){
                        nMap[xf][yf] = 1;
                    }
                    else{
                        nMap[xf][yf] = 2;
                    }
                } 
                else{
                    if(nbs > limiteN){
                        nMap[xf][yf] = 2;
                    }
                    else{
                        nMap[xf][yf] = 1;
                    }
                }
            }
        }
        return nMap;
    }
    
    
    
public int contarVecinosVivos(int[][] map, int x, int y){
    int cuenta = 0;
    for(int i=-1; i<2; i++){
        for(int j=-1; j<2; j++){
            int vecino_x = x+i;
            int vecino_y = y+j;
            if(i == 0 && j == 0){
            }            
            else if(vecino_x < 0 || vecino_y < 0 || vecino_x >= map.length || vecino_y >= map[0].length){
                cuenta = cuenta + 1;
            }
            else if(map[vecino_x][vecino_y] == 2){
                cuenta = cuenta + 1;
            }
        }
    }
    return cuenta;
}

    public int contarVecinost(int[][] map, int x, int y){
    int cuenta = 0;
    for(int i=-1; i<2; i++){
        for(int j=-1; j<2; j++){
            int vecino_x = x+i;
            int vecino_y = y+j;
            if(i == 0 && j == 0){
            }            
            else if(vecino_x < 0 || vecino_y < 0 || vecino_x >= map.length || vecino_y >= map[0].length){
                
            }
            else if(map[vecino_x][vecino_y] == 3){
                cuenta = cuenta + 1;
            }
        }
    }
    return cuenta;
}

    public int rt(int[][] map, int x, int y){
        int cuenta = 0;
        for(int i=-1; i<2; i++){
            for(int j=-1; j<2; j++){
                int vecino_x = x+i;
                int vecino_y = y+j;
                if(vecino_x < 0 || vecino_y < 0 || vecino_x >= map.length || vecino_y >= map[0].length){
            }
                else if(map[vecino_x][vecino_y] == 3){
                    cuenta++;
                    map[vecino_x][vecino_y] = 6;
                }
            }
        }
        return cuenta;
    }
    
    public void reveal(int[][] map, int x, int y){
    for(int i=-2; i<=2; i++){
        for(int j=-2; j<=2; j++){
            int vecino_x = x+i;
            int vecino_y = y+j;
            if(i == 0 && j == 0){
                if(map[vecino_x][vecino_y] == 0)
                map[vecino_x][vecino_y] = 1;
            }            
            else if(vecino_x < 0 || vecino_y < 0 || vecino_x >= map.length || vecino_y >= map[0].length){
            }
            else if(map[vecino_x][vecino_y] == 0){
                map[vecino_x][vecino_y] = 1;
            }
       
    }
        this.cuadl = map;
    }
   }
    
    public void obs(int [][] mapa){
        int[][] multi = new int[this.filas][this.columnas];
        for (int xf=0; xf < this.filas; xf++){
            for (int yf=0; yf < this.columnas; yf++){
                if(mapa[xf][yf] != 0)
                    multi[xf][yf] = 0;
              
            }
        }
        this.cuadl = multi;
    }

    public void ponerPolvo(int[][] map){
        Random rand = new Random();
        int piso;
        for (int xf=0; xf < this.filas; xf++){
            for (int yf=0; yf < this.columnas; yf++){
                if(map[xf][yf] == 1){
                    piso = rand.nextInt(100);  
                    if(piso < 100)
                        this.cuad[xf][yf] = 1;
                    if(piso < 20)
                        this.cuad[xf][yf] = 5;
                    if(piso < 10)
                        this.cuad[xf][yf] = 4;
                }
            }
        }
    }

    public void ponerTesoro(int[][] mapa){
  
    int tesoroescondido = 5;
    for (int xf=0; xf < this.filas; xf++){
        for (int yf=0; yf < this.columnas; yf++){
            if(mapa[xf][yf] == 1 || mapa[xf][yf] == 4 || mapa[xf][yf] == 5){
                int nbs = contarVecinosVivos(mapa, xf, yf);
                if(nbs >= tesoroescondido){
                    this.cuad[xf][yf] = 3;
                }
            }
        }
    }
}

    public void generarColisiones(int [][] mapa){
        int[][] multi = new int[this.filas][this.columnas];
        for (int xf=0; xf < this.filas; xf++){
            for (int yf=0; yf < this.columnas; yf++){
                if(mapa[xf][yf] == 2 || mapa[xf][yf] == 3){
                    multi[xf][yf] = 1;
                }else{
                    multi[xf][yf] = 0;
                }
            }
        }
        this.cuadC = multi;
    }
    
    public void imprimirCuad(int cuad[][],GraphicsContext grafico){
        
        Image m1 = new Image("mapa/tilesCueva"+zona+"/piso.png");   //suelo
        Image m2 = new Image("mapa/tilesCueva"+zona+"/stone.png");  //pared
        Image m3 = new Image("mapa/tilesCueva"+zona+"/tesoro.png"); //tesoro
        Image m4 = new Image("mapa/tilesCueva"+zona+"/polvo_1.png");    //polvo1
        Image m5 = new Image("mapa/tilesCueva"+zona+"/polvo_2.png");    //polvo2
        Image m6 = new Image("mapa/tilesCueva"+zona+"/tesoroa.png");    //tesoro abierto
        Image m7 = new Image("mapa/tilesCueva1/tileExit.png");
        Image m8 = new Image("mapa/tilesCueva1/nothing.png");
        /*
        Image m1 = new Image("mapa/tilesCueva/tile1.png");
        Image m2 = new Image("mapa/tilesCueva/tile2.png");
        Image m3 = new Image("mapa/tilesCueva/tiletesoro.png");
        Image m4 = new Image("mapa/tilesCueva/tile4.png");
        Image m5 = new Image("mapa/tilesCueva/tile4.png");
        Image m6 = new Image("mapa/tilesCueva/tiletesoroa.png");
        Image m7 = new Image("mapa/tilesCueva/tileExit.png");
        Image m8 = new Image("mapa/tilesCueva/nothing.png");
        */
        
        for(int xf=0;xf<this.filas;xf++){
            for(int yf=0;yf<this.columnas;yf++){
                if(this.cuadl[xf][yf] == 0){
                    grafico.drawImage(m8,xf*16,yf*16);
                }else{
                if(cuad[xf][yf] == 1)
                    grafico.drawImage(m1,xf*16,yf*16);
                if(cuad[xf][yf] == 2)
                    grafico.drawImage(m2,xf*16,yf*16);
                if(cuad[xf][yf] == 3)
                    grafico.drawImage(m3,xf*16,yf*16);
                if(cuad[xf][yf] == 4)
                    grafico.drawImage(m4,xf*16,yf*16);
                if(cuad[xf][yf] == 5)
                    grafico.drawImage(m5,xf*16,yf*16);
                if(cuad[xf][yf] == 6)
                    grafico.drawImage(m6,xf*16,yf*16);
                if(cuad[xf][yf] == 9)//Marca la salida
                    grafico.drawImage(m7,xf*16,yf*16);
                }
            }
        }
        
   }
   public  int combatir(int x, int y){
        if(contadorCombates==zona*5){
            return 0;
        }
        Random rand = new Random();
        int propuesta = rand.nextInt(100);
        if(zona==4){
            if(propuesta>=99)
                return Integer.parseInt(Integer.toString(zona)+Integer.toString(1));
            else
                return 0;
        }
        if (this.cuad[x][y]==4||this.cuad[x][y]==5){
            propuesta=propuesta+10;
        }
        if(contadorCombates!=0){
            propuesta=propuesta-(contadorCombates*5);
        }
        propuesta+=(zona-1)*5;
        if(propuesta>=90){
            contadorCombates++;
            int probabilidad = rand.nextInt(100);
            switch (zona) {
                case 1:
                    if (probabilidad > 0 && probabilidad <50)
                        return Integer.parseInt(Integer.toString(zona)+Integer.toString(1));
                    else if (probabilidad > 50){
                        return Integer.parseInt(Integer.toString(zona)+Integer.toString(2));
                    }   break;
                case 2:
                case 3:
                    if (probabilidad > 0 && probabilidad <25)
                        return Integer.parseInt(Integer.toString(zona)+Integer.toString(1));
                    else if (probabilidad > 25 && probabilidad < 50){
                        return Integer.parseInt(Integer.toString(zona)+Integer.toString(2));
                    }
                    else if (probabilidad > 50 && probabilidad < 75){
                        return Integer.parseInt(Integer.toString(zona)+Integer.toString(3));
                    }
                    else if (probabilidad > 75){
                        return Integer.parseInt(Integer.toString(zona)+Integer.toString(4));
                    }   break;
                default:
                    return 0;
            }
        }
        return 0;        
    }
    
}
