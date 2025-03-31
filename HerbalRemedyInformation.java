import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HerbalRemedyInformation {

    // Predefined list of herb URLs based on herb names
    private static final String WIKIPEDIA_URL = "https://en.wikipedia.org/wiki/";

    // Method to fetch herb information from predefined sources
    public String getHerbInformation(String herbName) {
        try {
            // Create the URL for Wikipedia by appending the herb name
            String wikipediaUrl = WIKIPEDIA_URL + herbName.replaceAll(" ", "_");  // Replace spaces with underscores for Wikipedia
            System.out.println("Fetching data from URL: " + wikipediaUrl);
            
            // Try to fetch data from Wikipedia
            String herbInfo = fetchHerbInfoFromWikipedia(wikipediaUrl);
            if (!herbInfo.isEmpty()) {
                return herbInfo;
            }

            // If no valid info from Wikipedia, you can add more sources or logic for fetching info from other websites
            return "No detailed herb information found.";

        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            return "Error: Unable to fetch herb information.";
        }
    }

    // Fetch herb information from Wikipedia
    private String fetchHerbInfoFromWikipedia(String url) {
        try {
            // Connect to Wikipedia and fetch the document
            Document doc = Jsoup.connect(url).get();

            // Extract content from the first paragraph of the main content area (the first <p> tag)
            Elements content = doc.select("div.mw-parser-output > p");
            if (!content.isEmpty()) {
                StringBuilder herbDescription = new StringBuilder();
                for (org.jsoup.nodes.Element p : content) {
                    herbDescription.append(p.text()).append("\n");
                }
                return herbDescription.toString();
            }
            return "";  // If no content found, return empty
        } catch (Exception e) {
            System.out.println("Error occurred while fetching from Wikipedia: " + e.getMessage());
            return "";  // Return empty if there is an issue
        }
    }

    public static void main(String[] args) {
        HerbalRemedyInformation herbInfo = new HerbalRemedyInformation();
        Scanner scanner = new Scanner(System.in);

        // Input herb name from the user
        System.out.println("Enter herb name: ");
        String herbName = scanner.nextLine().trim();  // Trim to avoid leading/trailing spaces

        // Fetch and display herb information
        String info = herbInfo.getHerbInformation(herbName);
        System.out.println("Herb Information: ");
        System.out.println(info);  // Display the fetched information
    }
}
