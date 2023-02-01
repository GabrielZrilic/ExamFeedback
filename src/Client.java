import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {
    public Socket socket;
    public boolean running;
    public InputStreamReader in;
    public BufferedReader bf;
    public PrintWriter out;
    public String receivedData;

    public Client(Socket socket) throws IOException {
        receivedData = "data not received";
        this.socket = socket;
        running = true;
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new InputStreamReader(socket.getInputStream());
        bf = new BufferedReader(in);
        HostPanel.idnum.incrementAndGet();
    }

    public void getData() throws IOException {
        receivedData = bf.readLine();
    }

    public void sendData(String str) throws IOException {
        out.println(str);
    }

    @Override
    public void run() {
        try {
            sendData(HostPanel.stringSend.get());
            // System.out.println(receivedData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(receivedData);
    }
}