package sample;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {

    private Stage stage;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Scene scene = new Scene(root, 300, 275);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();


        //saveStuff(scene, canvas);
    }


       /* canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                               new EventHandler<MouseEvent>() {
                                   @Override
                                   public void handle(MouseEvent e) {

                                       //gc.clearRect(e.getX() - 2, e.getY() - 2, 5, 5);
                                       gc.setFill(Color.GREEN);
                                       gc.setStroke(Color.BLUE);
                                       gc.fillRect(x, y , e.getX(), e.getY());
                                   }
                               });
*/






}