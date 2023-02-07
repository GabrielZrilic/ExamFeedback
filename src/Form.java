import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

class Question extends JPanel {
    private JPanel buttonsPanel;
    public ButtonGroup buttonsGroup;
    private ArrayList<JRadioButton> buttonsArray;

    public Question(User data, int n) {
        buttonsGroup = new ButtonGroup();
        buttonsPanel = new JPanel();
        buttonsArray = new ArrayList<JRadioButton>();
        JLabel label = new JLabel("Zadatak: " + n);

        this.setLayout(new GridBagLayout());
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        label.setFont(MainPanel.font.deriveFont((float) 25));
        this.add(label);

        this.add(label, MainPanel.setLocation(0, 0, GridBagConstraints.BOTH, 1, 0.5, 2));

        for(int i = 0; i<data.ansClient.size(); i++) {
            buttonsArray.add(new JRadioButton(data.ansClient.get(i)));
            buttonsArray.get(i).setFont(MainPanel.font.deriveFont((float) 19));
            buttonsArray.get(i).setActionCommand(buttonsArray.get(i).getText());
            buttonsGroup.add(buttonsArray.get(i));
            buttonsPanel.add(buttonsArray.get(i));
        }
        this.add(Box.createRigidArea(new Dimension(20, 0)), MainPanel.setLocation(0, 1, GridBagConstraints.HORIZONTAL, 0, 0, 1));
        this.add(buttonsPanel, MainPanel.setLocation(1, 1, GridBagConstraints.HORIZONTAL, 0, 0.5, 1));
    }
} 

public class Form extends JPanel{
    ArrayList<Question> questions;

    public Form(User data) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        questions = new ArrayList<Question>();
        
        for(int i = 0; i<data.numOfQuestions; i++) {
            questions.add(new Question(data, i+1));
            this.add(questions.get(i));
            this.add(Box.createRigidArea(new Dimension(0,10)));
        }
    }

    public String getData() {
        String retS = "";

        for(int i = 0; i<questions.size(); i++) {
            if(questions.get(i).buttonsGroup.getSelection() != null) retS += questions.get(i).buttonsGroup.getSelection().getActionCommand() + "@";
            else {
                JOptionPane.showMessageDialog(new JFrame("Greška"), "Odgovori na sva pitanja");
                return null;
            }
        }

        return retS;
    }
}