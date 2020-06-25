import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class ExamTask3 extends Application {

    private Color numberToColor(int number) {
        double lightness = (double) number / 255;
        return Color.gray(lightness);
    }

    private WritableImage pgmToImage(String filePath) throws Exception {
        // работает только с нашей версией .pgm

        FileInputStream inputStream = new FileInputStream(filePath);

        try(Scanner sc = new Scanner(inputStream)) {
            sc.nextLine();
            int width = sc.nextInt();
            int height = sc.nextInt();
            sc.nextInt();

            WritableImage newImage = new WritableImage(width, height);
            PixelWriter pw = newImage.getPixelWriter();

            for (int y = 0; y < height; y++)
                for (int x = 0; x < width; x++)
                    pw.setColor(x, y, numberToColor(sc.nextInt()));

            return newImage;
        }

    }

    private static void saveToFile(Image image, String filePath) {
        File outputFile = new File(filePath + ".png");
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Task 3");

        // Layout & controls
        VBox root = new VBox(10);
        Scene scene = new Scene(root);
        stage.setScene(scene);

        Button button = new Button("Choose PGM file");

        root.getChildren().add(button);

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PGM files (*.pgm)", "*.pgm");
        fileChooser.getExtensionFilters().add(extFilter);


        // Actions

        button.setOnAction(observable -> {
            File file = fileChooser.showOpenDialog(stage);
            Image image = null;
            try {
                image = pgmToImage(file.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
            ImageView iv = new ImageView(image);
            Label label = new Label(file.getName());
            root.getChildren().addAll(label, iv);
            stage.sizeToScene();

            saveToFile(image, file.getName());
        });



        stage.show();
    }
}
