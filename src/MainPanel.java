import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainPanel extends JPanel implements ActionListener {
    public static Font font;
    
    private JButton hostButton, clientButton;
    private HostPanel hostPanel;
    private ClientPanel clientPanel;

    public MainPanel() {
        hostButton = new JButton("Nastavnik");
        clientButton = new JButton(" Učenik ");
        font = hostButton.getFont();
        hostButton.setFocusable(false);
        clientButton.setFocusable(false);
        addUIElements();
        setupActionListener();
        this.revalidate();
        this.repaint();
    }

    private void addUIElements() {
        hostButton.setFont(font.deriveFont((float) 25));
        clientButton.setFont(font.deriveFont((float) 25));

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

    public static GridBagConstraints setLocation(int x, int y, int fill, double weightx, double weighty, int gridwidth) {
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = weightx; c.weighty = weighty;
        c.gridx = x; c.gridy = y;
        c.fill = fill;
        c.gridwidth = gridwidth;
        c.insets = new Insets(30, 10, 30, 10);
        return c;
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
