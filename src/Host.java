import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Host extends Thread {
    static ArrayList<Socket> connections = new ArrayList<Socket>();

    public ServerSocket serverSocket;
    public Socket socket;

    public void run() {
        try {
            serverSocket = new ServerSocket(0);                                 // You can't open a port below 1024
            System.out.println(serverSocket.getLocalPort());
        } catch (IOException e) {
            e.printStackTrace();
    
        }
        while (true) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            // new thread for a client
            new Client(socket).start();
            System.out.println("connected");
        }
    }
    
}