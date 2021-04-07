/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package combate;

import javafx.scene.image.Image;

/**
 *
 * @author DanteBM
 */
public interface Galeria {
    // Imagenes de los monstruos
    Image MONSTER11 = new Image("combate/monstruos/11.png");
    Image MONSTER12 = new Image("combate/monstruos/12.png");
    Image MONSTER21 = new Image("combate/monstruos/21.png");
    Image MONSTER22 = new Image("combate/monstruos/22.png");
    Image MONSTER23 = new Image("combate/monstruos/23.png");
    Image MONSTER27 = new Image("combate/monstruos/27.png");
    Image MONSTER31 = new Image("combate/monstruos/31.png");
    Image MONSTER32 = new Image("combate/monstruos/32.png");
    Image MONSTER33 = new Image("combate/monstruos/33.png");
    Image MONSTER41 = new Image("combate/monstruos/41.png");
    // Imagenes de fondo
    Image FONDO1 = new Image("combate/fondos/1.png"); 
    Image FONDO2 = new Image("combate/fondos/2.png");
    Image FONDO3 = new Image("combate/fondos/3.png");
    Image FONDO4 = new Image("combate/fondos/4.png");
   
    /**
     * Método que retorna la imagen del monstruo
     * @param id El identificador único del monstruo
     * @return La imagen correspondiente del monstruo
     */
    
    default Image getMonster(int id){
        switch(id){
            case 11:
                return MONSTER11;
            case 12:
                return MONSTER12;
            case 21:
                return MONSTER21;
            case 22:
                return MONSTER22;
            case 23:
                return MONSTER23;
            case 27:
                return MONSTER27;
            case 31:
                return MONSTER31;
            case 32:
                return MONSTER32;
            case 33:
                return MONSTER33;
            case 41:
                return MONSTER41;   
        }
        return null;
    }
    
    /**
     * Método que retorna el fondo de la zona.
     * @param zona El número de la zona (1,2,3,4)
     * @return La imagen de fondo
     */
    
    default Image getWallpaper(int zona){
        switch(zona){
            case 1:
                return FONDO1;
            case 2:
                return FONDO2;
            case 3:
                return FONDO3;
            case 4:
                return FONDO4;   
        }
        return null;
    }
   
}
