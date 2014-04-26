package kata.marsrover.app;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private int height;
    private int width;
    private List<Position> obstacles;

    public Grid(int height, int width) {
        this.height = height;
        this.width = width;
        this.obstacles = new ArrayList<Position>();
    }

    public int height() {
        return height;
    }

    public int width() {
        return width;
    }

    public void addObstacleAt(int x, int y) {
        this.obstacles.add(at(x, y));
    }

    public Position at(int x, int y) {
        Position position = new Position(this, wrap(x, width()), wrap(y, height()));
        throwIfObstacleIsFound(position);
        return position;
    }

    private void throwIfObstacleIsFound(Position position) {
        for (Position obstacle : obstacles)
            if (obstacle.equals(position))
                throw new ObstacleFoundException(obstacle);
    }

    private int wrap(int coordinate, int limit) {
        if (coordinate < 0) return limit - 1;
        else if (coordinate > limit - 1) return 0;
        else return coordinate;
    }
}
