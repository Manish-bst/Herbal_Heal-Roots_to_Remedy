package com.mycompany.herbal_heal_roots_to_remedy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class SymptomChecker {
    private static final String SYMPTOM_FILE = "symptoms.txt";

    private static Map<String, String> symptoms = new HashMap<>();
    private static Map<String, String> remedies = new HashMap<>();
    private static Map<String, java.util.List<String>> symptomRemedyMapping = new HashMap<>();
    private static Map<String, String> remedyUses = new HashMap<>();
    private static Map<String, String> remedyTime = new HashMap<>();

    private JCheckBox[] symptomCheckBoxes;
    private JButton submitButton;
    private JTextArea resultTextArea;

    public void createSymptomCheckerPanel(JTabbedPane tabbedPane) {
        loadSymptoms();
        loadRemedies();
        loadRemedyUses();
        loadRemedyTime();
        loadSymptomRemedyMapping();

        JPanel symptomCheckerPanel = new JPanel();
        symptomCheckerPanel.setLayout(new BorderLayout());
        symptomCheckerPanel.setBackground(Color.decode("#C9E4CA")); // Light greenish color

        JPanel symptomPanel = new JPanel();
        symptomPanel.setLayout(new BoxLayout(symptomPanel, BoxLayout.Y_AXIS));
        symptomPanel.setBackground(Color.decode("#F7DC6F")); // Light orangeish color

        symptomCheckBoxes = new JCheckBox[symptoms.size()];
        int i = 0;
        for (String symptom : symptoms.keySet()) {
            symptomCheckBoxes[i] = new JCheckBox(symptom + " - " + symptoms.get(symptom));
            symptomCheckBoxes[i].setForeground(Color.decode("#333333")); // Dark gray color
            symptomPanel.add(symptomCheckBoxes[i]);
            i++;
        }

        symptomCheckerPanel.add(new JScrollPane(symptomPanel), BorderLayout.CENTER);

        submitButton = new JButton("Submit");
        submitButton.setBackground(Color.decode("#4CAF50")); // Green color
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(new SubmitActionListener());
        symptomCheckerPanel.add(submitButton, BorderLayout.SOUTH);

        resultTextArea = new JTextArea(10, 40);
        resultTextArea.setEditable(false);
        resultTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        resultTextArea.setForeground(Color.decode("#333333")); // Dark gray color
        symptomCheckerPanel.add(new JScrollPane(resultTextArea), BorderLayout.EAST);

        tabbedPane.addTab("Symptom Checker", symptomCheckerPanel);
    }

    private class SubmitActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            java.util.List<String> userSymptoms = new ArrayList<>();
            for (int i = 0; i < symptomCheckBoxes.length; i++) {
                if (symptomCheckBoxes[i].isSelected()) {
                    userSymptoms.add(symptoms.keySet().toArray()[i].toString());
                }
            }

            java.util.List<String> predictedDiseases = predictDisease(userSymptoms);
            java.util.List<String> recommendedRemedies = getRecommendedRemedies(predictedDiseases);

            StringBuilder result = new StringBuilder();
            result.append("Recommended herbal remedies:\n");
            for (String remedy : recommendedRemedies) {
                result.append("Remedy: ").append(remedies.get(remedy)).append("\n");
                result.append("Use: ").append(remedyUses.get(remedy)).append("\n");
                result.append("Time to consume: ").append(remedyTime.get(remedy)).append("\n\n");
            }

            resultTextArea.setText(result.toString());
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
                String[] parts = line.split (",");
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

                symptomRemedyMapping.putIfAbsent(disease, new ArrayList<>());
                symptomRemedyMapping.get(disease).add(remedy1);
                symptomRemedyMapping.get(disease).add(remedy2);
            }
        } catch (IOException e) {
            System.out.println("Error loading symptom remedy mapping: " + e.getMessage());
        }
    }

    private static java.util.List<String> predictDisease(java.util.List<String> userSymptoms) {
        java.util.List<String> predictedDiseases = new ArrayList<>();
        for (String symptom : userSymptoms) {
            if (symptoms.containsKey(symptom)) {
                predictedDiseases.add(symptoms.get(symptom));
            }
        }
        return predictedDiseases;
    }

    private static java.util.List<String> getRecommendedRemedies(java.util.List<String> predictedDiseases) {
        java.util.List<String> recommendedRemedies = new ArrayList<>();
        for (String disease : predictedDiseases) {
            if (symptomRemedyMapping.containsKey(disease)) {
                recommendedRemedies.addAll(symptomRemedyMapping.get(disease));
            }
        }
        return recommendedRemedies;
    }
}