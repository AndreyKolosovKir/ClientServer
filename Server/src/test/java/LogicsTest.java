import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class LogicsTest {
    Logics logics = new Logics();
    CreaterFiles createrFiles = new CreaterFiles();

    @Test
    public void readFileForGetPortTest() {
        createrFiles.crateFileLog();
        int result = logics.readFileForGetPort();
        int expected = 34555;

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void writeTest() throws IOException {
        createrFiles.crateFileSettings();
        logics.write("messageTest", "nameTest");
        boolean expected = true;
        boolean result;
        List<Character> list = new ArrayList<>();
        FileReader reader = new FileReader("C:\\Users\\User\\IdeaProjects\\clientServer2\\Server\\file.log");
        int c;
        while ((c = reader.read()) != -1) {
            list.add((char) c);
        }
        String textTestForResult = null;
        for (char x : list) {
            textTestForResult += x;
        }
        result = textTestForResult.contains("messageTest");

        Assertions.assertEquals(expected, result);
    }
}
