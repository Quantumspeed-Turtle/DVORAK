package main;

import FileHandling.FileHandler;

import java.nio.channels.ScatteringByteChannel;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Main {

    private static int counter = 0;
    private static int mistakes = 0;

    public static void main(String[] args) {
        new GUI(new FileHandler());

        //start();
        //tart(new FileHandler(), new Scanner(System.in));
        //String[] textToWords = text.split("\\s+");
    }

    public static void start() {
        System.out.println("Press Enter to start ...");
        try {
            System.in.read(); // Wait for Enter key to start
        } catch (IOException e) {
            e.printStackTrace();
        }

        String output = "This is the first test for the dvorak program. Please don't remove for historic reasons.";
        String[] outputTextWords = output.split("\\s+");

        int counter = 0;
        StringBuilder currentWordInput = new StringBuilder();
        System.out.print(outputTextWords[counter]);

        try {
            while (counter < outputTextWords.length) {
                char inputChar = (char) System.in.read();

                if (inputChar == KeyEvent.VK_ENTER) {
                    String userInput = currentWordInput.toString().trim();
                    if (userInput.equals(outputTextWords[counter])) {
                        System.out.print(" ");
                    } else {
                        System.out.println(" fail");
                    }
                    counter++;
                    currentWordInput.setLength(0);
                    if (counter < outputTextWords.length) {
                        System.out.print(outputTextWords[counter]);
                    }
                } else {
                    currentWordInput.append(inputChar);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






    public static boolean validate(char displayedText, char userArray){

        char space = ' ';
        return displayedText == userArray;

    }

    public static boolean validate(String displayedText, String userInput) {
        if (displayedText == null || userInput == null) {
            throw new NoSuchElementException("Text not found.");
        }


        char[] display = displayedText.toCharArray();
        char[] user = userInput.toCharArray();

        for (int i = 0; i < user.length; i++) {
            if (display[i] != user[i]) {
                return false; // The input is incorrect up to charIndex
            }
        }

        return true; // The input up to charIndex is correct
    }


    public static double calculateWPM(String text) {

        if(text == null || text.isEmpty()) {
            System.out.println("No input detected");
        }

        assert text != null;
        String[] textToWords = text.split("\\s+");

        return textToWords.length;
    }







    /*
    public static void start(FileHandler displayedText, Scanner scanner) {
        System.out.println("Press Enter to start ...");
        scanner.nextLine();

        String userInput = "";
        long startTime = 0;


        while (displayedText.getText().length() != userInput.length()) {
            startTime = System.currentTimeMillis();

            System.out.println(displayedText.getText() + "\n");
            if(scanner.hasNextLine()) {
                userInput = scanner.nextLine();
            } else {
                System.out.println("not input");
                scanner.close();
            }
            boolean isValid = validate(displayedText.getText(), userInput);
            if (!isValid || displayedText.getText().length() != userInput.length()) {
                System.out.println("Wrong input. Try again.");
                userInput = scanner.nextLine();

            }
            System.out.println(counter);
            counter++;
        }


        long endTime = System.currentTimeMillis();
        long time = (endTime - startTime) / 1000;
        double WPM = Math.round((calculateWPM(userInput) / time) * 60);

        System.out.println("First course finished, you needed " + time + " seconds and WPM: " + WPM);

    }
    */

    /*
    public static void start(FileHandler displayedText, Scanner scanner) {
        System.out.println("Press Enter to start ...");
        scanner.nextLine();

        char[] userArray;
        String userInput = "";
        long startTime = 0;

        char[] charText = displayedText.getText().toCharArray();

        while (displayedText.getText().length() != userInput.length()) {
            startTime = System.currentTimeMillis();
            System.out.println(displayedText.getText() + "\n");

            userInput = scanner.nextLine();
            userArray = userInput.toCharArray();


            try{
                boolean isValid = validate(charText[counter], userArray[counter]);
                if (!isValid || displayedText.getText().length() != userInput.length()) {
                    System.out.println("Wrong input. Expected: " + charText[counter] + ", got: " + userArray[counter]);
                    mistakes ++;
                    break;
                }
            }catch (ArrayIndexOutOfBoundsException e ) {
                break;
            }



            System.out.println(counter);
            counter++;
        }


        long endTime = System.currentTimeMillis();
        long time = (endTime - startTime) / 1000;
        double WPM = Math.round((calculateWPM(userInput) / time) * 60);

        if(time > 0  || displayedText.getText().length() == userInput.length()) {
            System.out.println("First course finished, you needed " + time + " seconds and WPM: " + WPM);
        } else {
            System.out.println("your either flash or a mistake");
        }

    }

     */
}
