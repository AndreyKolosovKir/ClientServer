import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    protected static String host = "127.0.0.1";
    protected static Scanner scanner;
    protected static ReadFile readFile = new ReadFile();

    public static void main(String[] args) throws IOException, InterruptedException {

        scanner = new Scanner(System.in);
        Socket clientSocket = new Socket(host, readFile.readFileForGetPort());
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        AtomicBoolean flag = new AtomicBoolean(true);

        Thread outName = new Thread(() -> {
            String messageForUser = "Введите имя пользователя:";
            System.out.println(messageForUser);
            String name = scanner.next();
            out.println(name);
            System.out.println("В чате вы можете вводить сообщения и получать их");
        });
        outName.start();
        outName.join();
        outName.isAlive();

        new Thread(() -> {
            try {
                while (true) {
                    if (!flag.get()) {
                        in.close();
                        clientSocket.close();
                        break;
                    }
                    if (in.ready()) {
                        String messFormServer = in.readLine();
                        System.out.println(messFormServer);
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                if (scanner.hasNext()) {
                    String mess = scanner.nextLine();
                    if (mess.equalsIgnoreCase("/exit")) {
                        out.println(mess);
                        scanner.close();
                        out.close();
                        flag.set(false);
                        break;
                    }
                    out.println(mess);
                }
            }
        }).start();
    }
}