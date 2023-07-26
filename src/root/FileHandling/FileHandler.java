package FileHandling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class FileHandler {

    String text;

    public FileHandler() {

        ArrayList<String> testing = new ArrayList<>();
        testing.add("This is the first test for the dvorak program. Please don't remove for historic reasons. Ty");
        this.text = testing.get(0);

    }

    public FileHandler(String filePath) {
        this.text = readTextFromFile(filePath);
    }

    public String getText() {
        return this.text;
    }


    private String readTextFromFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

}
