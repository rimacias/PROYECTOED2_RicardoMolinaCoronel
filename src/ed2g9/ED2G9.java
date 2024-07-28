package ed2g9;

import TDAS.LinkedList;
import TDAS.Tree;

import java.io.File;
import java.util.Iterator;
import java.util.TreeMap;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 *
 * @author Ricardo
 */
public class ED2G9 extends Application {

     TreeMap<String, Pintura> colorMap; 

    @Override
    public void start(Stage primaryStage) throws Exception {
       PanelP prueba = new PanelP();
       Scene scene = new Scene(prueba.getRoot(),1365,729);
       primaryStage.setTitle("Tree Maping");
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.getStylesheets().clear();
        
    }
 
       

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

        // System.out.println(file.getAbsoluteFile());
    }


}
