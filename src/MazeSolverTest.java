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

    @Test
    void testReachableNoValidPathReturnsTrueOnTreasure() {
        int[][] maze = {
            {3}
        };

        assertTrue(MazeSolver.reachable(0, 0, maze));
    }

    @Test
    void testReachableNoValidPathReturnsTrueOnTreasure() {
        int[][] maze = {
            {1}
        };

        assertThrows(IllegalArgumentException.class,
            ()->MazeSolver.reachable(0, 0, maze)
        );
    }

    @Test
    void testReachableNoValidPathReturnsLongPath() {
        int[][] maze = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
            {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        };

        assertTrue(MazeSolver.reachable(0, 0, maze));
    }

    @Test
    void testSolveableValidPathOneRegionReturnsList() {
        int[][] maze = {
            {1, 0, 1, 0},
            {1, 0, 1, 0},
            {1, 0, 1, 1},
            {1, 0, 0, 3}
        };
        assertEquals(List.of(
            new Location(0, 1),
            new Location(1, 1),
            new Location(2, 1),
            new Location(3, 1),
            new Location(3, 2),
            new Location(3, 3)),
        (MazeSolver.solve(0, 1, maze)));
    }

    @Test
    void testSolveableValidPathTwoRegionReturnsList() {
        int[][] maze = {
            {1, 0, 0, 0},
            {1, 0, 1, 0},
            {1, 0, 1, 0},
            {1, 0, 0, 3}
        };
        assertEquals(List.of(
            new Location(0, 1),
            new Location(1, 1),
            new Location(2, 1),
            new Location(3, 1),
            new Location(3, 2),
            new Location(3, 3)),
        (MazeSolver.solve(0, 1, maze)));
    }

    @Test
    void testSolveableValidPathTwoRegionReturnsSingleLocation() {
        int[][] maze = {
            {3}
        };
        assertEquals(List.of(
            new Location(0, 0)),
        (MazeSolver.solve(0, 0, maze)));
    }

    @Test
    void testSolveableValidPathOneRegionThrowsIllegalArgExcept() {
        int[][] maze = {
            {1, 0, 1, 0},
            {1, 0, 1, 0},
            {1, 0, 1, 1},
            {1, 0, 0, 3}
        };
        assertThrows(IllegalArgumentException.class,
            ()->MazeSolver.solve(0, 0, maze)
        );
    }

    @Test
    void testSolveableValidPathOneRegionReturnsNull() {
        int[][] maze = {
            {1, 0, 1, 0},
            {1, 0, 1, 0},
            {1, 1, 1, 1},
            {1, 0, 0, 3}
        };
        assertEquals(null,(MazeSolver.solve(0, 1, maze)));
    }
}
