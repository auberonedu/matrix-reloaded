import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;
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
    void testReachableStartOnTreasureReturnTrue(){
        int[][]maze ={
            {3,0},
            {1,1}
        };
        //start on treasure
        assertTrue(MazeSolver.reachable(0, 0, maze));
    }

    @Test 
    void testReachableTreasureSurroundedByWallsReturnFalse(){
        int[][]maze={
            {0, 1, 0},
            {1, 3, 1},
            {0, 1, 0}
        };
        //treasure cant be reached
        assertFalse(MazeSolver.reachable(0, 0, maze));
    }

    // TODO 2: Write good tests for solve
    @Test
    void testSolveValidPathReturnsNull(){
        int[][] maze={
            {0, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 3}
        };
        //start at (0,0)
        var path= MazeSolver.solve(0, 0, maze);
        //no path
        assertNull(path);

    }

   
}
