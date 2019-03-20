package app;

public final class Queries {

    // addUser
    public static String NEW_USER(String name) {
        return "INSERT INTO user (name) Values (" + name + ")";
    }
    /*
	 * ================================ EXERCISES ===================================
	 */

    //addFreeExercise
    public static String INSERT_FREE_EXERCISE(String name, String description) {
        return "INSERT INTO exercise (name) Values (" + name + ")" 
            + "INSERT INTO freeexercise (eid, description) Values ((SELECT LAST_INSERT_ID() FROM exercise), " + description + ")";
    }

    //addMachineExercise
    public static String INSERT_MACHINE_EXERCISE(int kilos, int sets, int machineId) {
        return "INSERT INTO exercise (name) Values (" + name + ")" 
            + "INSERT INTO machineexercise (eid, kilos, sets, mid) Values ((SELECT LAST_INSERT_ID() FROM exercise), " + kilos + ", " + sets + ", " + machineId + ")";
    }

    //addExerciseGroup
    public static String INSERT_EXERCISE_GROUP(String name) {
        return "INSERT INTO exercisegroup (name) Values (" + name + ")";
    }

    //connectExerciseToGroup
    public static String CONNECT_EXERCISE_TO_GROUP(int exerciseId, int exerciseGroupId) {
        return "INSERT INTO exerciseispartofgroup (eid, egid) VALUES (" + exerciseId + ", " + exerciseGroupId + ")";
    }

    public static String GET_ALL_EXERCISES() {
        return "SELECT * FROM exercise";
    }

    public static String GET_EXERCISE_BY_ID(int id) {
        return "SELECT * FROM exercise WHERE eid = " + id;
    }

    public static String GET_EXERCISE_BY_NAME(String name) {
        return "SELECT * FROM exercise WHERE name = " + name;
    }
    /*
	 * ================================ MACHINES ===================================
	 */

    public static String GET_ALL_MACHINES() {
        return "SELECT * FROM machine";
    }

    public static String GET_ALL_MACHINE_BY_ID(int id) {
        return "SELECT * FROM machine WHERE mid = " + id;
    }

    public static String GET_MACHINE_BY_NAME(String name) {
        return "SELECT * FROM machine WHERE name = " + name;
    }

    public static String GET_MACHINE_ID_BY_NAME(String name) {
        return "SELECT mid FROM machine WHERE navn = " + name;
    }

    public static String INSERT_MACHINE(String name, String description) {
        return "INSERT INTO machine (name, functiondescription) VALUES (" + name +", " + description + ")";
    }

/*
* ================================ WORKOUTS ===================================
*/

    public static String GET_ALL_WORKOUTS() {
        return "SELECT wid, date, time, note, duration, fitness, perfomance FROM workout";
    }

    public static String GET_ALL_WORKOUTS_FOR_USER(int uid) {
        return "SELECT wid, date, time, note, duration, fitness, perfomance FROM (workout NATURAL JOIN userworkedout) WHERE uid = " +uid;
    }

    public static String INSERT_WORKOUT(String datetime, String note, int duration, int fitness, int perfomance) {
        return "INSERT INTO workout (datetime, note, duration, fitness, performance) VALUES" 
        + "(" + datetime + ", " + note + ", " + duration + ", " + fitness + ", " + perfomance + ")";
    }

    public static String INSERT_USER_WORKED_OUT(int uid, int wid) {
        return "INSERT INTO userworkedout (uid, wid) VALUES (" + uid + ", " + wid + ")";
    }

    public static String CONNECT_WORKOUT_EXERCISE(int wid, int eid) {
        return "INSERT INTO workoutcontains (wid, eid) VALUES (" + wid + ", " + eid + ")";
    }

    public static String GET_N_LAST_WORKOUTS_FOR_USER(int n, int uid) {
		return "SELECT * FROM (workout NATURAL JOIN userworkedout) WHERE uid = " + uid + " ORDER BY datetime DESC LIMIT " + n;
		
    }
    
    public static String GET_WORKOUT_BY_EXERCISE_AND_INTERVAL(int eid, String intervalStart, String intervalSlutt) {
		return "SELECT workout.* FROM workout NATURAL JOIN exercise"
                + "WHERE datetime > " + intervalStart + " AND datetime < " + intervalSlutt 
                + " AND exercise.wid = " + eid;
	}

}