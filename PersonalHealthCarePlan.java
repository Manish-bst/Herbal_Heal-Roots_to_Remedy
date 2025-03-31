import java.io.*;
import java.util.*;
import java.awt.Desktop;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class PersonalHealthCarePlan {
    private static final String FILE_NAME = "healthcare_plan.txt";

    private static Map<String, String> diseases = new HashMap<>();
    private static Map<String, String> remedies = new HashMap<>();
    private static Map<String, String> remedyDescriptions = new HashMap<>();
    private static Map<String, String> remedyConsumptionTimes = new HashMap<>();
    private static Map<String, String> remedySideEffects = new HashMap<>();
    private static Map<String, String> diets = new HashMap<>();
    private static Map<String, String> dietProteins = new HashMap<>();
    private static Map<String, String> dietVitamins = new HashMap<>();
    private static Map<String, String> dietFats = new HashMap<>();
    private static Map<String, String> dietCarbohydrates = new HashMap<>();
    private static Map<String, String> dietSugars = new HashMap<>();
    private static Map<String, String> exercises = new HashMap<>();
    private static Map<String, String> exerciseBenefits = new HashMap<>();
    private static Map<String, String> exerciseDurations = new HashMap<>();
    private static Map<String, String> yogaAsanas = new HashMap<>();

    public static void main(String[] args) {
        createFile();
        loadData();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Personal Health Care Plan!");
        System.out.println("Please enter your name:");
        String patientName = scanner.nextLine();
        System.out.println("Please enter your age:");
        int age = scanner.nextInt();
        System.out.println("Please enter your weight (in kg):");
        double weight = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.println("Please select a disease from the following options:");
        for (int i = 0; i < diseases.size(); i++) {
            System.out.println((i + 1) + ". " + diseases.get("disease" + (i + 1)));
        }
        int selectedDisease = scanner.nextInt();
        String selectedDiseaseName = diseases.get("disease" + selectedDisease);

        generateHealthCarePlan(patientName, selectedDiseaseName, age, weight);
    }

    private static void createFile() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                file.createNewFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write("disease1, Flu, Ginger tea, Relieves nausea and fever, 3 times a day, Nausea, 20% protein from chicken, 10% vitamin C from orange, 10% fats from nuts, 60% carbohydrates from rice, 10% sugar from honey, Rest, Reduces stress, 30 minutes, Bhujangasana, Paschimottanasana, Ardha Matsyendrasana\n");
                    writer.write("disease2, Headache, Peppermint tea, Relieves headache and stress, 2 times a day, Dizziness, 25% protein from fish, 15% vitamin B from spinach, 15% fats from olive oil, 55% carbohydrates from whole wheat, 10% sugar from apple, Avoid stress, Improves mood, 20 minutes, Sukhasana, Viparita Karani, Balasana\n");
                    writer.write("disease3, Cold, Honey tea, Relieves cold and cough, 3 times a day, Cough, 20% protein from eggs, 10% vitamin C from lemon, 10% fats from coconut oil, 60% carbohydrates from oats, 10% sugar from grapes, Rest, Boosts energy, 30 minutes, Ananda Balasana, Setu Bandhasana, Ustrasana\n");
                    writer.write("disease4, Diabetes, Turmeric tea, Relieves diabetes and inflammation, 2 times a day, Increased thirst, 25% protein from turkey, 15% vitamin D from milk, 15% fats from avocado, 55% carbohydrates from quinoa, 10% sugar from pear, Monitor blood sugar, Improves insulin sensitivity, 20 minutes, Dhanurasana, Bhujangasana, Salabhasana\n");
                    writer.write("disease5, Hypertension, Garlic tea, Relieves hypertension and stress, 3 times a day, Dizziness, 20% protein from chicken, 10% vitamin C from orange, 10% fats from nuts, 60% carbohydrates from rice, 10% sugar from honey, Monitor blood pressure, Improves cardiovascular health, 30 minutes, Viparita Karani, Sukhasana, Balasana\n");
                    writer.write("disease6, Asthma, Eucalyptus tea, Relieves asthma and congestion, 2 times a day, Shortness of breath, 20% protein from legumes, 15% vitamin C from kiwi, 10% fats from olive oil, 55% carbohydrates from sweet potatoes, 10% sugar from honey, Avoid triggers, Enhances lung function, 30 minutes, Ustrasana, Bhujangasana, Salabhasana\n");
                    writer.write("disease7, Arthritis, Ginger tea, Reduces inflammation and pain, 3 times a day, Joint pain, 25% protein from tofu, 15% vitamin K from kale, 15% fats from flaxseeds, 55% carbohydrates from brown rice, 10% sugar from beets, Regular movement, Increases flexibility, 30 minutes, Marjaryasana, Balasana, Anjaneyasana\n");
                    writer.write("disease8, Insomnia, Chamomile tea, Promotes relaxation and sleep, 1 time before bed, Fatigue, 20% protein from yogurt, 10% vitamin B6 from bananas, 10% fats from walnuts, 60% carbohydrates from whole grains, 10% sugar from honey, Establish sleep routine, Improves sleep quality, 20 minutes, Supta Baddha Konasana, Viparita Karani, Balasana\n");
                    writer.write("disease9, Anxiety, Lavender tea, Reduces anxiety and stress, 2 times a day, Nervousness, 25% protein from chickpeas, 15% vitamin C from strawberries, 10% fats from almonds, 55% carbohydrates from quinoa, 10% sugar from dates, Mindfulness practice, Enhances mental clarity, 30 minutes, Sukhasana, Ananda Balasana, Ustrasana\n");
                    writer.write("disease10, Depression, Green tea, Boosts mood and energy, 2 times a day, Low energy, 20% protein from fish, 10% vitamin D from eggs, 10% fats from olive oil, 60% carbohydrates from whole grains, 10% sugar from fruits, Regular exercise, Improves overall well-being, 30 minutes, Dhanurasana, Bhujangasana, Salabhasana\n");
                    writer.write("disease11, High Cholesterol, Oatmeal, Lowers cholesterol levels, 1 time a day, Fatigue, 25% protein from beans, 15% vitamin E from nuts, 10% fats from avocados, 55% carbohydrates from oats, 10% sugar from honey, Regular exercise, Improves heart health, 30 minutes, Viparita Karani, Sukhasana, Balasana\n");
                    writer.write("disease12, Gout, Cherry juice, Reduces uric acid levels, 2 times a day, Joint pain, 20% protein from chicken, 10% vitamin C from oranges, 10% fats from olive oil, 60% carbohydrates from brown rice, 10% sugar from honey, Stay hydrated, Reduces inflammation, 30 minutes, Ustrasana, Bhujangasana, Salabhasana\n");
                    writer.write("disease13, Osteoporosis, Milk, Strengthens bones, 2 times a day, Weak bones, 25% protein from fish, 15% vitamin D from sunlight, 10% fats from cheese, 55% carbohydrates from whole grains, 10% sugar from fruits, Weight-bearing exercises, Improves bone density, 30 minutes, Dhanurasana, Bhujangasana, Salabhasana\n");
                    writer.write("disease14, IBS, Peppermint tea, Relieves digestive issues, 2 times a day, Bloating, 20% protein from turkey, 10% vitamin B from spinach, 10% fats from olive oil, 60% carbohydrates from rice, 10% sugar from honey, Avoid trigger foods, Improves digestion, 30 minutes, Sukhasana, Viparita Karani, Balasana\n");
                    writer.write("disease15, Celiac Disease, Gluten-free bread, Prevents gluten intake, 3 times a day, Digestive discomfort, 25% protein from quinoa, 15% vitamin B from potatoes, 10% fats from nuts, 55% carbohydrates from rice, 10% sugar from fruits, Strict gluten-free diet, Improves gut health, 30 minutes, Dhanurasana, Bhujangasana, Salabhasana\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    private static void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String diseaseKey = parts[0].trim();
                String diseaseName = parts[1].trim ();
                String remedy = parts[2].trim();
                String remedyDescription = parts[3].trim();
                String remedyConsumptionTime = parts[4].trim();
                String remedySideEffect = parts[5].trim();
                String dietProtein = parts[6].trim();
                String dietVitamin = parts[7].trim();
                String dietFat = parts[8].trim();
                String dietCarbohydrate = parts[9].trim();
                String dietSugar = parts[10].trim();
                String exercise = parts[11].trim();
                String exerciseBenefit = parts[12].trim();
                String exerciseDuration = parts[13].trim();
                String asana1 = parts[14].trim();
                String asana2 = parts[15].trim();
                String asana3 = parts[16].trim();

                diseases.put(diseaseKey, diseaseName);
                remedies.put(diseaseKey, remedy);
                remedyDescriptions.put(diseaseKey, remedyDescription);
                remedyConsumptionTimes.put(diseaseKey, remedyConsumptionTime);
                remedySideEffects.put(diseaseKey, remedySideEffect);
                dietProteins.put(diseaseKey, dietProtein);
                dietVitamins.put(diseaseKey, dietVitamin);
                dietFats.put(diseaseKey, dietFat);
                dietCarbohydrates.put(diseaseKey, dietCarbohydrate);
                dietSugars.put(diseaseKey, dietSugar);
                exercises.put(diseaseKey, exercise);
                exerciseBenefits.put(diseaseKey, exerciseBenefit);
                exerciseDurations.put(diseaseKey, exerciseDuration);
                yogaAsanas.put(diseaseKey, asana1 + ", " + asana2 + ", " + asana3);
            }
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    private static void generateHealthCarePlan(String patientName, String disease, int age, double weight) {
        String diseaseKey = null;
        for (String key : diseases.keySet()) {
            if (diseases.get(key).equals(disease)) {
                diseaseKey = key;
                break;
            }
        }

        String remedy = remedies.get(diseaseKey);
        String remedyDescription = remedyDescriptions.get(diseaseKey);
        String remedyConsumptionTime = remedyConsumptionTimes.get(diseaseKey);
        String remedySideEffect = remedySideEffects.get(diseaseKey);
        String dietProtein = dietProteins.get(diseaseKey);
        String dietVitamin = dietVitamins.get(diseaseKey);
        String dietFat = dietFats.get(diseaseKey);
        String dietCarbohydrate = dietCarbohydrates.get(diseaseKey);
        String dietSugar = dietSugars.get(diseaseKey);
        String exercise = exercises.get(diseaseKey);
        String exerciseBenefit = exerciseBenefits.get(diseaseKey);
        String exerciseDuration = exerciseDurations.get(diseaseKey);
        String asanas = yogaAsanas.get(diseaseKey);

        StringBuilder plan = new StringBuilder();
        plan.append("Personal Health Care Plan for ").append(patientName).append("\n");
        plan.append("Age: ").append(age).append("\n");
        plan.append("Weight: ").append(weight).append(" kg\n");
        plan.append("Disease: ").append(disease).append("\n");
        plan.append("Recommended Remedy: ").append(remedy).append("\n");
        plan.append("Remedy Description: ").append(remedyDescription).append("\n");
        plan.append("Remedy Consumption Time: ").append(remedyConsumptionTime).append("\n");
        plan.append("Side Effects: ").append(remedySideEffect).append("\n");
        plan.append("Dietary Needs:\n");
        plan.append(" - Protein: ").append(dietProtein).append("\n");
        plan.append(" - Vitamin: ").append(dietVitamin).append("\n");
        plan.append(" - Fats: ").append(dietFat).append("\n");
        plan.append(" - Carbohydrates: ").append(dietCarbohydrate).append("\n");
        plan.append(" - Sugar: ").append(dietSugar).append("\n");
        plan.append("Exercise Needed: ").append(exercise).append("\n");
        plan.append("Benefits of Exercise: ").append(exerciseBenefit).append("\n");
        plan.append("Minimum Duration: ").append(exerciseDuration).append("\n");
        plan.append("Recommended Yoga Asanas: ").append(asanas).append("\n");

        String pdfFileName = patientName + "_healthcare_plan.pdf";
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(pdfFileName));
            document.open();
            document.add(new Paragraph(plan.toString()));
            document.close();
            System.out.println("PDF created successfully: " + pdfFileName);
            System.out.println("Location: " + System.getProperty("user.dir") + "/" + pdfFileName);
            openPDF(pdfFileName);
        } catch (DocumentException | FileNotFoundException e) {
            System.out.println("Error generating PDF: " + e.getMessage());
        }
    }

    private static void openPDF(String pdfFileName) {
        try {
            File pdfFile = new File(pdfFileName);
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(pdfFile);
            } else {
                System.out.println("Desktop is not supported. Cannot open PDF.");
            }
        } catch (IOException e) {
            System.out.println("Error opening PDF: " + e.getMessage());
        }
    }
}
