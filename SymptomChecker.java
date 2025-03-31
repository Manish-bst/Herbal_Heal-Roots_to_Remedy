
import java.io.*;
import java.util.*;

public class SymptomChecker {
    private static final String SYMPTOM_FILE = "symptoms.txt";

    private static Map<String, String> symptoms = new HashMap<>();
    private static Map<String, String> remedies = new HashMap<>();
    private static Map<String, List<String>> symptomRemedyMapping = new HashMap<>();
    private static Map<String, String> remedyUses = new HashMap<>();
    private static Map<String, String> remedyTime = new HashMap<>();

    public static void main(String[] args) {
        createFile();

        loadSymptoms();
        loadRemedies();
        loadRemedyUses();
        loadRemedyTime();
        loadSymptomRemedyMapping();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Symptom Checker!");
        System.out.println("Please answer the following questions to get a personalized health assessment.");

        List<String> userSymptoms = new ArrayList<>();

        for (String symptom : symptoms.keySet()) {
            System.out.println("Do you have " + symptom + "? (" + symptoms.get(symptom) + ")");
            System.out.println("Yes/No");
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("yes")) {
                userSymptoms.add(symptom);
            }
        }

        List<String> predictedDiseases = predictDisease(userSymptoms);
        List<String> recommendedRemedies = getRecommendedRemedies(predictedDiseases);

        System.out.println("Recommended herbal remedies:");
        for (String remedy : recommendedRemedies) {
            System.out.println("Remedy: " + remedies.get(remedy));
            System.out.println("Use: " + remedyUses.get(remedy));
            System.out.println("Time to consume: " + remedyTime.get(remedy));
        }
    }

    private static void createFile() {
        try {
            File symptomFile = new File(SYMPTOM_FILE);
            if (!symptomFile.exists()) {
                symptomFile.createNewFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(symptomFile))) {
                    writer.write("headache, Pain in the head, ginger, turmeric, Relieves pain and reduces inflammation, Morning and evening\n");
                    writer.write("fever, Elevated body temperature, ginger, honey, Reduces fever and soothes the throat, Every 4 hours\n");
                    writer.write("cough, Coughing, honey, turmeric, Soothes the throat and reduces coughing, Before bed\n");
                    writer.write("fatigue, Feeling tired or weak, peppermint, chamomile, Boosts energy and promotes relaxation, Afternoon\n");
                    writer.write("nausea, Feeling queasy or sick to the stomach, ginger, peppermint, Relieves nausea and soothes the stomach, As needed\n");
                    writer.write("diarrhea, Loose or watery stools, chamomile, lavender, Soothes the stomach and reduces diarrhea, After meals\n");
                    writer.write("constipation, Difficulty passing stools, garlic, onion, Stimulates digestion and relieves constipation, Morning\n");
                    writer.write("abdominal pain, Pain in the abdomen, cayenne pepper, ginger, Relieves pain and reduces inflammation, As needed\n");
                    writer.write("chest pain, Pain in the chest, garlic, turmeric, Relieves pain and reduces inflammation, As needed\n");
                    writer.write("shortness of breath, Difficulty breathing, eucalyptus, peppermint, Relieves congestion and promotes breathing, As needed\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    private static void loadSymptoms() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SYMPTOM_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                symptoms.put(parts[0].trim(), parts[1].trim());
            }
        } catch (IOException e) {
            System.out.println("Error loading symptoms: " + e.getMessage());
        }
    }

    private static void loadRemedies() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SYMPTOM_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                remedies.put(parts[2].trim(), parts[2].trim() + " tea");
                remedies.put(parts[3].trim(), parts[3].trim() + " tea");
            }
        } catch (IOException e) {
            System.out.println("Error loading remedies: " + e.getMessage());
        }
    }

    private static void loadRemedyUses() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SYMPTOM_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                remedyUses.put(parts[2].trim(), parts[4].trim());
                remedyUses.put(parts[3].trim(), parts[4].trim());
            }
        } catch (IOException e) {
            System.out.println("Error loading remedy uses: " + e.getMessage());
        }
    }

    private static void loadRemedyTime() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SYMPTOM_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                remedyTime.put(parts[2].trim(), parts[5].trim());
                remedyTime.put(parts[3].trim(), parts[5].trim());
            }
        } catch (IOException e) {
            System.out.println("Error loading remedy time: " + e.getMessage());
        }
    }

    private static void loadSymptomRemedyMapping() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SYMPTOM_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String disease = parts[1].trim();
                String remedy1 = parts[2].trim();
                String remedy2 = parts[3].trim();

                if (!symptomRemedyMapping.containsKey(disease)) {
                    symptomRemedyMapping.put(disease, new ArrayList<>());
                }

                symptomRemedyMapping.get(disease).add(remedy1);
                symptomRemedyMapping.get(disease).add(remedy2);
            }
        } catch (IOException e) {
            System.out.println("Error loading symptom remedy mapping: " + e.getMessage());
        }
    }

    private static List<String> predictDisease(List<String> userSymptoms) {
        List<String> predictedDiseases = new ArrayList<>();
        for (String symptom : userSymptoms) {
            if (symptoms.containsKey(symptom)) {
                predictedDiseases.add(symptoms.get(symptom));
            }
        }
        return predictedDiseases;
    }

    private static List<String> getRecommendedRemedies(List<String> predictedDiseases) {
        List<String> recommendedRemedies = new ArrayList<>();
        for (String disease : predictedDiseases) {
            if (symptomRemedyMapping.containsKey(disease)) {
                recommendedRemedies.addAll(symptomRemedyMapping.get(disease));
            }
        }
        return recommendedRemedies;
    }
}

