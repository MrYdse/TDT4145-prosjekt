package app;

import java.sql.*;
import java.io.PrintStream;
import java.util.ArrayList;

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

    private void addExercise(String type, String name, ArrayList<String> subCategoryParams) {

    }

    private void addFreeExercise(String description) {

    }

    private void addMachine(String name, String functionDescription) {

    }

    private void connectExerciseToGroup(String exerciseID) {

    }

    private void addMachineExercise(String machineID, String kilos, String sets) {

    }

    private String listExerciseGroups() {

    }
}
