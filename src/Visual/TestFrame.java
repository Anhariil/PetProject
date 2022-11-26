package Visual;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;

public class TestFrame {
    public static void createGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Test frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // after close the window app also close

        JLabel label = new JLabel("U all are pidors?");
//        frame.getContentPane().add(label); // add label on the Frame

        frame.setSize(new Dimension(800, 600));

        frame.pack(); // ?
        frame.setVisible(true);
        DisplayGraphics m = new DisplayGraphics();
        frame.add(m);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
    }
}

