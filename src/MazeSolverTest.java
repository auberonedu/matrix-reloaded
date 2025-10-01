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
    void testReachable_ThrowsExceptionOutOfBounds() {
        int[][] maze = {
            {1, 0, 0, 0, 1, 1},
            {0, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 1},
            {1, 0, 0, 1, 3, 1},
        };

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            MazeSolver.reachable(-1, 0, maze);
        });
        assertEquals("Out of bounds location: -1, 0", ex.getMessage());
    }

    @Test
    void testReachable_AroundTheOutside_AccessableGoal() {
        int[][] maze = {
            {0, 1, 3, 0, 0},
            {0, 1, 1, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
        };
        assertTrue(MazeSolver.reachable(0, 0, maze));
    }

    @Test
    void testReachable_AroundTheOutside_InaccessableGoal() {
        int[][] maze = {
            {0, 1, 3, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
        };
        assertFalse(MazeSolver.reachable(0, 0, maze));
    }

    @Test
    void testReachable_StartIsEnd() {
        int[][] maze = {
            {3, 1, 1, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
        };
        assertTrue(MazeSolver.reachable(0, 0, maze));
    }

    @Test
    void testReachable_Spiral_AccessableGoal() {
        int[][] maze = {
            {0, 1, 0, 0, 0, 0},
            {0, 1, 0, 1, 1, 0},
            {0, 1, 0, 3, 1, 0},
            {0, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0},
        };
        assertTrue(MazeSolver.reachable(0, 0, maze));
    }

    @Test
    void testReachable_Spiral_InaccessableGoal() {
        int[][] maze = {
            {0, 1, 0, 0, 0, 0},
            {0, 1, 0, 1, 1, 0},
            {0, 1, 1, 3, 1, 0},
            {0, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0},
        };
        assertFalse(MazeSolver.reachable(0, 0, maze));
    }

    // TODO 2: Write good tests for solve
    @Test
    void testSolve_SingleLocation() {
        int[][] maze = {
            {3}
        };

        List<Location> validPath = new ArrayList<>();
        validPath.add(new Location(0, 0));

        List<Location> testPath = MazeSolver.solve(0, 0, maze);

        assertEquals(validPath.get(0), testPath.get(0));
    }

    @Test
    void testSolve_ValidPath() {
        int[][] maze = {
            {1, 0, 0, 0, 1, 1},
            {0, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 1},
            {1, 0, 0, 1, 3, 1},
        };

        List<Location> validPath = new ArrayList<>();
        validPath.add(new Location(1, 0));
        validPath.add(new Location(1, 1));
        validPath.add(new Location(0, 1));
        validPath.add(new Location(0, 2));
        validPath.add(new Location(0, 3));
        validPath.add(new Location(1, 3));
        validPath.add(new Location(1, 4));
        validPath.add(new Location(2, 4));
        validPath.add(new Location(3, 4));

        List<Location> solvePath = MazeSolver.solve(1, 0, maze);

        assertEquals(validPath.get(0), solvePath.get(0));
        assertEquals(validPath.get(validPath.size()-1), solvePath.get(solvePath.size()-1));
        assertEquals(validPath.size(), solvePath.size());
    }

    @Test
    void testSolve_TwoValidPaths_SameDistance() {
        int[][] maze = {
          {0,0,0},
          {0,1,0},
          {0,3,0},
        };

        // the path should return the left path based on priority but right path should also be valid
        List<Location> validPath = new ArrayList<>();
        validPath.add(new Location(0, 1));
        validPath.add(new Location(0, 2));
        validPath.add(new Location(1, 2));
        validPath.add(new Location(2, 2));
        validPath.add(new Location(2, 1));
        

        List<Location> solvePath = MazeSolver.solve(0, 1, maze);
        System.out.println(solvePath);
        assertEquals(validPath.get(0), solvePath.get(0));
        assertEquals(validPath.get(validPath.size()-1), solvePath.get(solvePath.size()-1));
        assertEquals(validPath.size(), solvePath.size()); // same path length for both paths(5 moves)
    }

    @Test
    void testSolve_TwoValidPaths_VariableDistance() {
        int[][] maze = {
          {0,0,0,0},
          {0,1,1,0},
          {0,0,3,0},
        };

        // the path should return the left path based on priority but right path should also be valid
        List<Location> validPath = new ArrayList<>();
        validPath.add(new Location(0, 2));
        validPath.add(new Location(0, 3));
        validPath.add(new Location(1, 3));
        validPath.add(new Location(2, 3));
        validPath.add(new Location(2, 2));
        
        List<Location> solvePath = MazeSolver.solve(0, 2, maze);
        System.out.println(solvePath);
        assertEquals(validPath.get(0), solvePath.get(0));
        assertEquals(validPath.get(validPath.size()-1), solvePath.get(solvePath.size()-1));
    }

    @Test
    void testSolve_NoValidPath() {
        int[][] maze = {
            {0, 0, 0, 1, 1},
            {0, 1, 1, 0, 0},
            {0, 0, 1, 0, 1},
            {0, 0, 1, 3, 1},
            {1, 1, 1, 1, 1}
        };

        List<Location> validPath = new ArrayList<>();
        validPath.add(new Location(1, 0));
        validPath.add(new Location(1, 1));
        validPath.add(new Location(0, 1));
        validPath.add(new Location(0, 2));
        validPath.add(new Location(0, 3));
        validPath.add(new Location(1, 3));
        validPath.add(new Location(1, 4));
        validPath.add(new Location(2, 4));
        validPath.add(new Location(3, 4));

        List<Location> solvePath = MazeSolver.solve(1, 0, maze);

        assertEquals(null, solvePath);
    }

    @Test
    void testSolve_ThrowsExceptionInsideWall() {
        int[][] maze = {
            {1, 0, 0, 0, 1, 1},
            {0, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 1},
            {1, 0, 0, 1, 3, 1},
        };

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            MazeSolver.solve(0, 0, maze);
        });
        assertEquals("Location inside a wall: 0, 0", ex.getMessage());
    }

    @Test
    void testSolve_ThrowsExceptionOutOfBounds() {
        int[][] maze = {
            {1, 0, 0, 0, 1, 1},
            {0, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 1},
            {1, 0, 0, 1, 3, 1},
        };

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            MazeSolver.solve(-1, 0, maze);
        });
        assertEquals("Out of bounds location: -1, 0", ex.getMessage());
    }
}