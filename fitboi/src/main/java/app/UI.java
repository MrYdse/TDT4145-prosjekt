package app;

import java.sql.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UI extends Sql {

    private PrintStream printStream;

    private Map<String, String> methods = new HashMap<String, String>();
    private String format = "%-40s%s%n";

    public UI(PrintStream stream) {
        this.printStream = stream;
        this.connect();

        methods.put("'Help'", "Prints list of methods");
        methods.put("'Add user'", "Syntax: AddUser <name>");
        methods.put("'Add machine exercise'", "Syntax: AddMachineExercise <kilos> <sets>");
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
        methods.put("'List users last workouts'", "Syntax: listUsersLastWorkouts <number>");
        methods.put("'List users'", "Syntax: listUsers");
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
            case "exit": break;
            case "listexercisegroups" : listExerciseGroups();
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
            case "addmachineexercise": print(addMachineExercise(args.get(1), args.get(2)));
                            break;
            default: System.out.println("Is your method in the method list, and/or have you specified the required arguments?");
                            break;
            }
          }

          if (args.size() == 4){
            switch(args.get(0)) {
              default: System.out.println("Is your method in the method list, and/or have you specified the required arguments?");
                              break;
            }
          }

    /*      if (args.size() > 6){
            switch(args.get(0)) {
            case "addexercise": addExercise(args.get(1), args.get(2), new ArrayList<String>(Arrays.asList(args.subList(3, args.size())));
                              break;
            default: System.out.println("Is your method in the method list, and/or have you specified the required arguments?");
                            break;
            }
          }*/
        }

    /* ADDS */

    private String addMachineExercise(String kilos, String sets) {
        return sets;
    }
    private String addUser(String name) {
        return super.executeInsertQuery(Queries.INSERT_USER(name));
    }

    private String addFreeExercise(String description) {
        return description;
    }

    private String addMachineExercise(String type, String name, String kilos, String sets) {
        return name;
    }

    private String addGroupExercise(String type, String name, String description) {
        return name;
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
        return name;
    }

    private void addWorkout(String date, String time, String note, String duration, String fitness, String performance) {}

    /* CONNECTIONS */

    private String connectExerciseToGroup(String exerciseID, String exerciseGroupID) {
        return exerciseGroupID;
    }

    private String connectUserWorkout(String userID, String workoutID) {
        return workoutID;
    }

    private String connectWorkoutExercise(String workoutID, String exerciseID) {
        return exerciseID;
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
