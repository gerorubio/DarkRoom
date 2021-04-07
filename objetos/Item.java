/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.io.Serializable;

/**
 *
 * @author 
 */
public abstract class Item implements Serializable{
    
    private final String type;

    public Item( String type ) {
        this.type = type;
    }
    
    public String getType(){
        return this.type;
    }
    
}