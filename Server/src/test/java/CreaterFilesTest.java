import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class CreaterFilesTest {
    CreaterFiles createrFiles = new CreaterFiles();

    @Test
    public void crateFileLogTest() throws IOException {
        File file = new File("C:\\Users\\User\\IdeaProjects\\clientServer2\\Server\\src\\test\\fileTest.log");
        file.createNewFile();

        boolean expected = file.canExecute();

        createrFiles.crateFileLog();
        File file2 = new File("C:\\Users\\User\\IdeaProjects\\clientServer2\\file.log");
        boolean result = file2.canExecute();

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void crateFileSettingsTest() throws IOException {
        File file = new File("C:\\Users\\User\\IdeaProjects\\clientServer2\\Server\\src\\test\\settingsTest.txt");
        file.createNewFile();

        boolean expected = file.canExecute();

        createrFiles.crateFileLog();
        File file2 = new File("C:\\Users\\User\\IdeaProjects\\clientServer2\\settings.txt");
        boolean result = file2.canExecute();

        Assertions.assertEquals(expected, result);
    }

}
