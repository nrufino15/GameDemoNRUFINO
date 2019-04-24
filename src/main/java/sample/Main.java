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
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private Pane root;
    private int life = 3;

    private AnimationTimer timer;

    private List<ImageView> asteroids = new ArrayList<>();
    private ImageView ship;
    private ImageView space;
    private ImageView earth;
    private ImageView sun;

    private Parent createContent() {
        root = new Pane();
        root.setPrefSize(800,500);

        space = initBackground();
        root.getChildren().add(space);

        earth = initEarth();
        root.getChildren().add(earth);

        sun = initSun();
        root.getChildren().add(sun);

        ship = initShip();
        root.getChildren().add(ship);


        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                onUpdate();
            }
        };
        timer.start();

        return root;
    }

    private ImageView initBackground() {
        Image image = new Image("/space.png");
        javafx.scene.image.ImageView spaceImage = new javafx.scene.image.ImageView(image);
        spaceImage.setFitWidth(800);
        spaceImage.setFitHeight(500);
        spaceImage.setTranslateX(0);
        spaceImage.setTranslateY(0);
        return spaceImage;
    }

    private ImageView initEarth() {
        Image image = new Image("/earth.png");
        ImageView earthImage = new javafx.scene.image.ImageView(image);
        earthImage.setTranslateX(100);
        earthImage.setTranslateY(300);
        return earthImage;
    }

    private ImageView initSun() {
        Image image = new Image("/sun.png");
        ImageView sunImage = new javafx.scene.image.ImageView(image);
        sunImage.setTranslateX(700);
        sunImage.setTranslateY(100);
        return sunImage;
    }

    private ImageView initShip(){
        Image image = new Image("/ship.png");
        ImageView shipImage = new javafx.scene.image.ImageView(image);
        shipImage.setFitWidth(60);
        shipImage.setFitHeight(40);
        shipImage.setTranslateX(400);
        shipImage.setTranslateY(400);
        return shipImage;
    }

    private ImageView spawnAsteroid(){
        Image image = new Image("/asteroid.png");

        ImageView asteroidImage = new javafx.scene.image.ImageView(image);

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
            if (asteroid.getBoundsInParent().intersects(ship.getBoundsInParent())) {
                life = life -1;
                ship.setTranslateX(600);
                ship.setTranslateY(400);
                return;
            }
        }

        if (ship.getTranslateY() <= 0) {
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
                    ship.setTranslateY(ship.getTranslateY() - 30);
                    break;
                case S:
                    ship.setTranslateY(ship.getTranslateY() + 30);
                    break;
                case A:
                    ship.setTranslateX(ship.getTranslateX() - 30);
                    break;
                case D:
                    ship.setTranslateX(ship.getTranslateX() + 30);
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

