import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ClientPanel extends JPanel implements ActionListener {
    public JTextField portInput, nameInput, ipInput;
    public JButton joinButton, endButton;
    public Socket connection;
    public PrintWriter pr;
    public User data;
    public InputStreamReader in;
    public BufferedReader bf;
    public String receivedData;
    public JScrollPane scrollPane;
    public Form form;

    public ClientPanel() {
        portInput = new JTextField();
        nameInput = new JTextField("Demo");
        ipInput = new JTextField("192.168.5.19");
        joinButton = new JButton("Riješi anketu");
        endButton = new JButton("Pošalji");
        
        setGui();
        addListener();
    }

    private void setGui() {
        this.setLayout(new GridBagLayout());
        JLabel imeLabel = new JLabel("Korisničko ime:"), ipLabel = new JLabel("IP adresa:"), portLabel = new JLabel("Upiši sučelje:");

        portInput.setFont(MainPanel.font.deriveFont((float) 31));
        nameInput.setFont(MainPanel.font.deriveFont((float) 31));
        ipInput.setFont(MainPanel.font.deriveFont((float) 31));
        joinButton.setFont(MainPanel.font.deriveFont((float) 31));
        imeLabel.setFont(MainPanel.font.deriveFont((float) 19));
        ipLabel.setFont(MainPanel.font.deriveFont((float) 19));
        portLabel.setFont(MainPanel.font.deriveFont((float) 19));

        nameInput.setToolTipText("Primjer: Marko");
        ipInput.setToolTipText("Primjer: 192.168.5.19");
        portInput.setToolTipText("Primjer: 40153");


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
        endButton.addActionListener(this);
    }

    private void connecting() throws IOException {
        connection = new Socket(ipInput.getText(), Integer.parseInt(portInput.getText()));
        pr = new PrintWriter(connection.getOutputStream());
        in = new InputStreamReader(connection.getInputStream());
        bf = new BufferedReader(in);
        getData();
        System.out.println(receivedData);
        data = new User(receivedData, User.side.CLIENT);
        data.userName = nameInput.getText();
    }

    private void getData() throws IOException {
        while(receivedData == null) {
            receivedData = bf.readLine();
        }
    }

    private void sendData(String data) {
        pr.println(data);
        pr.flush();
        System.out.println(data);
    }

    private void startForm() {
        form = new Form(data);
        scrollPane = new JScrollPane(form);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(endButton, BorderLayout.PAGE_END);
        this.revalidate();
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == nameInput) {
            portInput.requestFocus();
            System.out.println(nameInput.getText());
        }else if(e.getSource() == portInput || e.getSource() == joinButton) {
            try {
                connecting();
                startForm();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }else if(e.getSource() == endButton) {
            // "id@userName@ans0@ans1@ans2@ans3..."
            sendData(Integer.toString(data.id) + "@" + data.userName + "@" + form.getData());
            JLabel label = new JLabel("Hvala na povratnoj informaciji");
            this.removeAll();
            this.add(label);
        }
    }
}
