package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

public class PasswordGeneratorApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Password Generator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(6, 1));

            JLabel lengthLabel = new JLabel("Enter the password length:");
            JTextField lengthTextField = new JTextField();
            lengthTextField.setPreferredSize(new Dimension(200, 25)); // Ustaw rozmiar pola tekstowego
            JPanel lengthPanel = new JPanel();
            lengthPanel.add(lengthLabel);
            lengthPanel.add(lengthTextField);

            JCheckBox lowercaseCheckBox = new JCheckBox("Use Lowercase Letters");
            JCheckBox uppercaseCheckBox = new JCheckBox("Use Uppercase Letters");
            JCheckBox digitsCheckBox = new JCheckBox("Use Digits");
            JCheckBox specialCharsCheckBox = new JCheckBox("Use Special Characters");

            JButton generateButton = new JButton("Generate Password");

            JTextArea resultTextArea = new JTextArea(5, 20);
            resultTextArea.setEditable(false);
            resultTextArea.setWrapStyleWord(true);
            resultTextArea.setLineWrap(true);
            JScrollPane scrollPane = new JScrollPane(resultTextArea);

            panel.add(lengthPanel);
            panel.add(lowercaseCheckBox);
            panel.add(uppercaseCheckBox);
            panel.add(digitsCheckBox);
            panel.add(specialCharsCheckBox);
            panel.add(generateButton);
            panel.add(scrollPane);

            frame.add(panel);

            generateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String lengthInput = lengthTextField.getText();
                    if (lengthInput.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid password length.");
                        return; // Nie kontynuuj, jeśli pole jest puste
                    }

                    int passwordLength = Integer.parseInt(lengthInput);
                    boolean useLowercase = lowercaseCheckBox.isSelected();
                    boolean useUppercase = uppercaseCheckBox.isSelected();
                    boolean useDigits = digitsCheckBox.isSelected();
                    boolean useSpecialChars = specialCharsCheckBox.isSelected();

                    String generatedPassword = generatePassword(passwordLength, useLowercase, useUppercase, useDigits, useSpecialChars);
                    resultTextArea.setText("Generated Password: " + generatedPassword);
                }
            });

            frame.setVisible(true);
        });
    }

    public static String generatePassword(int length, boolean useLowercaseLetters, boolean useUppercaseLetters, boolean useDigits, boolean useSpecialCharacters) {
        String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "0123456789";
        String specialCharacters = "!@#$%^&*()_+-=[]{}|;:<>,.?";

        String availableCharacters = "";

        if (useLowercaseLetters) {
            availableCharacters += lowercaseLetters;
        }
        if (useUppercaseLetters) {
            availableCharacters += uppercaseLetters;
        }
        if (useDigits) {
            availableCharacters += digits;
        }
        if (useSpecialCharacters) {
            availableCharacters += specialCharacters;
        }

        if (availableCharacters.isEmpty()) {
            JOptionPane.showMessageDialog(null, "You must select at least one option (lowercase letters, uppercase letters, digits, or special characters).");
            System.exit(1);
        }

        SecureRandom randomGenerator = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = randomGenerator.nextInt(availableCharacters.length());
            char randomChar = availableCharacters.charAt(randomIndex);
            password.append(randomChar);
        }

        return password.toString();
    }
}