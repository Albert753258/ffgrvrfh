package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    public static LinkedList<Note> search_result = new LinkedList();

    @FXML
    public Button viewButton;
    @FXML
    public TextArea textColumn;
    @FXML
    public TextArea nameColumn;
    @FXML
    public TextArea numberColumn;
    @FXML
    public TextField numberOfView;
    @FXML
    public TextArea text;
    @FXML
    public TextArea name;
    @FXML
    public TextArea number;
    @FXML
    public TextField searchText;
    @FXML
    public CheckBox showDeletedNotes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setEditable(false);
        number.setEditable(false);
        text.setEditable(false);
        numberColumn.setEditable(false);
        textColumn.setEditable(false);
        nameColumn.setEditable(false);
        Main.controller1 = this;
        for(int i = 1; i <= Main.notes.size(); i ++){
            Note note = Main.notes.get(i - 1);
            numberColumn.appendText(i + "\n");
            nameColumn.appendText(note.getName() + "\n");
            textColumn.appendText(note.getText() + "\n");
        }
        searchText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")) {
                    nameColumn.setText("");
                    textColumn.setText("");
                    numberColumn.setText("");
                    int i = 1;
                    for(Note note: Main.notes){
                        numberColumn.appendText(i + "\n");
                        nameColumn.appendText(note.getName() + "\n");
                        textColumn.appendText(note.getText() + "\n");
                        i ++;
                    }
                }
                else {
                    findNote();
                }
            }
        });
        showDeletedNotes.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                findNote();
            }
        });
        viewButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                String s = numberOfView.getText();
                Integer i = Integer.parseInt(s);
                if (i == null){
                }
                else {
                    Stage stage = new Stage();
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("SearchViewNote.fxml"));
                        stage.setTitle("");
                        stage.setScene(new Scene(root, 700,500));
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    public void findNote(){
        boolean b = showDeletedNotes.selectedProperty().get();
        if (b == false){
            nameColumn.setText("");
            textColumn.setText("");
            numberColumn.setText("");
            int i = 1;
            FindNotes.findNote(searchText.getText(), searchText.getText(), search_result, Main.notes);
            for(Note note: search_result){
                numberColumn.appendText(i + "\n");
                nameColumn.appendText(note.getName() + "\n");
                textColumn.appendText(note.getText() + "\n");
                i ++;
            }
        }
        if (b == true){
            nameColumn.setText("");
            textColumn.setText("");
            numberColumn.setText("");
            int i = 1;
            FindNotes.findNote(searchText.getText(), searchText.getText(), search_result, Main.notes_deleted);
            for(Note note: search_result){
                numberColumn.appendText(i + "\n");
                nameColumn.appendText(note.getName() + "\n");
                textColumn.appendText(note.getText() + "\n");
                i ++;
            }
        }
    }
}
