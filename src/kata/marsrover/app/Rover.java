package kata.marsrover.app;

public class Rover {
    private Direction direction;
    private Position position;

    public Rover(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    public Position position() {
        return position;
    }

    public Direction direction() {
        return direction;
    }

    public void execute(Command... commands) {
        MutableRover mutator = makeMutator();
        for (Command command : commands)
            command.executeOn(this, mutator);
    }

    private MutableRover makeMutator() {
        return new MutableRover() {
            public void setDirection(Direction direction) {
                Rover.this.direction = direction;
            }

            public void setPosition(Position position) {
                Rover.this.position = position;
            }
        };
    }
}
