import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends Thread {
    public Socket socket;
    public boolean running;
    public InputStreamReader in;
    public BufferedReader bf;
    public ObjectOutputStream out;
    public String receivedData;

    public Client(Socket socket) throws IOException {
        receivedData = "data not received";
        this.socket = socket;
        running = true;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new InputStreamReader(socket.getInputStream());
        bf = new BufferedReader(in);
        sendData(HostPanel.idnum.toString());
        HostPanel.idnum.incrementAndGet();
    }

    public void getData() throws IOException {
        receivedData = bf.readLine();
    }

    public void sendData(String str) throws IOException {
        out.writeObject(str);
    }

    @Override
    public void run() {
        try {
            getData();
            System.out.println("written");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(receivedData);
    }
}