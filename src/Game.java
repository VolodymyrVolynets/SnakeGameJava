import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Game extends JPanel implements Runnable, KeyListener {
    static final int WIDTH = 1920;
    static final int HEIGHT = 1080;
    static final int CELLSIZE = 30;
    static final int XSIZE = WIDTH / CELLSIZE;
    static final int YSIZE = HEIGHT / CELLSIZE;
    private double speed = 8;
    private Point food;
    private Snake snake;

    Game() {
        snake = new Snake(XSIZE, YSIZE);
        generateFood();
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.addKeyListener(game);
        Thread a = new Thread(game);
        a.start();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case 87:
                snake.setNewDirection(0);
                break;
            case 68:
                snake.setNewDirection(1);
                break;
            case 83:
                snake.setNewDirection(2);
                break;
            case 65:
                snake.setNewDirection(3);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double ns;
        double delta = 0;
        while(true){
            ns = 1000000000.0 / speed;
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                nextMove();
                repaint();
                delta--;
            }
        }
    }

    public void generateFood() {
        Random random = new Random();
        Point p;
        do {
            p = new Point(random.nextInt(XSIZE), random.nextInt(YSIZE));
        } while (snake.getSnakeBody().contains(p));
        food = p;
    }

    public void nextMove() {
        snake.move();
        if (snake.getHeadPosition().equals(food)) {
            snake.increaseSize();
            generateFood();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Point snakeBodyPoint: snake.getSnakeBody()) {
            g.fillRect(snakeBodyPoint.x * CELLSIZE, snakeBodyPoint.y * CELLSIZE, CELLSIZE, CELLSIZE);
        }
        g.setColor(Color.GREEN);
        g.fillRect(food.x * CELLSIZE, food.y * CELLSIZE, CELLSIZE, CELLSIZE);
    }
}
