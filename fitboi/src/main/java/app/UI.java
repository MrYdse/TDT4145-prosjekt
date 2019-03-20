package app;

import java.sql.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import sun.net.www.content.text.plain;

public class UI extends Sql {

    private PrintStream printStream;

    private Map<String, String> methods = new HashMap<String, String>();
    private String format = "%-40s%s%n";

    public UI(PrintStream stream) {
        this.printStream = stream;
        this.connect();

        methods.put("'Help'", "Prints list of methods");
        methods.put("'Add user'", "Syntax: AddUser");
        methods.put("'Add exercise group'", "Syntax: AddExerciseGroup <name>");
        methods.put("'Add machine'", "Syntax: AddMachine <machineID> <kilos> <sets>");
        methods.put("'Add free exercise'", "Syntax: AddFreeExercise <description>");
        methods.put("'Add workout'", "Syntax: AddWorkout <date> <time> <note> <duration> <fitness> <performance>");
        methods.put("'Connect exercise to group'", "Syntax: ConnectExerciseToGroup <exerciseId> <exerciseGroupId>");
        methods.put("'Connect user workout'", "Syntax: ConnectUserWorkout <userID> <workoutID>");
        methods.put("'Connect workout exercise'", "Syntax: ConnectWorkoutExercise <workoutID> <exerciseID>");
        methods.put("'List exercise groups'", "Syntax: listExerciseGroups");
        methods.put("'List machines'", "Syntax: listMachines");
        methods.put("'List exercises'", "Syntax: listExercises");
        methods.put("'List workouts'", "Syntax: listWorkouts");
        //methods.put("''", "Syntax: ");
    }

    public void handleInput(String input) {
        ArrayList<String> args = new ArrayList<String>(Arrays.asList(input.toLowerCase().split(" ")));
          if (args.size() == 1){
            switch(args.get(0)) {
            case "help":    for (Map.Entry<String, String> entry : methods.entrySet()) {
                                  System.out.printf(format, entry.getKey(), entry.getValue());
                            }
                            break;
            case "listexercisegroups": listExerciseGroups();
                            break;
            default: System.out.println("Is your method in the method list, and/or have you specified the required arguments?");
                            break;
            }
          }

          if (args.size() == 2){
            switch(args.get(0)) {
            case "adduser": print(addUser(args.get(1)));
                            break;
            case "addexercisegroup": print(addExerciseGroup(args.get(1)));
                            break;
            case "addfreeexercise": print(addFreeExercise(args.get(1)));
                            break;
            default: System.out.println("Is your method in the method list, and/or have you specified the required arguments?");
                            break;
            }
          }

          if (args.size() == 3){
            switch(args.get(0)) {
            case "addmachine": print(addMachine(args.get(1), args.get(2)));
                            break;
            case "connectexercisetogroup": print(connectExerciseToGroup(args.get(1), args.get(2)));
                            break;
            default: System.out.println("Is your method in the method list, and/or have you specified the required arguments?");
                            break;
            }
          }

          if (args.size() == 4){
            switch(args.get(0)) {
              case "addmachineexercise": print(addMachineExercise(args.get(1), args.get(2), args.get(3)));
                              break;
              case "addexercise": print(addExercise(args.get(1), args.get(2), new ArrayList<String>(Arrays.asList(args.get(3)))));
                              break;
              default: System.out.println("Is your method in the method list, and/or have you specified the required arguments?");
                              break;
            }
          }

    /*      if (args.size() > 4){
            switch(args.get(0)) {
            case "addexercise": addExercise(args.get(1), args.get(2), new ArrayList<String>(Arrays.asList(args.subList(3, args.size())));
                              break;
            default: System.out.println("Is your method in the method list, and/or have you specified the required arguments?");
                            break;
            }
          }*/
        }

    /* ADDS */

    private String addMachineExercise(String name, int machineID, int kilos, int sets) {
        return super.executeInsertQuery(Queries.INSERT_MACHINE_EXERCISE(name, kilos, sets, machineID));
    }
    private String addUser(String name) {
        return super.executeInsertQuery(Queries.INSERT_USER(name));
    }

    private String addFreeExercise(String description) {
        return super.executeInsertQuery(Queries.INSERT_FREE_EXERCISE(name, description));
    }

    private String addMachine(String name, String functionDescription) {
        return super.executeInsertQuery(Queries.INSERT_MACHINE(name, functionDescription));
    }

    private String addWorkout(String datetime, String note, int duration, int fitness, int perfomance) {
        return super.executeInsertQuery(Queries.INSERT_WORKOUT(datetime, note, duration, fitness, perfomance));
    }

    private String addUserWorkedOut(int uid, int wid) {
        return super.executeInsertQuery(Queries.INSERT_USER_WORKED_OUT(uid, wid));
    }

    private String connectWorkoutExercise(int wid, int eid) {
        return super.executeInsertQuery(Queries.CONNECT_WORKOUT_EXERCISE(wid, eid));
    }
    private String addExerciseGroup(String name) {
        return super.executeInsertQuery(Queries.INSERT_EXERCISE_GROUP(name));
    }

    /* CONNECTIONS */

    private String connectExerciseToGroup(int exerciseID, int exerciseGroupID) {
        return super.executeInsertQuery(Queries.CONNECT_EXERCISE_TO_GROUP(exerciseID, exerciseGroupID));
    }

    private String connectUserWorkout(int userID, int workoutID) {
        return super.executeInsertQuery(Queries.INSERT_USER_WORKED_OUT(userID, workoutID));
    }

    private String connectWorkoutExercise(int workoutID, int exerciseID) {
        return super.executeInsertQuery(Queries.CONNECT_WORKOUT_EXERCISE(workoutID, exerciseID));
    }




    /* LISTINGS */

    private void listExerciseGroups() {}

    private void listMachines() {}

    private void listExercises() {}

    private void print(String string) {
        printStream.println(string);
    }

    private void listUsersLastWorkouts(String userID, String n) {}

    private void listUsers() {}
}
