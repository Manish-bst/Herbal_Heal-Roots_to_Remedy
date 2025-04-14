
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserRegistration {
    private static final String FILE_NAME = "users.txt";
    private static boolean isLoggedIn = false;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (!isLoggedIn) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    register(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }

        System.out.println("Exiting...");
        System.exit(0);
    }

    private static void register(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.next();

        if (isUsernameAlreadyRegistered(username)) {
            System.out.println("Username already registered. Please login instead.");
            return;
        }

        String password = getPassword(scanner);

        if (password != null) {
            System.out.print("Confirm password: ");
            String confirmPassword = scanner.next();

            if (password.equals(confirmPassword)) {
                try {
                    FileWriter writer = new FileWriter(FILE_NAME, true);
                    writer.write(username + ":" + password + "\n");
                    writer.close();
                    System.out.println("Registration successful!");
                    isLoggedIn = true;
                } catch (IOException e) {
                    System.out.println("Error registering user: " + e.getMessage());
                }
            } else {
                System.out.println("Passwords do not match. Please try again.");
            }
        }
    }

    private static boolean isUsernameAlreadyRegistered(String username) {
        try {
            File file = new File(FILE_NAME);
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] credentials = line.split(":");

                if (credentials[0].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error checking username: " + e.getMessage());
        }

        return false;
    }

    private static String getPassword(Scanner scanner) {
        while (true) {
            System.out.print("Enter password (at least 8 characters, 1 uppercase letter, 1 special character): ");
            String password = scanner.next();

            if (isValidPassword(password)) {
                return password;
            } else {
                System.out.println("Password does not meet the requirements. Please try again.");
            }
        }
    }

    private static boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean hasUppercase = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            }

            if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }
        }

        return hasUppercase && hasSpecialChar;
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.next();

        System.out.print("Enter password: ");
        String password = scanner.next();

        try {
            File file = new File(FILE_NAME);
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] credentials = line.split(":");

                if (credentials[0].equals(username) && credentials[1].equals(password)) {
                    System.out.println("Login successful!");
                    isLoggedIn = true;
                    return;
                }
            }

            System.out.println("Invalid username or password. Please try again.");
        } catch (IOException e) {
            System.out.println("Error logging in: " + e.getMessage());
        }
    }
}
