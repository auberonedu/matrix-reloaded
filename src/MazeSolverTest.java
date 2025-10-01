import static org.junit.Assert.*;

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
    void testReachableStartOnTreasureReturnsTrue() {
        int[][] maze = {
            {1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1},
            {1, 3, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1}
        };

        assertTrue(MazeSolver.reachable(2, 1, maze));
    }

    @Test
    void testReachableStartingColumnOutOfBoundsThrowsIllegalArgumentException() {
        int[][] maze = {
            {1, 0, 0, 0, 1, 0},
            {0, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 0},
            {1, 0, 0, 1, 3, 0},
            {0, 1, 0, 0, 0, 0},
        };

        assertThrows(IllegalArgumentException.class,
            ()->MazeSolver.reachable(1, 8, maze)
        );
    }

    @Test
    void testReachableStartingRowOutOfBoundsThrowsIllegalArgumentException() {
        int[][] maze = {
            {1, 0, 0, 0, 1, 0},
            {0, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 0},
            {1, 0, 0, 1, 3, 0},
            {0, 1, 0, 0, 0, 0},
        };

        assertThrows(IllegalArgumentException.class,
            ()->MazeSolver.reachable(6, 2, maze)
        );
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
    
    @Test
    void testReachableMultipleTreasuresValidPathToBothReturnsTrue() {
        int[][] maze = {
            {0, 1, 3, 0, 1, 1},
            {0, 1, 1, 0, 0, 0},
            {0, 0, 1, 0, 1, 0},
            {1, 0, 1, 3, 1, 0},
            {1, 0, 1, 1, 1, 0},
            {1, 0, 0, 0, 0, 0}
        };
        assertTrue(MazeSolver.reachable(0, 0, maze)); 
    }

    // TODO 2: Write good tests for solve

    
}
