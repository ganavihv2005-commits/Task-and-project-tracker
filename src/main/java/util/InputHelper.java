package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

// Small helper methods to read input from the keyboard.
// Each method keeps asking until the user types something valid.
public class InputHelper {
    // One Scanner shared by the whole program
    private static Scanner scanner = new Scanner(System.in);

    // Date format dd-MM-yyyy. STRICT means 31-02-2026 is rejected as a wrong date.
    private static DateTimeFormatter dateFormat =
            DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);

    // Reads a whole number
    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input); // works only if input is a number
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a valid integer.");
            }
        }
    }

    // Reads text and does not accept an empty answer
    public static String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("This value cannot be empty.");
        }
    }

    // Reads text, empty answer is allowed
    public static String readOptionalString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // Reads a date in dd-MM-yyyy format
    public static LocalDate readDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return LocalDate.parse(input, dateFormat);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Use dd-MM-yyyy (for example, 31-12-2026).");
            }
        }
    }
}
