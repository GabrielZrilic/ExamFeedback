import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class HostPanel extends JPanel implements ActionListener {
    public static AtomicInteger hostnum = new AtomicInteger(0);
    public static AtomicInteger idnum = new AtomicInteger(0);
    public static AtomicReference<String> stringSend = new AtomicReference<String>();

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

    private void setAtomicToString() {
        String p = "";
        for(int i = 0; i<options.size(); i++) {
            p += options.get(i);
            p += "@";
        }
        stringSend.set(p);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == optionField) {
            options.add(optionField.getText());
            optionField.setText("");
        } else if(e.getSource() == startButton) {
            questionsNum = (Integer) numSpinner.getValue();
            this.remove(startButton);
            this.add(endButton);
            setAtomicToString();
            startServer();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            this.add(new JLabel(hostnum.toString()));
        } else {
            host.running = false;
        }
    }
}
