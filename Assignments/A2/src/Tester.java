import java.awt.*;
import javax.swing.*;

public class Tester {
    public static void main(String[] args){
        JFrame window = new JFrame();

        JPanel p = new JPanel(new BorderLayout());

        JButton button = new JButton();
        button.setIcon(new ImageIcon("icons/Minesweeper_unopened_square.png"));

        p.add(button, BorderLayout.CENTER);
        window.add(p);

        window.setSize(600, 400);
        //window.pack();
        window.setVisible(true);
    }
}
