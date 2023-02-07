import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

class QuestionData extends JPanel {
    private JPanel optionsPanel;
    private HashMap<String, ArrayList<String>> ansMap;
    private ArrayList<JProgressBar> progressBars;

    public QuestionData(int n) {
        optionsPanel = new JPanel();
        ansMap = new HashMap<>();
        progressBars = new ArrayList<JProgressBar>();
        JLabel label = new JLabel("Zadatak: " + Integer.toString(n+1));
        String p;

        this.setLayout(new GridBagLayout());
        optionsPanel.setLayout(new GridBagLayout());
        label.setFont(MainPanel.font.deriveFont((float) 25));
        this.add(label);

        this.add(label, MainPanel.setLocation(0, 0, GridBagConstraints.BOTH, 1, 0.5, 2));

        for(int i = 0; i<Data.users.get().size(); i++) {
            p = Data.users.get().get(i).ansClient.get(n);
            if(!ansMap.containsKey(p)) ansMap.put(p, new ArrayList<String>());

            ansMap.get(p).add(Data.users.get().get(i).userName);
        }

        for(int i = 0; i<HostPanel.options.size(); i++) {
            progressBars.add(new JProgressBar(0, HostPanel.numOfStudents.get()));
            try {
                progressBars.get(i).setValue(ansMap.get(HostPanel.options.get(i)).size());
                progressBars.get(i).setToolTipText(Integer.toString(ansMap.get(HostPanel.options.get(i)).size()));
            } catch (Exception e) {
                progressBars.get(i).setValue(0);
            }
            optionsPanel.add(new JLabel(HostPanel.options.get(i)), MainPanel.setLocation(0, i, GridBagConstraints.BOTH, 1, 1, 1));
            optionsPanel.add(progressBars.get(i), MainPanel.setLocation(1, i, GridBagConstraints.BOTH, 1, 1, 1));
        }


        this.add(Box.createRigidArea(new Dimension(20, 0)), MainPanel.setLocation(0, 1, GridBagConstraints.HORIZONTAL, 0, 0, 1));
        this.add(optionsPanel, MainPanel.setLocation(1, 1, GridBagConstraints.HORIZONTAL, 0, 0.5, 1));
    }
}

public class DataOutput extends JPanel{
    ArrayList<QuestionData> questions;

    public DataOutput() {
        JLabel total = new JLabel(HostPanel.numOfStudents.toString() + " učenika je predalo anketu");
        questions = new ArrayList<QuestionData>();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        total.setFont(MainPanel.font.deriveFont((float) 30));
        this.add(total);

        for(int i = 0; i<HostPanel.questionNum.get(); i++) {
            questions.add(new QuestionData(i));
            this.add(questions.get(i));
            this.add(Box.createRigidArea(new Dimension(0,10)));
        }
    }
}