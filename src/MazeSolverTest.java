import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class MazeSolverTest {
    @Test
    void testReachableValidPathOneRegionReturnsTrue() {
        int[][] maze = {
                { 1, 0, 0, 0, 1, 1 },
                { 0, 0, 1, 0, 0, 0 },
                { 1, 0, 0, 1, 0, 1 },
                { 1, 0, 0, 1, 3, 1 },
        };

        assertTrue(MazeSolver.reachable(3, 2, maze));
    }

    @Test
    void testReachableValidPathTwoRegionReturnsTrue() {
        int[][] maze = {
                { 0, 0, 0, 1, 1 },
                { 0, 1, 1, 0, 0 },
                { 0, 0, 1, 0, 1 },
                { 0, 0, 1, 3, 1 },
                { 1, 1, 1, 1, 1 }
        };

        assertTrue(MazeSolver.reachable(1, 3, maze));
    }

    @Test
    void testReachableNoValidPathReturnsFalse() {
        int[][] maze = {
                { 0, 0, 0, 1, 1 },
                { 0, 1, 1, 0, 0 },
                { 0, 0, 1, 0, 1 },
                { 0, 0, 1, 3, 1 },
                { 1, 1, 1, 1, 1 }
        };

        assertFalse(MazeSolver.reachable(2, 1, maze));
    }

    @Test
    void testReachableStartingInWallThrowsIllegalArgumentException() {
        int[][] maze = {
                { 1, 0, 0, 0, 1, 1 },
                { 0, 0, 1, 0, 0, 0 },
                { 1, 0, 0, 1, 0, 1 },
                { 1, 0, 0, 1, 3, 1 },
        };

        assertThrows(IllegalArgumentException.class,
                () -> MazeSolver.reachable(0, 0, maze));
    }

    // TODO 1: Write more tests for reachable
    @Test
    void testReachableStartingOutOfBoundsThrowsIllegalArgumentException() {
        int[][] maze = {
                { 1, 0, 0, 0, 1, 1 },
                { 0, 0, 1, 0, 0, 0 },
                { 1, 0, 0, 1, 0, 1 },
                { 1, 0, 0, 1, 3, 1 },
        };

        assertThrows(IllegalArgumentException.class,
                () -> MazeSolver.reachable(4, 2, maze));
    }

    @Test
    void testReachableStatingAtEnd() {
        int[][] maze = {
                { 1, 0, 0, 0, 1, 1 },
                { 0, 0, 1, 0, 0, 0 },
                { 1, 0, 0, 1, 0, 1 },
                { 1, 0, 0, 1, 3, 1 },
        };

        assertTrue(MazeSolver.reachable(3, 4, maze));
    }

    // TODO 2: Write good tests for solve
    @Test
    void testSolveValidPath() {
        int[][] maze = {
                { 1, 0, 0, 0, 1, 1 },
                { 0, 0, 1, 0, 0, 0 },
                { 1, 0, 0, 1, 0, 1 },
                { 1, 0, 0, 1, 3, 1 },
        };

        Location start = new Location(0, 1);
        Location end = new Location(3, 4);
        List<Location> path = MazeSolver.solve(0, 1, maze);

        assertTrue(start.equals(path.get(0)));
        assertTrue(end.equals(path.get(6)));
    }

}
