package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewNoteController implements Initializable {
    @FXML
    public Button removeButton;
    @FXML
    public TextArea number;
    @FXML
    public TextArea name;
    @FXML
    public TextArea text;
    @FXML
    public TextArea numberColumn;
    @FXML
    public TextArea nameColumn;
    @FXML
    public TextArea textColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        number.setText("Number");
        name.setText("Name");
        text.setText("Text");
        String s = Main.controller.numberOfView.getText();
        int i = Integer.parseInt(s);
        Note note = Main.findNote(i);
        numberColumn.setText(note.getNumber() + "");
        nameColumn.setText(note.getName() + "");
        textColumn.setText(note.getText());
        removeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.notes.remove(i - 1);
                Main.textAreaFilling(Main.controller.text);
            }
        });
    }
}