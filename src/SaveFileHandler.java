import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveFileHandler implements EventHandler<ActionEvent> {
    private TextField fileURL;
    private TextArea data;

    public SaveFileHandler(TextField fileURL, TextArea data) {
        this.fileURL = fileURL;
        this.data = data;
    }

    public void save() {
        try {
            BufferedWriter stdout = new BufferedWriter(new FileWriter(fileURL.getText()));
            stdout.write(data.getText());
            stdout.flush();
            stdout.close();
        } catch (IOException e) {
            System.out.println("File was deleted, can not save");
        }
    }

    @Override
    public void handle(ActionEvent event) {
        save();
    }
}
