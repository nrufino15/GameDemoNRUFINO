package sample;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 800, 500);

        Image space = new Image("/space.png");
        ImageView scapeImage = new ImageView(space);
        scapeImage.setFitWidth(800);
        scapeImage.setFitHeight(500);
        pane.getChildren().addAll(scapeImage);

        Image ship = new Image("/ship.png");
        ImageView shipImage = new ImageView(ship);
        shipImage.setFitWidth(120);
        shipImage.setFitHeight(80);
        shipImage.setX(pane.getHeight() / 2);
        shipImage.setY(pane.getWidth() / 2);
        pane.getChildren().addAll(shipImage);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event){

                if (event.getCode() == KeyCode.RIGHT) {
                    shipImage.setLayoutX(shipImage.getLayoutX() + 15);
                } else if (event.getCode() == KeyCode.LEFT) {
                    shipImage.setLayoutX(shipImage.getLayoutX() - 15);
                }
            }
        });

        stage.setTitle("Asteroids");
        stage.setScene(scene);
        stage.show();
}

    public static void main(String[] args) {
        launch(args);
    }
}

