package app;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

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
        ArrayList<String> args = new ArrayList<String>(Arrays.asList(input.toLowerCase().replaceAll("\\s+","").split("-")));
          if (args.size() == 1){
            switch(args.get(0)) {
            case "help":    for (Map.Entry<String, String> entry : methods.entrySet()) {
                                  System.out.printf(format, entry.getKey(), entry.getValue());
                            }
                            break;
            case "listexercisegroups": print(listExerciseGroups());
                            break;
            case "listexercises": print(listExercises());
                            break;
            case "listmachines": print(listMachines());
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
            case "whois": print(whoIsUsername(args.get(1)));
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
            case "addfreeexercise": print(addFreeExercise(args.get(1), args.get(2)));
                            break;
            case "connectuserworkoout": print(connectUserWorkout(args.get(1), args.get(2)));
                            break;
            case "connectworkoutexercise": print(connectWorkoutExercise(args.get(1), args.get(2)));
            default: System.out.println("Is your method in the method list, and/or have you specified the required arguments?");
                            break;
            }
          }

          if (args.size() == 4){
            switch(args.get(0)) {
              case "addmachineexercise": print(addMachineExercise(args.get(1), args.get(2), args.get(3), input));
                              break;
              default: System.out.println("Is your method in the method list, and/or have you specified the required arguments?");
                              break;
            }
          }

        if (args.size() > 4){
            switch(args.get(0)) {
            case "addworkout": print(addWorkout(args.get(1), args.get(2), args.get(3), args.get(4), args.get(5)));
                              break;
            default: System.out.println("Is your method in the method list, and/or have you specified the required arguments?");
                            break;
            }
          }
        }

    /* ADDS */

    private String addMachineExercise(String name, String sets, String machineID, String kilos) {
        return super.executeInsertQuery(Queries.INSERT_MACHINE_EXERCISE(name, kilos, sets, machineID));
    }
    private String addUser(String name) {
        return super.executeInsertQuery(Queries.INSERT_USER(name));
    }

    private String addFreeExercise(String name, String description) {
        return super.executeInsertQuery(Queries.INSERT_FREE_EXERCISE(name, description));
    }

    private String addMachine(String name, String functionDescription) {
        return super.executeInsertQuery(Queries.INSERT_MACHINE(name, functionDescription));
    }

    private String addWorkout(String datetime, String note, String duration, String fitness, String perfomance) {
        return super.executeInsertQuery(Queries.INSERT_WORKOUT(datetime, note, duration, fitness, perfomance));
    }


    private String addExerciseGroup(String name) {
        return super.executeInsertQuery(Queries.INSERT_EXERCISE_GROUP(name));
    }

    /* CONNECTIONS */

    private String connectExerciseToGroup(String string, String string2) {
        return super.executeInsertQuery(Queries.CONNECT_EXERCISE_TO_GROUP(string, string2));
    }

    private String connectUserWorkout(String string, String string2) {
        return super.executeInsertQuery(Queries.INSERT_USER_WORKED_OUT(string, string2));
    }

    private String connectWorkoutExercise(String workoutID, String exerciseID) {
        return super.executeInsertQuery(Queries.CONNECT_WORKOUT_EXERCISE(workoutID, exerciseID));
    }




    /* LISTINGS */

    private String listExerciseGroups() {
        Object response = super.executeReturnQuery(Queries.GET_ALL_EXERCISE_GROUPS());
        if (response instanceof String) {
            return (String) response;
        } else {
            ResultSet rs = (ResultSet) response;
            String out = "";
            try {
                while(rs.next()) {
                    out += "Exercise Group ID: " + rs.getString("egid") + " Name: " + rs.getString("name") +"\n";
                }
                return out;
            } catch (Exception e) {
                return "Failed to read resultset with error: " + e.getMessage();
            }  
        } 
    }

    private String listMachines() {
        Object response = super.executeReturnQuery(Queries.GET_ALL_MACHINES());
        if (response instanceof String) {
            return (String) response;
        } else {
            ResultSet rs = (ResultSet) response;
            String out = "";
            try {
                while(rs.next()) {
                    out += "Machine ID: " + rs.getString("mid") + " Name: " + rs.getString("name") + " Description: " + rs.getString("functiondescription") + "\n";
                }
                return out;
            } catch (Exception e) {
                return "Failed to read resultset with error: " + e.getMessage();
            }  
        }
    }

    private String listExercises() {
        Object response = super.executeReturnQuery(Queries.GET_ALL_FREE_EXERCISES());
        String out = "Free exercises:\n";
        if (response instanceof String) {
            out = (String) response;
        } else {
            ResultSet rs = (ResultSet) response;
            try {
                while(rs.next()) {
                    out += "Exercise ID: " + rs.getString("eid") + " Name: " + rs.getString("name") + " Description: " + rs.getString("functiondescription") + "\n";
                }
            } catch (Exception e) {
                return "Failed to read resultset with error: " + e.getMessage();
            }
        }
        out += "Machine exercices:\n";
        response = super.executeReturnQuery(Queries.GET_ALL_MACHINE_EXERCISES());
        if (response instanceof String) {
            out = (String) response;
        } else {
            ResultSet rs = (ResultSet) response;
            try {
                while(rs.next()) {
                    out += "Exercise ID: " + rs.getString("eid") + " Name: " + rs.getString("exercise.name") + " Machine name: "+ rs.getString("machine.name") + " Kilos: " + rs.getString("kilos") + rs.getString("sets") + "\n";
                }
            } catch (Exception e) {
                return "Failed to read resultset with error: " + e.getMessage();
            }
        }
        return out;
    }

    private String listUsersLastWorkouts(String userID, String n) {
        Object response = super.executeReturnQuery(Queries.GET_N_LAST_WORKOUTS_FOR_USER(n, userID));
        if (response instanceof String) {
            return (String) response;
        } else {
            ResultSet rs = (ResultSet) response;
            String out = "";
            try {
                while(rs.next()) {
                    out += "User ID: " + rs.getString("uid") + " Date: " + rs.getString("wodatetime") + " Note: " + rs.getString("note") + rs.getString("duration") + rs.getString("fitness") + rs.getString("performance") + "\n";
                }
                return out;
            } catch (Exception e) {
                return "Failed to read resultset with error: " + e.getMessage();
            }  
        }
    }

    private String listUsers() {
        Object response = super.executeReturnQuery(Queries.GET_ALL_USERS());
        if (response instanceof String) {
            return (String) response;
        } else {
            ResultSet rs = (ResultSet) response;
            String out = "";
            try {
                while(rs.next()) {
                    out += "User ID: " + rs.getString("uid") + " Name: " + rs.getString("name") + "\n";
                }
                return out;
            } catch (Exception e) {
                return "Failed to read resultset with error: " + e.getMessage();
            }  
        }
    }

    private String listExercisesInGroup(String groupName) {
        Object response = super.executeReturnQuery(Queries.GET_EXERCISE_GROUP_BY_NAME(groupName));
        String egid = "";
        if (response instanceof String) {
            return (String) response;
        } else {
            ResultSet rs = (ResultSet) response;
            try {
                egid = rs.getString("egid");
            } catch (Exception e) {
                return "Failed to read resultset with error: " + e.getMessage();
            }  
        }
        response = super.executeReturnQuery(Queries.GET_ALL_EXERCISES_IN_GROUP(egid));
        if (response instanceof String) {
            return (String) response;
        } else {
            ResultSet rs = (ResultSet) response;
            String out = "";
            try {
                while(rs.next()) {
                    out += "User ID: " + rs.getString("uid") + " Name: " + rs.getString("name") + "\n";
                }
                return out;
            } catch (Exception e) {
                return "Failed to read resultset with error: " + e.getMessage();
            }  
        }
    }

    private String listPerformanceLastWeek(String username) {
        // YYYY-MM-DD HH:MM:SS
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        Date lastWeek = new Date(System.currentTimeMillis() - (7 * DAY_IN_MS));
        String uid = whoIsUsername(username);
        String result = "";
        try {
            ResultSet rs = (ResultSet) super.executeReturnQuery(Queries.GET_WORKOUT_PERFORMANCE_LAST_WEEK(uid, format.format(lastWeek)));
            while(rs.next()) {
                result += "Datetime: " + rs.getString("datetime") + ", Performance: " + rs.getString("performance") + "\n";
            }
        } catch (Exception e) {
            result = "Could not get last week's performance";
        }
        return result;
    }

    /* TESTS */

    private String whoIsUsername(String username) {
        try {
            ResultSet rs = (ResultSet) super.executeReturnQuery(Queries.GET_USER_ID_BY_NAME(username));
            rs.next();
            String result = "" + rs.getString("uid");
            return result;
        } catch (Exception e) {
            return "Could not get user id from username";
        }
    }

    private void print(String string) {
        printStream.println(string);
    }
}