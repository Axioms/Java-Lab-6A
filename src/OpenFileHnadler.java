import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class OpenFileHnadler implements EventHandler<ActionEvent> {

    private TextField fileURL;
    private TextArea data;
    private Stage stage;
    private Button openButton;
    private Button close;

    public OpenFileHnadler(TextField fileURL, TextArea data, Stage stage, Button openButton, Button close) {
        this.fileURL = fileURL;
        this.data = data;
        this.stage = stage;
        this.openButton = openButton;
        this.close = close;
    }

    @Override
    public void handle(ActionEvent event) {
        if (fileURL.getText().equals("")) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            try {
                fileURL.setText(fileChooser.showOpenDialog(stage).toString());
            } catch (NullPointerException e) {
                fileURL.setText("No file provided");
            }
        }

        try {
            BufferedReader stdin = new BufferedReader(new FileReader(fileURL.getText()));
            data.setText("");
            String str;
            while ((str = stdin.readLine()) != null) {
                if (str.equals("")) {
                    data.appendText("\n");
                } else {
                    data.appendText(str + "\n");
                }
            }
            data.setText(data.getText().substring(0, data.getText().length() - 1));
            stdin.close();
            fileURL.setEditable(false);
            fileURL.setDisable(true);
            openButton.setDisable(true);
            close.setDisable(false);
            data.setEditable(true);
            data.setDisable(false);
        } catch (IOException e) {
            try {
                File file = new File(fileURL.getText());
                file.createNewFile();
                fileURL.setEditable(false);
                fileURL.setDisable(true);
                openButton.setDisable(true);
                close.setDisable(false);
                data.setEditable(true);
                data.setDisable(false);

            } catch (IOException eSig) {
                fileURL.setText("File could not be opened, so we tried to make one... it did not work");
            }
        }
    }
}
