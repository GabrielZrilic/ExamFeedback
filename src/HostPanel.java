import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class HostPanel extends JPanel implements ActionListener {
    public static AtomicInteger hostnum = new AtomicInteger(0);
    public static AtomicInteger idnum = new AtomicInteger(0);
    public static AtomicInteger questionNum = new AtomicInteger(0);
    public static AtomicInteger numOfStudents = new AtomicInteger(0);
    public static AtomicReference<String> stringSend = new AtomicReference<String>();

    public static ArrayList<String> options;
    private JTextField optionField;
    private JLabel txt1, txt2;
    private JSpinner numSpinner;
    private JButton startButton, endButton;
    private Host host;
    private JScrollPane scrollPane;

    public HostPanel() {
        options = new ArrayList<String>();
        addUIElements();
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

    private void addUIElements() {
        optionField = new JTextField(20);   
        numSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 200, 1));
        startButton = new JButton("Pokreni anketu");
        endButton = new JButton("Završi anketu");
        txt1 = new JLabel("Ponuđeni odgovori");
        txt2 = new JLabel("Broj pitanja");

        optionField.setFont(MainPanel.font.deriveFont((float) 25));
        numSpinner.setFont(MainPanel.font.deriveFont((float) 25));
        startButton.setFont(MainPanel.font.deriveFont((float) 35));
        endButton.setFont(MainPanel.font.deriveFont((float) 35));
        txt1.setFont(MainPanel.font.deriveFont((float) 25));
        txt2.setFont(MainPanel.font.deriveFont((float) 25));
        
        this.setLayout(new GridBagLayout());
        this.add(txt1, MainPanel.setLocation(0, 0, GridBagConstraints.BOTH, 1, 0.1, 1));
        this.add(txt2, MainPanel.setLocation(1, 0, GridBagConstraints.BOTH, 1, 0.1, 1));
        this.add(optionField, MainPanel.setLocation(0, 1, GridBagConstraints.HORIZONTAL, 1, 1, 1));
        this.add(numSpinner, MainPanel.setLocation(1, 1, GridBagConstraints.HORIZONTAL, 1, 1, 1));
        this.add(startButton, MainPanel.setLocation(0, 2, GridBagConstraints.BOTH, 1, 1, 2));
        setupActionListener();
    }

    private void setupActionListener() {
        optionField.addActionListener(this);
        startButton.addActionListener(this);
        endButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == optionField && optionField.getText().length() > 0) {
            if(!options.contains(optionField.getText())) options.add(optionField.getText());
            else JOptionPane.showMessageDialog(new JFrame("Greška"), "Opcija postoji");
            optionField.setText("");
        } else if(e.getSource() == startButton) {
            if(options.size() < 2) {
                JOptionPane.showMessageDialog(new JFrame("Greška"), "Postavi minimalno 2 opcije");
                return;
            }
            questionNum.set((Integer) numSpinner.getValue());
            this.remove(startButton);
            this.add(endButton, MainPanel.setLocation(0, 3, GridBagConstraints.BOTH, 1, 1, 3));  startButton.addActionListener(this);
            setAtomicToString();
            startServer();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e1) { }
            JPanel p = new JPanel();
            JLabel portLabel = new JLabel(Data.getIp() + "    " + hostnum.toString());
            portLabel.setFont(MainPanel.font.deriveFont((float) 35));
            
            p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
            p.add(Box.createHorizontalGlue());
            p.add(portLabel);
            p.add(Box.createHorizontalGlue());
            this.add(p, MainPanel.setLocation(0, 2, GridBagConstraints.BOTH, 1, 1, 3));
            optionField.setEnabled(false);
            numSpinner.setEnabled(false);
        } else if(e.getSource() == endButton){
            try {
                host.serverSocket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            host.isRunning = false;
            
            DataOutput dataOutput = new DataOutput();
            scrollPane = new JScrollPane(dataOutput);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);

            this.removeAll();
            this.setLayout(new BorderLayout());
            this.add(scrollPane, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        }
    }
}
