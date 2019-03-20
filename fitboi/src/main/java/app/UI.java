package app;
import app.Sql;
import app.Queries;
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

/*
* ================================ INSERT-METHODS ===================================
*/

    private String addUser(String name) {
        return super.executeInsertQuery(Queries.INSERT_USER(name));
    }

    private String addExerciseGroup(String name) {

    }

    private String addExercise(String type, String name, ArrayList<String> subCategoryParams) {

    }

    private String addFreeExercise(String description) {

    }

    private String addMachine(String name, String functionDescription) {
        return super.executeInsertQuery(Queries.INSERT_MACHINE(name, functionDescription));
    }

    private String addWorkout(String datetime, String note, int duration, int fitness, int perfomance) {
        return super.executeInsertQuery(Queries.INSERT_WORKOUT(datetime, note, duration, fitness, perfomance))
    }

    private String addUserWorkedOut(int uid, int wid) {
        return super.executeInsertQuery(Queries.INSERT_USER_WORKED_OUT(uid, wid));
    }

    private String connectWorkoutExercise(int wid, int eid) {
        return super.executeInsertQuery(Queries.CONNECT_WORKOUT_EXERCISE(wid, eid));
    }

    private void connectExerciseToGroup(String exerciseID) {

    }

    private void addMachineExercise(String machineID, String kilos, String sets) {

    }

}

/*
* ================================ SHOW-METHODS ===================================
*/

    private void listExerciseGroups() {
    
    }


/*
* ================================ SEARCH-METHODS ===================================
*/