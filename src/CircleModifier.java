import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class CircleModifier extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Circle Modifier");

        // Layout & controls
        GridPane root = new GridPane();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        VBox controls = new VBox(10);
        Pane circleContainer = new Pane();
        circleContainer.setStyle("-fx-background-color: #808080");
        Slider slider = new Slider();
        ColorPicker circleCP = new ColorPicker(Color.web("#F08080"));
        ColorPicker bgCP = new ColorPicker(Color.web("#808080"));
        Circle circle = new Circle();
        Label label1 = new Label("Размер круга");
        Label label2 = new Label("Цвет круга");
        Label label3 = new Label("Цвет фона");



        controls.getChildren().addAll(
                label1,
                slider,
                label2,
                circleCP,
                label3,
                bgCP);
        root.add(controls,0,0);
        circleContainer.getChildren().add(circle);
        root.add(circleContainer, 1,0);


        // Grid Constraints
        ColumnConstraints rootCol1 = new ColumnConstraints(90, 300, Double.MAX_VALUE);
        ColumnConstraints rootCol2 = new ColumnConstraints(200, 700, Double.MAX_VALUE);
        rootCol2.setHgrow(Priority.ALWAYS);
        root.getColumnConstraints().addAll(rootCol1, rootCol2);
        RowConstraints row1 = new RowConstraints(300, 700, Double.MAX_VALUE);
        row1.setVgrow(Priority.ALWAYS);
        root.getRowConstraints().add(row1);


        // Event handling

        //  circle color
        circle.fillProperty().bind(circleCP.valueProperty());

        //  background color
        bgCP.valueProperty().addListener(
                observable -> {
                    BackgroundFill fill = new BackgroundFill(
                            bgCP.getValue(),
                            null,
                            null);
                    BackgroundFill[] fills = new BackgroundFill[]{fill};
                    circleContainer.setBackground(new Background(fills));
                }
                );

        //  slider max
        slider.maxProperty().bind(Bindings
                .min(
                circleContainer.widthProperty(),
                circleContainer.heightProperty()
                )
                .divide(2)
        );

        //  circle size
        circle.radiusProperty().bind(slider.valueProperty());

        //  circle positioning
        circle.centerXProperty().bind(circleContainer.widthProperty().divide(2));
        circle.centerYProperty().bind(circleContainer.heightProperty().divide(2));



        // Padding
        controls.setPadding(new Insets(20, 30, 20, 30));
        Insets labelInsets = new Insets(20, 0, 0, 0);
        label1.setPadding(labelInsets);
        label2.setPadding(labelInsets);
        label3.setPadding(labelInsets);


        stage.show();
    }
}
