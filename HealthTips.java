package com.mycompany.herbal_heal_roots_to_remedy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class HealthTips {
    private static final String FILE_NAME = "health_tips.txt";
    private static final String[] CONDITIONS = {
        "Bacterial Infections", "Viral Infections", "Fungal Infections", "Parasite Infections",
        "Chronic Conditions", "Allergic Reactions", "Respiratory Infections", "Skin Conditions",
        "Autoimmune Diseases"
    };

    public void createHealthTipsPanel(JTabbedPane tabbedPane) {
        JPanel healthTipsPanel = new JPanel();
        healthTipsPanel.setLayout(new BorderLayout());
        healthTipsPanel.setBackground(Color.decode("#C9E4CA")); // Light greenish color

        // Top input panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.setBackground(Color.decode("#F7DC6F")); // Light orangeish color

        JLabel tipsLabel = new JLabel("Select Health Tips Category:");
        tipsLabel.setForeground(Color.decode("#008000")); // Dark green color
        JComboBox<String> categoryComboBox = new JComboBox<>(CONDITIONS);
        JButton showTipsButton = new JButton("Show Tips");
        showTipsButton.setBackground(Color.decode("#4CAF50")); // Green color
        showTipsButton.setForeground(Color.WHITE);
        topPanel.add(tipsLabel);
        topPanel.add(categoryComboBox);
        topPanel.add(showTipsButton);

        // Result area
        JTextArea resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        resultTextArea.setLineWrap(true);
        resultTextArea.setWrapStyleWord(true);
        resultTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        resultTextArea.setForeground(Color.decode("#333333")); // Dark gray color

        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        healthTipsPanel.add(topPanel, BorderLayout.NORTH);
        healthTipsPanel.add(scrollPane, BorderLayout.CENTER);

        // Add action listener to show tips button
        showTipsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) categoryComboBox.getSelectedItem();
                displayHealthTips(selectedCategory, resultTextArea);
            }
        });

        tabbedPane.addTab("Health Tips", healthTipsPanel);

        generateFileIfNeeded();
    }
    private void generateFileIfNeeded() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("Health Tips for Various Infections\n\n");

                writeSection(writer, "Bacterial Infections", new String[]{
                    "Wash your hands regularly to prevent the spread of bacteria",
                    "Cook food thoroughly to kill harmful bacteria",
                    "Stay hydrated and rest to help your body recover",
                    "Use antibiotics only when prescribed by a doctor",
                    "Avoid sharing personal items like towels",
                    "Clean wounds with antiseptic to prevent infection",
                    "Keep your environment clean to minimize bacteria",
                    "Avoid smoking, which can weaken your immune system",
                    "Practice safe food handling and storage",
                    "Avoid contact with infected individuals"
                });

                writeSection(writer, "Viral Infections", new String[]{
                    "Get vaccinated to prevent viral infections",
                    "Avoid close contact with infected individuals",
                    "Maintain good hygiene practices",
                    "Rest and hydrate to help your body recover",
                    "Wear a mask to prevent spreading the virus",
                    "Avoid touching your face to reduce risk of infection",
                    "Disinfect frequently touched surfaces regularly",
                    "Limit outdoor activities during an outbreak",
                    "Eat a healthy diet to boost your immune system",
                    "Seek medical attention if symptoms worsen"
                });

                writeSection(writer, "Fungal Infections", new String[]{
                    "Keep your skin dry and clean",
                    "Use antifungal powders or creams as needed",
                    "Wear breathable clothing to reduce moisture",
                    "Avoid tight-fitting shoes that trap moisture",
                    "Change socks frequently, especially if they become damp",
                    "Do not share personal items like shoes or towels",
                    "Keep nails trimmed and clean",
                    "Use antifungal treatments as directed by a doctor",
                    "Disinfect household surfaces and bathrooms",
                    "Avoid walking barefoot in public places like pools or gyms"
                });

                writeSection(writer, "Parasite Infections", new String[]{
                    "Wash hands thoroughly before eating or preparing food",
                    "Drink clean, treated water to prevent waterborne parasites",
                    "Avoid consuming undercooked meat or seafood",
                    "Use insect repellent to protect against mosquito bites",
                    "Treat pets for fleas, ticks, and worms regularly",
                    "Maintain good hygiene in outdoor activities like camping",
                    "Always wash fruits and vegetables thoroughly before eating",
                    "Avoid walking barefoot in areas where parasites are common",
                    "Seek medical treatment if symptoms of parasitic infections appear",
                    "Keep your environment clean and free of pests"
                });

                writeSection(writer, "Chronic Conditions", new String[]{
                    "Follow a balanced diet rich in fruits, vegetables, and whole grains",
                    "Exercise regularly to improve heart and lung health",
                    "Stay hydrated and drink plenty of water throughout the day",
                    "Manage stress through relaxation techniques like meditation",
                    "Take medications as prescribed and regularly monitor symptoms",
                    "Get enough sleep to help your body repair and rejuvenate",
                    "Avoid smoking, excessive alcohol, and other harmful substances",
                    "Keep track of your health with regular check-ups and screenings",
                    "Stay socially active to maintain mental and emotional health",
                    "Follow your doctor’s advice for managing your chronic condition"
                });

                writeSection(writer, "Allergic Reactions", new String[]{
                    "Identify and avoid triggers (e.g., pollen, dust, pet dander)",
                    "Carry an epinephrine auto-injector if prescribed by your doctor",
                    "Take antihistamines to relieve allergy symptoms",
                    "Keep windows closed during pollen season to reduce exposure",
                    "Wash hands and change clothes after being in contact with allergens",
                    "Use air purifiers to reduce indoor allergens",
                    "Avoid smoking or secondhand smoke, which can worsen allergies",
                    "Clean carpets and bedding regularly to reduce dust mites",
                    "Keep pets clean and bathe them regularly to reduce allergens",
                    "Consult with an allergist for personalized treatment"
                });

                writeSection(writer, "Respiratory Infections", new String[]{
                    "Avoid close contact with infected individuals",
                    "Wash your hands frequently to reduce the spread of germs",
                    "Wear a mask if you are in public during a respiratory outbreak",
                    "Use a humidifier to help with dry air and ease breathing",
                    "Rest and drink plenty of fluids to aid recovery",
                    "Avoid smoking or exposure to secondhand smoke",
                    "Seek medical attention if symptoms worsen or become severe",
                    "Use cough drops or lozenges to soothe a sore throat",
                    "Avoid cold air and extreme temperature changes",
                    "Keep your living space clean and disinfect commonly touched surfaces"
                });

                writeSection(writer, "Skin Conditions", new String[]{
                    "Keep your skin clean and moisturized",
                    "Avoid harsh soaps and hot water that can dry out your skin",
                    "Use sunscreen to protect your skin from harmful UV rays",
                    "Avoid scratching or picking at rashes or wounds",
                    "Use over-the-counter creams or ointments for common skin conditions",
                    "Stay hydrated by drinking plenty of water",
                    "Wear loose clothing to prevent irritation on sensitive skin",
                    "Seek medical advice for persistent or severe skin conditions",
                    "Practice good hygiene and wash your hands regularly",
                    "Avoid exposure to allergens or irritants that may worsen skin conditions"
                });

                writeSection(writer, "Autoimmune Diseases", new String[]{
                    "Follow a balanced diet to support your immune system",
                    "Get regular exercise to maintain strength and flexibility",
                    "Manage stress with relaxation techniques such as meditation",
                    "Take prescribed medications as directed by your healthcare provider",
                    "Keep track of your symptoms and report changes to your doctor",
                    "Get enough sleep to allow your body to recover",
                    "Avoid smoking, as it can aggravate autoimmune conditions",
                    "Join support groups to connect with others who understand your condition",
                    "Stay hydrated to help maintain overall health",
                    "Regularly monitor and manage any other health conditions that may impact your immune system"
                });

                System.out.println("Health tips written to " + FILE_NAME);

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error creating tips file: " + e.getMessage());
            }
        }
    }
    private void writeSection(FileWriter writer, String header, String[] tips) throws IOException {
        writer.write(header + ":\n");
        for (int i = 0; i < tips.length; i++) {
            writer.write((i + 1) + ". " + tips[i] + "\n");
        }
        writer.write("\n");
    }
    private void displayHealthTips(String selected, JTextArea tipsArea) {
        try (Scanner fileScanner = new Scanner(new File(FILE_NAME))) {
            boolean found = false;
            StringBuilder tips = new StringBuilder();
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.contains(selected)) {
                    found = true;
                    tips.append(line).append("\n");
                } else if (found && line.isEmpty()) {
                    break;
                } else if (found) {
                    tips.append(line).append("\n");
                }
            }
            if (!found) {
                tipsArea.setText("Health tips for " + selected + " not found.");
            } else {
                tipsArea.setText(tips.toString());
            }
        } catch (IOException e) {
            tipsArea.setText("Error reading health tips file: " + e.getMessage());
        }
    }
}