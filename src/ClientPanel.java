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
    JTextField portInput, nameInput;
    JButton joinButton;
    Socket connection;
    PrintWriter pr;

    public ClientPanel() {
        portInput = new JTextField(20);
        nameInput = new JTextField(20);
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
        System.out.println("Entered");
        connection = new Socket(InetAddress.getLocalHost(), Integer.parseInt(portInput.getText()));
        
        pr = new PrintWriter(connection.getOutputStream());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == nameInput) {
            portInput.requestFocus();
            System.out.println("sending...");
            pr.println(nameInput.getText());
            pr.flush();
        }else{
            try {
                connecting();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }  

}
