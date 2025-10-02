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
    @Test
    void testReachableNoTreasureReturnsFalse() {
        int[][] maze = {
            {0, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 0, 1},
        };

        assertFalse(MazeSolver.reachable(0, 0, maze));
    }

     @Test
    void testReachableMultipleTreasuresValidPathToOneReturnsTrue() {
        int[][] maze = {
            {0, 1, 3, 1, 1, 1},
            {0, 1, 1, 0, 0, 0},
            {0, 0, 1, 0, 1, 0},
            {1, 0, 1, 3, 1, 0},
            {1, 0, 1, 1, 1, 0},
            {1, 0, 0, 0, 0, 0}
        };
        assertTrue(MazeSolver.reachable(0, 0, maze));
    }

    // TODO 2: Write good tests for solve

     @Test
    void testSolveableNoTreasureReturnsNull() {
        int[][] maze = {
            {0, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 0, 1},
        };

        List<Location> path = MazeSolver.solveable(0, 0, maze);
        assertNull(path);
    }

      @Test
    void testSolveableNoValidPathReturnsNull() {
        int[][] maze = {
            {0, 0, 0, 1, 1},
            {0, 1, 1, 0, 0},
            {0, 0, 1, 0, 1},
            {0, 0, 1, 3, 1},
            {1, 1, 1, 1, 1}
        };

        List<Location> path = MazeSolver.solveable(2, 1, maze);
        assertNull(path);
    }

    void testSolveableValidPathOneRegionReturnsPath() {
        int[][] maze = {
            {1, 0, 0, 0, 1, 1},
            {0, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 1},
            {1, 0, 0, 1, 3, 1},
        };

        List<Location> path = MazeSolver.solveable(3, 2, maze);
        assertNotNull(path);
        assertEquals(new Location(3, 2), path.get(0));
        assertEquals(new Location(3, 4), path.get(path.size()-1));
    }
}
