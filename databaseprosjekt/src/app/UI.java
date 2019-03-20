package app;

import java.sql.*;
import java.io.PrintStream;

public class UI extends Sql {

    private PrintStream printStream;

    public UI(PrintStream stream) {
        this.printStream = stream;
        this.connect();
    }

    public void handleInput(String input) {
        switch(input) {
            case "hi":      printStream.print("Hi yourself");
                            break;
            case "test":    printStream.print("Yup, that's a test");
                            break;
            case "":    printStream.print("Yup, that's a test");
                            break;
        }
    }

    private void addUser() {

    }

    private void addExerciseGroup(String name) {
        
    }

    private void addExercise(String type, String eafa) {
        
    }

    private void connectExerciseToGroup() {

    }

    private void 
}