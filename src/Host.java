import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Host extends Thread {
    static ArrayList<Socket> connections = new ArrayList<Socket>();

    public ServerSocket serverSocket;
    public Socket socket;
    public boolean running = true;
    
    public void run() {
        try {
            serverSocket = new ServerSocket(0);
            HostPanel.hostnum.set(serverSocket.getLocalPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                socket = serverSocket.accept();
                new Client(socket).start();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            System.out.println("Client added");
        }
    } 
}
