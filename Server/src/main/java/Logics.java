import org.joda.time.DateTime;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Logics {
    public int readFileForGetPort() {
        File file = new File("settings.txt");
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

    public static void write(String message, String name) {
        DateTime dateTime = new DateTime();
        String messageResult = ("[" + dateTime + "//" + name + "//" + message + "]\r\n");
        byte[] buffer = messageResult.getBytes();
        File file = new File("file.log");
        try (FileOutputStream out = new FileOutputStream(file, true);
             BufferedOutputStream buf = new BufferedOutputStream(out)) {
            buf.write(buffer, 0, buffer.length);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void sendMessages(Socket clientSocket) {
        try (Scanner inMess = new Scanner(clientSocket.getInputStream())) {
            while (true) {
                if (inMess.hasNext()) {
                    String message = inMess.nextLine();
                    for (Map.Entry<Integer, User> entry : Main.users.entrySet()) {
                        if (!message.isEmpty()) {
                            String messageOut = entry.getValue().getName() + " : " + message;
                            entry.getValue().outMessage(messageOut);
                            write(message, entry.getValue().getName());
                            System.out.println("Отправлено");
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
