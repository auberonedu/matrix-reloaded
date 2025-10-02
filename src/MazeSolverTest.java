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
    void testReachableStartingOnTreasureReturnsTrue() {
        int[][] maze = {
            {3, 1},
            {0, 0}
        };
        assertTrue(MazeSolver.reachable(0, 0, maze));
    }

    @Test
    void testReachableSingleCellNoTreasureReturnsFalse() {
        int[][] maze = {
            {0}
        };
        assertFalse(MazeSolver.reachable(0, 0, maze));
    }

    @Test
    void testReachableSingleCellTreasureReturnsTrue() {
        int[][] maze = {
            {3}
        };
        assertTrue(MazeSolver.reachable(0, 0, maze));
    }

    @Test
    void testReachableStartOutOfBounds() {
        int[][] maze = {
            {0, 0},
            {0, 3}
        };
        assertThrows(IllegalArgumentException.class,
            ()->MazeSolver.reachable(-1, 0, maze)
        );
    }

    // TODO 2: Write good tests for solve
    @Test
    void testSolvePath() {
        int[][] maze = {
            {1, 0, 0, 0, 1, 1},
            {0, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 1},
            {1, 0, 0, 1, 3, 1},
        };

        var path = MazeSolver.solve(0, 1, maze);
        assertNotNull(path);
        assertTrue(path.size() >= 5);
        assertEquals(new Location(0, 1), path.get(0));
        assertEquals(new Location(3, 4), path.get(path.size() - 1));
    }
    @Test
    void testSolveNoPathReturnsNull() {
        int[][] maze = {
            {0, 0, 0, 1, 1},
            {0, 1, 1, 0, 0},
            {0, 0, 1, 0, 1},
            {0, 0, 1, 3, 1},
            {1, 1, 1, 1, 1}
        };

        assertNull(MazeSolver.solve(2, 1, maze));
    }

    @Test
    void testSolveStartOnTreasure() {
        int[][] maze = {
            {3, 1},
            {0, 0}
        };
        
        List<Location> path = MazeSolver.solve(0, 0, maze);
        assertNotNull(path);
        assertEquals(1, path.size());
        assertEquals(new Location(0, 0), path.get(0));
    }

}
