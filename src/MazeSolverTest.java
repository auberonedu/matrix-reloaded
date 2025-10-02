import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class MazeSolverTest {
    @Test
    void testReachableValidPathOneRegionReturnsTrue() {
        int[][] maze = {
            {1, 0, 0, 0, 1, 1},
            {0, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 1},
            {1, 0, 0, 1, 3, 1},
        };

        assertTrue(MazeSolver.reachable(3, 2, maze));
    }

    @Test
    void testReachableValidPathTwoRegionReturnsTrue() {
        int[][] maze = {
            {0, 0, 0, 1, 1},
            {0, 1, 1, 0, 0},
            {0, 0, 1, 0, 1},
            {0, 0, 1, 3, 1},
            {1, 1, 1, 1, 1}
        };

        assertTrue(MazeSolver.reachable(1, 3, maze));
    }

    @Test
    void testReachableNoValidPathReturnsFalse() {
        int[][] maze = {
            {0, 0, 0, 1, 1},
            {0, 1, 1, 0, 0},
            {0, 0, 1, 0, 1},
            {0, 0, 1, 3, 1},
            {1, 1, 1, 1, 1}
        };

        assertFalse(MazeSolver.reachable(2, 1, maze));
    }

    @Test
    void testReachableStartingInWallThrowsIllegalArgumentException() {
        int[][] maze = {
            {1, 0, 0, 0, 1, 1},
            {0, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 1},
            {1, 0, 0, 1, 3, 1},
        };

        assertThrows(IllegalArgumentException.class,
            ()->MazeSolver.reachable(0, 0, maze)
        );
    }

    // TODO 1: Write more tests for reachable

    // TODO 2: Write good tests for solve
    @Test
    void testSolveStartingInWallThrowsIllegalArgumentException() {
        int[][] maze = {
            {1, 0, 0, 0, 1, 1},
            {0, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 1},
            {1, 0, 0, 1, 3, 1},
        };

        assertThrows(IllegalArgumentException.class,
            ()->MazeSolver.solve(0, 0, maze)
        );
    }

    @Test
    void testSolveNoPathFound() {
        int[][] maze = {
            {1, 0, 0, 0, 1, 1},
            {0, 0, 1, 0, 1, 0},
            {1, 0, 0, 1, 0, 1},
            {1, 0, 0, 1, 3, 1},
        };

        List<Location> path = MazeSolver.solve(0, 1, maze);

        assertNull(path);
    }

    @Test
    void testStartAtTreasurePathFound() {
        int[][] maze = {
            {1, 0, 0, 0, 1, 1},
            {0, 0, 1, 0, 1, 0},
            {1, 0, 0, 1, 0, 1},
            {1, 0, 0, 1, 3, 1},
        };

        List<Location> path = MazeSolver.solve(3, 4, maze);

        assertNotNull(path);
    }
}
