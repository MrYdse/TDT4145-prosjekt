package app;

public final class Queries {

    // addUser
    public static String NEW_USER() {
        return "INSERT INTO user () Values ()";
    }
    
    //addFreeExercise
    public static String INSERT_FREE_EXERCISE(String description) {
        return "INSERT INTO exercise (name, type, feid, meid) Values ((SELECT ), \"" + exerciseId + "\", \"" + name + "\", \"" + type + "\")";
    }

    //addExerciseGroup
    public static String NEW_EXERCISE_GROUP(String name) {
        return "INSERT INTO exercisegroup (name) Values (\"" + name + "\")";
    }

    //addExercise -- ikke ferdig - foreign keys
    public static String INSERT_EXERCISE(String name, boolean type) {
        return "INSERT INTO exercise (eid, name, type, feid, meid) Values ((SELECT ), \"" + exerciseId + "\", \"" + name + "\", \"" + type + "\")";
    }

    //connectExerciseToGroup
    public static String CONNECT_EXERCISE_TO_GROUP(int exerciseId, int exerciseGroupId) {
        return "INSERT INTO exerciseispartofgroup (eid, egid) VALUES (\"" + exerciseId + "\", \"" + exerciseGroupId + "\")";
    }

    public static String GET_EXERCISE_LIST() {

    }

    public static String GET_EXERCISE_BY_NAME() {

    }
}