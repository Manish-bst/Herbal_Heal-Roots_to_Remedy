package com.mycompany.herbal_heal_roots_to_remedy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserRegistration {
    private static final String FILE_NAME = "users.txt";
    private boolean isLoggedIn = false;

    private JPanel loginPanel;
    private JButton loginButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton registerButton;

    public void createLoginPanel(JPanel loginPanel) {
        this.loginPanel = loginPanel;

        // Create username label and field
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);

        // Create password label and field
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        // Create confirm password label and field
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordField = new JPasswordField(20);

        // Create register button
        registerButton = new JButton("Register");
        registerButton.addActionListener(new RegisterButtonListener());

        // Create login button
        loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginButtonListener());

        // Add components to panel
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(confirmPasswordLabel);
        loginPanel.add(confirmPasswordField);
        loginPanel.add(registerButton);
        loginPanel.add(loginButton);
    }

    private class RegisterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (password.equals(confirmPassword)) {
 try {
                    Scanner fileScanner = new Scanner(new File(FILE_NAME));

                    while (fileScanner.hasNextLine()) {
                        String line = fileScanner.nextLine();
                        String[] credentials = line.split(":");

                        if (credentials[0].equals(username)) {
                            JOptionPane.showMessageDialog(loginPanel, "Username already exists. Please choose a different username.");
                            return;
                        }
                    }

                    fileScanner.close();

                    FileWriter writer = new FileWriter(FILE_NAME, true);
                    writer.write(username + ":" + password + "\n");
                    writer.close();
                    JOptionPane.showMessageDialog(loginPanel, "Registration successful!");
                    isLoggedIn = true;
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(loginPanel, "Error registering user: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(loginPanel, "Passwords do not match. Please try again.");
            }
        }
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            try {
                Scanner fileScanner = new Scanner(new File(FILE_NAME));

                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    String[] credentials = line.split(":");

                    if (credentials[0].equals(username) && credentials[1].equals(password)) {
                        JOptionPane.showMessageDialog(loginPanel, "Login successful!");
                        isLoggedIn = true;
                        return;
                    }
                }
                JOptionPane.showMessageDialog(loginPanel, "Invalid username or password. Please try again.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(loginPanel, "Error logging in: " + ex.getMessage());
            }
        }
    }
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
public JTextField getUsernameField() {
    return usernameField;
}
public void setLoggedIn(boolean isLoggedIn) {
    this.isLoggedIn = isLoggedIn;
}
    public JButton getLoginButton() {
        return loginButton;
    }
}