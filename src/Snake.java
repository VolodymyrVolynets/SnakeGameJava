import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<Point> snakeBody;
    private int direction;
    private int xBoardSize;
    private int yBoardSize;
    private Point lastTailPosition;

    public Snake(int xBoardSize, int yBoardSize) {
        Point headPosition = new Point(xBoardSize / 2, yBoardSize / 2);
        snakeBody = new ArrayList<>();
        snakeBody.add(headPosition);
        snakeBody.add(new Point(headPosition.x, headPosition.y + 1));
        snakeBody.add(new Point(headPosition.x, headPosition.y + 2));
        direction = Direction.UP;
        this.xBoardSize = xBoardSize;
        this.yBoardSize = yBoardSize;
    }

    public void move() {
        lastTailPosition = snakeBody.get(snakeBody.size() - 1);
        for (int i = snakeBody.size() - 1; i > 0; i--) {
            snakeBody.get(i).x = snakeBody.get(i - 1).x;
            snakeBody.get(i).y = snakeBody.get(i - 1).y;
        }
        switch (direction) {
            case 0:
                if (snakeBody.get(0).y > 0) {
                    snakeBody.get(0).y -= 1;
                }
                break;
            case 1:
                if (snakeBody.get(0).x < xBoardSize) {
                    snakeBody.get(0).x += 1;
                }
                break;
            case 2:
                if (snakeBody.get(0).y < yBoardSize) {
                    snakeBody.get(0).y += 1;
                }
                break;
            case 3:
                if (snakeBody.get(0).x > 0) {
                    snakeBody.get(0).x -= 1;
                }
                break;
        }
    }

    public Point getHeadPosition() {
        return snakeBody.get(0);
    }

    public void increaseSize() {
        snakeBody.add(new Point(lastTailPosition));
    }

    public int size() {
        return snakeBody.size();
    }

    public List<Point> getSnakeBody() {
        return snakeBody;
    }

    public void setNewDirection(int newDirection) {
        if (isValidDirection(newDirection)) direction = newDirection;
    }

    private boolean isValidDirection(int nextMoveDirection) {
        return Math.abs(direction - nextMoveDirection) <= 1 || Math.abs(direction - nextMoveDirection) == 3;
    }

    public class Direction {
        static int UP = 0;
        static int RIGHT = 1;
        static int DOWN = 2;
        static int LEFT = 3;
    }
}
