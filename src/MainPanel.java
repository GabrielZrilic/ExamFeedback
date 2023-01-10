import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MainPanel extends JPanel implements ActionListener {
    JButton hostButton, clientButton;
    HostPanel hostPanel;

    public MainPanel() {
        hostButton = new JButton("Host");
        clientButton = new JButton("Client");
        addUIElements();
        setupActionListener();
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
        if(e.getSource() == hostButton) {
            this.removeAll();
            this.revalidate();
            this.repaint();
            hostPanel = new HostPanel();
            this.add(hostPanel);
        } else {

        }
    }
}
