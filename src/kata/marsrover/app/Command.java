package kata.marsrover.app;

public enum Command {
    MOVE_BACKWARD {
        public void executeOn(Rover rover, MutableRover mutableRover) {
            mutableRover.setPosition(rover.position().previous(rover.direction()));
        }
    }, MOVE_FORWARD {
        public void executeOn(Rover rover, MutableRover mutableRover) {
            mutableRover.setPosition(rover.position().next(rover.direction()));
        }
    }, TURN_LEFT {
        public void executeOn(Rover rover, MutableRover mutableRover) {
            mutableRover.setDirection(rover.direction().left());
        }
    }, TURN_RIGHT {
        public void executeOn(Rover rover, MutableRover mutableRover) {
            mutableRover.setDirection(rover.direction().right());
        }
    };

    abstract void executeOn(Rover rover, MutableRover mutableRover);
}
