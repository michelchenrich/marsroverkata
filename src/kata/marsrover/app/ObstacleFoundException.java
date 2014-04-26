package kata.marsrover.app;

public class ObstacleFoundException extends RuntimeException {
    public ObstacleFoundException(Position obstacle) {
        super(String.format("Obstacle found at position %s", obstacle.toString()));
    }
}
