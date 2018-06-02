package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import javax.swing.text.html.ImageView;
import java.io.*;

public class Controller {

    private StackPane currStack;
    private BlockSet blockSet;
    @FXML
    AnchorPane root;
    @FXML
    Canvas canvas;
    private Color color = Color.BLACK;

    public enum  Block {
        ONE, TWO, THREE, FOUR, FIVE, SIX
    }
    public void initialize() {

        blockSet = new BlockSet();
    }
    private void drawRect(Rectangle r, StackPane stackPane, MouseEvent event, double x, double y) {

        if (event.getX() > x ) {
            r.setWidth(event.getX() - x );
        }
        else {
            r.setX(event.getX());
            r.setWidth(x- event.getX());
            stackPane.setLayoutX(event.getX());
        }
        if (event.getY() > y) {
            r.setHeight(event.getY() - y );
        }

        else {
            r.setY(event.getY());
            r.setHeight(y - event.getY());
            stackPane.setLayoutY(event.getY());
        }
    }

    private void storeRectData(Rectangle r, Block blockNum) {

        sample.Block block = new sample.Block(r);
        switch (blockNum) {

            case ONE:
                blockSet.setOne(block);
                break;
            case TWO:
                blockSet.setTwo(block);
                break;
            case THREE:
                blockSet.setThree(block);
                break;
            case FOUR:
                blockSet.setFour(block);
                break;
            case FIVE:
                blockSet.setFive(block);
                break;
            case SIX:
                blockSet.setSix(block);
                break;
        }
        //block = new Block(r);
        //System.out.println(r.getX() + " " + r.getY());
        //System.out.println(r.getWidth() + " " + r.getHeight());
    }

    private void borderRect(MouseEvent event) {
        Rectangle rectangle = (Rectangle) event.getSource();
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(4);

        if (event.isShiftDown()) {
            System.out.println("delete " + rectangle);
        }
    }

    private void removeFocus(KeyEvent event) {

    }

    private Label getBlockValue(Block blockNum) {

        String blockVal = "0";
        switch (blockNum) {

            case ONE:
                blockVal = "1";
                break;
            case TWO:
                blockVal = "2";
                break;
            case THREE:
                blockVal = "3";
                break;
            case FOUR:
                blockVal = "4";
                break;
            case FIVE:
                blockVal =  "5";
                break;
            case SIX:
                blockVal = "6";
                break;
        }
        return new Label(blockVal);
    }
    private void removeBlock(MouseEvent event, StackPane stackPane) {
        if (event.isShiftDown()) {
            System.out.println("here");
            root.getChildren().remove(stackPane);
        }
    }
    private void drawShapes(Canvas canvas, Color color, Block blockNum) {

        canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                StackPane stackPane = new StackPane();
                stackPane.setOnMouseClicked(event -> removeBlock(event, stackPane));
                System.out.println( e.getSceneX() +  " " + e.getX() + " " + e.getScreenX());
                Label text = getBlockValue(blockNum);
                text.setFont(new Font(30));
                // record a delta distance for the drag and drop operation.
                Rectangle r = new Rectangle();
                r.setStrokeWidth(5);
                r.setStroke(Color.WHITE);
                r.setOnMouseClicked(event -> borderRect(event));
                r.setOnKeyPressed(event -> removeFocus(event));
                stackPane.getChildren().addAll(r, text);
                root.getChildren().add(stackPane);
                double x = e.getX() ;
                double y = e.getY();
                stackPane.setLayoutX(e.getX());
                stackPane.setLayoutY(e.getY());

                r.setX(x);
                r.setY(y);
                r.setFill(color);

                canvas.setOnMouseDragged(event -> drawRect(r, stackPane, event, x, y));
                canvas.setOnMouseReleased(event -> storeRectData(r, blockNum));
            }
        });
    }

    @FXML
    private void one(ActionEvent event) {

        color = Color.LIMEGREEN;
        drawShapes(canvas, color, Block.ONE);
    }

    @FXML
    private void two(ActionEvent event) {
        color = Color.ORANGERED;
        drawShapes( canvas, color, Block.TWO);
    }
    @FXML
    private void three(ActionEvent event) {
        //currentRect = null;
        color = Color.DODGERBLUE;
        drawShapes(canvas, color, Block.THREE);
    }
    @FXML
    private void four(ActionEvent event) {
        //currentRect = null;
        color = Color.DEEPPINK;
        drawShapes(canvas, color, Block.FOUR);
    }
    @FXML
    private void five(ActionEvent event) {

        color = Color.ORANGE;
        drawShapes(canvas, color, Block.FIVE);
    }
    @FXML
    private void six(ActionEvent event) {
        color = Color.CORAL;
        drawShapes(canvas, color, Block.SIX);
    }

    @FXML private void saveStuff(ActionEvent event) throws IOException {
        serializeDataOut(blockSet);
        System.out.println("Successful");

    }

    @FXML private void testLoader(ActionEvent event) throws IOException, ClassNotFoundException {
        BlockSet blockSet = serializeDataIn();
        Rectangle rectangle = new Rectangle();

        rectangle.setHeight(blockSet.getFour().getHeight());
        rectangle.setWidth(blockSet.getFour().getWidth());
        rectangle.setX(blockSet.getFour().getX());
        rectangle.setY(blockSet.getFour().getY());
        rectangle.setFill(Paint.valueOf(blockSet.getFour().getColor()));

        root.getChildren().add(rectangle);
    }
    public static void serializeDataOut(BlockSet blockSet)throws IOException {
        String fileName= "Test.txt";
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(blockSet);
        oos.close();
    }

    public static BlockSet serializeDataIn() throws IOException, ClassNotFoundException {
        String fileName= "Test.txt";
        FileInputStream fin = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fin);
        BlockSet canvasDat= (BlockSet) ois.readObject();
        ois.close();
        return canvasDat;
    }
}
