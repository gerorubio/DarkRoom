/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

/**
 *
 * @author 
 */
public class Pocion extends Item {
    
    private static int healPoints = 6;
    
    public Pocion() {
        super( "Pocion" );
    }
    
    public static int getHealPoints(){
        return Pocion.healPoints;
    }
    
    public static void addHealPoints(){
        Pocion.healPoints += 6;
    }
    
}
