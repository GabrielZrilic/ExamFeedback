import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientPanel extends JPanel implements ActionListener {
    JTextField portInput, nameInput;
    JButton joinButton;

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

    private void connecting() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == nameInput) {
            portInput.requestFocus();
        }else{
            connecting();
        }
    }  

}
