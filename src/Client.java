import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {
    public Socket socket;
    public InputStreamReader in;
    public BufferedReader bf;
    public PrintWriter out;
    public String receivedData;
    public int numOfQuestions;

    public Client(Socket socket) throws IOException {
        receivedData = null;
        this.socket = socket;
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new InputStreamReader(socket.getInputStream());
        bf = new BufferedReader(in);
    }

    public void getData() throws IOException {
        while(receivedData == null) {
            receivedData = bf.readLine();
        }
    }

    public void sendData() throws IOException {
        String str = Integer.toString(HostPanel.idnum.getAndIncrement()) + "@" + Integer.toString(HostPanel.questionNum.getAcquire()) +
                      "@" + HostPanel.stringSend;
        out.println(str);
    }

    @Override
    public void run() {
        try {
            sendData();
            getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(receivedData);
    }
}