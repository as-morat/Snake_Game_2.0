import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameMenu extends JPanel implements ActionListener {
    private JFrame frame;
    private JButton startButton;
    private JButton levelButton;
    private String difficulty = "Easy";

    public GameMenu(JFrame frame) {
        this.frame = frame;
        setLayout(null);
        setBackground(Color.BLACK);

        JLabel title = new JLabel("SNAKE !!", JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.ITALIC + Font.BOLD, 110));
        title.setBounds(200, 50, 500, 120);
        title.setForeground(Color.WHITE);
        add(title);

        startButton = new JButton("Play");
        startButton.setFont(new Font("Impact", Font.BOLD, 40));
        startButton.setBounds(270, 300, 340, 50);
        styleButton(startButton);
        startButton.addActionListener(this);
        add(startButton);

        levelButton = new JButton("Level: " + difficulty);
        levelButton.setFont(new Font("Impact", Font.BOLD, 40));
        levelButton.setBounds(270, 400, 340, 50);
        styleButton(levelButton);
        levelButton.addActionListener(this);
        add(levelButton);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(255, 165, 0));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            startGame();
        } else if (e.getSource() == levelButton) {
            toggleDifficulty();
        }
    }

    private void startGame() {
        GamePanel gamePanel = new GamePanel(difficulty);
        frame.getContentPane().removeAll();
        frame.add(gamePanel);
        gamePanel.requestFocusInWindow();
        frame.revalidate();
        frame.repaint();
    }

    private void toggleDifficulty() {
        if (difficulty.equals("Easy")) {
            difficulty = "Medium";
        } else if (difficulty.equals("Medium")) {
            difficulty = "Hard";
        } else {
            difficulty = "Easy";
        }
        levelButton.setText("Level: " + difficulty);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        g.fillRect(25, 75, getWidth() - 50, getHeight() - 100);
    }
}
