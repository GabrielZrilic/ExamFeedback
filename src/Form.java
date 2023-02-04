import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
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

        label.setFont(MainPanel.font);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(label);

        for(int i = 0; i<data.ansClient.size(); i++) {
            buttonsArray.add(new JRadioButton(data.ansClient.get(i)));
            buttonsArray.get(i).setFont(MainPanel.font);
            buttonsGroup.add(buttonsArray.get(i));
            this.add(buttonsArray.get(i));
        }
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
            this.add(Box.createRigidArea(new Dimension(0,20)));
        }
    }
}