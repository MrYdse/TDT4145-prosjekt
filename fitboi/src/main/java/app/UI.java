package app;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import java.util.Date;

public class UI extends Sql {

    private PrintStream printStream;

    private Map<String, String> methods = new LinkedHashMap<String, String>();
    private String format = "%-40s%s%n";

    public UI(PrintStream stream) {
        this.printStream = stream;
        this.connect();

        methods.put("'Help'", "Prints list of methods");
        methods.put("'Add user'", "Syntax: AddUser /<username>");
        methods.put("'Who has this username'", "Syntax: whois /<username>");
        methods.put("'Add exercise group'", "Syntax: AddExerciseGroup /<name>");
        methods.put("'Add machine'", "Syntax: AddMachine /<name> /<description>");
        methods.put("'Add free exercise'", "Syntax: AddFreeExercise /<name> /<description>");
        methods.put("'Add machine exercise'", "Syntax: AddMachineExercise /<name> /<sets> /<machineID> /<kilos>");
        methods.put("'Add workout'", "Syntax: AddWorkout /<datetime> /<note> /<duration> /<fitness> /<performance>");
        methods.put("'Connect exercise to group'", "Syntax: ConnectExerciseToGroup /<exerciseId> /<exerciseGroupId>");
        methods.put("'Connect user workout'", "Syntax: ConnectUserWorkout /<userID> /<workoutID>");
        methods.put("'Connect workout exercise'", "Syntax: ConnectWorkoutExercise /<workoutID> /<exerciseID>");
        methods.put("'List exercise groups'", "Syntax: listExerciseGroups");
        methods.put("'List exercises in group'", "Syntax: listExercisesInGroup /<groupname>");
        methods.put("'List user performace for last week'", "Syntax: listPerformanceLastWeek /<userID>");
        methods.put("'List user's n last workouts'", "Syntax: listUsersLastWorkouts /<userID> /<n>");
        methods.put("'List all user's workouts'", "Syntax: listAllUserWorkouts /<userID>");
        methods.put("'List machines'", "Syntax: listMachines");
        methods.put("'List exercises'", "Syntax: listExercises");
        methods.put("'List workouts'", "Syntax: listWorkouts");
        methods.put("'List users'", "Syntax: listUsers");

        // methods.put("''", "Syntax: ");
    }

    public void handleInput(String input) {
        ArrayList<String> args = new ArrayList<String>(
                Arrays.asList(input.toLowerCase().split("/")));
        for (int i = 0 ; i < args.size(); i++) {
            args.set(i, args.get(i).trim());
        }
        if (args.size() == 1) {
            switch (args.get(0)) {
            case "help":
                for (Map.Entry<String, String> entry : methods.entrySet()) {
                    System.out.printf(format, entry.getKey(), entry.getValue());
                }
                break;
            case "listexercisegroups": case "leg":
                print(listExerciseGroups());
                break;
            case "listexercises": case "le":
                print(listExercises());
                break;
            case "listmachines": case "lm":
                print(listMachines());
                break;
            case "listusers": case "lu":
                print(listUsers());
                break;
            case "exit":
                break;
            default:
                System.out.println(
                        "Is your method in the method list, and/or have you specified the required arguments?");
                break;
            }
        }

        if (args.size() == 2) {
            switch (args.get(0)) {
            case "adduser": case "au":
                print(addUser(args.get(1)));
                break;
            case "addexercisegroup": case "aeg":
                print(addExerciseGroup(args.get(1)));
                break;
            case "whois": case "wi":
                print(whoIsUsername(args.get(1)));
                break;
            case "listexercisesingroup": case "leig":
                print(listExercisesInGroup(args.get(1)));
                break;
            case "listalluserworkouts": case "lauw":
                print(listUsersWorkouts(args.get(1)));
                break;
            case "listperformancelastweek": case "lplw":
                print(listPerformanceLastWeek(args.get(1)));
                break;
            default:
                System.out.println(
                        "Is your method in the method list, and/or have you specified the required arguments?");
                break;
            }
        }

        if (args.size() == 3) {
            switch (args.get(0)) {
            case "addmachine": case "am":
                print(addMachine(args.get(1), args.get(2)));
                break;
            case "connectexercisetogroup": case "cetg":
                print(connectExerciseToGroup(args.get(1), args.get(2)));
                break;
            case "addfreeexercise": case "afe":
                print(addFreeExercise(args.get(1), args.get(2)));
                break;
            case "connectuserworkout": case "cuw":
                print(connectUserWorkout(args.get(1), args.get(2)));
                break;
            case "connectworkoutexercise": case "cwe":
                print(connectWorkoutExercise(args.get(1), args.get(2)));
                break;
            case "listuserslastworkouts": case "lulw":
                print(listUsersLastWorkouts(args.get(1), args.get(2)));
                break;
            default:
                System.out.println(
                        "Is your method in the method list, and/or have you specified the required arguments?");
                break;
            }
        }
        if (args.size() == 4) {
            switch(args.get(0)) {
            case "listworkoutsbyexerciseindaterange": case "lwedr":
                print(listWorkoutsByExerciseInDateRange(args.get(1), args.get(2), args.get(3)));
                break;
            default:
                System.out.println(
                        "Is your method in the method list, and/or have you specified the required arguments?");
                break;
            }
        }

        if (args.size() == 5) {
            switch (args.get(0)) {
            case "addmachineexercise": case "ame":
                print(addMachineExercise(args.get(1), args.get(2), args.get(3), args.get(4)));
                break;
            default:
                System.out.println(
                        "Is your method in the method list, and/or have you specified the required arguments?");
                break;
            }
        }

        if (args.size() > 5) {
            switch (args.get(0)) {
            case "addworkout": case "aw":
                print(addWorkout(args.get(1), args.get(2), args.get(3), args.get(4), args.get(5)));
                break;
            default:
                System.out.println(
                        "Is your method in the method list, and/or have you specified the required arguments?");
                break;
            }
        }
    }

    /* ADDS */

    private String addMachineExercise(String name, String sets, String machineID, String kilos) {
        String result = super.executeInsertQuery(Queries.INSERT_MACHINE_EXERCISE_1(name)) + "\n";
        result += super.executeInsertQuery(Queries.INSERT_MACHINE_EXERCISE_2(kilos, sets, machineID));
        return result;
    }

    private String addUser(String name) {
        if (whoIsUsername(name) == "Could not get user id from username") {
            return super.executeInsertQuery(Queries.INSERT_USER(name));
        }
        else {
            return "A user by that name already exists";
        }
    }

    private String addFreeExercise(String name, String description) {
        String result = super.executeInsertQuery(Queries.INSERT_FREE_EXERCISE_1(name)) + "\n";
        result += super.executeInsertQuery(Queries.INSERT_FREE_EXERCISE_2(description));
        return result;
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

    private String connectExerciseToGroup(String eid, String egid) {
        return super.executeInsertQuery(Queries.CONNECT_EXERCISE_TO_GROUP(eid, egid));
    }

    private String connectUserWorkout(String uid, String wid) {
        return super.executeInsertQuery(Queries.INSERT_USER_WORKED_OUT(uid, wid));
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
                while (rs.next()) {
                    out += "Exercise Group ID: " + rs.getString("egid") + " Name: " + rs.getString("name") + "\n";
                }
            } catch (Exception e) {
                out = "Failed to read resultset with error: " + e.getMessage();
            } finally {
                try {
                    rs.close();
                } catch (Exception e) {
                    out = "Failed to close resultset with error: " + e.getMessage();
                }
            }
            return out;
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
                while (rs.next()) {
                    out += "Machine ID: " + rs.getString("mid") + " Name: " + rs.getString("name") + " Description: "
                            + rs.getString("functiondescription") + "\n";
                }
            } catch (Exception e) {
                out = "Failed to read resultset with error: " + e.getMessage();
            } finally {
                try {
                    rs.close();
                } catch (Exception e) {
                    out = "Failed to close resultset with error: " + e.getMessage();
                }
            }
            return out;
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
                while (rs.next()) {
                    out += "Exercise ID: " + rs.getString("eid") + " Name: " + rs.getString("name") + " Description: "
                            + rs.getString("description") + "\n";
                }
            } catch (Exception e) {
                return "Failed to read resultset with error: " + e.getMessage();
            } finally {
                try {
                    rs.close();
                } catch (Exception e) {
                    return "Failed to close resultset with error: " + e.getMessage();
                }
            }
        }
        out += "Machine exercises:\n";
        response = super.executeReturnQuery(Queries.GET_ALL_MACHINE_EXERCISES());
        if (response instanceof String) {
            out = (String) response;
        } else {
            ResultSet rs = (ResultSet) response;
            try {
                while (rs.next()) {
                    out += "Exercise ID: " + rs.getString("eid") + " Name: " + rs.getString("exercise.name")
                            + " Machine name: " + rs.getString("machine.name") + " Kilos: " + rs.getString("kilos")
                            + " Sets: " + rs.getString("sets") + "\n";
                }
            } catch (Exception e) {
                return "Failed to read resultset with error: " + e.getMessage();
            } finally {
                try {
                    rs.close();
                } catch (Exception e) {
                    return "Failed to close resultset with error: " + e.getMessage();
                }
            }
        }
        return out;
    }

    private String listUsersWorkouts(String userID) {
        Object response = super.executeReturnQuery(Queries.GET_ALL_WORKOUTS_FOR_USER(userID));
        if (response instanceof String) {
            return (String) response;
        } else {
            ResultSet rs = (ResultSet) response;
            String out = "";
            try {
                while (rs.next()) {
                    out += "User ID: " + rs.getString("uid") + " Date: " + rs.getString("wodatetime") + " Note: "
                            + rs.getString("note") +" Duration: " + rs.getString("duration") + " Fitness: " + rs.getString("fitness")
                            + " Performance: " + rs.getString("performance") + "\n";
                }
            } catch (Exception e) {
                out = "Failed to read resultset with error: " + e.getMessage();
            } finally {
                try {
                    rs.close();
                } catch (Exception e) {
                    out = "Failed to close resultset with error: " + e.getMessage();
                }
            }
            return out;
        }
    }

    private String listUsersLastWorkouts(String userID, String n) {
        Object response = super.executeReturnQuery(Queries.GET_N_LAST_WORKOUTS_FOR_USER(n, userID));
        if (response instanceof String) {
            return (String) response;
        } else {
            ResultSet rs = (ResultSet) response;
            String out = "";
            try {
                while (rs.next()) {
                    out += "User ID: " + rs.getString("uid") + " Date: " + rs.getString("wodatetime") + " Note: "
                            + rs.getString("note") + " Duration (seconds): " + rs.getString("duration") + " Fitness: " + rs.getString("fitness")
                            + " Performance: " + rs.getString("performance") + "\n";
                }
            } catch (Exception e) {
                out = "Failed to read resultset with error: " + e.getMessage();
            } finally {
                try {
                    rs.close();
                } catch (Exception e) {
                    out = "Failed to close resultset with error: " + e.getMessage();
                }
            }
            return out;
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
                while (rs.next()) {
                    out += "User ID: " + rs.getString("uid") + " Username: " + rs.getString("username") + "\n";
                }
            } catch (Exception e) {
                out = "Failed to read resultset with error: " + e.getMessage();
            } finally {
                try {
                    rs.close();
                } catch (Exception e) {
                    out = "Failed to close resultset with error: " + e.getMessage();
                }
            }
            return out;
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
                rs.next();
                egid = rs.getString("egid");
            } catch (Exception e) {
                return "Failed to read resultset with error: " + e.getMessage();
            } finally {
                try {
                    rs.close();
                } catch (Exception e) {
                    return "Failed to close resultset with error: " + e.getMessage();
                }
            }
        }
        print("Found group " + egid);
        response = super.executeReturnQuery(Queries.GET_ALL_EXERCISES_IN_GROUP(egid));
        if (response instanceof String) {
            return (String) response;
        } else {
            ResultSet rs = (ResultSet) response;
            String out = "";
            try {
                while (rs.next()) {
                    out += "Exercise: " + rs.getString("e.name") + "\n";
                }
            } catch (Exception e) {
                return "Failed to read resultset with error: " + e.getMessage();
            } finally {
                try {
                    rs.close();
                } catch (Exception e) {
                    return "Failed to close resultset with error: " + e.getMessage();
                }
            }
            return out;
        }
    }


    private String listPerformanceLastWeek(String uid) {
        // YYYY-MM-DD HH:MM:SS
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate dateNow = LocalDate.now();
        LocalDate lastWeek = LocalDate.of(dateNow.getYear(), dateNow.getMonthValue(), dateNow.getDayOfMonth() - 7);
        print(dtf.format(lastWeek));

        Object response = super.executeReturnQuery(
            Queries.GET_WORKOUT_PERFORMANCE_LAST_WEEK(uid, dtf.format(lastWeek)));

        String result = "";
        if (response instanceof String) {
            result = (String) response;
        } else {
            ResultSet rs = (ResultSet) response;
            try {
                while (rs.next()) {
                    result += "Date and time: " + rs.getString("wodatetime") + ", Performance: " + rs.getString("performance")
                            + "\n";
                }
            } catch (Exception e) {
                result = "Could not get last week's performance: " + e.getMessage();
            } finally {
                try {
                    rs.close();
                } catch (Exception e) {
                    result = "Failed to close resultset with error: " + e.getMessage();
                }
            }
        }
        return result;
    }

    private String listWorkoutsByExerciseInDateRange(String exerciseID, String intervalStart, String intervalEnd) {
        Object response = super.executeReturnQuery(Queries.GET_WORKOUT_BY_EXERCISE_AND_INTERVAL(exerciseID, intervalStart, intervalEnd));
        String out = "";
        if (response instanceof String) {
            out = (String) response;
        } else {
            ResultSet rs = (ResultSet) response;
            try {
                while (rs.next()) {
                    out += " Date: " + rs.getString("wodatetime") + " Note: "
                        + rs.getString("note") + " Duration (seconds): " + rs.getString("duration") + " Fitness: " + rs.getString("fitness")
                        + " Performance: " + rs.getString("performance") + " Exercise: " +rs.getString("exercise.name") + "\n";
                }
            } catch (Exception e) {
                return "Could not get last week's performance: " + e.getMessage();
            } finally {
                try {
                    rs.close();
                } catch (Exception e) {
                    return "Failed to close resultset with error: " + e.getMessage();
                }
            }
        }
        return out;
    }

    /* TESTS */

    private String whoIsUsername(String username) {
        ResultSet rs = (ResultSet) super.executeReturnQuery(Queries.GET_USER_ID_BY_NAME(username));
        String result = "";
        try {
            rs.next();
            result += rs.getString("uid");
        } catch (Exception e) {
            result = "Could not get user id from username";
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
                result = "Failed to close resultset with error: " + e.getMessage();
            }
        }
        return result;
    }

    private void print(String string) {
        printStream.println(string);
    }
}
