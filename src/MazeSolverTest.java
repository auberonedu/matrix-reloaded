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
    void testSolveStartingColumnOutOfBoundsThrowsIllegalArgumentException() {
        int[][] maze = {
            {1, 0, 0, 0, 1, 0},
            {0, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 0},
            {1, 0, 0, 1, 3, 0},
            {0, 1, 0, 0, 0, 0},
        };

        assertThrows(IllegalArgumentException.class,
            ()->MazeSolver.solve(1, 8, maze)
        );
    }

    @Test
    void testSolveStartingRowOutOfBoundsThrowsIllegalArgumentException() {
        int[][] maze = {
            {1, 0, 0, 0, 1, 0},
            {0, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 0},
            {1, 0, 0, 1, 3, 0},
            {0, 1, 0, 0, 0, 0},
        };

        assertThrows(IllegalArgumentException.class,
            ()->MazeSolver.solve(6, 2, maze)
        );
    }

    @Test
    void testSolveStartOnTreasureReturnsCorrectList() {
        int[][] maze = {
            {1, 0, 0, 0},
            {0, 0, 1, 0},
            {0, 1, 0, 1},
            {0, 0, 0, 3},
        };

        List<Location> expectedPath = List.of( 
            new Location(3, 3) 
        );

        List<Location> actualPath = MazeSolver.solve(3, 3, maze);
        assertEquals(expectedPath, actualPath);
    }

    @Test
    void testSolveOneValidPathReturnsCorrectList() {
        int[][] maze = {
            {1, 0, 0, 0},
            {0, 0, 1, 0},
            {0, 1, 0, 1},
            {0, 0, 0, 3},
        };

        List<Location> expectedPath = List.of(
            new Location(0, 2),
            new Location(0, 1),
            new Location(1, 1),
            new Location(1, 0),
            new Location(2, 0),
            new Location(3, 0),
            new Location(3, 1),
            new Location(3, 2), 
            new Location(3, 3) 
        );

        List<Location> actualPath = MazeSolver.solve(0, 2, maze);
        assertEquals(expectedPath, actualPath);
    }

    @Test
    void testSolveTwoValidPathsReturnsCorrectList() {
        int[][] maze = {
            {0, 0, 0, 3},
            {1, 1, 0, 1},
            {0, 0, 0, 0},
            {0, 1, 1, 0}
        };

        List<Location> expectedPathA = List.of(
            new Location(3,0),
            new Location(2,0),
            new Location(2,1),
            new Location(2,2),
            new Location(1,2),
            new Location(0,2),
            new Location(0,3)
        );

        List<Location> expectedPathB = List.of(
            new Location(3,0),
            new Location(2,0),
            new Location(2,1),
            new Location(2,2),
            new Location(2,3),
            new Location(1,2),
            new Location(0,2),
            new Location(0,3)
        );

        List<Location> actualPath = MazeSolver.solve(3, 0, maze);
        assertTrue(actualPath.equals(expectedPathA) || actualPath.equals(expectedPathB));
    }

    @Test
    void testSolveNoValidPathReturnsNull() {
        int[][] maze = {
            {0, 0, 0, 0},
            {0, 1, 1, 0},
            {0, 1, 1, 1},
            {0, 0, 1, 3},
        };
        
        List<Location> actualPath = MazeSolver.solve(0, 0, maze);
        assertNull(actualPath);
    }

    @Test
    void testSolveNoTreasureReturnsNull() {
        int[][] maze = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
        };

        List<Location> actualPath = MazeSolver.solve(0, 0, maze);
        assertNull(actualPath);
    }
}
