package adventuregame.ui.javafx;

import adventuregame.logic.Direction;
import adventuregame.logic.Game;
import adventuregame.logic.TileType;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.Duration;

public class JavaFxUI extends Application {

    private static Game map;
    private static boolean runMonstersIndependently;

    private final Canvas canvas = new Canvas(
            map.width() * Tiles.TILE_WIDTH,
            map.height() * Tiles.TILE_WIDTH);
    private final GraphicsContext context = canvas.getGraphicsContext2D();
    private final Label healthLabel = new Label();

    public static void start(Game map, boolean runMonstersIndependently) {
        JavaFxUI.map = map;
        JavaFxUI.runMonstersIndependently = runMonstersIndependently;
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
        scene.setOnKeyReleased(this::onKeyReleased);

        primaryStage.setTitle("Adventure Game");
        primaryStage.show();

        if (runMonstersIndependently) {
            runMonstersIndependently();
        }
    }

    private void runMonstersIndependently() {
        new Thread(() -> {
            for (; ; ) {
                try {
                    Thread.sleep(Duration.ofMillis(200));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                map = map.moveGame();
                refresh();
            }
        }).start();
    }

    private void onKeyReleased(KeyEvent keyEvent) {
        KeyCombination exitCombinationMac = new KeyCodeCombination(KeyCode.W, KeyCombination.SHORTCUT_DOWN);
        KeyCombination exitCombinationWin = new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN);
        if (exitCombinationMac.match(keyEvent)
                || exitCombinationWin.match(keyEvent)
                || keyEvent.getCode() == KeyCode.ESCAPE) {
            exit();
        }
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        map = movePlayer(keyEvent, map);
        refresh();
    }

    private static Game movePlayer(KeyEvent keyEvent, Game map) {
        Direction direction = switch (keyEvent.getCode()) {
            case UP -> Direction.NORTH;
            case DOWN -> Direction.SOUTH;
            case LEFT -> Direction.WEST;
            case RIGHT -> Direction.EAST;
            default -> null;
        };
        if (direction != null) {
            map = map.movePlayer(direction);
        }
        return map;
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.width(); x++) {
            for (int y = 0; y < map.height(); y++) {
                TileType tileType = map.getDrawable(x, y);
                Tiles.drawTile(context, tileType, x, y);
            }
        }
        healthLabel.setText("" + map.getPlayerHealth());
    }

    private void exit() {
        try {
            stop();
        } catch (Exception e) {
            System.exit(1);
        }
        System.exit(0);
    }
}
