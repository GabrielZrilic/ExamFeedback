import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class HostPanel extends JPanel implements ActionListener {
    public JTextField optionField;
    public JSpinner numSpinner;
    public ArrayList<String> options;
    public JButton startButton, endButton;
    public int questionsNum;
    public Host host;

    public HostPanel() {
        options = new ArrayList<String>();
        addOptions();
    }

    private void startServer() {
        host = new Host();
        host.start();
        
        // Refresh frame
        this.revalidate();
        this.repaint();
    }

    // Add options and number of questions
    private void addOptions() {
        optionField = new JTextField(20);
        numSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 200, 1));
        startButton = new JButton("Start");
        endButton = new JButton("End");
        


        this.add(optionField);  optionField.addActionListener(this);
        this.add(numSpinner);
        this.add(startButton);  startButton.addActionListener(this);
        endButton.addActionListener(this);
    }

    private void stopServer() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == optionField) {
            options.add(optionField.getText());
            optionField.setText("");
        } else if(e.getSource() == startButton) {
            questionsNum = (Integer) numSpinner.getValue();
            this.remove(startButton);
            this.add(endButton);
            startServer();
        } else {
            stopServer();
        }
    }
}
