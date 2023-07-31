import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientTest {

    ReadFile readFile = new ReadFile();

    @Test
    public void readFileForGetPortTest() {
        File file = new File("C:\\Users\\User\\IdeaProjects\\clientServer2\\Client0\\settingsTest.txt");
        try {
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\User\\IdeaProjects\\clientServer2\\Client0\\settingsTest.txt");
            fileOutputStream.write("34555".getBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        List<Integer> portIntTestExpected = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            int i;
            while ((i = fileInputStream.read()) != -1) {
                portIntTestExpected.add(i - '0');
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        int resultTest = 0;
        for (int i : portIntTestExpected) {
            resultTest = resultTest * 10 + i;
        }
        int expected = resultTest;

        int result = readFile.readFileForGetPort();

        Assertions.assertEquals(expected, result);
    }
}
