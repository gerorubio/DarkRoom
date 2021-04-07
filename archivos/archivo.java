/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivos;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import mapa.Mapa;
import entidad.Jugador;
import java.io.IOException;

/**
 *
 * @author LOLO
 */
public class archivo implements Serializable{
    
   private static final long serialVersionUID = 1L; 
   public Mapa Mt;
   public Jugador Pt;
   public archivo at;
    
    public archivo(){
        
    }
    
    
    public void guardar(){
        try {
            FileOutputStream fileOut = new FileOutputStream("./f1.txt");
            try (ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
                objectOut.writeObject(this);
            }
            System.out.println("El objeto fue escrito correctamente a un arhcivo");
        } catch (IOException ex) {
        }

    }
    
    public archivo cargar(){
        archivo obj; 
        try {
            FileInputStream fileIn = new FileInputStream("./f1.txt");
            try (ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
                obj = (archivo)objectIn.readObject();
                System.out.println("El objeto ha sido leído de un archivo");
            }
            this.at = obj;
            return obj;
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }


    }
    
  
    
    
    
}
