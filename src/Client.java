import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client extends Thread {
    public Socket socket;
    public boolean running;
    InputStreamReader in;
    BufferedReader bf;

    public Client(Socket socket) {
        this.socket = socket;
        running = true;
    }

    @Override
    public void run() {
        try {
            in = new InputStreamReader(socket.getInputStream());
            bf = new BufferedReader(in);

            System.out.println(bf.readLine());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
