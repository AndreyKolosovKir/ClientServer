import java.io.PrintWriter;
import java.net.Socket;

public class User {
    protected String name;
    protected PrintWriter message;
    protected Socket socket;

    public User(Socket socket, PrintWriter message) {
        this.message = message;
        this.socket = socket;
    }

    public void outMessage(String mes) {
        message.println(mes);
        message.flush();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Socket getSocket() {
        return socket;
    }

    public String toString() {
        return "Имя - " + getName() + ", сокет - " + getSocket();
    }
}
