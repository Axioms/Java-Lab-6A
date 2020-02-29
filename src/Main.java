import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application{

    Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.window = primaryStage;

        Button open = new Button("Open");
        Button save = new Button("Save");
        Button close = new Button("Close");

        TextField fileURL = new TextField("");
        TextArea fileDisplay = new TextArea("");

        VBox mainBox = new VBox();

        HBox firstRow = new HBox();
        HBox secondRow = new HBox();

        mainBox.getStyleClass().add("mainWindow");
        fileDisplay.getStyleClass().add("text-area");
        fileDisplay.setEditable(false);
        fileDisplay.setDisable(true);
        close.setDisable(true);
        firstRow.setPadding(new Insets(50, 10, 20, 10));

        firstRow.setAlignment(Pos.CENTER);
        secondRow.setAlignment(Pos.CENTER);

        firstRow.getChildren().addAll(new Node[]{fileDisplay});
        secondRow.getChildren().addAll(new Node[] {fileURL, open, close, save});
        mainBox.getChildren().addAll(new Node[] {firstRow, secondRow});

        OpenFileHnadler ofh = new OpenFileHnadler(fileURL,fileDisplay, window, open, close);
        SaveFileHandler sfh = new SaveFileHandler(fileURL, fileDisplay);
        CloseFileHandler cfh = new CloseFileHandler(fileURL, fileDisplay, open, close);
        open.setOnAction(ofh);
        save.setOnAction(sfh);
        close.setOnAction(cfh);

        Scene mainStage = new Scene(mainBox, 800,600);
        mainStage.getStylesheets().add("styles.css");

        window.setScene(mainStage);
        window.setTitle("Module 6A");
        window.show();
    }
}