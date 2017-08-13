/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.braco;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author ntbra
 */
public class Constants {
    private static final Constants CONSTANTS = new Constants();
    
    private Constants(){
        
    }
    
    private URL getRes(String res){
        return this.getClass().getResource(res);
    }
    
    public static Image getIcon(){
        return new ImageIcon(CONSTANTS.getRes("/logo/icon.png")).getImage();
    }
    
    public static ImageIcon getConfirmIcon(int size){
        return new ImageIcon(CONSTANTS.getRes("/icons/"+size+"/checkmark-"+size+".png"));
    }
    
    public static ImageIcon getCancelIcon(int size){
        return new ImageIcon(CONSTANTS.getRes("/icons/"+size+"/cancel-"+size+".png"));
    }
    
}
