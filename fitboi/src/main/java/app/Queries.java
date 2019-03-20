package app;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

public final class Queries {

    // addUser
    public static String INSERT_USER(String name) {
        return "INSERT INTO exuser (username) VALUES (\"" +  name  + "\");";
    }

    public static String GET_USER_ID_BY_NAME(String name) {
        return "SELECT uid FROM exuser WHERE name = " + name + ";";
    }

    public static String GET_ALL_USERS() {
        return "SELECT * FROM exuser;";
    }

    /*
	 * ================================ EXERCISES ===================================
	 */

    //addFreeExercise
    public static String INSERT_FREE_EXERCISE(String name, String description) {
        return "INSERT INTO exercise (name) VALUES (\"" + name + "\");"
            + "INSERT INTO freeexercise (eid, description) Values ((SELECT LAST_INSERT_ID() FROM exercise), " + description + "\");";
    }

    //addMachineExercise
    public static String INSERT_MACHINE_EXERCISE(String name, String kilos, String sets, String machineID) {
        return "INSERT INTO exercise (name) VALUES (\"" + name + "\");"
            + "INSERT INTO machineexercise (eid, kilos, sets, mid) Values ((SELECT LAST_INSERT_ID() FROM exercise), \"" + kilos + "\", \"" + sets + "\", \"" + machineID + "\");";
    }

    //addExerciseGroup
    public static String INSERT_EXERCISE_GROUP(String name) {
        return "INSERT INTO exercisegroup (name) Values (\"" + name + "\");";
    }

    public static String GET_EXERCISE_GROUP_BY_NAME(String name) {
        return "SELECT egid FROM exuser WHERE name = " + name + ";";
    }

    //connectExerciseToGroup
    public static String CONNECT_EXERCISE_TO_GROUP(String string, String string2) {
        return "INSERT INTO exerciseispartofgroup (eid, egid) VALUES (\"" + string + "\", \"" + string2 + "\");";
    }

    public static String GET_ALL_FREE_EXERCISES() {
        return "SELECT * FROM (freeexercise NATURAL JOIN exercise);";
    }

    public static String GET_ALL_MACHINE_EXERCISES() {
        return "SELECT * FROM ((machineexercise NATURAL JOIN machine) INNER JOIN exercise ON machineexercise.eid = exercise.eid);";
    }

    public static String GET_ALL_EXERCISE_GROUPS() {
        return "SELECT * FROM exercisegroup;";
    }

    public static String GET_EXERCISE_BY_ID(int id) {
        return "SELECT * FROM exercise WHERE eid = " + id + ";";
    }

    public static String GET_EXERCISE_BY_NAME(String name) {
        return "SELECT * FROM exercise WHERE name = " + name + ";";
    }

    public static String GET_ALL_EXERCISES_IN_GROUP(String egid) {
        return "SELECT * FROM (exercise NATURAL JOIN (SELECT * FROM exercisegroup where egid = \"" +  egid + "\"));";
    }

/*
* ================================ MACHINES ===================================
*/

    public static String GET_ALL_MACHINES() {
        return "SELECT * FROM machine";
    }

    public static String GET_ALL_MACHINE_BY_ID(int id) {
        return "SELECT * FROM machine WHERE mid = " + id + ";";
    }

    public static String GET_MACHINE_BY_NAME(String name) {
        return "SELECT * FROM machine WHERE name = " + name + ";";
    }

    public static String GET_MACHINE_ID_BY_NAME(String name) {
        return "SELECT mid FROM machine WHERE navn = " + name + ";";
    }

    public static String INSERT_MACHINE(String name, String description) {
        return "INSERT INTO machine (name, functiondescription) VALUES (\"" + name +"\", \"" + description + "\");";
    }

/*
* ================================ WORKOUTS ===================================
*/

    public static String GET_ALL_WORKOUTS() {
        return "SELECT wid, wodatetime, note, duration, fitness, perfomance FROM workout;";
    }

    public static String GET_ALL_WORKOUTS_FOR_USER(int uid) {
        return "SELECT wid, wodatetime, note, duration, fitness, perfomance FROM (workout NATURAL JOIN userworkedout) WHERE uid = " + uid + ";";
    }

    public static String INSERT_WORKOUT(String datetime, String note, String duration, String fitness, String perfomance) {
        return "INSERT INTO workout (wodatetime, note, duration, fitness, performance) VALUES"
        + "(\"" + datetime + "\", \"" + note + "\", \"" + duration + "\", \"" + fitness + "\", \"" + perfomance + "\");";
    }

    public static String INSERT_USER_WORKED_OUT(String string, String string2) {
        return "INSERT INTO userworkedout (uid, wid) VALUES (\"" + string + "\", \"" + string2 + "\"\");";
    }

    public static String CONNECT_WORKOUT_EXERCISE(String workoutID, String exerciseID) {
        return "INSERT INTO workoutcontains (wid, eid) VALUES (\"" + workoutID + "\", \"" + exerciseID + "\");";
    }

    public static String GET_N_LAST_WORKOUTS_FOR_USER(String n, String userID) {
		return "SELECT * FROM (workout NATURAL JOIN userworkedout) WHERE uid = " + userID + " ORDER BY datetime DESC LIMIT " + n + ";";

    }

    public static String GET_WORKOUT_BY_EXERCISE_AND_INTERVAL(int eid, String intervalStart, String intervalEnd) {
		return "SELECT workout.* FROM workout NATURAL JOIN exercise"
                + "WHERE datetime > " + intervalStart + " AND datetime < " + intervalEnd
                + " AND exercise.wid = " + eid + ";";
	}

    public static String GET_WORKOUT_PERFORMANCE_LAST_WEEK(String uid, String oneWeekAgoDatetime) {
        return "SELECT datetime, performance FROM workout NATURAL JOIN exuser"
                + "WHERE datetime > " + oneWeekAgoDatetime + " AND exuser.uid = " + uid + ";";
    }
}
