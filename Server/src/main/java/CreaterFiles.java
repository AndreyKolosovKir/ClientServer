import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreaterFiles {
    public void crateFileLog() {
        File file = new File("file.log");
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void crateFileSettings() {
        File file = new File("settings.txt");
        try {
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream("settings.txt");
            fileOutputStream.write("34555".getBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
