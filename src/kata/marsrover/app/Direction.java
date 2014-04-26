package kata.marsrover.app;

public enum Direction {
    SOUTH {
        public boolean isHorizontal() {
            return false;
        }

        public boolean isPositive() {
            return false;
        }

        public Direction opposite() {
            return NORTH;
        }

        public Direction left() {
            return EAST;
        }

        public Direction right() {
            return WEST;
        }
    }, WEST {
        public boolean isHorizontal() {
            return true;
        }

        public boolean isPositive() {
            return false;
        }

        public Direction opposite() {
            return EAST;
        }

        public Direction left() {
            return SOUTH;
        }

        public Direction right() {
            return NORTH;
        }
    }, EAST {
        public boolean isHorizontal() {
            return true;
        }

        public boolean isPositive() {
            return true;
        }

        public Direction opposite() {
            return WEST;
        }

        public Direction left() {
            return NORTH;
        }

        public Direction right() {
            return SOUTH;
        }
    }, NORTH {
        public boolean isHorizontal() {
            return false;
        }

        public boolean isPositive() {
            return true;
        }

        public Direction opposite() {
            return SOUTH;
        }

        public Direction left() {
            return WEST;
        }

        public Direction right() {
            return EAST;
        }
    };

    abstract boolean isHorizontal();
    abstract boolean isPositive();
    abstract Direction opposite();
    abstract Direction left();
    abstract Direction right();
}
