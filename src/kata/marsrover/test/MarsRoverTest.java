package kata.marsrover.test;

import kata.marsrover.app.*;
import org.junit.Before;
import org.junit.Test;

import static kata.marsrover.app.Command.*;
import static kata.marsrover.app.Direction.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MarsRoverTest {
    private Grid grid;
    private Rover rover;

    @Before
    public void setUp() {
        grid = new Grid(100, 100);
        grid.addObstacleAt(10, 10);
    }

    private void givenPositionAndDirection(int x, int y, Direction direction) {
        rover = new Rover(grid.at(x, y), direction);
    }

    private void executeCommands(Command... commands) {
        rover.execute(commands);
    }

    private void assertPositionAndDirection(int x, int y, Direction direction) {
        assertEquals(grid.at(x, y), rover.position());
        assertEquals(direction, rover.direction());
    }

    @Test
    public void initialState() {
        givenPositionAndDirection(0, 0, NORTH);
        assertPositionAndDirection(0, 0, NORTH);
    }

    @Test
    public void moveSouth() {
        givenPositionAndDirection(0, 0, SOUTH);
        executeCommands(MOVE_FORWARD);
        assertPositionAndDirection(0, 1, SOUTH);
    }

    @Test
    public void moveNorth() {
        givenPositionAndDirection(0, 1, NORTH);
        executeCommands(MOVE_FORWARD);
        assertPositionAndDirection(0, 0, NORTH);
    }

    @Test
    public void moveWest() {
        givenPositionAndDirection(1, 0, WEST);
        executeCommands(MOVE_FORWARD);
        assertPositionAndDirection(0, 0, WEST);
    }

    @Test
    public void moveEast() {
        givenPositionAndDirection(0, 0, EAST);
        executeCommands(MOVE_FORWARD);
        assertPositionAndDirection(1, 0, EAST);
    }

    @Test
    public void moveBackNorth() {
        givenPositionAndDirection(0, 1, SOUTH);
        executeCommands(MOVE_BACKWARD);
        assertPositionAndDirection(0, 0, SOUTH);
    }

    @Test
    public void moveBackSouth() {
        givenPositionAndDirection(0, 0, NORTH);
        executeCommands(MOVE_BACKWARD);
        assertPositionAndDirection(0, 1, NORTH);
    }

    @Test
    public void moveBackEast() {
        givenPositionAndDirection(0, 0, WEST);
        executeCommands(MOVE_BACKWARD);
        assertPositionAndDirection(1, 0, WEST);
    }

    @Test
    public void moveBackWest() {
        givenPositionAndDirection(1, 0, EAST);
        executeCommands(MOVE_BACKWARD);
        assertPositionAndDirection(0, 0, EAST);
    }

    @Test
    public void atNorthEdge_WrapToSouthernMostEdge() {
        givenPositionAndDirection(0, 0, NORTH);
        executeCommands(MOVE_FORWARD);
        assertPositionAndDirection(0, 99, NORTH);
    }

    @Test
    public void atSouthEdge_WrapToNorthernMostEdge() {
        givenPositionAndDirection(0, 99, SOUTH);
        executeCommands(MOVE_FORWARD);
        assertPositionAndDirection(0, 0, SOUTH);
    }

    @Test
    public void atWestEdge_WrapToEasternMostEdge() {
        givenPositionAndDirection(0, 0, WEST);
        executeCommands(MOVE_FORWARD);
        assertPositionAndDirection(99, 0, WEST);
    }

    @Test
    public void atEastEdge_WrapToWesternMostEdge() {
        givenPositionAndDirection(99, 0, EAST);
        executeCommands(MOVE_FORWARD);
        assertPositionAndDirection(0, 0, EAST);
    }

    @Test
    public void fromNorthTurnWest() {
        givenPositionAndDirection(0, 0, NORTH);
        executeCommands(TURN_LEFT);
        assertPositionAndDirection(0, 0, WEST);
    }

    @Test
    public void fromWestTutnSouth() {
        givenPositionAndDirection(0, 0, WEST);
        executeCommands(TURN_LEFT);
        assertPositionAndDirection(0, 0, SOUTH);
    }

    @Test
    public void fromSouthTurnEast() {
        givenPositionAndDirection(0, 0, SOUTH);
        executeCommands(TURN_LEFT);
        assertPositionAndDirection(0, 0, EAST);
    }

    @Test
    public void fromEastTurnNorth() {
        givenPositionAndDirection(0, 0, EAST);
        executeCommands(TURN_LEFT);
        assertPositionAndDirection(0, 0, NORTH);
    }

    @Test
    public void fromNorthTurnEast() {
        givenPositionAndDirection(0, 0, NORTH);
        executeCommands(TURN_RIGHT);
        assertPositionAndDirection(0, 0, EAST);
    }

    @Test
    public void fromEastTurnSouth() {
        givenPositionAndDirection(0, 0, EAST);
        executeCommands(TURN_RIGHT);
        assertPositionAndDirection(0, 0, SOUTH);
    }

    @Test
    public void fromSouthTurnWest() {
        givenPositionAndDirection(0, 0, SOUTH);
        executeCommands(TURN_RIGHT);
        assertPositionAndDirection(0, 0, WEST);
    }

    @Test
    public void fromWestTurnNorth() {
        givenPositionAndDirection(0, 0, WEST);
        executeCommands(TURN_RIGHT);
        assertPositionAndDirection(0, 0, NORTH);
    }

    @Test(expected = ObstacleFoundException.class)
    public void obstacleAhead() {
        givenPositionAndDirection(10, 9, SOUTH);
        executeCommands(MOVE_FORWARD);
    }

    @Test
    public void clockwiseAroundObstacle() {
        givenPositionAndDirection(9, 9, EAST);
        executeCommands(MOVE_FORWARD, MOVE_FORWARD, TURN_RIGHT, MOVE_FORWARD, MOVE_FORWARD, TURN_RIGHT, MOVE_FORWARD, MOVE_FORWARD, TURN_RIGHT, MOVE_FORWARD, MOVE_FORWARD);
        assertPositionAndDirection(9, 9, NORTH);
    }

    @Test
    public void counterClockwiseAroundObstacle_ThenHitItAndStop() {
        givenPositionAndDirection(11, 9, WEST);

        try {
            executeCommands(MOVE_FORWARD, MOVE_FORWARD, TURN_LEFT, MOVE_FORWARD, MOVE_FORWARD, TURN_LEFT, MOVE_FORWARD, MOVE_FORWARD, TURN_LEFT, MOVE_FORWARD, MOVE_FORWARD, MOVE_BACKWARD, TURN_LEFT, MOVE_FORWARD);
            fail();
        } catch (ObstacleFoundException exception) {
            assertPositionAndDirection(11, 10, WEST);
        }
    }
}
