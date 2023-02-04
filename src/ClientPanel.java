import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientPanel extends JPanel implements ActionListener {
    public JTextField portInput, nameInput, ipInput;
    public JButton joinButton;
    public Socket connection;
    public PrintWriter pr;
    public User data;
    public InputStreamReader in;
    public BufferedReader bf;
    public String receivedData;

    public ClientPanel() {
        portInput = new JTextField();
        nameInput = new JTextField();
        ipInput = new JTextField();
        joinButton = new JButton("Riješi anketu");
        
        setGui();
        addListener();
    }

    private void setGui() {
        this.setLayout(new GridBagLayout());
        JLabel imeLabel = new JLabel("Korisničko ime:"), ipLabel = new JLabel("IP adresa:"), portLabel = new JLabel("Upiši sučelje:");
        imeLabel.setFont(MainPanel.font);
        ipLabel.setFont(MainPanel.font);
        portLabel.setFont(MainPanel.font);
        joinButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 35));

        nameInput.setToolTipText("Primjer: Marko");
        nameInput.setFont(MainPanel.font);
        ipInput.setToolTipText("Primjer: 192.168.5.19");
        ipInput.setFont(MainPanel.font);
        portInput.setToolTipText("Primjer: 40153");
        portInput.setFont(MainPanel.font);


        this.add(imeLabel, MainPanel.setLocation(0, 0, GridBagConstraints.BOTH, 0.5, 0.5, 1));
        this.add(nameInput, MainPanel.setLocation(1, 0, GridBagConstraints.BOTH, 0.5, 0.5, 1));
        this.add(ipLabel, MainPanel.setLocation(0, 1, GridBagConstraints.BOTH, 0.5, 0.5, 1));
        this.add(ipInput, MainPanel.setLocation(1, 1, GridBagConstraints.BOTH, 0.5, 0.5, 1));
        this.add(portLabel, MainPanel.setLocation(0, 2, GridBagConstraints.BOTH, 0.5, 0.5, 1));
        this.add(portInput, MainPanel.setLocation(1, 2, GridBagConstraints.BOTH, 0.5, 0.5, 1));
        this.add(joinButton, MainPanel.setLocation(0, 3, GridBagConstraints.BOTH, 0.5, 0.5, 2));
    }

    private void addListener() {
        nameInput.addActionListener(this);
        portInput.addActionListener(this);
        joinButton.addActionListener(this);
    }

    public void connecting() throws IOException {
        // TODO: fix ip address connection
        connection = new Socket(ipInput.getText(), Integer.parseInt(portInput.getText()));
        pr = new PrintWriter(connection.getOutputStream());
        data = new User();
        in = new InputStreamReader(connection.getInputStream());
        bf = new BufferedReader(in);
        System.out.println("Connected to host");
        getData();
        System.out.println(receivedData);
    }

    public void getData() throws IOException {
        while(receivedData == null) {
            receivedData = bf.readLine();
        }
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
                sendData("hello");
                data.userName = nameInput.getText();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
