import javax.swing.JFrame;

import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;

public class App {
    public static void main(String[] args) {
        FlatArcOrangeIJTheme.setup();

        JFrame frame = new JFrame("Anketa");
        MainPanel mainPanel = new MainPanel();

        frame.setSize(500, 500);
        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
