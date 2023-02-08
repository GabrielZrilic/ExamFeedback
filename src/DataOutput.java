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

// One question - listing every option data
class QuestionData extends JPanel {
    private JPanel optionsPanel;
    private HashMap<String, ArrayList<String>> ansMap;
    private ArrayList<JProgressBar> progressBars;
    private ArrayList<JLabel> labels;

    public QuestionData(int n) {
        optionsPanel = new JPanel();
        ansMap = new HashMap<>();
        labels = new ArrayList<JLabel>();
        progressBars = new ArrayList<JProgressBar>();
        JLabel label = new JLabel("Zadatak: " + Integer.toString(n+1));
        String p;

        this.setLayout(new GridBagLayout());
        optionsPanel.setLayout(new GridBagLayout());
        label.setFont(MainPanel.font.deriveFont((float) 25));
        this.add(label);

        this.add(label, MainPanel.setLocation(0, 0, GridBagConstraints.BOTH, 1, 0.5, 2, 30));

        for(int i = 0; i<Data.users.get().size(); i++) {
            p = Data.users.get().get(i).ansClient.get(n);
            if(!ansMap.containsKey(p)) ansMap.put(p, new ArrayList<String>());
            ansMap.get(p).add(Data.users.get().get(i).userName);
        }

        // Add progress bars
        for(int i = 0; i<HostPanel.options.size(); i++) {
            progressBars.add(new JProgressBar(0, HostPanel.numOfStudents.get()));
            progressBars.get(i).setStringPainted(true);
            progressBars.get(i).setFont(MainPanel.font.deriveFont((float) 18));
            try {
                System.out.println(ansMap.get(HostPanel.options.get(i)));
                progressBars.get(i).setValue(ansMap.get(HostPanel.options.get(i)).size());
                progressBars.get(i).setString(Integer.toString(ansMap.get(HostPanel.options.get(i)).size()) + " / " + Integer.toString(HostPanel.numOfStudents.get()));
                progressBars.get(i).setToolTipText("<html> Učenici koji su odgovorili <br> " + String.join(" <br> ", ansMap.get(HostPanel.options.get(i))) + "</html>");
            } catch (Exception e) {
                progressBars.get(i).setValue(0);
                progressBars.get(i).setString("0 / " + Integer.toString(HostPanel.numOfStudents.get()));
                progressBars.get(i).setFont(MainPanel.font.deriveFont((float) 20));
                progressBars.get(i).setToolTipText("0 učenika odgovorilo");
            }
            labels.add(new JLabel(HostPanel.options.get(i)));
            labels.get(i).setFont(MainPanel.font.deriveFont((float) 20));
            optionsPanel.add(labels.get(i), MainPanel.setLocation(0, i, GridBagConstraints.BOTH, 1, 1, 1, 30));
            optionsPanel.add(progressBars.get(i), MainPanel.setLocation(1, i, GridBagConstraints.BOTH, 1, 1, 1, 30));
        }
        this.add(Box.createRigidArea(new Dimension(20, 0)), MainPanel.setLocation(0, 1, GridBagConstraints.HORIZONTAL, 0, 0, 1, 30));
        this.add(optionsPanel, MainPanel.setLocation(1, 1, GridBagConstraints.HORIZONTAL, 0, 0.5, 1, 30));
    }
}

public class DataOutput extends JPanel{
    ArrayList<QuestionData> questions;

    public DataOutput() {
        JPanel div = new JPanel();
        JLabel total = new JLabel(HostPanel.numOfStudents.toString() + " učenika je predalo anketu");
        questions = new ArrayList<QuestionData>();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        total.setFont(MainPanel.font.deriveFont((float) 30));
        div.setLayout(new GridBagLayout());
        div.add(total);
        this.add(div);

        // Show every question
        for(int i = 0; i<HostPanel.questionNum.get(); i++) {
            questions.add(new QuestionData(i));
            this.add(questions.get(i));
            this.add(Box.createRigidArea(new Dimension(0,10)));
        }
    }
}