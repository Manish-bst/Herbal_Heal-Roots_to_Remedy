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

    public void createPersonalHealthCarePlanPanel(JTabbedPane tabbedPane) {
        loadData();
        JPanel healthCarePlanPanel = new JPanel();
        healthCarePlanPanel.setLayout(new BorderLayout());
        healthCarePlanPanel.setBackground(Color.decode("#C9E4CA")); // Light greenish color

        // Top input panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(5, 2));
        topPanel.setBackground(Color.decode("#F7DC6F")); // Light orangeish color

        JLabel nameLabel = new JLabel("Enter Your Name:");
        nameLabel.setForeground(Color.decode("#333333")); // Dark gray color
        JTextField nameField = new JTextField(20);
        JLabel ageLabel = new JLabel("Enter Your Age:");
        ageLabel.setForeground(Color.decode("#333333")); // Dark gray color
        JTextField ageField = new JTextField(20);
        JLabel weightLabel = new JLabel("Enter Your Weight (kg):");
        weightLabel.setForeground(Color.decode("#333333")); // Dark gray color
        JTextField weightField = new JTextField(20);
        JLabel diseaseLabel = new JLabel("Select Disease:");
        diseaseLabel.setForeground(Color.decode("#333333")); // Dark gray color
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
        generatePlanButton.setBackground(Color.decode("#4CAF50")); // Green color
        generatePlanButton.setForeground(Color.WHITE);
        generatePlanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patientName = nameField.getText();
                int age;
                double weight;
                try {
                    age = Integer.parseInt(ageField.getText());
                    weight = Double.parseDouble(weightField.getText());
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

    private void loadData() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 17) {
                    diseases.put(parts[0], parts[1]);
                    remedies.put(parts[0], parts[2]);
                    remedyDescriptions.put(parts[0], parts[3]);
                    remedyConsumptionTimes.put(parts[0], parts[4]);
                    remedySideEffects.put(parts[0], parts[5]);
                    dietProteins.put(parts[0], parts[6]);
                    dietVitamins.put(parts[0], parts[7]);
                    dietFats.put(parts[0], parts[8]);
                    dietCarbohydrates.put(parts[0], parts[9]);
                    dietSugars.put(parts[0], parts[10]);
                    exercises.put(parts[0], parts[11]);
                    exerciseBenefits.put(parts[0], parts[12]);
                    exerciseDurations.put(parts[0], parts[13]);
                    yogaAsanas.put(parts[0], parts[14] + ", " + parts[15] + ", " + parts[16]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}