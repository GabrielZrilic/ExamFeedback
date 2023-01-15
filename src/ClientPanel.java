import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientPanel extends JPanel implements ActionListener {
    public int port; 
    public JTextField portInput, nameInput;
    public JButton joinButton;
    public Socket connection;
    public PrintWriter pr;
    public User data;

    public ClientPanel() {
        portInput = new JTextField(10);
        nameInput = new JTextField(10);
        joinButton = new JButton("Join");

        this.add(nameInput);
        this.add(portInput);
        this.add(joinButton);

        addListener();
    }

    private void addListener() {
        nameInput.addActionListener(this);
        portInput.addActionListener(this);
        joinButton.addActionListener(this);
    }

    public void connecting() throws IOException {
        port = Integer.parseInt(portInput.getText());
        connection = new Socket(InetAddress.getLocalHost(), port);
        pr = new PrintWriter(connection.getOutputStream());
        data = new User();
        System.out.println("Connected to host");
    }

    public void sendData(String data) {
        pr.println(data);
        pr.flush();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == nameInput) {
            portInput.requestFocus();
            System.out.println(nameInput.getText());
        }else if(e.getSource() == portInput) {
            try {
                connecting();
                data.userName = nameInput.getText();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
