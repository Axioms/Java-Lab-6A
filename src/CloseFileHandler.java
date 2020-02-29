import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CloseFileHandler implements EventHandler<ActionEvent>{

    Button open;
    Button close;
    TextField fileURL;
    TextArea data;

    public CloseFileHandler(TextField fileURL, TextArea data, Button open, Button close) {
        this.open = open;
        this.close = close;
        this.fileURL = fileURL;
        this.data = data;
    }

    @Override
    public void handle(ActionEvent event) {
        open.setDisable(false);
        close.setDisable(true);
        SaveFileHandler cfh = new SaveFileHandler(fileURL,data);
        cfh.save();
        data.setText("");
        data.setEditable(false);
        data.setDisable(true);
        fileURL.setText("");
        fileURL.setEditable(true);
        fileURL.setDisable(false);
    }
}
