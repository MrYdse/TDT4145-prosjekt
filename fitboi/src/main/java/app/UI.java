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

    /* ADDS */

    private void addUser() {}

    private void addMachine(String name, String functionDescription) {}

    private void addMachineExercise(String machineID, String kilos, String sets) {}

    private void addFreeExercise(String description) {}

    private void addExercise(String type, String name, ArrayList<String> subCategoryParams) {}

    private void addExerciseGroup(String name) {}

    private void addWorkout(String date, String time, String note, String duration, String fitness, String performance) {}

    /* CONNECTIONS */

    private void connectExerciseToGroup(String exerciseID, String exerciseGroupID) {}

    private void connectUserWorkout(String userID, String workoutID) {}

    private void connectWorkoutExercise(String workoutID, String exerciseID) {}




    /* LISTINGS */

    private void listExerciseGroups() {

    }

    private void listMachines() {

    }

    private void listExercises() {

    }

    private void list
}
