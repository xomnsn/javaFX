import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Colors extends Application {

    private Color pixelToBW(Color pixel) {
        double lightness =
                0.2126 * pixel.getRed()
                + 0.7152 * pixel.getGreen()
                + 0.0722 * pixel.getBlue();
        return Color.gray(lightness);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Colors");

        // Layout & controls
        VBox root = new VBox(10);
        Scene scene = new Scene(root);
        stage.setScene(scene);

        ImageView iv1 = new ImageView();
        ImageView iv2 = new ImageView();
        ImageView iv3 = new ImageView();
        ImageView iv4 = new ImageView();
        ImageView iv5 = new ImageView();
        ImageView iv6 = new ImageView();
        ImageView iv7 = new ImageView();

        HBox row1 = new HBox(iv1, iv2, iv3);
        HBox row4 = new HBox(iv6, iv7);

        root.getChildren().addAll(row1, iv4, iv5, row4);


        // Padding
        Insets padding = new Insets(10);
        root.setPadding(padding);





        // 1
        WritableImage image1 = new WritableImage(120,100);
        PixelWriter pw1 = image1.getPixelWriter();
        for (int x = 0; x < 120; x++)
            for (int y = 0; y < 100; y++)
                pw1.setColor(x, y, Color.GREEN);

        iv1.setImage(image1);


        // 2
        WritableImage image2 = new WritableImage(255,255);
        PixelWriter pw2 = image2.getPixelWriter();
        for (int x = 0; x < 255; x++)
            for (int y = 0; y < 255; y++)
                pw2.setColor(x, y, Color.rgb(0, x, y));

        iv2.setImage(image2);
        iv2.setFitWidth(120);
        iv2.setFitHeight(100);


        // 3
        WritableImage image3 = new WritableImage(255,255);
        PixelWriter pw3 = image3.getPixelWriter();
        for (int x = 0; x < 255; x++)
            for (int y = 0; y < 255; y++)
                pw3.setColor(x, y, Color.rgb(x, x, y));

        iv3.setImage(image3);
        iv3.setFitWidth(120);
        iv3.setFitHeight(100);


        // 4
        WritableImage image4 = new WritableImage(360,100);
        PixelWriter pw4 = image4.getPixelWriter();
        for (int x = 0; x < 360; x++)
            for (int y = 0; y < 100; y++)
                pw4.setColor(x, y, Color.hsb(x, (double) y/99, 1));

        iv4.setImage(image4);


        // 5
        WritableImage image5 = new WritableImage(360,140);
        PixelWriter pw5 = image5.getPixelWriter();
        for (int x = 0; x < 360; x++)
            for (int y = 0; y < 140; y++)
                pw5.setColor(x, y, LCH.colorFromLCH(80, y * 1.0,  x * 1.0));

        iv5.setImage(image5);


        // 6
        Image image6 = new Image(
                "file:./media/colors/image.jpg",
                256,
                256,
                false,
                true
                );
        iv6.setImage(image6);
        iv6.setFitWidth(180);
        iv6.setFitHeight(180);



        // 7
        PixelReader pr = image6.getPixelReader();
        WritableImage image7 = new WritableImage(256,256);
        PixelWriter pw7 = image7.getPixelWriter();
        for (int x = 0; x < 256; x++)
            for (int y = 0; y < 256; y++)
                pw7.setColor(x, y, pixelToBW(pr.getColor(x, y)));

        iv7.setImage(image7);
        iv7.setFitWidth(180);
        iv7.setFitHeight(180);



        stage.show();
    }
}
