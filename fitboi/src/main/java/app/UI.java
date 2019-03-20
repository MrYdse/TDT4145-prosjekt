package app;

import java.sql.*;
import java.io.PrintStream;
import java.util.*;

public class UI extends Sql {

    private PrintStream printStream;

    private Map<String, String> methods = new HashMap<String, String>();
    private String format = "%-40s%s%n";

    public UI(PrintStream stream) {
        this.printStream = stream;
        this.connect();
        methods.put("Help", "Prints list of methods");
    }

    public void handleInput(String input) {
        switch(input) {
            case "hi":      printStream.print("Hi yourself");
                            break;
            case "test":    printStream.print("Yup, that's a test");
                            break;
            case "help":    for (Map.Entry<String, String> entry : methods.entrySet()) {
                              System.out.printf(format, entry.getKey(), entry.getValue());
                            }
                            break;
        }
    }
}
