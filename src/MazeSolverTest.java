import static org.junit.Assert.*;

import java.util.ArrayList;
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
    void testReachableValidPathStartOnTreasureReturnsTrue() {
        int[][] maze = {
            {0, 0, 0, 1, 1},
            {0, 1, 1, 0, 0},
            {0, 0, 1, 0, 1},
            {0, 0, 1, 3, 1},
            {1, 1, 1, 1, 1}
        };

        assertTrue(MazeSolver.reachable(3, 3, maze));
    }

    //empty maze
    @Test
    void testReachableEmptyMaze() {
        int[][] maze = {};

        assertThrows(IllegalArgumentException.class,
            ()->MazeSolver.reachable(0, 0, maze)
        );    
    }

    // TODO 2: Write good tests for solve

    //return path long
    @Test
    void testSolveValidPathReturnLongPath() {
        int[][] maze = {
            {1, 0, 0, 0, 1, 1},
            {0, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 1},
            {1, 0, 0, 1, 0, 3},
        };

        List<Location> expected = List.of(
            new Location(1,0),
            new Location(1,1),
            new Location(0,1),
            new Location(0,2),
            new Location(0,3),
            new Location(1,3),
            new Location(1,4),
            new Location(2,4),
            new Location(3,4),
            new Location(3,5)
        );

        assertEquals(expected , MazeSolver.solve(1, 0, maze));
    }

    //return path short
    @Test
    void testSolveValidPathReturnShortPath() {
        int[][] maze = {
            {1, 0, 0, 0, 1, 1},
            {0, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 1},
            {1, 0, 0, 1, 0, 3},
        };

        List<Location> expected = List.of(
            new Location(2,4),
            new Location(3,4),
            new Location(3,5)
        );

        assertEquals(expected , MazeSolver.solve(2, 4, maze));
    }

    //return path narrow margin
    @Test
    void testSolveValidPathReturnNarrowPath() {
        int[][] maze = {
            {1, 1, 0, 0, 0, 1},
            {1, 1, 0, 1, 0, 1},
            {1, 1, 0, 1, 0, 3},
            {1, 1, 0, 1, 1, 1},
        };

        List<Location> expected = List.of(
            new Location(3,2),
            new Location(2,2),
            new Location(1,2),
            new Location(0,2),
            new Location(0,3),
            new Location(0,4),
            new Location(1,4),
            new Location(2,4),
            new Location(2,5)
        );

        assertEquals(expected , MazeSolver.solve(3, 2, maze));
    }

    //test start on treasure
    @Test
    void testSolveValidPathReturnStartPath() {
        int[][] maze = {
            {1, 1, 0, 0, 0, 1},
            {1, 1, 0, 1, 0, 1},
            {1, 1, 0, 1, 0, 0},
            {1, 1, 3, 1, 1, 1},
        };

        List<Location> expected = List.of(
            new Location(3,2)
        );

        assertEquals(expected , MazeSolver.solve(3, 2, maze));
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
    void testSolveNoValidPathReturnsNull() {
        int[][] maze = {
            {0, 0, 0, 1, 1},
            {0, 1, 1, 0, 0},
            {0, 0, 1, 0, 1},
            {0, 0, 1, 3, 1},
            {1, 1, 1, 1, 1}
        };

        assertEquals(null,MazeSolver.solve(2, 1, maze));
    }

    @Test
    void testSolveEmptyMazeThrowsIllegalArgumentException() {
        int[][] maze = {
        };

        assertThrows(IllegalArgumentException.class,
            ()->MazeSolver.solve(0, 0, maze)
        );
    }
}
