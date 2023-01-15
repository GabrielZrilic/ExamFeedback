import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MainPanel extends JPanel implements ActionListener {
    public JButton hostButton, clientButton;
    public HostPanel hostPanel;
    public ClientPanel clientPanel;

    public MainPanel() {
        hostButton = new JButton("Host");
        clientButton = new JButton("Client");
        addUIElements();
        setupActionListener();
        this.revalidate();
        this.repaint();
    }

    private void addUIElements() {
        this.add(hostButton);
        this.add(clientButton);
    }

    private void setupActionListener() {
        hostButton.addActionListener(this);
        clientButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.removeAll();
        this.revalidate();
        this.repaint();
        if(e.getSource() == hostButton) {
            hostPanel = new HostPanel();
            this.add(hostPanel);
        } else {
            clientPanel = new ClientPanel();
            this.add(clientPanel);
        }
    }
}
