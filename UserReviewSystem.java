import java.io.*;
import java.util.Scanner;

public class UserReviewSystem {
    private static final String FILE_NAME = "user_reviews.txt";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Write a review");
            System.out.println("2. Read reviews");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (option) {
                case 1:
                    writeReview();
                    break;
                case 2:
                    readReviews();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void writeReview() {
        System.out.print("Enter your review: ");
        String review = scanner.nextLine();

        try (FileWriter fileWriter = new FileWriter(FILE_NAME, true)) {
            fileWriter.write(review + "\n");
            System.out.println("Review saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving review: " + e.getMessage());
        }
    }

    private static void readReviews() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                System.out.println("No reviews available yet.");
            } else {
                System.out.println("Error reading reviews: " + e.getMessage());
            }
        }
    }
}