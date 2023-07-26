package main;

import FileHandling.FileHandler;

import java.nio.channels.ScatteringByteChannel;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        //new GUI(new FileHandler());
        String filePath = "src/files/HelloWorld.txt";
        new GUI(new FileHandler(filePath));

        // Assuming you have the "example.txt" file inside the "files" folder at the same level as the "src" folder

        //FileHandler fileHandler = new FileHandler(filePath);
        //String fileContent = fileHandler.getText();
        //System.out.println(fileContent);


    }
}