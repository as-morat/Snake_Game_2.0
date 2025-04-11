import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 917, 712);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GameMenu gameMenu = new GameMenu(frame);
        frame.add(gameMenu);

        frame.setVisible(true);
    }
}
