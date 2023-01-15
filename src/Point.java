public class Point {
    int x;
    int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point o) {
        this.x = o.x;
        this.y = o.y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Point))
            return false;
        Point other = (Point) o;
        return other.x == x && other.y == y;
    }
}
