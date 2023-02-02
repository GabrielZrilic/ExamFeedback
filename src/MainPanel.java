import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainPanel extends JPanel implements ActionListener {
    public static Font font = new Font(Font.MONOSPACED, Font.BOLD,  16);

    public JButton hostButton, clientButton;
    public HostPanel hostPanel;
    public ClientPanel clientPanel;

    public MainPanel() {
        hostButton = new JButton("Host");       hostButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        clientButton = new JButton("Client");   clientButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        hostButton.setFocusable(false);
        clientButton.setFocusable(false);
        addUIElements();
        setupActionListener();
        this.revalidate();
        this.repaint();
    }

    private void addUIElements() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(Box.createHorizontalGlue());
        this.add(hostButton);
        this.add(Box.createHorizontalGlue());
        this.add(clientButton);
        this.add(Box.createHorizontalGlue());
        this.setVisible(true);
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
