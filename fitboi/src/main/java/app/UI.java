package app;
import app.Sql;
import app.Queries;
import java.sql.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UI extends Sql {

    private PrintStream printStream;

    private Map<String, String> methods = new HashMap<String, String>();
    private String format = "%-50s%s%n";


    public UI(PrintStream stream) {
        this.printStream = stream;
        this.connect();

        methods.put("Help", "Prints list of methods");
        methods.put("Add user", "Syntax: AddUser");
        methods.put("Add exercise group", "Syntax: AddExerciseGroup <name>");
    }

    public void handleInput(String input) {
        ArrayList<String> args = new ArrayList<String>(Arrays.asList(input.toLowerCase().split(" ")));
          if (args.size() == 1){
            switch(args.get(0)) {
            case "help":    for (Map.Entry<String, String> entry : methods.entrySet()) {
                                  System.out.printf(format, entry.getKey(), entry.getValue());
                            }
                            break;
            case "adduser": addUser();
                            break;
            case "listexercisegroups": listExerciseGroups();
                            break;
            default: System.out.println("Is your method in the method list, and/or have you specified the required arguments?");
                            break;
            }
          }

          if (args.size() == 2){
            switch(args.get(0)) {
            case "addexercisegroup": addExerciseGroup(args.get(1));
                            break;
            case "addfreeexercise": addFreeExercise(args.get(1));
                            break;
            default: System.out.println("Is your method in the method list, and/or have you specified the required arguments?");
                            break;
            }
          }

          if (args.size() == 3){
            switch(args.get(0)) {
            case "addmachine": addMachine(args.get(1), args.get(2));
                            break;
            case "connectexercisetogroup": connectExerciseToGroup(args.get(1), args.get(2));
                            break;
            default: System.out.println("Is your method in the method list, and/or have you specified the required arguments?");
                            break;
            }
          }

          if (args.size() == 4){
            switch(args.get(0)) {
              case "addmachineexercise": addMachineExercise(args.get(1), args.get(2), args.get(3));
                              break;
              case "addexercise": addExercise(args.get(1), args.get(2), new ArrayList<String>(Arrays.asList(args.get(3))));
                              break;
              default: System.out.println("Is your method in the method list, and/or have you specified the required arguments?");
                              break;
            }
          }

          /*if (args.size() > 4){
            switch(args.get(0)) {
            case "addexercise": addExercise(args.get(1), args.get(2), new ArrayList<String>(Arrays.asList(args.subList(3, args.size())));
                              break;
            default: System.out.println("Is your method in the method list, and/or have you specified the required arguments?");
                            break;
            }
          }*/
        }

/*
* ================================ INSERT-METHODS ===================================
*/

    private String addUser(String name) {
        return super.executeInsertQuery(Queries.INSERT_USER(name));
    }
    /* ADDS */

    private void addMachine(String name, String functionDescription) {}

    private void addMachineExercise(String machineID, String kilos, String sets) {}

    private void addFreeExercise(String description) {}

    private void addExercise(String type, String name, ArrayList<String> subCategoryParams) {}

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
    private void addExerciseGroup(String name) {}

    private void addWorkout(String date, String time, String note, String duration, String fitness, String performance) {}

    /* CONNECTIONS */

    private void connectExerciseToGroup(String exerciseID, String exerciseGroupID) {}

    private void connectUserWorkout(String userID, String workoutID) {}

    private void connectWorkoutExercise(String workoutID, String exerciseID) {}




    /* LISTINGS */

    private void listExerciseGroups() {}

    private void listMachines() {}


    private void listExercises() {
        

