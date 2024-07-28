/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ed2g9;

import java.io.Serializable;
import javafx.scene.paint.Color;

/**
 *
 * @author César
 */
public class Pintura implements Serializable{
    private double red;
    private double green;
    private double blue;
 
    public Pintura(Color color) {
        red=color.getRed();
        green=color.getGreen();
        blue=color.getBlue();
    }

    public double getRed() {
        return red;
    }

    public void setRed(double red) {
        this.red = red;
    }

    public double getGreen() {
        return green;
    }

    public void setGreen(double green) {
        this.green = green;
    }

    public double getBlue() {
        return blue;
    }

    public void setBlue(double blue) {
        this.blue = blue;
    }
    
    
    
    
}
