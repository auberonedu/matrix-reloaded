import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MazeSolver {
    public static void main(String[] args) {
        Location myLocation = new Location(4, 7);
        Location location2 = new Location(4, 7);
        
        System.out.println(myLocation.equals(location2));
        // row 3
        // col 2
        // int[][] maze1 = {
        //     {1, 0, 0, 0, 1, 1},
        //     {0, 0, 1, 0, 0, 0},
        //     {1, 0, 0, 1, 0, 1},
        //     {1, 0, 0, 1, 3, 1},
        // };

        // // [{0, 2}, {1, 1}]
        // boolean[][] visited = new boolean[maze1.length][maze1[0].length];
        // List<int[]> neighbors = validNeighbors(0, 1, maze1, visited);
        // for (int[] neighbor : neighbors) {
        //     System.out.println(Arrays.toString(neighbor));
        // }

        // // row 1
        // // col 1
        // int[][] maze2 = {
        //     {0, 0, 0, 1, 1},
        //     {0, 1, 1, 0, 0},
        //     {0, 0, 1, 0, 1},
        //     {0, 0, 1, 3, 1},
        //     {1, 1, 1, 1, 1}
        // };
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
        if (row < 0 || col < 0 || row >= maze.length || col >= maze[0].length) {
            throw new IllegalArgumentException("Out of bounds location: " + row + ", " + col);
        }
        if (maze[row][col] == 1) {
            throw new IllegalArgumentException("Location is in wall: " + row + ", " + col);
        }
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return reachableHelper(row, col, maze, visited);
    }

    private static boolean reachableHelper(int row, int col, int[][] maze, boolean[][] visited) {
        if (maze[row][col] == 3) return true;

        visited[row][col] = true;

        List<int[]> neighbors = validNeighbors(row, col, maze, visited);

        for (int[] neighbor : neighbors) {
            if (reachableHelper(neighbor[0], neighbor[1], maze, visited)) {
                return true;
            }
        }
        return false;
    }

    // [{}, {}]
    public static List<int[]> validNeighbors(int startRow, int startCol, int[][] maze, boolean[][] visited) {
        int[][] moves = {
            {-1, 0}, // UP
            {1, 0}, // DOWN
            {0, 1}, // RIGHT
            {0, -1} // LEFT
        };

        List<int[]> neighbors = new ArrayList<>();

        for(int[] move : moves) {
            int newRow = startRow + move[0];
            int newCol = startCol + move[1];

            if(newRow >= 0 &&
               newRow < maze.length &&
               newCol >= 0 &&
               newCol < maze[newRow].length &&
               maze[newRow][newCol] != 1 &&
               !visited[newRow][newCol]) {
                neighbors.add(new int[]{newRow, newCol});
            }
        }
        
        return neighbors;
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