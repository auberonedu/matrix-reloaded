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
    void testReachableLoopAroundTrue() {
        int[][] maze = {
            {0, 1, 0, 0, 0},
            {0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0},
            {0, 0, 0, 1, 3}
        };

        assertTrue(MazeSolver.reachable(0, 0, maze));
    }

    @Test
    void testReachableLoopAroundFalse() {
        int[][] maze = {
            {0, 1, 0, 0, 0},
            {0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0},
            {0, 1, 0, 1, 1},
            {0, 0, 0, 1, 3}
        };

        assertTrue(MazeSolver.reachable(0, 0, maze));
    }

    // TODO 2: Write good tests for solve

    @Test
    void testSmallRoundAboutTrue(){
        int[][] maze = {
            {0, 0, 0, 0},
            {1, 1, 1, 0},
            {3, 0, 0, 0}
        };
        assertEquals(List.of(
            new Location(0, 0),
            new Location(0, 1),
            new Location(0, 2),
            new Location(0, 3),
            new Location(1, 3),
            new Location(2, 3),
            new Location(2, 2),
            new Location(2, 1),
            new Location(2, 0)
        ), MazeSolver.solve(0, 0, maze));
    }
    @Test
    void testSmallRoundAboutFalse(){
        int[][] maze = {
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {3, 0, 0, 0}
        };
        assertNotEquals(List.of(
            new Location(0, 0),
            new Location(0, 1),
            new Location(0, 2),
            new Location(0, 3),
            new Location(1, 3),
            new Location(2, 3),
            new Location(2, 2),
            new Location(2, 1),
            new Location(2, 0)
        ), MazeSolver.solve(0, 0, maze));
    }
}
