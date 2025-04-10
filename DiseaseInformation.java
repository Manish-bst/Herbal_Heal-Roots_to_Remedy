import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class DiseaseInformation {

    // Predefined base URL for Wikipedia
    private static final String WIKIPEDIA_URL = "https://en.wikipedia.org/wiki/";

    // Method to fetch disease information from predefined sources
    public String getDiseaseInformation(String diseaseName) {
        try {
            // Create the URL for Wikipedia by appending the disease name
            String wikipediaUrl = WIKIPEDIA_URL + diseaseName.replaceAll(" ", "_");  // Replace spaces with underscores for Wikipedia
            System.out.println("Fetching data from URL: " + wikipediaUrl);
            
            // Fetch and return the entire information about the disease
            String diseaseInfo = fetchDiseaseInfoFromWikipedia(wikipediaUrl);
            if (!diseaseInfo.isEmpty()) {
                return diseaseInfo;
            }

            // If no valid info from Wikipedia, return a default message
            return "No detailed disease information found.";

        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            return "Error: Unable to fetch disease information.";
        }
    }

    // Fetch complete disease information from Wikipedia
    private String fetchDiseaseInfoFromWikipedia(String url) {
        try {
            // Connect to Wikipedia and fetch the document
            Document doc = Jsoup.connect(url).get();

            // Extract the entire content of the disease page
            Elements content = doc.select("div.mw-parser-output > p");  // Select paragraphs inside the content area
            if (!content.isEmpty()) {
                StringBuilder diseaseDescription = new StringBuilder();
                for (org.jsoup.nodes.Element p : content) {
                    diseaseDescription.append(p.text()).append("\n");  // Append each paragraph's text
                }
                return diseaseDescription.toString();
            }
            return "";  // If no content found, return empty
        } catch (Exception e) {
            System.out.println("Error occurred while fetching from Wikipedia: " + e.getMessage());
            return "";  // Return empty if there is an issue
        }
    }

    public static void main(String[] args) {
        DiseaseInformation diseaseInfo = new DiseaseInformation();
        Scanner scanner = new Scanner(System.in);

        // Input disease name from the user
        System.out.println("Enter disease name: ");
        String diseaseName = scanner.nextLine().trim();  // Trim to avoid leading/trailing spaces
        // Fetch and display disease information
        String info = diseaseInfo.getDiseaseInformation(diseaseName);
        System.out.println("Disease Information: ");
        System.out.println(info);  // Display the fetched information
    }
}
