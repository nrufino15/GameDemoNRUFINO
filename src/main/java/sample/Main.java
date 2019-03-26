package sample;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Main extends Application {

    @Override
    public void start(Stage theStage)
    {
        theStage.setTitle( "AnimationTimer Example" );

        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        Canvas canvas = new Canvas( 1280, 720 );
        root.getChildren().add( canvas );

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Image space = new Image("/space.png" );
        Image ship = new Image("/ship.png");


        new AnimationTimer() {

            public void handle(long currentNanoTime) {
                // Clear the canvas
                gc.clearRect(0, 0, 512,512);

                // background image clears canvas
                gc.drawImage( space, 0, 0 );
                gc.drawImage(ship, 0, 0);
            }
        }.start();

        theStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

