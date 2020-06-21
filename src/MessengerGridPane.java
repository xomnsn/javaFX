import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;


public class MessengerGridPane extends Application {

    private HBox makeMessage(String text, Boolean sendFromRight) {
        HBox message = new HBox();
        message.setPrefWidth(700);

        Label label = new Label(text);
        label.setPadding(new Insets(15));
        label.setWrapText(true);
        label.getStyleClass().add("messageText");
        if (sendFromRight)
            message.getStyleClass().add("messageFromRight");
        else
            label.getStyleClass().add("messageFromLeft");

        message.getChildren().add(label);

        return message;
    }


    private GridPane makeContactElement(String iconPath, String name, Boolean isHeader) {
        GridPane contact = new GridPane();
        contact.setHgap(10);
        contact.setMinWidth(150);
        contact.setPadding(new Insets(10));

        javafx.scene.image.Image image = new Image("file:" + iconPath);
        ImageView iv = new ImageView();
        iv.setImage(image);
        iv.setFitWidth(50);
        iv.setPreserveRatio(true);

        GridPane iconContainer = new GridPane();
        iconContainer.setPadding(new javafx.geometry.Insets(5));
        iconContainer.add(iv, 0, 0, 1, 1);

        Label label = new Label(name);
        label.setPadding(new Insets(15, 0, 0, 0));

        if (isHeader) {
            contact.getStyleClass().add("contactHeader");
            label.getStyleClass().add("contactNameHeader");
            iconContainer.getStyleClass().add("iconHeader");
        }   else {
            contact.getStyleClass().add("contact");
            label.getStyleClass().add("contactName");
            iconContainer.getStyleClass().add("icon");
        }

        contact.add(iconContainer, 0, 0, 1, 1);
        contact.add(label, 1, 0, 1, 1);

        return contact;
    }


    @Override
    public void start(Stage stage) throws Exception {


        // Setting up stage, scene and root element
        stage.setTitle("My messenger");
        stage.setMinHeight(400);
        stage.setMinWidth(400);
        GridPane root = new GridPane();
        Scene scene = new Scene(root);
        stage.setScene(scene);


        // Initializing main layout elements
        GridPane contacts = new GridPane();
        ScrollPane leftArea = new ScrollPane(contacts);
        leftArea.fitToWidthProperty().set(true);
        leftArea.fitToHeightProperty().set(true);
        leftArea.setMinWidth(300);
        GridPane rightArea = new GridPane();
        GridPane header = new GridPane();
        VBox messages = new VBox();
        ScrollPane main = new ScrollPane(messages);
        main.fitToWidthProperty().set(true);
        GridPane footer = new GridPane();


        // Distributing elements
        rightArea.add(header, 0, 0);
        rightArea.add(main, 0, 1);
        rightArea.add(footer, 0, 2);
        root.add(leftArea, 0, 0);
        root.add(rightArea,1, 0);


        // Add contacts
        GridPane capybara = makeContactElement(
                "./media/messenger/icons/capybara.png",
                "Capybara (You)",
                false);
        capybara.setId("capybara");

        GridPane cat = makeContactElement(
                "./media/messenger/icons/cat.png",
                "Cat",
                false);
        cat.setId("cat");

        GridPane dog = makeContactElement(
                "./media/messenger/icons/dog.png",
                "Dog",
                false);
        dog.setId("dog");

        GridPane horse = makeContactElement(
                "./media/messenger/icons/horse.png",
                "Horse",
                false);
        horse.setId("horse");

        contacts.add(capybara, 0, 0);
        contacts.add(cat, 0, 1);
        contacts.add(dog, 0, 2);
        contacts.add(horse, 0, 3);


        // Header
        GridPane currentRecipient = makeContactElement(
                "./media/messenger/icons/cat.png",
                "Cat",
                true
        );
        header.getChildren().addAll(currentRecipient);


        // Footer
        footer.setHgap(10);
        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setPrefWidth(6000);
        textArea.setMinHeight(80);
        Button send = new Button("send");
        send.setId("sendButton");
        send.setMinWidth(60);
        footer.add(textArea, 0, 0);
        footer.add(send, 1, 0);


        send.setOnAction(observable -> {
                    String message = textArea.getText();
                    if (!message.isEmpty())
                        messages
                                .getChildren()
                                .add(makeMessage(message, true));
                    textArea.setText("");
                }
        );

        messages.heightProperty().addListener(observable -> main.setVvalue(1D));


        // Row & column constraints
        //---root---
        ColumnConstraints rootCol1 = new ColumnConstraints(90, 300, Double.MAX_VALUE);
        ColumnConstraints rootCol2 = new ColumnConstraints(200, 700, Double.MAX_VALUE);
        rootCol2.setHgrow(Priority.ALWAYS);
        root.getColumnConstraints().addAll(rootCol1, rootCol2);
        RowConstraints row1 = new RowConstraints(300, 700, Double.MAX_VALUE);
        row1.setVgrow(Priority.ALWAYS);
        root.getRowConstraints().add(row1);
        //---rightArea---
        ColumnConstraints rightCol1 = new ColumnConstraints(200, 700, Double.MAX_VALUE);
        rightCol1.setHgrow(Priority.ALWAYS);
        rightArea.getColumnConstraints().add(rightCol1);
        RowConstraints headerRow = new RowConstraints(120, 120, 120);
        RowConstraints mainRow = new RowConstraints(100, 500, Double.MAX_VALUE);
        mainRow.setVgrow(Priority.ALWAYS);
        RowConstraints footerRow = new RowConstraints(100, 150, Double.MAX_VALUE);
        rightArea.getRowConstraints().addAll(headerRow, mainRow, footerRow);
        //---contacts---
        ColumnConstraints contactsCol1 = new ColumnConstraints(250, 300, Double.MAX_VALUE);
        contactsCol1.setHgrow(Priority.ALWAYS);
        contacts.getColumnConstraints().add(contactsCol1);
        RowConstraints contactRow = new RowConstraints(100, 100, 100);
        RowConstraints fillRow = new RowConstraints(100, 100, Double.MAX_VALUE);
        fillRow.setVgrow(Priority.ALWAYS);
        contacts.getRowConstraints().addAll(contactRow, contactRow, contactRow, fillRow);


        // Set padding
        header.setPadding(new Insets(10));
        messages.setPadding(new Insets(10));
        footer.setPadding(new Insets(10));


        // Styles
        scene.getStylesheets().addAll("css/messengerGridPane.css");
        leftArea.getStyleClass().add("contacts");
        header.getStyleClass().add("header");
        main.getStyleClass().add("main");
        footer.getStyleClass().add("footer");


        stage.show();
    }
}
