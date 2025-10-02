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
    void testReachableStartingRowLessThanZero() {
        int[][] maze = {
            {1, 0, 0, 0, 1, 1},
            {0, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 1},
            {1, 0, 0, 1, 3, 1},
        };

        assertThrows(IllegalArgumentException.class,
            ()->MazeSolver.reachable(-1, 4, maze)
        );
    }

    @Test
    void testReachableStartingColLessThanZero() {
        int[][] maze = {
            {1, 0, 0, 0, 1, 1},
            {0, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 1},
            {1, 0, 0, 1, 3, 1},
        };

        assertThrows(IllegalArgumentException.class,
            ()->MazeSolver.reachable(2, -1, maze)
        );
    }

    @Test
    void testReachableStartingRowGreaterThanLength() {
        int[][] maze = {
            {1, 0, 0, 0, 1, 1},
            {0, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 1},
            {1, 0, 0, 1, 3, 1},
        };

        assertThrows(IllegalArgumentException.class,
            ()->MazeSolver.reachable(4, 4, maze)
        );
    }

    @Test
    void testReachableStartingColGreaterThanLength() {
        int[][] maze = {
            {0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 1},
            {1, 0, 0, 1, 3, 1},
        };

        assertThrows(IllegalArgumentException.class,
            ()->MazeSolver.reachable(0,6, maze)
        );
    }

    @Test
    void testReachableStartingOnTreasure() {
        int[][] maze = {
            {1, 0, 0, 0, 1, 1},
            {0, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 1},
            {1, 0, 0, 1, 3, 1},
        };

        assertTrue(MazeSolver.reachable(3, 4, maze));
    }

    // TODO 2: Write good tests for solve
    @Test
    void testSolveWithOneRoute() {
        int[][] maze = {
            {1, 0, 1},
            {1, 0, 1},
            {1, 0, 1},
            {1, 3, 1},
        };

        List<Location> oneWay = List.of(new Location(0, 1), new Location(1, 1), 
        new Location(2, 1), new Location(3, 1));

        assertEquals(oneWay, MazeSolver.solve(0, 1, maze));
    }

    @Test
    void testSolveWithTwoRoutes() {
        int[][] maze = {
            {0, 0, 0},
            {0, 1, 0},
            {0, 1, 0},
            {0, 3, 0},
        };

        List<Location> firstWay = List.of(new Location(0, 1), new Location(0, 0), 
        new Location(1, 0), new Location(2, 0), new Location(3, 0), new Location(3, 1));

        List<Location> secondWay = List.of(new Location(0, 1), new Location(0, 2), 
        new Location(1, 2), new Location(2, 2), new Location(3, 2), new Location(3, 1));

        assertTrue(MazeSolver.solve(0, 1, maze).equals(firstWay) || MazeSolver.solve(0, 1, maze).equals(secondWay));
        // assertEquals(secondWay, MazeSolver.solve(0, 1, maze)); ----> THIS IS THE CORRECT PATH BUT MULTIPLE CAN BE CORRECT, THUS THE TESTING FOR MULTIPLE PATHS ABOVE
    }

    @Test
    void testSolveWithNoRoutes() {
        int[][] maze = {
            {1, 0, 1},
            {1, 0, 1},
            {1, 1, 1},
            {1, 3, 1},
        };

        assertNull(MazeSolver.solve(0, 1, maze));
    }

    @Test
    void testSolveWithRowLessThanZero() {
        int[][] maze = {
            {1, 0, 1},
            {1, 0, 1},
            {1, 1, 1},
            {1, 3, 1},
        };

        assertThrows(IllegalArgumentException.class,
            ()->MazeSolver.solve(-1,1, maze)
        );
    }

    @Test
    void testSolveWithColLessThanZero() {
        int[][] maze = {
            {1, 0, 1},
            {1, 0, 1},
            {1, 1, 1},
            {1, 3, 1},
        };

        assertThrows(IllegalArgumentException.class,
            ()->MazeSolver.solve(1, -1, maze)
        );
    }

    @Test
    void testSolveWithRowGreaterThanLength() {
        int[][] maze = {
            {1, 0, 1},
            {1, 0, 1},
            {1, 1, 1},
            {1, 3, 1},
        };

        assertThrows(IllegalArgumentException.class,
            ()->MazeSolver.solve(4, 1, maze)
        );
    }

    @Test
    void testSolveWithColGreaterThanLength() {
        int[][] maze = {
            {0, 0, 0},
            {1, 0, 1},
            {1, 1, 1},
            {1, 3, 1},
        };

        assertThrows(IllegalArgumentException.class,
            ()->MazeSolver.solve(0, 3, maze)
        );
    }

    @Test
    void testSolveLandingOnWall() {
        int[][] maze = {
            {0, 0, 0},
            {1, 0, 1},
            {1, 1, 1},
            {1, 3, 1},
        };

        assertThrows(IllegalArgumentException.class,
            ()->MazeSolver.solve(1, 0, maze)
        );
    }

    @Test
    void testSolveLandingOnTreasure() {
        int[][] maze = {
            {0, 0, 0},
            {1, 0, 1},
            {1, 1, 1},
            {1, 3, 1},
        };

        List<Location> onePlace = List.of(new Location(3, 1));
        assertEquals(onePlace, MazeSolver.solve(3, 1, maze));
    }
}
