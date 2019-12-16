package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import sample.Controller;

public class Main extends Application {
    public static Controller controller;
    public static LinkedList<Note> notes = new LinkedList<>();
    public static LinkedList<Note> notes_deleted = new LinkedList<>();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Notes");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();
    }
    @Override
    public void stop() throws IOException {
        Files.writeList();
    }
    public static void main(String[] args) throws FileNotFoundException {
        Files.readNotes(notes, notes_deleted);
        launch(args);
    }

    public static Note findNote(int i){
        for (Note note: notes){
            if (note.getNumber() == i){
                return note;
            }
        }
        return null;
    }
    public static void showNotes(TextArea text) {
        text.setText("");
        for(Note note: Main.notes){
            if (note.getDeleted() == false){
                String nameString = TextAnalyze.textDeAnalyze(note.getName());
                String textString = TextAnalyze.textDeAnalyze(note.getText());
                text.appendText(note.getNumber() + " " + nameString + " " + textString + "\n");
            }
        }
    }
}
