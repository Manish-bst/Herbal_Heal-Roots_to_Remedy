package com.mycompany.herbal_heal_roots_to_remedy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HerbalRemedyInformation {
    private static final String WIKIPEDIA_URL = "https://en.wikipedia.org/wiki/";

    public void createHerbalRemedyInformationPanel(JTabbedPane tabbedPane) {
        JPanel herbalRemedyPanel = new JPanel();
        herbalRemedyPanel.setLayout(new BorderLayout());
        herbalRemedyPanel.setBackground(Color.decode("#C9E4CA")); // Light greenish color

        // Top input panel
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.setBackground(Color.decode("#F7DC6F")); // Light orangeish color

        JLabel herbLabel = new JLabel("Enter Herb Name:");
        herbLabel.setForeground(Color.decode("#008000")); // Dark green color
        JTextField herbField = new JTextField(20);
        JButton fetchHerbButton = new JButton("Fetch Herb Information");
        fetchHerbButton.setBackground(Color.decode("#4CAF50")); // Green color
        fetchHerbButton.setForeground(Color.WHITE);

        topPanel.add(herbLabel);
        topPanel.add(herbField);
        topPanel.add(fetchHerbButton);

        // Result area
        JTextArea resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        resultTextArea.setLineWrap(true);
        resultTextArea.setWrapStyleWord(true);
        resultTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        resultTextArea.setForeground(Color.decode("#333333")); // Dark gray color

        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        herbalRemedyPanel.add(topPanel, BorderLayout.NORTH);
        herbalRemedyPanel.add(scrollPane, BorderLayout.CENTER);

        // Add action listener to fetch herb button
        fetchHerbButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String herbName = herbField.getText();
                if (!herbName.isEmpty()) {
                    resultTextArea.setText("Fetching data for: " + herbName + "...\n");
                    SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
                        @Override
                        protected String doInBackground() {
                            return getHerbInformation(herbName);
                        }
                        @Override
                        protected void done() {
                            try {
                                resultTextArea.setText(get());
                            } catch (Exception ex) {
                                resultTextArea.setText("Error: " + ex.getMessage());
                            }
                        }
                    };
                    worker.execute();
                } else {
                    resultTextArea.setText("Please enter a herb name.");
                }
            }
        });
        tabbedPane.addTab("Herbal Remedy Information", herbalRemedyPanel);
    }
    private String getHerbInformation(String herbName) {
        try {
            String wikipediaUrl = WIKIPEDIA_URL + herbName.replaceAll(" ", "_");
            return fetchHerbInfoFromWikipedia(wikipediaUrl);
        } catch (Exception e) {
            return "Error: Unable to fetch herb information. " + e.getMessage();
        }
    }
    private String fetchHerbInfoFromWikipedia(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements content = doc.select("div.mw-parser-output > p");
            if (!content.isEmpty()) {
                StringBuilder herbDescription = new StringBuilder();
                for (org.jsoup.nodes.Element p : content) {
                    String text = p.text().trim();
                    if (!text.isEmpty()) {
                        herbDescription.append(text).append("\n\n");
                        // Show only first 3 paragraphs to keep it concise
                        if (herbDescription.toString().split("\n\n").length >= 3) {
                            break;
                        }
                    }
                }
                return herbDescription.toString();
            }
            return "No detailed information found for this herb.";
        } catch (Exception e) {
            return "Error occurred while fetching from Wikipedia: " + e.getMessage();
        }  
    }
}