import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;

public class ExamTask2 extends Application {

    private int pixelToBW(Color pixel) {
        double lightness =
                0.2126 * pixel.getRed()
                + 0.7152 * pixel.getGreen()
                + 0.0722 * pixel.getBlue();
        return (int) Math.round(lightness * 255);

    }

    private void makePGM(String filename, Image image) throws Exception {

        PixelReader pr = image.getPixelReader();

        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        PGMImage newImage = new PGMImage(width, height);

        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                newImage.setPixel(x, y, pixelToBW(pr.getColor(x, y)));

        newImage.saveTo(filename + ".pgm");
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Task 2");

        // Layout & controls
        VBox root = new VBox(10);
        Scene scene = new Scene(root);
        stage.setScene(scene);

        Button button = new Button("Choose file");

        root.getChildren().add(button);

        FileChooser fileChooser = new FileChooser();


        // Actions

        button.setOnAction(observable -> {
            File file = fileChooser.showOpenDialog(stage);
            Image image = new Image("file:" + file.getAbsolutePath());
            ImageView iv = new ImageView(image);
            Label label = new Label(file.getName());
            root.getChildren().addAll(label, iv);
            stage.sizeToScene();
            try {
                makePGM(file.getName(), image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });



        stage.show();
    }
}
