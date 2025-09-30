import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MazeSolver {
    public static void main(String[] args) {
        // startRow 0
        // startCol 1
        int[][] maze1 = {
            {1, 0, 0, 0, 1, 1},
            {0, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 1},
            {1, 0, 0, 1, 3, 1},
        };

        // [{0, 2}, {1, 1}] <-- what we expect to print; it worked!

        boolean[][] visited = new boolean[maze1.length][maze1[0].length];                       // STEP 8
        List<int[]> neighbors = validNeighbors(0,1, maze1, visited);          // STEP 9

        for (int[] neighbor : neighbors) {                                                      // STEP 10
            System.out.println(Arrays.toString(neighbor));
        }

        int[][] maze2 = {
            {0, 0, 0, 1, 1},
            {0, 1, 1, 0, 0},
            {0, 0, 1, 0, 1},
            {0, 0, 1, 3, 1},
            {1, 1, 1, 1, 1}
        };
    }

    /**
     * Returns whether it is possible to reach a treasure in a maze from a
     * given row / column position.
     * 
     * The maze is represented as a 2d rectangular array of ints where:
     * - 0 represents a passable space
     * - 1 represents a wall
     * - 3 represents a treasure
     * 
     * The player starts at the given row/column position and can move one
     * square at a time, up/down/left/right. The player cannot move diagonally
     * or off the edge of the board. They can also not move onto a wall.
     * 
     * Throws an IllegalArgumentException if the starting position is out of bounds of
     * the maze or is in a wall.
     * 
     * @param row the starting row of the player
     * @param col the starting column of the player
     * @param maze a 2d array of ints representing the maze
     * @return true if a treasure is reachable, false otherwise
     * @throws IllegalArgumentException if the position is out of bounds of the maze or is in a wall.
     */
    public static boolean reachable(int row, int col, int[][] maze) {
        // We will solve this together as a class.
        if (row < 0 || col < 0 || row >= maze.length || col >= maze[0].length) {                                 // STEP 14-CONTINUED
            throw new IllegalArgumentException("Out of bounds location: " + row + "," + col);
        }
        if (maze[row][col] == 1) {                                                                               // STEP 15
            throw new IllegalArgumentException("Location is in wall: " + row + "," + col);
        }

        boolean[][] visited = new boolean[maze.length][maze[0].length];                                          // STEP 11
        return reachable(row, col, maze, visited);                                                               // STEP 13
    }

    private static boolean reachable(int row, int col, int[][] maze, boolean[][] visited)                         // STEP 12
    {
        // make instant/early return base cases: [1]out of bound, [2]wall, [3]visited, [4]treasure-is-found       // STEP 14
        if (maze[row][col] == 3) return true;                                                                     // STEP 16 (visited taken care of in validNeighbors)

        visited[row][col] = true;                                                                                 // STEP 17

        List<int[]> neighbors = validNeighbors(row, col, maze, visited);                                          // STEP 18

        for (int[] neighbor : neighbors) {
            if (reachable(neighbor[0], neighbor[1], maze, visited)) {                                             // STEP 19
                return true;
            }
        }
        return false;
    }

    // return a list where inside it is integer arrays. ex, for startRow 0 & startCol 1 -> [{0, 2}, {1, 1}]
    public static List<int[]> validNeighbors(int startRow, int startCol, int[][] maze, boolean[][] visited) {    // STEP 1
        int[][] moves = {                                                                                        // STEP 2
            {-1, 0},     // UP
            {1, 0},      // DOWN
            {0, 1},      // RIGHT
            {0, -1}      // LEFT
        };

        List<int[]> neighbors = new ArrayList<>();                                                               // STEP 3: make list

        for (int[] move : moves) {
            int newRow = startRow + move[0];
            int newCol = startCol + move[1];                                                                     // STEP 4 --> check surroundings; for loop

            if (newRow >= 0 &&                                                                                   // STEP 5 --> checking we're inbounds & no wall && not visited. (recommends this method!)
                newRow < maze.length &&
                newCol >= 0 &&
                newCol < maze[0].length &&
                maze[newRow][newCol] != 1 &&
                !visited[newRow][newCol]) {
                    neighbors.add(new int[]{newRow, newCol});                                                   // STEP 6: add to list
                }
        }

        return neighbors;                                                                                       // STEP 7: return list 
    }

    /**
     * Returns a valid path from the starting location to a treasure in a maze
     * if one exists or null if there is no path.
     * 
     * The maze is represented as a 2d rectangular array of ints where:
     * - 0 represents a passable space
     * - 1 represents a wall
     * - 3 represents a treasure
     * 
     * The player starts at the given row/column position and can move one
     * square at a time, up/down/left/right. The player cannot move diagonally
     * or off the edge of the board. They can also not move onto a wall.
     * 
     * The returned path should include each position the player visits in order.
     * It should include both the starting position, and the position of the treasure.
     * 
     * If there are multiple valid paths, any one of them may be returned.
     * If there is NO valid path, it should return null. 
     * 
     * Throws an IllegalArgumentException if the starting position is out of bounds of
     * the maze or is in a wall.
     * 
     * @param row the starting row of the player
     * @param col the starting column of the player
     * @param maze a 2d array of ints representing the maze
     * @return a list of locations indiciating any valid path, or null if there is no valid path.
     * @throws IllegalArgumentException if the position is out of bounds of the maze or is in a wall.
     */
    public static List<Location> solve(int row, int col, int[][] maze) {
        // You will solve this with a partner
        // Please do not begin work on this until directed to!
        return null;
    }
}