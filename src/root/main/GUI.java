package main;

import FileHandling.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class GUI extends JFrame {

    private JLabel wordLabel;
    private JTextField userInputField;
    private String output;
    private String[] outputTextWords;
    private int counter;
    private long startTime;
    private JLabel timeLabel;

    private String currentExpectedWord;

    public GUI(FileHandler displayedText) {

        this.output = displayedText.getText();
        this.outputTextWords = output.split("\\s+");
        this.counter = 0;
        this.currentExpectedWord = outputTextWords[counter];

        initializeComponents();
        centerWindowOnScreen();

        // Set the size of the JFrame manually after initializing components
        setSize(400, 200);

        // Start the timer when the program starts
        startTime = System.currentTimeMillis();
        this.setVisible(true);
    }

    private void initializeComponents() {
        setTitle("Word Comparison");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1)); // Four rows: wordLabel, userInputField, timeLabel, and checkButton

        wordLabel = new JLabel(output);
        wordLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the word horizontally
        add(wordLabel);

        userInputField = new JTextField(20);
        add(userInputField);

        timeLabel = new JLabel("");
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the time label horizontally
        add(timeLabel);

        userInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    String userInput = userInputField.getText().trim();
                    if (userInput.equals(currentExpectedWord)) {
                        userInputField.setText(""); // Clear the text field for the next word

                        // Move to the next word when the correct word is entered
                        counter++;
                        if (counter < outputTextWords.length) {
                            currentExpectedWord = outputTextWords[counter]; // Update the expected word
                            wordLabel.setText(getFormattedWordLabel());
                        } else {
                            userInputField.setEditable(false);
                            userInputField.removeKeyListener(this); // Disable further input handling

                            // Calculate and display the time needed to finish
                            long endTime = System.currentTimeMillis();
                            long totalTime = endTime - startTime;
                            timeLabel.setText("Time needed: " + totalTime /1000 + " Seconds");
                        }
                    } else {
                        // Incorrect input, don't move to the next word
                        // You can provide feedback to the user here if needed.
                    }
                }
            }
        });

    }

    private String getFormattedWordLabel() {
        // Highlight the current expected word in blue
        StringBuilder formattedText = new StringBuilder();
        for (int i = 0; i < outputTextWords.length; i++) {
            if (i == counter) {
                formattedText.append("<font color='blue'>").append(outputTextWords[i]).append("</font> ");
            } else {
                formattedText.append(outputTextWords[i]).append(" ");
            }
        }
        return "<html>" + formattedText.toString().trim() + "</html>";
    }

    private void centerWindowOnScreen() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
    }


}
