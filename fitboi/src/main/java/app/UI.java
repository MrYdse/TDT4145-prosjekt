package app;

import java.sql.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UI extends Sql {

    private PrintStream printStream;

    private Map<String, String> methods = new HashMap<String, String>();
    private String format = "%-40s%s%n";

    public UI(PrintStream stream) {
        this.printStream = stream;
        this.connect();
        methods.put("Help", "Prints list of methods");
    }

    public void handleInput(String input) {
        switch(input) {
            case "hi":      printStream.print("Hi yourself");
                            break;
            case "test":    printStream.print("Yup, that's a test");
                            break;
            case "help":    for (Map.Entry<String, String> entry : methods.entrySet()) {
                                  System.out.printf(format, entry.getKey(), entry.getValue());
                            }
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

    private void listExerciseGroups() {

    }
}