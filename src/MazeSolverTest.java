import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import java.util.List;

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
    void testReachableOutOfBoundsThrows() {
        int[][] maze = {
            {0, 0, 0},
            {0, 3, 0},
            {0, 0, 0}
        };

        
    // if row too high
    assertThrows(IllegalArgumentException.class, () -> MazeSolver.reachable(3, 1, maze));

    // if column too high
    assertThrows(IllegalArgumentException.class, () -> MazeSolver.reachable(1, 3, maze));

    // if negative row
    assertThrows(IllegalArgumentException.class, () -> MazeSolver.reachable(-1, 0, maze));

    // if negative column
    assertThrows(IllegalArgumentException.class, () -> MazeSolver.reachable(0, -1, maze));
    }

    @Test
    void testReachableIfStartingAtTreasure() {
        int[][] maze = {
            {0, 0, 0},
            {0, 3, 0},
            {0, 0, 0}
        };

        assertTrue(MazeSolver.reachable(1, 1, maze));
    }

    @Test
    void testSolveNoValidPathReturnsNull() {
        int[][] maze = {
            {0, 0, 0, 1, 1},
            {0, 1, 1, 0, 0},
            {0, 0, 1, 0, 1},
            {0, 0, 1, 3, 1},
            {1, 1, 1, 1, 1}
        };

        assertEquals(null, MazeSolver.solve(2, 1, maze));
    }

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
    void testSolveIfStartingAtTreasure() {
        int[][] maze = {
            {0, 0 ,0 },
            {0, 3, 0},
            {0, 0, 0}
        };

        List<Location> path = MazeSolver.solve(1, 1, maze);

        assertEquals(new Location(1, 1), path.get(0));
    }

    @Test
    void testSolveMultipleValidPaths() {
        int[][] maze = {
            {1, 0, 0, 0, 1, 1},
            {0, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 3, 1},
        };

        List<Location> path = MazeSolver.solve(0, 1, maze);

        Location last = path.get(path.size()-1);
        assertEquals(3, maze[last.row()][last.col()]);
    }

    @Test
    void testSolveStartingOutOfBoundsThrows() {
        int[][] maze = {
            {0, 0, 0},
            {0, 3, 0},
            {0, 0, 0}
        };

        
    // if row too high
    assertThrows(IllegalArgumentException.class, () -> MazeSolver.solve(3, 1, maze));

    // if column too high
    assertThrows(IllegalArgumentException.class, () -> MazeSolver.solve(1, 3, maze));

    // if negative row
    assertThrows(IllegalArgumentException.class, () -> MazeSolver.solve(-1, 0, maze));

    // if negative column
    assertThrows(IllegalArgumentException.class, () -> MazeSolver.solve(0, -1, maze));
    }
}
