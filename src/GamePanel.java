import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private int[] snakeXlength = new int[750];
    private int[] snakeYlength = new int[750];
    private int lengthOfSnake = 3;

    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;

    private Timer timer;
    private int delay;

    private int move = 0;
    private int score = 0;
    private boolean gameOver = false;
    private boolean gamePaused = false;

    private ImageIcon snaketitle = new ImageIcon(getClass().getResource("snaketitle.jpg"));
    private ImageIcon leftmouth = new ImageIcon(getClass().getResource("leftmouth.png"));
    private ImageIcon rightmouth = new ImageIcon(getClass().getResource("rightmouth.png"));
    private ImageIcon upmouth = new ImageIcon(getClass().getResource("upmouth.png"));
    private ImageIcon downmouth = new ImageIcon(getClass().getResource("downmouth.png"));
    private ImageIcon snakeimage = new ImageIcon(getClass().getResource("snakeimage.png"));
    private ImageIcon foodImage = new ImageIcon(getClass().getResource("enemy.png"));

    private Random random = new Random();
    private int foodX, foodY;

    public GamePanel(String difficulty) {
        this.delay = getDifficultyDelay(difficulty);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        snakeXlength[0] = 100;
        snakeXlength[1] = 75;
        snakeXlength[2] = 50;
        snakeYlength[0] = 100;
        snakeYlength[1] = 100;
        snakeYlength[2] = 100;

        spawnFood();
        timer = new Timer(delay, this);
        timer.start();
    }

    private int getDifficultyDelay(String difficulty) {
        switch (difficulty) {
            case "Medium":
                return 85;
            case "Hard":
                return 70;
            default:
                return 100;
        }
    }

    private void spawnFood() {
        HashSet<String> snakePositions = new HashSet<>();
        for (int i = 0; i < lengthOfSnake; i++) {
            snakePositions.add(snakeXlength[i] + "," + snakeYlength[i]);
        }

        do {
            foodX = 25 + random.nextInt(34) * 25;
            foodY = 75 + random.nextInt(23) * 25;
        } while (snakePositions.contains(foodX + "," + foodY));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.white);
        g.drawRect(24, 10, 851, 55);
        g.drawRect(24, 74, 851, 576);
        snaketitle.paintIcon(this, g, 25, 11);

        g.setColor(Color.black);
        g.fillRect(25, 75, 850, 576);
        foodImage.paintIcon(this, g, foodX, foodY);

        if (left) leftmouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
        if (right) rightmouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
        if (up) upmouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
        if (down) downmouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);

        for (int i = 1; i < lengthOfSnake; i++) {
            snakeimage.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
        }

        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("Game Over !!", 300, 300);
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Press Space to Restart", 345, 350);
        }

        if (gamePaused) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("Game Paused", 300, 300);
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Press Enter to Resume", 360, 350);
        }

        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString("Score : " + score, 750, 30);
        g.drawString("Length : " + (lengthOfSnake - 3), 750, 50);

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (move > 0 && !gameOver && !gamePaused) {
            collidesWithBody();
            for (int i = lengthOfSnake - 1; i > 0; i--) {
                snakeXlength[i] = snakeXlength[i - 1];
                snakeYlength[i] = snakeYlength[i - 1];
            }

            if (left) snakeXlength[0] -= 25;
            if (right) snakeXlength[0] += 25;
            if (up) snakeYlength[0] -= 25;
            if (down) snakeYlength[0] += 25;

            if (snakeXlength[0] > 850) snakeXlength[0] = 25;
            if (snakeXlength[0] < 25) snakeXlength[0] = 850;
            if (snakeYlength[0] > 625) snakeYlength[0] = 75;
            if (snakeYlength[0] < 75) snakeYlength[0] = 625;

            checkFoodCollision();
            repaint();
        }
    }

    private void checkFoodCollision() {
        if (snakeXlength[0] == foodX && snakeYlength[0] == foodY) {
            lengthOfSnake++;
            score += 5;
            spawnFood();
        }
    }

    private void collidesWithBody() {
        for (int i = 1; i < lengthOfSnake; i++) {
            if (snakeXlength[i] == snakeXlength[0] && snakeYlength[i] == snakeYlength[0]) {
                timer.stop();
                gameOver = true;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (gameOver && key == KeyEvent.VK_SPACE) {
            restartGame();
        }

        if (gamePaused && (key == KeyEvent.VK_ENTER)) {
            resumeGame();
        }

        if (key == KeyEvent.VK_LEFT && !right) { left = true; up = false; down = false; move++; }
        if (key == KeyEvent.VK_RIGHT && !left) { right = true; up = false; down = false; move++; }
        if (key == KeyEvent.VK_UP && !down) { up = true; left = false; right = false; move++; }
        if (key == KeyEvent.VK_DOWN && !up) { down = true; left = false; right = false; move++; }

        if (key == KeyEvent.VK_P) {
            pauseGame();
        }
    }

    private void resumeGame() {
        gamePaused = false;
        timer.start();
        repaint();
    }

    private void pauseGame() {
        gamePaused = true;
        timer.stop();
        repaint();
    }

    private void restartGame() {
        gameOver = false;
        move = 0;
        score = 0;
        lengthOfSnake = 3;
        left = false;
        right = true;
        up = false;
        down = false;
        spawnFood();
        timer.start();
        repaint();
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}
