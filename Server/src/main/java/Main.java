import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Main {
    protected static Map<Integer, User> users = new HashMap<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        CreaterFiles createrFiles = new CreaterFiles();
        Logics logics = new Logics();
        Thread setFiles = new Thread(() -> {
            createrFiles.crateFileLog();
            createrFiles.crateFileSettings();
        });
        setFiles.start();
        setFiles.join();
        setFiles.isAlive();


        try (ServerSocket serverSocket = new ServerSocket(logics.readFileForGetPort())) {
            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.print("Новое подключение - " + clientSocket.getPort() + ", имя - ");

                    new Thread(() -> {
                        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                            User user = new User(clientSocket, out);
                            String name = in.readLine();
                            user.setName(name);
                            users.put(clientSocket.getPort(), user);
                            System.out.println(user.getName());
                            logics.sendMessages(clientSocket);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}