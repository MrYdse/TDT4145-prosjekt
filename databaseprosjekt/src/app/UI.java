package app;

import java.sql.*;
import java.io.PrintStream;

public class UI extends Sql {

    private PrintStream printStream;

    public UI(PrintStream stream) {
        this.printStream = stream;
    }

    public void handleInput(String input) {
        switch(input) {
            case "hi":      printStream.print("Hi yourself");
                            break;
            case "test":    printStream.print("Yup, that's a test");
                            break;
        }
    }
}