import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HealthTips {
    public static void main(String[] args) {
        try {
            File file = new File("health_tips.txt");
            if (!file.exists()) {
                file.createNewFile();
                FileWriter writer = new FileWriter(file);
                writer.write("Health Tips for Various Infections\n\n");

                // Bacterial Infections
                writer.write("Bacterial Infections:\n");
                writer.write("1. Wash your hands regularly to prevent the spread of bacteria\n");
                writer.write("2. Cook food thoroughly to kill harmful bacteria\n");
                writer.write("3. Stay hydrated and rest to help your body recover\n\n");

                // Viral Infections
                writer.write("Viral Infections:\n");
                writer.write("1. Get vaccinated to prevent viral infections\n");
                writer.write("2. Avoid close contact with infected individuals\n");
                writer.write("3. Maintain good hygiene practices\n\n");

                // Fungal Infections
                writer.write("Fungal Infections:\n");
                writer.write("1. Keep your skin dry and clean\n");
                writer.write("2. Use antifungal powders or creams as needed\n");
                writer.write("3. Wear breathable clothing to reduce moisture\n\n");

                writer.close();
                System.out.println("Health tips written to health_tips.txt");
            } else {
                System.out.println("File already exists. Reading health tips from the file...");
                readHealthTips(file);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    private static void readHealthTips(File file) {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the file.");
            e.printStackTrace();
        }
    }
}