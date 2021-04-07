/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import mapa.Cueva;

/**
 *
 * @author 
 */
public class Lentes extends Item {
    
    public Lentes() {
        super( "Lentes" );
    }
    
    public void usarL(Cueva cave) {
        for(int i = 0; i < 31; i++){
            for(int j = 0; j < 31; j++){
                if(cave.cuad[i][j] == 3){
                    cave.cuadl[i][j] = 1;
                }
            }
        }
    }
    
}