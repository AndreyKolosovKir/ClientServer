import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    public static int readFileForGetPort() {
        File file = new File("C:\\Users\\User\\IdeaProjects\\clientServer2\\settings.txt");
        List<Integer> portInt = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            int i;
            while ((i = fileInputStream.read()) != -1) {
                portInt.add(i - '0');
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        int result = 0;
        for (int i : portInt) {
            result = result * 10 + i;
        }
        return result;
    }
}
