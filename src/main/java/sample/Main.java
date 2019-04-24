package sample;


import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.util.Duration;

import javax.swing.text.MaskFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main extends Application {
    private Pane root;

    private AnimationTimer timer;

    private List<ImageView> asteroids = new ArrayList<>();
    private javafx.scene.image.ImageView frog;

    private Parent createContent() {
        root = new Pane();
        root.setPrefSize(800,500);

        frog = initShip();
        root.getChildren().add(frog);

        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                onUpdate();
            }
        };
        timer.start();

        return root;
    }

    private javafx.scene.image.ImageView initShip(){
        Image image = new Image("/ship.png");
        javafx.scene.image.ImageView shipImage = new javafx.scene.image.ImageView(image);
        shipImage.setFitWidth(70);
        shipImage.setFitHeight(40);
        shipImage.setTranslateX(400);
        shipImage.setTranslateY(400);
        return shipImage;
    }

    private javafx.scene.image.ImageView spawnAsteroid(){
        Image image = new Image("/asteroid.png");

        javafx.scene.image.ImageView asteroidImage = new javafx.scene.image.ImageView(image);

        asteroidImage.setFitWidth(40);
        asteroidImage.setFitHeight(40);

        asteroidImage.setTranslateX((int) (Math.random() * 14) *40);

        root.getChildren().add(asteroidImage);
        return asteroidImage;
    }

    private void onUpdate() {
        for (ImageView asteroid : asteroids) {
            asteroid.setTranslateY(asteroid.getTranslateY() + Math.random() * 10);
        }
        if (Math.random() < 0.075) {
            asteroids.add(spawnAsteroid());
        }
        checkState();
    }

    private void checkState() {
        for (ImageView asteroid : asteroids ) {
            if (asteroid.getBoundsInParent().intersects(frog.getBoundsInParent())) {
                frog.setTranslateX(400);
                frog.setTranslateY(400);
                return;
            }
        }
        if (frog.getTranslateY() <= 0) {
            timer.stop();
            String win = "YOU WIN";

            for (int i = 0; i < win.toCharArray().length; i++){
                char letter = win.charAt(i);

                HBox hBox = new HBox();
                hBox.setTranslateX(350);
                hBox.setTranslateY(250);
                root.getChildren().add(hBox);

                Text text = new Text(String.valueOf(letter));
                text.setFont(Font.font(48));
                text.setOpacity(0);

                hBox.getChildren().add(text);

                FadeTransition ft = new FadeTransition(Duration.seconds(0.66), text);
                ft.setToValue(1);
                ft.setDelay(Duration.seconds(i * 0.15));
                ft.play();
            }
        }
    }

    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(createContent()));
        stage.getScene().setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:
                    frog.setTranslateY(frog.getTranslateY() - 40);
                    break;
                case S:
                    frog.setTranslateY(frog.getTranslateY() + 40);
                    break;
                case A:
                    frog.setTranslateX(frog.getTranslateX() - 40);
                    break;
                case D:
                    frog.setTranslateX(frog.getTranslateX() + 40);
                    break;
                default:
                    break;
            }
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

