package FileHandling;

import java.io.File;
import java.util.ArrayList;

public class FileHandler {

    String text;

    public FileHandler() {

        ArrayList<String> testing = new ArrayList<>();
        testing.add("This is the first test for the dvorak program. Please don't remove for historic reasons. Ty");
        this.text = testing.get(0);
    }

    public String getText() {
        return this.text;
    }


}
