/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ed2g9;

import TDAS.Tree;
import static TDAS.Tree.build;
import file.Archivo;
import java.io.File;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author César
 */
public class CrearMapa {
    private String ruta;
    private FlowPane root;
    private TreeMap<String,Pintura> colorMap;
    
    
    
    public CrearMapa(String ruta, TreeMap<String,Pintura> colorMap){
        root = new FlowPane();
        this.ruta = ruta;
        this.colorMap=colorMap;
        createContent();
        
    }
    
    private void createContent(){
        Pane pane = new Pane();
        
        File fichero=new File(ruta);
        Archivo ar=new Archivo(fichero);
        Tree<Archivo> tree=new Tree(ar);
        build(tree);
           
        try {
          buildRectangules(pane,tree,1360,700,1,0,0);
          root.getChildren().add(pane);
        } catch (Exception ex) {
          Logger.getLogger(PanelP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    private void buildRectangules(Pane pane,Tree<Archivo> tree,double ancho,double largo,int direccion,double coordenadaX,double coordenadaY) throws Exception{
        double total=tree.getRaiz().getContent().getSize();
        if(direccion==1){
            for (int i = 0; i < tree.getRaiz().getHijos().size(); i++) {
                double size=tree.getRaiz().getHijos().get(i).getRaiz().getContent().getSize();
                double ancho1=(size/total)*ancho;
                if(tree.getRaiz().getHijos().get(i).getRaiz().getContent().getArchivo().isDirectory()){
                    buildRectangules(pane,tree.getRaiz().getHijos().get(i),ancho1,largo,2,coordenadaX,coordenadaY);
                    coordenadaX=coordenadaX+ancho1;
                }
                else{
                    String pal;
                    Rectangle rectangle = new Rectangle();
                    rectangle.setWidth(ancho1); //ancho 
                    rectangle.setHeight(largo); //largo
                    String fileName = tree.getRaiz().getContent().getArchivo().getName();
                    fileName= tree.getRaiz().getHijos().get(i).getRaiz().getContent().getArchivo().getName();
                    String[] list = fileName.split("\\.");
                    rectangle.setFill(selectColor(list[list.length-1]));
                    rectangle.setStroke(Color.BLACK);
                    rectangle.setX(coordenadaX);
                    rectangle.setY(coordenadaY);
                    Tooltip t=new Tooltip("Nombre del archivo: "+fileName+"\n"+"Peso en megabytes: "+size/(1024*1024));
                    Tooltip.install(rectangle, t);
                    
                    
                    
                    coordenadaX=coordenadaX+ancho1;
                    pane.getChildren().add(rectangle);
                    pal=fileName;
                    
                   rectangle.setOnMouseClicked(event -> {
                       
                       Alert alert=new Alert(Alert.AlertType.INFORMATION);
                       alert.setTitle("Informacion");
                       alert.setContentText("Nombre del archivo: "+pal+"\n"+"Peso en megabytes: "+size/(1024*1024));
                       alert.setHeaderText(null);
                       alert.showAndWait();

                   }); 
                }
            }
        }
        else{
            for (int i = 0; i < tree.getRaiz().getHijos().size(); i++) {
                double size=tree.getRaiz().getHijos().get(i).getRaiz().getContent().getSize();
                double largo1=(size/total)*largo;
                if(tree.getRaiz().getHijos().get(i).getRaiz().getContent().getArchivo().isDirectory()){
                    buildRectangules(pane,tree.getRaiz().getHijos().get(i),ancho,largo1,1,coordenadaX,coordenadaY);
                    coordenadaY=coordenadaY+largo1;
                }
                else{
                    String pal;
                    Rectangle rectangle = new Rectangle();
                    rectangle.setWidth(ancho); //ancho 
                    rectangle.setHeight(largo1); //largo
                     String fileName = tree.getRaiz().getContent().getArchivo().getName();
                    fileName= tree.getRaiz().getHijos().get(i).getRaiz().getContent().getArchivo().getName();
                    String[] list = fileName.split("\\.");
                    rectangle.setFill(selectColor(list[list.length-1]));
                    rectangle.setStroke(Color.BLACK);
                    rectangle.setX(coordenadaX);
                    rectangle.setY(coordenadaY);
                    
                    Tooltip t=new Tooltip("Nombre del archivo: "+fileName+"\n"+"Peso en megabytes: "+size/(1024*1024));
                    Tooltip.install(rectangle, t);
                    
                    coordenadaY=coordenadaY+largo1;
                    pane.getChildren().add(rectangle);
                    
                    pal=fileName;
                   
                    rectangle.setOnMouseClicked(event -> {
                        
                        
                        
                       Alert alert=new Alert(Alert.AlertType.INFORMATION);
                       alert.setTitle("Informacion");
                       alert.setContentText("Nombre del archivo: "+pal+"\n"+"Peso en megabytes: "+size/(1024*1024));
                       alert.setHeaderText(null);
                       alert.showAndWait();
                          
          
                   });
                }
            }
        }
        
    }
    
  
    private Color selectColor(String extension){
    
       if(colorMap.containsKey(extension)){
           double red = colorMap.get(extension).getRed();
           double green = colorMap.get(extension).getGreen();
           double blue = colorMap.get(extension).getBlue();
           return new Color(red,green,blue,1);
       }else{
           return Color.ALICEBLUE;
       }
          
   }

    public FlowPane getRoot() {
        return root;
    }
    
 
}


