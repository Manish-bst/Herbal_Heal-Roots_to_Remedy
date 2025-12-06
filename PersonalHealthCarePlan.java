package com.mycompany.herbal_heal_roots_to_remedy;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class PersonalHealthCarePlan {
    private static final String FILE_NAME = "healthcare_plan.txt";

    private static Map<String, String> diseases = new HashMap<>();
    private static Map<String, String> remedies = new HashMap<>();
    private static Map<String, String> remedyDescriptions = new HashMap<>();
    private static Map<String, String> remedyConsumptionTimes = new HashMap<>();
    private static Map<String, String> remedySideEffects = new HashMap<>();
    private static Map<String, String> dietProteins = new HashMap<>();
    private static Map<String, String> dietVitamins = new HashMap<>();
    private static Map<String, String> dietFats = new HashMap<>();
    private static Map<String, String> dietCarbohydrates = new HashMap<>();
    private static Map<String, String> dietSugars = new HashMap<>();
    private static Map<String, String> exercises = new HashMap<>();
    private static Map<String, String> exerciseBenefits = new HashMap<>();
    private static Map<String, String> exerciseDurations = new HashMap<>();
    private static Map<String, String> yogaAsanas = new HashMap<>();

    // NEW: Only create the file IF it doesn't exist
    public void ensureHealthCarePlanFileExists() {
        File file = new File(FILE_NAME);
        if (file.exists()) return;

        String data =
             "disease1, Flu, Ginger tea, Relieves nausea and fever, 3 times a day, Nausea, 20% protein from chicken, 10% vitamin C from orange, 10% fats from nuts, 60% carbohydrates from rice, 10% sugar from honey, Rest, Reduces stress, 30 minutes, Bhujangasana, Paschimottanasana, Ardha Matsyendrasana\n"
            +"disease2, Headache, Peppermint tea, Relieves headache and stress, 2 times a day, Dizziness, 25% protein from fish, 15% vitamin B from spinach, 15% fats from olive oil, 55% carbohydrates from whole wheat, 10% sugar from apple, Avoid stress, Improves mood, 20 minutes, Sukhasana, Viparita Karani, Balasana\n"
            +"disease3, Cold, Honey tea, Relieves cold and cough, 3 times a day, Cough, 20% protein from eggs, 10% vitamin C from lemon, 10% fats from coconut oil, 60% carbohydrates from oats, 10% sugar from grapes, Rest, Boosts energy, 30 minutes, Ananda Balasana, Setu Bandhasana, Ustrasana\n"
            +"disease4, Diabetes, Turmeric tea, Relieves diabetes and inflammation, 2 times a day, Increased thirst, 25% protein from turkey, 15% vitamin D from milk, 15% fats from avocado, 55% carbohydrates from quinoa, 10% sugar from pear, Monitor blood sugar, Improves insulin sensitivity, 20 minutes, Dhanurasana, Bhujangasana, Salabhasana\n"
            +"disease5, Hypertension, Garlic tea, Relieves hypertension and stress, 3 times a day, Dizziness, 20% protein from chicken, 10% vitamin C from orange, 10% fats from nuts, 60% carbohydrates from rice, 10% sugar from honey, Monitor blood pressure, Improves cardiovascular health, 30 minutes, Viparita Karani, Sukhasana, Balasana\n"
            +"disease6, Asthma, Eucalyptus tea, Relieves asthma and congestion, 2 times a day, Shortness of breath, 20% protein from legumes, 15% vitamin C from kiwi, 10% fats from olive oil, 55% carbohydrates from sweet potatoes, 10% sugar from honey, Avoid triggers, Enhances lung function, 30 minutes, Ustrasana, Bhujangasana, Salabhasana\n"
            +"disease7, Arthritis, Ginger tea, Reduces inflammation and pain, 3 times a day, Joint pain, 25% protein from tofu, 15% vitamin K from kale, 15% fats from flaxseeds, 55% carbohydrates from brown rice, 10% sugar from beets, Regular movement, Increases flexibility, 30 minutes, Marjaryasana, Balasana, Anjaneyasana\n"
            +"disease8, Insomnia, Chamomile tea, Promotes relaxation and sleep, 1 time before bed, Fatigue, 20% protein from yogurt, 10% vitamin B6 from bananas, 10% fats from walnuts, 60% carbohydrates from whole grains, 10% sugar from honey, Establish sleep routine, Improves sleep quality, 20 minutes, Supta Baddha Konasana, Viparita Karani, Balasana\n"
            +"disease9, Anxiety, Lavender tea, Reduces anxiety and stress, 2 times a day, Nervousness, 25% protein from chickpeas, 15% vitamin C from strawberries, 10% fats from almonds, 55% carbohydrates from quinoa, 10% sugar from dates, Mindfulness practice, Enhances mental clarity, 30 minutes, Sukhasana, Ananda Balasana, Ustrasana\n"
            +"disease10, Depression, Green tea, Boosts mood and energy, 2 times a day, Low energy, 20% protein from fish, 10% vitamin D from eggs, 10% fats from olive oil, 60% carbohydrates from whole grains, 10% sugar from fruits, Regular exercise, Improves overall well-being, 30 minutes, Dhanurasana, Bhujangasana, Salabhasana\n"
            +"disease11, High Cholesterol, Oatmeal, Lowers cholesterol levels, 1 time a day, Fatigue, 25% protein from beans, 15% vitamin E from nuts, 10% fats from avocados, 55% carbohydrates from oats, 10% sugar from honey, Regular exercise, Improves heart health, 30 minutes, Viparita Karani, Sukhasana, Balasana\n"
            +"disease12, Gout, Cherry juice, Reduces uric acid levels, 2 times a day, Joint pain, 20% protein from chicken, 10% vitamin C from oranges, 10% fats from olive oil, 60% carbohydrates from brown rice, 10% sugar from honey, Stay hydrated, Reduces inflammation, 30 minutes, Ustrasana, Bhujangasana, Salabhasana\n"
            +"disease13, Osteoporosis, Milk, Strengthens bones, 2 times a day, Weak bones, 25% protein from fish, 15% vitamin D from sunlight, 10% fats from cheese, 55% carbohydrates from whole grains, 10% sugar from fruits, Weight-bearing exercises, Improves bone density, 30 minutes, Dhanurasana, Bhujangasana, Salabhasana\n"
            +"disease14, IBS, Peppermint tea, Relieves digestive issues, 2 times a day, Bloating, 20% protein from turkey, 10% vitamin B from spinach, 10% fats from olive oil, 60% carbohydrates from rice, 10% sugar from honey, Avoid trigger foods, Improves digestion, 30 minutes, Sukhasana, Viparita Karani, Balasana\n"
            +"disease15, Celiac Disease, Gluten-free bread, Prevents gluten intake, 3 times a day, Digestive discomfort, 25% protein from quinoa, 15% vitamin B from potatoes, 10% fats from nuts, 55% carbohydrates from rice, 10% sugar from fruits, Strict gluten-free diet, Improves gut health, 30 minutes, Dhanurasana, Bhujangasana, Salabhasana\n";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(data);
            System.out.println("File '" + FILE_NAME + "' created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 17) {
                    String key = parts[0].trim();
                    diseases.put(key, parts[1].trim());
                    remedies.put(key, parts[2].trim());
                    remedyDescriptions.put(key, parts[3].trim());
                    remedyConsumptionTimes.put(key, parts[4].trim());
                    remedySideEffects.put(key, parts[5].trim());
                    dietProteins.put(key, parts[6].trim());
                    dietVitamins.put(key, parts[7].trim());
                    dietFats.put(key, parts[8].trim());
                    dietCarbohydrates.put(key, parts[9].trim());
                    dietSugars.put(key, parts[10].trim());
                    exercises.put(key, parts[11].trim());
                    exerciseBenefits.put(key, parts[12].trim());
                    exerciseDurations.put(key, parts[13].trim());
                    yogaAsanas.put(key, parts[14].trim() + ", " + parts[15].trim() + ", " + parts[16].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading healthcare_plan.txt file:\n" + e.getMessage());
        }
    }

    public void createPersonalHealthCarePlanPanel(JTabbedPane tabbedPane) {
        ensureHealthCarePlanFileExists();  // <--- Always ensures file is ready!
        loadData();

        JPanel healthCarePlanPanel = new JPanel();
        healthCarePlanPanel.setLayout(new BorderLayout());
        healthCarePlanPanel.setBackground(Color.decode("#C9E4CA")); // Light greenish color

        // Top input panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(5, 2));
        topPanel.setBackground(Color.decode("#F7DC6F")); // Light orangeish color

        JLabel nameLabel = new JLabel("Enter Your Name:");
        nameLabel.setForeground(Color.decode("#333333"));
        JTextField nameField = new JTextField(20);
        JLabel ageLabel = new JLabel("Enter Your Age:");
        ageLabel.setForeground(Color.decode("#333333"));
        JTextField ageField = new JTextField(20);
        JLabel weightLabel = new JLabel("Enter Your Weight (kg):");
        weightLabel.setForeground(Color.decode("#333333"));
        JTextField weightField = new JTextField(20);
        JLabel diseaseLabel = new JLabel("Select Disease:");
        diseaseLabel.setForeground(Color.decode("#333333"));
        JComboBox<String> diseaseComboBox = new JComboBox<>();
        for (String disease : diseases.values()) {
            diseaseComboBox.addItem(disease);
        }

        topPanel.add(nameLabel);
        topPanel.add(nameField);
        topPanel.add(ageLabel);
        topPanel.add(ageField);
        topPanel.add(weightLabel);
        topPanel.add(weightField);
        topPanel.add(diseaseLabel);
        topPanel.add(diseaseComboBox);

        JButton generatePlanButton = new JButton("Generate Health Care Plan");
        generatePlanButton.setBackground(Color.decode("#4CAF50"));
        generatePlanButton.setForeground(Color.WHITE);
        generatePlanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patientName = nameField.getText().trim();
                int age;
                double weight;
                try {
                    age = Integer.parseInt(ageField.getText().trim());
                    weight = Double.parseDouble(weightField.getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(healthCarePlanPanel, "Please enter valid age and weight.");
                    return;
                }
                String selectedDiseaseName = (String) diseaseComboBox.getSelectedItem();
                String diseaseKey = null;
                for (String key : diseases.keySet()) {
                    if (diseases.get(key).equals(selectedDiseaseName)) {
                        diseaseKey = key;
                        break;
                    }
                }
                if (diseaseKey == null) {
                    JOptionPane.showMessageDialog(healthCarePlanPanel, "Selected disease not found.");
                    return;
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
                plan.append("Disease: ").append(selectedDiseaseName).append("\n");
                plan.append("Recommended Remedy: ").append(remedy).append("\n");
                plan.append("Remedy Description: ").append(remedyDescription).append("\n");
                plan.append("Recommended Consumption Time: ").append(remedyConsumptionTime).append("\n");
                plan.append("Possible Side Effects: ").append(remedySideEffect).append("\n");
                plan.append("Dietary Proteins: ").append(dietProtein).append("\n");
                plan.append("Dietary Vitamins: ").append(dietVitamin).append("\n");
                plan.append("Dietary Fats: ").append(dietFat).append("\n");
                plan.append("Dietary Carbohydrates: ").append(dietCarbohydrate).append("\n");
                plan.append("Dietary Sugars: ").append(dietSugar).append("\n");
                plan.append("Recommended Exercise: ").append(exercise).append("\n");
                plan.append("Exercise Benefits: ").append(exerciseBenefit).append("\n");
                plan.append("Exercise Duration: ").append(exerciseDuration).append("\n");
                plan.append("Yoga Asanas: ").append(asanas).append("\n");

                String pdfFileName = patientName.replaceAll(" ", "_") + "_healthcare_plan.pdf";
                try {
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream(pdfFileName));
                    document.open();
                    document.add(new Paragraph(plan.toString()));
                    document.close();

                    JOptionPane.showMessageDialog(healthCarePlanPanel, "PDF generated successfully: " + pdfFileName);

                    // Try to open the PDF
                    File pdfFile = new File(pdfFileName);
                    if (Desktop.isDesktopSupported() && pdfFile.exists()) {
                        Desktop.getDesktop().open(pdfFile);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(healthCarePlanPanel, "Error generating PDF: " + ex.getMessage());
                }
            }
        });

        healthCarePlanPanel.add(topPanel, BorderLayout.NORTH);
        healthCarePlanPanel.add(generatePlanButton, BorderLayout.SOUTH);
        tabbedPane.addTab("Health Care Plan", healthCarePlanPanel);
    }

    public static void main(String[] args) {
        PersonalHealthCarePlan plan = new PersonalHealthCarePlan();

        // NOT recommended to call generateHealthCarePlanFile() here if you want to preserve edits.
        // Setup GUI on Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Herbal Heal Roots to Remedy");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(700, 500);
                frame.setLocationRelativeTo(null);

                JTabbedPane tabbedPane = new JTabbedPane();
                plan.createPersonalHealthCarePlanPanel(tabbedPane);

                frame.add(tabbedPane);
                frame.setVisible(true);
            }
        });
    }
}
