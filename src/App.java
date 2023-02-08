import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.formdev.flatlaf.FlatIntelliJLaf;

// Main class and frame setup
public class App {
    public static void main(String[] args) {
        FlatIntelliJLaf.setup();    // Theme setup

        JFrame frame = new JFrame("Exam Feedback");
        MainPanel mainPanel = new MainPanel();

        try { frame.setIconImage(ImageIO.read(new File("img/icon.png"))); } 
        catch (IOException e) { }

        frame.setSize(1000, 500);
        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

