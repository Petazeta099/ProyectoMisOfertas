/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Skin;
import mdlaf.*;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Jonathan
 */
public class ClaseSkin {
    
    public static void agregarSkin(){
        try {
			UIManager.setLookAndFeel (new MaterialLookAndFeel ());
		}
		catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace ();
		}
    }
    
}
