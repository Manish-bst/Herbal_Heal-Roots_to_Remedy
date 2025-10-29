package com.mycompany.herbal_heal_roots_to_remedy;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MainMenu extends JFrame {
    private JTabbedPane tabbedPane;
    private UserRegistration userRegistration;
    private SymptomChecker symptomChecker;
    private DiseaseInformation diseaseInformation;
    private HerbalRemedyInformation herbalRemedyInformation;
    private HealthTips healthTips;
    private PersonalHealthCarePlan personalHealthCarePlan;
    private UserReviewSystem userReviewSystem;
    private JLabel welcomeLabel;
    private JLabel messageLabel;

    public MainMenu() {
        super("Herbal Heal Roots to Remedy");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create welcome label
        welcomeLabel = new JLabel();
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setForeground(Color.decode("#008000")); // Dark green color

        // Create message label
        messageLabel = new JLabel();
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        messageLabel.setForeground(Color.decode("#333333")); // Dark gray color

        // Create panel for welcome and message labels
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.add(welcomeLabel);
        welcomePanel.add(messageLabel);
        welcomePanel.setBackground(Color.decode("#C9E4CA")); // Light greenish color

        // Add welcome panel to north of frame
        add(welcomePanel, BorderLayout.NORTH);

        // Create tabs
        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);

        // Create login panel
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBackground(Color.decode("#C6E2B5")); // Light greenish color
        userRegistration = new UserRegistration();
        userRegistration.createLoginPanel(loginPanel);
        tabbedPane.addTab("Login", loginPanel);

        // Create symptom checker panel
        symptomChecker = new SymptomChecker();
        symptomChecker.createSymptomCheckerPanel(tabbedPane);
        tabbedPane.setBackgroundAt(1, Color.decode("#F7DC6F")); // Light orangeish color

        // Create disease information panel
        diseaseInformation = new DiseaseInformation();
        diseaseInformation.createDiseaseInformationPanel(tabbedPane);
        tabbedPane.setBackgroundAt(2, Color.decode("#87CEEB")); // Light blueish color

        // Create herbal remedy information panel
        herbalRemedyInformation = new HerbalRemedyInformation();
        herbalRemedyInformation.createHerbalRemedyInformationPanel(tabbedPane);
        tabbedPane.setBackgroundAt(3, Color.decode("#C9E4CA")); // Light greenish color

        // Create health tips panel
        healthTips = new HealthTips();
        healthTips.createHealthTipsPanel(tabbedPane);
        tabbedPane.setBackgroundAt(4, Color.decode("#F2C464")); // Light orangeish color

        // Create personal health care plan panel
        personalHealthCarePlan = new PersonalHealthCarePlan();
        personalHealthCarePlan.createPersonalHealthCarePlanPanel(tabbedPane);
        tabbedPane.setBackgroundAt(5, Color.decode("#ADD8E6")); // Light blueish color

        // Create user review system panel
        userReviewSystem = new UserReviewSystem();
        userReviewSystem.createUserReviewSystemPanel(tabbedPane);
        tabbedPane.setBackgroundAt(6, Color.decode("#C5C3C5")); // Light grayish color

        // Add action listener to login button
       // Add action listener to login button
userRegistration.getLoginButton().addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (userRegistration.isLoggedIn()) {
            tabbedPane.setEnabledAt(1, true);
            tabbedPane.setEnabledAt(2, true);
            tabbedPane.setEnabledAt(3, true);
            tabbedPane.setEnabledAt(4, true);
            tabbedPane.setEnabledAt(5, true);
            tabbedPane.setEnabledAt(6, true);
            tabbedPane.setEnabledAt(0, false); // Disable login tab

            // Display welcome message
            welcomeLabel.setText("Welcome " + userRegistration.getUsernameField().getText());
            messageLabel.setText("Herbal Heal – Roots to Remedy is a project that aims to aware the youths of today about herbal herbs and various health tips");

            // Add logout button
            JButton logoutButton = new JButton("Logout");
            logoutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    userRegistration.setLoggedIn(false);
                    tabbedPane.setEnabledAt(0, true); // Enable login tab
                    tabbedPane.setEnabledAt(1, false);
                    tabbedPane.setEnabledAt(2, false);
                    tabbedPane.setEnabledAt(3, false);
                    tabbedPane.setEnabledAt(4, false);
                    tabbedPane.setEnabledAt(5, false);
                    tabbedPane.setEnabledAt(6, false);
                    welcomeLabel.setText("");
                    messageLabel.setText("");
                }
            });
            add(logoutButton, BorderLayout.SOUTH);
        }
    }
});
        // Disable tabs initially
        tabbedPane.setEnabledAt(1, false);
        tabbedPane.setEnabledAt(2, false);
        tabbedPane.setEnabledAt(3, false);
        tabbedPane.setEnabledAt(4, false);
        tabbedPane.setEnabledAt(5, false);
        tabbedPane.setEnabledAt(6, false);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}