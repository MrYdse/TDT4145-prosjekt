package app;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

public final class Queries {

    // addUser
    public static String INSERT_USER(String name) {
        return "INSERT INTO exuser (username) VALUES (\"" + name + "\");";
    }

    public static String GET_USER_ID_BY_NAME(String name) {
        return "SELECT uid FROM exuser WHERE username = \"" + name + "\";";
    }

    public static String GET_ALL_USERS() {
        return "SELECT * FROM exuser;";
    }

    /*
     * ================================ EXERCISES
     * ===================================
     */

    // addFreeExercise

    /*
     * public static String INSERT_FREE_EXERCISE(String name, String description) {
     * return "INSERT INTO exercise (name) VALUES (\"" + name + "\");\n" +
     * "INSERT INTO freeexercise (eid, description) VALUES (LAST_INSERT_ID(), \"" +
     * description + "\");"; }
     */

    public static String INSERT_FREE_EXERCISE_1(String name) {
        return "INSERT INTO exercise (name) VALUES (\"" + name + "\");";
    }

    public static String INSERT_FREE_EXERCISE_2(String description) {
        return "INSERT INTO freeexercise (eid, description) VALUES (LAST_INSERT_ID(), \"" + description + "\");";
    }

    // addMachineExercise
    public static String INSERT_MACHINE_EXERCISE(String name, String kilos, String sets, String machineID) {
        return "INSERT INTO exercise (name) VALUES (\"" + name + "\");"
                + "INSERT INTO machineexercise (eid, kilos, sets, mid) Values ((SELECT LAST_INSERT_ID() FROM exercise), "
                + kilos + ", " + sets + ", " + machineID + ");";
    }

    public static String INSERT_MACHINE_EXERCISE_1(String name) {
        return "INSERT INTO exercise (name) VALUES (\"" + name + "\");";
    }

    public static String INSERT_MACHINE_EXERCISE_2(String kilos, String sets, String machineID) {
        return "INSERT INTO machineexercise (eid, kilos, sets, mid) Values (LAST_INSERT_ID(), "
                + kilos + ", " + sets + ", " + machineID + ");";
    }

    // addExerciseGroup
    public static String INSERT_EXERCISE_GROUP(String name) {
        return "INSERT INTO exercisegroup (name) Values (\"" + name + "\");";
    }

    public static String GET_EXERCISE_GROUP_BY_NAME(String name) {
        return "SELECT egid FROM exercisegroup WHERE name = \"" + name + "\";";
    }

    // connectExerciseToGroup
    public static String CONNECT_EXERCISE_TO_GROUP(String eid, String egid) {
        return "INSERT INTO exerciseispartofgroup (eid, egid) VALUES (" + eid + ", " + egid + ");";
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
        // select eg.egid, eg.name, e.eid, e.name from exercise as e join
        // exerciseispartofgroup as eipg on
        // e.eid = eipg.eid join exercisegroup as eg on eipg.egid = eg.egid group by
        // e.eid having eg.egid = 1;
        return "SELECT eg.egid, eg.name, e.eid, e.name FROM exercise AS e JOIN exerciseispartofgroup AS eipg "
                + "ON e.eid = eipg.eid JOIN exercisegroup AS eg ON eipg.egid = eg.egid GROUP BY e.eid HAVING eg.egid = "
                + egid + ";";
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
        return "INSERT INTO machine (name, functiondescription) VALUES (\"" + name + "\", \"" + description + "\");";
    }

    /*
     * ================================ WORKOUTS ===================================
     */

    public static String GET_ALL_WORKOUTS() {
        return "SELECT wid, wodatetime, note, duration, fitness, performance FROM workout;";
    }

    public static String GET_ALL_WORKOUTS_FOR_USER(String userID) {
        return "SELECT uid, wid, wodatetime, note, duration, fitness, performance FROM (workout NATURAL JOIN userworkedout) WHERE uid = "
                + userID + ";";
    }

    public static String INSERT_WORKOUT(String datetime, String note, String duration, String fitness,
            String perfomance) {
        return "INSERT INTO workout (wodatetime, note, duration, fitness, performance) VALUES" + "(\"" + datetime
                + "\", \"" + note + "\", " + duration + ", " + fitness + ", " + perfomance + ");";
    }

    public static String INSERT_USER_WORKED_OUT(String uid, String wid) {
        return "INSERT INTO userworkedout (uid, wid) VALUES (" + uid + ", " + wid + ");";
    }

    public static String CONNECT_WORKOUT_EXERCISE(String workoutID, String exerciseID) {
        return "INSERT INTO workoutcontains (wid, eid) VALUES (" + workoutID + ", " + exerciseID + ");";
    }

    public static String GET_N_LAST_WORKOUTS_FOR_USER(String n, String userID) {
        return "SELECT * FROM (workout NATURAL JOIN userworkedout) WHERE uid = " + userID
                + " ORDER BY wodatetime DESC LIMIT " + n + ";";

    }

    public static String GET_WORKOUT_BY_EXERCISE_AND_INTERVAL(int eid, String intervalStart, String intervalEnd) {
        return "SELECT workout.* FROM workout NATURAL JOIN exercise" + "WHERE datetime > " + intervalStart
                + " AND datetime < " + intervalEnd + " AND exercise.wid = " + eid + ";";
    }

    public static String GET_WORKOUT_PERFORMANCE_LAST_WEEK(String uid, String oneWeekAgoDatetime) {
        // SELECT * from workout natural join (select * from userworkedout natural join
        // exuser where uid = 1)
        // as uw where DATE(wodatetime) > '2019-03-18';
        return "SELECT wodatetime, performance FROM workout NATURAL JOIN (SELECT * FROM userworkedout NATURAL JOIN exuser WHERE uid = "
                + uid + ") AS uw WHERE DATE(wodatetime) > \'" + oneWeekAgoDatetime + "\';";
    }
}
