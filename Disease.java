import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Disease {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the disease:");
        String disease = scanner.nextLine();
        try {
            URL url = new URL("https://en.wikipedia.org/wiki/" + disease);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();

            System.out.println("Content:");
            System.out.println(content.toString());

            // Extract the disease information
            String diseaseInfo = extractDiseaseInfo(content.toString());

            System.out.println("Disease Information:");
            System.out.println(diseaseInfo);
        } catch (IOException ex) {
            System.out.println("Failed to fetch data");
        }
    }

    private static String extractDiseaseInfo(String content) {
        // Remove HTML tags
        content = content.replaceAll("<.*?>", "");

        // Remove non-English characters
        content = content.replaceAll("[^a-zA-Z0-9\\s\\.\\,\\!\\?\\:\\;]", "");

        System.out.println("Cleaned Content:");
        System.out.println(content);

        // Extract the disease information
        String[] sections = {"Causes", "Symptoms", "Diagnosis", "Treatment", "Prognosis"};
        StringBuilder diseaseInfo = new StringBuilder();
        for (String section : sections) {
            Pattern pattern = Pattern.compile(section + ":\\s*(.*)");
            Matcher matcher = pattern.matcher(content);
            if (matcher.find()) {
                diseaseInfo.append(section).append(": ").append(matcher.group(1).trim()).append("\n");
            } else {
                System.out.println("No match found for section: " + section);
            }
        }

        return diseaseInfo.toString();
    }
}