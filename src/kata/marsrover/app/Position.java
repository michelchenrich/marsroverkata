package kata.marsrover.app;

class Position {
    private final Grid grid;
    private final int x;
    private final int y;

    Position(Grid grid, int x, int y) {
        this.grid = grid;
        this.x = x;
        this.y = y;
    }

    Position previous(Direction direction) {
        return next(direction.opposite());
    }

    Position next(Direction direction) {
        int movement = direction.isPositive() ? 1 : -1;

        if (direction.isHorizontal()) return grid.at(x + movement, y);
        else return grid.at(x, y - movement);
    }

    public String toString() {
        return String.format("x: %d, y: %d", x, y);
    }

    public boolean equals(Object object) {
        Position position = (Position) object;
        return this.x == position.x && this.y == position.y;
    }
}
