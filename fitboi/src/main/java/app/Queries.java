package app;

public final class Queries {

    // addUser
    public static String INSERT_USER() {
        return "INSERT INTO user () Values ()";
    }
    
    //addExerciseGroup
    public static String INSERT_EXERCISE_GROUP(int exerciseGroupId, String name) {
        return "INSERT INTO exercisegroup (egid, name) Values (\"" + exerciseGroupId + "\", \"" + name + "\")";
    }

    //addExercise -- ikke ferdig - foreign keys
    public static String INSERT_EXERCISE(int exerciseId, String name, boolean type) {
        return "INSERT INTO exercise (eid, name, type, feid, meid) Values ((SELECT ), \"" + exerciseId + "\", \"" + name + "\", \"" + type + "\")";
    }

    //connectExerciseToGroup
    public static String CONNECT_EXERCISE_TO_GROUP(int exerciseId, int exerciseGroupId) {
        return "INSERT INTO exerciseispartofgroup (eid, egid) VALUES (\"" + exerciseId + "\", \"" + exerciseGroupId + "\")";
    }

}