package app;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        UI ui = new UI(System.out);
        Scanner scanner = new Scanner(System.in);
        String input = "";
        System.out.println("Welcome to fitboi");
        while((!input.equals("exit")) && (scanner.hasNextLine())) {
            input = scanner.nextLine();
            ui.handleInput(input);
        }
        scanner.close();
    }
}