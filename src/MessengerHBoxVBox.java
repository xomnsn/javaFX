import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MessengerHBoxVBox extends Application {

    private HBox makeContactElement(String iconPath, String name, Boolean isHeader) {
        HBox contact = new HBox();
        contact.setSpacing(10);
        contact.setPadding(new Insets(10));


        Image image = new Image("file:" + iconPath);
        ImageView iv = new ImageView();
        iv.setImage(image);
        iv.setFitWidth(50);
        iv.setPreserveRatio(true);

        HBox iconContainer = new HBox();
        iconContainer.setPrefWidth(60);
        iconContainer.setPadding(new Insets(5));
        iconContainer.getChildren().add(iv);

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

        contact.getChildren().addAll(iconContainer, label);

        return contact;
    }


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


    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("My messenger");
        stage.setHeight(700);
        stage.setWidth(1000);
        stage.setMaxHeight(700);
        stage.setMaxWidth(1000);
        stage.setMinHeight(300);
        stage.setMinWidth(400);

        // Main layout elements initialization
        HBox root = new HBox();
        VBox contacts = new VBox(5);
        ScrollPane contactsWrap = new ScrollPane(contacts);
        VBox rightArea = new VBox();
        HBox header = new HBox(20);
        VBox messages = new VBox(25);
        ScrollPane main = new ScrollPane(messages);
        HBox footer = new HBox(10);


        // Scene build
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll("css/messengerHBoxVBox.css");
        stage.setScene(scene);
        root.getChildren().addAll(contactsWrap, rightArea);
        rightArea.getChildren().addAll(header, main, footer);


        // Setting preferable size and styles to layout elements
        contactsWrap.fitToWidthProperty().set(true);
        contactsWrap.fitToHeightProperty().set(true);
        contactsWrap.setPrefSize(300, 700);
        contactsWrap.setMinWidth(90);
        contacts.getStyleClass().add("contacts");
        rightArea.setPrefSize(700, 700);
        header.setPrefSize(700, 100);
        header.getStyleClass().add("header");
        main.setPrefSize(700, 500);
        main.getStyleClass().add("main");
        main.fitToWidthProperty().set(true);
        header.setPadding(new Insets(10));
        messages.setPadding(new Insets(20));
        footer.setPrefSize(700, 100);
        footer.getStyleClass().add("footer");


        // Add contacts
        HBox capybara = makeContactElement(
                "./media/messenger/icons/capybara.png",
                "Capybara (You)",
                false);
        capybara.setId("capybara");

        HBox cat = makeContactElement(
                "./media/messenger/icons/cat.png",
                "Cat",
                false);
        cat.setId("cat");

        HBox dog = makeContactElement(
                "./media/messenger/icons/dog.png",
                "Dog",
                false);
        dog.setId("dog");

        HBox horse = makeContactElement(
                "./media/messenger/icons/horse.png",
                "Horse",
                false);
        horse.setId("horse");

        contacts.getChildren().addAll(capybara, cat, dog, horse);


        // Setting up footer
        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setPrefWidth(600);
        textArea.setMinHeight(80);
        Button send = new Button("send");
        send.setId("sendButton");
        send.setMinWidth(60);
        footer.getChildren().addAll(textArea, send);
        footer.setPadding(new Insets(10));


        send.setOnAction(ev -> {
            String message = textArea.getText();
            if (!message.isEmpty())
                    messages
                        .getChildren()
                        .add(makeMessage(message, true));
            textArea.setText("");
            }
        );

        messages.heightProperty().addListener(observable -> main.setVvalue(1D));


        // Add sample messages
        HBox currentRecipient = makeContactElement(
                "./media/messenger/icons/cat.png",
                "Cat",
                true
        );
        header.getChildren().addAll(currentRecipient);









        stage.show();
    }
}
