import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

class Question extends JPanel {
    ButtonGroup buttonsGroup;
    ArrayList<JRadioButton> buttonsArray;

    public Question(User data, int n) {
        buttonsGroup = new ButtonGroup();
        buttonsArray = new ArrayList<JRadioButton>();
        JLabel label = new JLabel("Zadatak: " + n);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        label.setFont(MainPanel.font.deriveFont((float) 25));
        this.add(label);

        for(int i = 0; i<data.ansClient.size(); i++) {
            buttonsArray.add(new JRadioButton(data.ansClient.get(i)));
            buttonsArray.get(i).setFont(MainPanel.font.deriveFont((float) 19));
            buttonsGroup.add(buttonsArray.get(i));
            this.add(buttonsArray.get(i));
        }
    }
} 

public class Form extends JPanel{
    ArrayList<Question> questions;
    JButton endForm;

    public Form(User data) {
        endForm = new JButton("Pošalji");
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        questions = new ArrayList<Question>();
        
        for(int i = 0; i<data.numOfQuestions; i++) {
            questions.add(new Question(data, i+1));
            this.add(questions.get(i));
            this.add(Box.createRigidArea(new Dimension(0,20)));
        }
        this.add(endForm);
    }
}