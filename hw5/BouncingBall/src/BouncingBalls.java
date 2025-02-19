import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BouncingBalls extends Application {

    private static final int PANE_WIDTH = 800;
    private static final int PANE_HEIGHT = 600;
    private static final int BALL_RADIUS = 20;
    private static final int MINIMUM_BALLS = 30;

    private Pane pane;
    private List<Circle> balls;

    @Override
    public void start(Stage primaryStage) {
        pane = new Pane();
        balls = new ArrayList<>();

        pane.setOnMouseClicked(this::handleMouseClick);

        Scene scene = new Scene(pane, PANE_WIDTH, PANE_HEIGHT);
        primaryStage.setTitle("Bouncing Balls");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleMouseClick(MouseEvent event) {
        if (balls.size() >= MINIMUM_BALLS) {
            return;
        }

        double x = event.getX();
        double y = event.getY();
        Color color = getRandomColor();
        Circle ball = createBall(x, y, color);
        balls.add(ball);
        pane.getChildren().add(ball);

        double dx = getRandomVelocity();
        double dy = getRandomVelocity();
        Thread ballThread = new Thread(() -> animateBall(ball, dx, dy));
        ballThread.setDaemon(true);
        ballThread.start();
    }

    private Circle createBall(double x, double y, Color color) {
        Circle ball = new Circle(x, y, BALL_RADIUS, color);
        ball.setStroke(Color.BLACK);
        return ball;
    }

    private void animateBall(Circle ball, double dx, double dy) {
        while (true) {
            if (ball.getCenterX() - BALL_RADIUS <= 0 || ball.getCenterX() + BALL_RADIUS >= PANE_WIDTH) {
                dx = -dx;
            }

            if (ball.getCenterY() - BALL_RADIUS <= 0 || ball.getCenterY() + BALL_RADIUS >= PANE_HEIGHT) {
                dy = -dy;
            }

            double tdx = dx;
            double tdy = dy;
            Platform.runLater(() -> {
                ball.setCenterX(ball.getCenterX() + tdx);
                ball.setCenterY(ball.getCenterY() + tdy);
            });

            try {
                Thread.sleep(16); // Adjust the sleep time to control the speed of the balls
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Color getRandomColor() {
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return Color.rgb(red, green, blue);
    }

    private double getRandomVelocity() {
        Random random = new Random();
        return random.nextDouble() * 10 - 5;
    }

    public static void main(String[] args) {
        launch(args);
    }
}