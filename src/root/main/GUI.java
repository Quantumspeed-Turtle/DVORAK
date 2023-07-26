package main;

import FileHandling.FileHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI extends JFrame {

    private JButton beginButton;
    private JPanel cardPanel;
    private JLabel wordLabel;
    private JTextField userInputField;
    private final String[] outputTextWords;
    private int counter;
    private long startTime;
    private JLabel timeLabel;

    private String currentExpectedWord;


    public GUI(FileHandler displayedText) {

        String output = displayedText.getText();
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
        setLayout(new BorderLayout());

        // Create a panel with CardLayout to switch between views
        cardPanel = new JPanel(new CardLayout());

        // View 1: Show the "Begin Program" button
        JPanel beginPanel = new JPanel();
        beginButton = new JButton("Begin Program");
        beginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTextPanel(); // Show the output text and input text field
            }
        });
        beginPanel.add(beginButton);

        // View 2: Show the output text and input text field
        JPanel textPanel = new JPanel(new BorderLayout());
        wordLabel = new JLabel(getFormattedWordLabel());
        wordLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the word horizontally
        textPanel.add(wordLabel, BorderLayout.NORTH);

        userInputField = new JTextField(0);



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

                            double minutes = totalTime / 1000.0 / 60.0; // Convert totalTime to minutes (in floating-point)
                            double wpm = counter / minutes;

                            timeLabel.setText("Time needed: " + totalTime /1000 + " Seconds and "+ Math.round(wpm) + " WPM " );
                        }
                    } else {
                        // Incorrect input, don't move to the next word
                        // You can provide feedback to the user here if needed.

                    }
                }
            }
        });
        textPanel.add(userInputField, BorderLayout.CENTER);

        timeLabel = new JLabel(""); // Initialize the timeLabel
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the time label horizontally
        textPanel.add(timeLabel, BorderLayout.SOUTH); // Add the timeLabel to the textPanel

        cardPanel.add(beginPanel, "beginPanel");
        cardPanel.add(textPanel, "textPanel");

        add(cardPanel, BorderLayout.CENTER);
    }

    private void showTextPanel() {
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.show(cardPanel, "textPanel");
        beginButton.setVisible(false); // Hide the "Begin Program" button
        startTime = System.currentTimeMillis(); // Start the timer when the program starts
        userInputField.setEditable(true); // Enable user input
        userInputField.requestFocus(); // Set focus on the input field
    }

    private String getFormattedWordLabel() {
        // Highlight the current expected word in blue, and already entered words in blue
        StringBuilder formattedText = new StringBuilder();
        for (int i = 0; i < outputTextWords.length; i++) {
            if (i < counter) {
                // Word has been correctly entered
                formattedText.append("<font color='blue'>").append(outputTextWords[i]).append("</font> ");
            } else if (i == counter) {
                // Word is the current expected word
                formattedText.append("<font color='green'>").append(outputTextWords[i]).append("</font> ");
            } else {
                // Word has not been entered yet
                formattedText.append(outputTextWords[i]).append(" ");
            }
        }
        return "<html>" + formattedText.toString().trim() + "</html>";
    }


    private void centerWindowOnScreen() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x-200, y-100);
    }


}
