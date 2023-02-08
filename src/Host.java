import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

// Making a host thread. While running wait for more connections
// Stop when !isRunning (changed by HostPanel)
public class Host extends Thread {
    static ArrayList<Socket> connections = new ArrayList<Socket>();

    public ServerSocket serverSocket;
    public Socket socket;
    public boolean isRunning;
    
    public void run() {
        isRunning = true;
        try {
            serverSocket = new ServerSocket(0);
            HostPanel.hostnum.set(serverSocket.getLocalPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (isRunning) {
            try {
                socket = serverSocket.accept();
                new Client(socket).start();
            } catch (IOException e) { }
            System.out.println("Client added");
        }
    } 
}
