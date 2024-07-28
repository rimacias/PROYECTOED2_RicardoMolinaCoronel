/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ed2g9;

import TDAS.LinkedList;
import TDAS.Tree;
import static TDAS.Tree.build;
import file.Archivo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Iterator;
import java.util.TreeMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author César
 */
public class PanelP {
    private BorderPane root;
    private TextField txtDirectory;
    private Pane pane;
    TreeMap<String, Pintura> colorMap;  
    
    public PanelP() throws Exception{
        root = new BorderPane();
        PanelPrincipal();
        colorMap=new TreeMap();
        colorMap=deserializar();
        
    }
    private String[] colores = {"#55efc4", "#81ecec", "#74b9ff", "#74b9ff", "#a29bfe", "#dfe6e9", "#00b894", "#00cec9", "#0984e3", "#6c5ce7", "#b2bec3", "#ffeaa7", "#fab1a0", "#ff7675", "#fd79a8",
         "#636e72", "#fdcb6e", "#e17055", "#d63031", "#e84393"};
    private int contador=1;
    
     public void PanelPrincipal(){
         
        //Stage stage= new Stage();
        Label lb= new Label("Escoja el directorio a representar");
        lb.setTextFill(Color.web("#3498db"));
        lb.setFont(new Font("Arial Black",18));
        Button bt= new Button("Buscar");
        Button bt2 = new Button("Configuración");
        bt.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-padding: 20px 25px;-fx-background-color: #9b59b6;"/*+"-fx-border-color: #ED4C67"*/);
        bt.setTextFill(Color.web("#ffffff"));
        bt2.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-padding: 20px 25px;-fx-background-color: #000000"/*+"-fx-border-color: #ED4C67"*/);
        bt2.setTextFill(Color.web("#ffffff"));
        //aviso.setTextFill(Color.web("#e74c3c"));
        Label aviso= new Label();
        aviso.setTextFill(Color.web("#e74c3c"));
        aviso.setFont(new Font("Arial Black",18));
        aviso.setVisible(false);
        VBox vb= new VBox(lb,aviso,bt,bt2);
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(40);
        vb.setStyle("-fx-background-color: #bdc3c7;");
        
        
        
        lb.setTextFill(Color.web("#FFFFFF"));
        lb.setFont(new Font("Times New Roman",18));
       
        bt.setFont(new Font("Times New Roman",18));
        bt.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-padding: 20px 25px;-fx-background-color: #000000"/*+"-fx-border-color: #ED4C67"*/);
        bt.setTextFill(Color.web("#ffffff"));
        
        //A
       
        aviso.setTextFill(Color.web("#e74c3c"));
        aviso.setFont(new Font("Times New Roman",18));
        aviso.setVisible(false);
        
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(40);
        vb.setStyle("-fx-background-color: #FF0000");
        bt.setOnAction(new EventHandler<ActionEvent>(){
             @Override
             public void handle(ActionEvent event) {
                 aviso.setVisible(false);
                 File file=NavegarCarpetas();
                 if(file!=null){
                     
                     String ruta = file.getAbsolutePath();
                     
                     //Archivo fil = new Archivo(file);
                     //Preguntar el nombre de la carpeta y agregar archivos
                     //RepesentacionGrafica(file.getAbsolutePath());
                     CrearMapa mw = new CrearMapa(ruta ,colorMap);
               Scene escena = new Scene(mw.getRoot(),1360,700);
          
              Stage newWindow = new Stage();
              newWindow.setTitle("Tree maping");
              newWindow.setScene(escena);
              newWindow.show();
              newWindow.setResizable(false);

              newWindow.setMaxWidth(1365);
              newWindow.setMaxHeight(729);
                     
                 }
                 else{
                     aviso.setText("No se ha escogido ningun directorio");
                     aviso.setTextFill(Color.web("#ffffff"));
                     aviso.setVisible(true);
                 }
             }
         });
        bt2.setOnAction(e->{
           Configuracion sw = new Configuracion(colorMap);
           Scene escena2 = new Scene(sw.getRoot(),500,180);
          
            Stage newWindow2 = new Stage();
            newWindow2.setTitle("Configuración");
            newWindow2.setScene(escena2);
            newWindow2.show();
            newWindow2.setResizable(false);
            newWindow2.setWidth(500);
            newWindow2.setHeight(400);
            
         
         
         });
        /*Scene scene= new Scene(vb,600,300);
        stage.setScene(scene);
        stage.show();*/
        root.setCenter(vb);
        
        
    }
     public BorderPane getRoot() {
        return root;
    }
    
    private TreeMap<String,Pintura> deserializar() throws ClassNotFoundException{
        FileInputStream fis = null;
        ObjectInputStream entrada = null;
        TreeMap<String,Pintura> mapa = null;
        try {
            fis = new FileInputStream("colorMap.dat");
            entrada = new ObjectInputStream(fis);
            mapa = (TreeMap<String,Pintura>) entrada.readObject();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        
       return mapa;
    }
    
    
   
    public File NavegarCarpetas() {
        DirectoryChooser fc = new DirectoryChooser();
        File file = fc.showDialog(null);
        if(file!=null) return file;
        return null;
    }
    public File AbrirArchivo(String path){
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(path));
        File file = fc.showOpenDialog(null);
        if(file!=null) return file;
        return null;
    }
    public void ArchivoMasPesado(File carpeta,Label lb){
        File lista[] = carpeta.listFiles();
        String name="None";
        double peso=0;
        if(lista!=null){
        for (File file : lista) {
            if(!file.isDirectory()){
                if(file.length()>peso){
                    name=file.getName();
                    peso=file.length();
                }
            }
            
        }
        Tooltip tool = new Tooltip(name+": "+conversor(peso));
                
        tool.setTextAlignment(TextAlignment.LEFT);
        lb.setTooltip(tool);
        }
    }
    

    public static Tree<File> crearArbol(String path) {
        File carpeta = new File(path);
//        System.out.println(carpeta.isDirectory());
        Tree<File> archivos = new Tree(carpeta);
        File lista[] = carpeta.listFiles();
//        System.out.println("Length:" + lista.length);
        for (File file : lista) {
//            System.out.println(file.getAbsoluteFile());
//            System.out.println(file.length() + "\n");
            archivos.addDirectory(file);
        }

        System.out.println("Impresion arbol*******");
        archivos.ImprimirArbol();
        return archivos;
    }
    
    public void RepesentacionGrafica(String carpeta){
        Stage stage= new Stage();
        HBox flow = new HBox();
        //flow.setPadding(new Insets(400, 400, 400, 400));
        flow.setPrefSize(850, 850);
        flow.setAlignment(Pos.TOP_CENTER);
        System.out.println(txtDirectory.getText());
        File directorio = new File(txtDirectory.getText());
        Archivo ar = new Archivo(new File(txtDirectory.getText()));
        Tree<Archivo> tree = new Tree<>(ar);
        build(tree);
        Tree<File> file=crearArbol(directorio.getAbsolutePath());
        tree.getRaiz().setHeight(800);
        tree.getRaiz().setWidth(800);
        tree.getRaiz().setPanel(flow);
        dispersarDimensiones(tree);
        Label lb= new Label("Click en la carpeta que desee examinar"+"\n"+tree.getRaiz().getContent().getArchivo()+": "+conversor(tree.getRaiz().getPeso()));
        ArchivoMasPesado(file.getRaiz().getContent(),lb);
        lb.setFont(new Font("Arial Black",18));
        VBox vb= new VBox(lb,flow);
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(20);
        flow.setStyle("-fx-background-color: #686de0" + ";"+"-fx-border-insets: 1 1 1 1;"+"-fx-border-color:#2f3640;");

        ScrollPane scroll=new ScrollPane(vb);
        
        
            Scene scene = new Scene(scroll, 850, 850);
        
        stage.setScene(scene);
        stage.show();
    }

    public void dispersarDimensiones(Tree<Archivo> raiz) {
        LinkedList<Tree<Archivo>> hijos = raiz.getRaiz().getHijos();
        Iterator<Tree<Archivo>> iterator = hijos.iterator();
        int cantidad = hijos.size();
        double alto = raiz.getRaiz().getHeight();
        double ancho = raiz.getRaiz().getWidth();
        while (iterator.hasNext()) {

            Tree<Archivo> hijo = iterator.next();
            //if (hijo.getRaiz().getContent().isDirectory()) { 
                FlowPane pane = new FlowPane();
                Label lb= new Label(hijo.getRaiz().getContent().getArchivo()+"\nPeso:"+conversor(hijo.getRaiz().getPeso()));
                lb.setFont(new Font("Arial",18));
                pane.getChildren().add(lb);
//                Rectangle rect= new Rectangle(ancho/cantidad,alto/cantidad);
//                pane.getChildren().add(rect);
                //pane.setPadding(new Insets(alto / (3 * cantidad), ancho / (3 * cantidad), alto / (3 * cantidad), ancho / (3 * cantidad)));
                
                if(hijo.isLeaf()){
                    pane.setPrefSize(60, 60);
                    pane.setMaxHeight(60);
                    pane.setMaxWidth(60);
                }
                else{
                pane.setPrefSize(ancho/(cantidad), alto/(cantidad));
                pane.setMaxHeight(alto/(cantidad));
                    pane.setMaxWidth(ancho/(cantidad));
                }
                int ind = (int) (Math.random() * (colores.length - 1));
                String color=colores[ind];
                pane.setOnMouseEntered(new EventHandler <MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {
                        System.out.println("Hola dice : "+hijo.getRaiz().getContent().getArchivo()+" con size: "+pane.getWidth()+", "+pane.getHeight());
                       pane.setPrefSize(pane.getWidth()+40, pane.getHeight()+40);
                       contador=1;
                       pane.setStyle("-fx-background-color: " + color + ";"+"-fx-border-insets: 2 2 2 2;"+"-fx-border-color:#2f3640;");
                    }
                });
                pane.setOnMouseExited(new EventHandler <MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {
                        System.out.println("ADIOS dice : "+hijo.getRaiz().getContent().getArchivo()+" con size: "+pane.getWidth()+", "+pane.getHeight());
                       pane.setPrefSize(ancho/(cantidad), alto/(cantidad));
                       contador=1;
                       pane.setStyle("-fx-background-color: " + color + ";"+"-fx-border-insets: 1 1 1 1;"+"-fx-border-color:#2f3640;");
                      
                    }
                });
                pane.setOnMousePressed(new EventHandler <MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {
                        if(contador==1){
                        //AbrirArchivo(hijo.getRaiz().getContent().getAbsolutePath());
                        RepesentacionGrafica(hijo.getRaiz().getContent().toString());
                        contador--;
                        }
                    }
                });
                
                File f = new File(hijo.getRaiz().getContent().toString());
                ArchivoMasPesado(f,lb);

                System.out.println("Carpeta: "+hijo.getRaiz().getContent().getArchivo()+"alto: "+alto/(cantidad)+"ancho: "+ancho/(cantidad));
                //pane.maxWidth(ancho/cantidad);
                //pane.maxHeight(alto/cantidad);
                
                pane.setStyle("-fx-background-color: " + color + ";"+"-fx-border-insets: 1 1 1 1;"+"-fx-border-color:#2f3640;");
                //pane.setStyle("-fx-background-color: #f5f6fa" + ";"+"-fx-border-insets: 1 1 1 1;"+"-fx-border-color:#2f3640;");
                pane.setVgap(20);
                pane.setHgap(20);
                hijo.getRaiz().setHeight(alto / cantidad);
                hijo.getRaiz().setWidth(ancho / cantidad);
                hijo.getRaiz().setPanel(pane);
                raiz.getRaiz().getPanel().getChildren().add(pane);
                dispersarDimensiones(hijo);
            //}
        }
    }
    public String conversor(double peso){
        if(peso<1024){
            return peso+" b";
        }
        else if(peso <1048576 ){
            return Math.round(((peso/1024)*100d))/100d+" Kb"; 
        }
        else if(peso<1073741824){
            return Math.round(((peso/1048576)*100d))/100d+" Mb";
        }
        else{
            return Math.round(((peso/1073741824)*100d))/100d+" Gb";
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
//                    rectangle.setFill(selectColor(list[list.length-1]));
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
                    //rectangle.setFill(selectColor(list[list.length-1]));
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

    /*public FlowPane getRoot() {
        return root;
    }*/
}
