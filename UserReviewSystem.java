package com.mycompany.herbal_heal_roots_to_remedy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class UserReviewSystem {
    private static final String FILE_NAME = "user_reviews.txt";

    public void createUserReviewSystemPanel(JTabbedPane tabbedPane) {
        JPanel reviewPanel = new JPanel();
        reviewPanel.setLayout(new BorderLayout());
        reviewPanel.setBackground(Color.decode("#C9E4CA")); // Light greenish color

        // Write Review Panel
        JPanel writePanel = new JPanel(new BorderLayout(10, 10));
        writePanel.setBackground(Color.decode("#F7DC6F")); // Light orangeish color

        JTextArea reviewTextArea = new JTextArea(5, 40);
        reviewTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        reviewTextArea.setForeground(Color.decode("#333333")); // Dark gray color
        JScrollPane scrollPane = new JScrollPane(reviewTextArea);

        JPanel ratingPanel = new JPanel();
        ratingPanel.setBackground(Color.decode("#F7DC6F")); // Light orangeish color
        ratingPanel.add(new JLabel("Rating (1-5):"));
        ratingPanel.add(new JLabel("Rating (1-5):"));
        JComboBox<Integer> ratingComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        ratingPanel.add(ratingComboBox);

        JButton submitButton = new JButton("Submit Review");
        submitButton.setBackground(Color.decode("#4CAF50")); // Green color
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String review = reviewTextArea.getText().trim();
                int rating = (int) ratingComboBox.getSelectedItem();

                if (review.isEmpty()) {
                    JOptionPane.showMessageDialog(reviewPanel, "Review cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
                    fw.write(review + " - Rating: " + rating + "/5\n");
                    JOptionPane.showMessageDialog(reviewPanel, "Review saved successfully!");
                    reviewTextArea.setText("");
                    ratingComboBox.setSelectedIndex(0);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(reviewPanel, "Error saving review: " + ex.getMessage());
                }
            }
        });

        writePanel.add(new JLabel("Write your review:"), BorderLayout.NORTH);
        writePanel.add(scrollPane, BorderLayout.CENTER);
        writePanel.add(ratingPanel, BorderLayout.SOUTH);
        writePanel.add(submitButton, BorderLayout.EAST);

        // Read Review Panel
        JPanel readPanel = new JPanel(new BorderLayout());
        readPanel.setBackground(Color.decode("#F7DC6F")); // Light orangeish color
        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        displayArea.setForeground(Color.decode("#333333")); // Dark gray color
        JScrollPane displayScroll = new JScrollPane(displayArea);
        JButton loadButton = new JButton("Load Reviews");
        loadButton.setBackground(Color.decode("#4CAF50")); // Green color
        loadButton.setForeground(Color.WHITE);
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArea.setText("");
                File file = new File(FILE_NAME);

                if (!file.exists()) {
                    displayArea.setText("No reviews available yet.");
                    return;
                }
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        int rating = extractRating(line);
                        displayArea.append(line + "\n");
                        displayArea.append("Rating: " + "*".repeat(rating) + "\n\n");
                    }
                } catch (IOException ex) {
                    displayArea.setText("Error reading reviews: " + ex.getMessage());
                }
            }
        });
        readPanel.add(displayScroll, BorderLayout.CENTER);
        readPanel.add(loadButton, BorderLayout.SOUTH);
        JTabbedPane tabbedPane1 = new JTabbedPane();
        
        tabbedPane1.addTab("Write Review", writePanel);
        tabbedPane1.addTab("Read Reviews", readPanel);
        reviewPanel.add(tabbedPane1, BorderLayout.CENTER);
        tabbedPane.addTab("User   Review System", reviewPanel);
    }
    private int extractRating(String line) {
        try {
            String[] parts = line.split(" - Rating: ");
            if (parts.length > 1) {
                String[] ratingParts = parts[1].split("/");
                return Integer.parseInt(ratingParts[0].trim());
            }
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }
}