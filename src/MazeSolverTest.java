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
    // test for out of bounds
    @Test
    void testUnReachableStartingThrowsIllegalArgumentException() {
        int[][] maze = {
            {1, 0, 0, 1},
            {0, 0, 1, 0},
            {3, 0, 1, 1}
        };

        assertThrows(IllegalArgumentException.class,
            ()->MazeSolver.reachable(4, 6, maze)
        );
    }
    // no treasure
    @Test
    void testNoTreasureReturnsFalse() {
        int[][] maze = {
            {1, 0, 0, 0, 0, 1},
            {0, 0, 1, 0, 0, 1},
            {0, 0, 1, 1, 0, 0},
            {1, 1, 1, 0, 0, 0}
        };
        assertFalse(MazeSolver.reachable(0, 3, maze));
    }

    // TODO 2: Write good tests for solve
    // out of bounds test
    @Test
    void testOutOfBoundsForSolve() {
        int[][] maze = {
            {0, 0, 0, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 1, 1},
            {0, 0, 0, 0, 0, 1, 0},
            {1, 0, 0, 1, 1, 1, 0},
            {1, 0, 0, 0, 0, 0, 3}
        };
        assertThrows(IllegalArgumentException.class,
            ()->MazeSolver.solve(5, 6, maze)
        );
    }
    // wall test
    @Test
    void testInWallForSolve() {
        int[][] maze = {
            {1, 0, 0, 1},
            {0, 0, 1, 1},
            {0, 1, 1, 0},
            {0, 0, 0, 3},
        };
        assertThrows(IllegalArgumentException.class,
            ()->MazeSolver.solve(2, 1, maze)
        );
    }
    // no reachable treasure
    @Test
    void testNoReachableTreasureForSolve() {
        int[][] maze = {
            {1, 0, 0, 1},
            {0, 0, 1, 0},
            {0, 1, 0, 3}
        };
        List<Location> path = MazeSolver.solve(1, 0, maze);
        assertEquals(null, path);
    }
    // one route
    @Test
    void testOnePathForSolve() {
        int[][] maze = {
            {0, 1, 0, 0, 3},
            {0, 1, 1, 0, 1},
            {0, 0, 1, 0, 1},
            {0, 1, 0, 0, 1},
            {0, 0, 0, 1, 1}
        };
        List<Location> path = MazeSolver.solve(0, 0, maze);
        boolean finder = false;
        if(path.contains(new Location(0, 0))) {
            finder = true;
        }
        
        assertTrue(finder);
    }
    // multi route

}
