package com.mycompany.herbal_heal_roots_to_remedy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class DiseaseInformation {
    private static final String WIKIPEDIA_URL = "https://en.wikipedia.org/wiki/";

    public void createDiseaseInformationPanel(JTabbedPane tabbedPane) {
        JPanel diseaseInfoPanel = new JPanel();
        diseaseInfoPanel.setLayout(new BorderLayout());
        diseaseInfoPanel.setBackground(Color.decode("#C9E4CA")); // Light greenish color

        // Top input panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.setBackground(Color.decode("#F7DC6F")); // Light orangeish color

        JLabel diseaseLabel = new JLabel("Enter Disease Name:");
        diseaseLabel.setForeground(Color.decode("#008000")); // Dark green color
        JTextField diseaseField = new JTextField(20);
        JButton fetchButton = new JButton("Fetch Information");
        fetchButton.setBackground(Color.decode("#4CAF50")); // Green color
        fetchButton.setForeground(Color.WHITE);

        topPanel.add(diseaseLabel);
        topPanel.add(diseaseField);
        topPanel.add(fetchButton);

        // Result area
        JTextArea resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        resultTextArea.setLineWrap(true);
        resultTextArea.setWrapStyleWord(true);
        resultTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        resultTextArea.setForeground(Color.decode("#333333")); // Dark gray color

        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        diseaseInfoPanel.add(topPanel, BorderLayout.NORTH);
        diseaseInfoPanel.add(scrollPane, BorderLayout.CENTER);

        // Add action listener to fetch button
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String diseaseName = diseaseField.getText();
                if (!diseaseName.isEmpty()) {
                    fetchDiseaseInformationAsync(diseaseName, resultTextArea);
                } else {
                    JOptionPane.showMessageDialog(diseaseInfoPanel,
                            "Please enter a disease name.",
                            "Input Error",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        tabbedPane.addTab("Disease Information", diseaseInfoPanel);
    }
    // Asynchronous fetch using SwingWorker (Java 8 compatible)
    private void fetchDiseaseInformationAsync(String diseaseName, JTextArea resultTextArea) {
        resultTextArea.setText("Fetching information for: " + diseaseName + "...\n");

        SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() {
                return getDiseaseInformation(diseaseName);
            }
            @Override
            protected void done() {
                try {
                    String result = get();
                    resultTextArea.setText(result);
                } catch (Exception ex) {
                    resultTextArea.setText("Error fetching data: " + ex.getMessage());
                }
            }
        };
        worker.execute();
    }
    // Method to fetch disease info from Wikipedia
    public String getDiseaseInformation(String diseaseName) {
        try {
            String url = WIKIPEDIA_URL + diseaseName.replaceAll(" ", "_");
            Document doc = Jsoup.connect(url).get();
            Elements content = doc.select("div.mw-parser-output > p");

            if (!content.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (org.jsoup.nodes.Element p : content) {
                    String text = p.text().trim();
                    if (!text.isEmpty()) {
                        sb.append(text).append("\n\n");
                        if (sb.length() > 1000) break; // Limit output for readability
                    }
                }
                return sb.toString();
            }
            return "No detailed disease information found.";
        } catch (IOException e) {
            return "Error: Unable to fetch disease information.\n" + e.getMessage();
        }
    }
}