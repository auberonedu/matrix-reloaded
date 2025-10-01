import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
    void testReachableStartingOffGridThrowsIllegalArgumentException() {
        int[][] maze = {
            {1, 0, 0, 0, 1, 1},
            {0, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 1},
            {1, 0, 0, 1, 3, 1},
        };

        assertThrows(IllegalArgumentException.class,
            ()->MazeSolver.reachable(8, 0, maze)
        );
    }

    @Test
    void testReachableStartTreasureTrue() {
        int[][] maze = {
            {3, 0, 0, 1, 1},
            {0, 1, 1, 0, 0},
            {0, 0, 1, 0, 1},
            {0, 0, 1, 1, 1},
            {1, 1, 1, 1, 1}
        };

        assertTrue(MazeSolver.reachable(0, 0, maze));
    }
    @Test
    void testReachableValidLongPathTrue() {
        int[][] maze = {
            {0, 0, 0, 0, 0, 0},
            {1, 1, 1, 0, 1, 0},
            {3, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0},
        };

        assertTrue(MazeSolver.reachable(3, 2, maze));
    }

    // TODO 2: Write good tests for solve

    @Test
    void testReachableOnePath() {
        int[][] maze = {
            {0, 1, 0, 0, 1, 1},
            {0, 0, 1, 0, 0, 0},
            {1, 3, 0, 1, 0, 1},
            {1, 0, 0, 1, 0, 1},
        };
        List<Location> path = new ArrayList<>();
        Location p1 = new Location(0,0);
        Location p2 = new Location(1,0);
        Location p3 = new Location(1,1);
        Location p4 = new Location(2,1);
        path.add(p1);
        path.add(p2);
        path.add(p3);
        path.add(p4);

       assertEquals(path, MazeSolver.solve(0, 0, maze));
    }

    @Test
    void testSolveTwoPaths() {
        int[][] maze = {
            {0, 0, 0, 1, 1},
            {0, 1, 1, 0, 0},
            {0, 0, 1, 0, 3},
            {0, 0, 1, 1, 1},
            {1, 1, 1, 1, 1}
        };
        List<Location> path = new ArrayList<>();
        Location p1 = new Location(1,3);
        Location p2 = new Location(1,4);
        Location p3 = new Location(2,4);
        
        path.add(p1);
        path.add(p2);
        path.add(p3);

        List<Location> path2 = new ArrayList<>();
        Location l1 = new Location(1,3);
        Location l2 = new Location(2,3);
        Location l3 = new Location(2,4);
        path2.add(l1);
        path2.add(l2);
        path2.add(l3);

        List<Location> actual = MazeSolver.solve(1, 3, maze);
        if(path == actual){
            assertEquals(path, actual);
        }else{
            assertEquals(path2, actual);
        }
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

        assertEquals(MazeSolver.solve(2, 1, maze), null);
    }

}
