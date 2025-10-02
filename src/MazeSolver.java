import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MazeSolver {
    public static void main(String[] args) {
        
        int[][] maze1 = {
            // startRow 0
            // startCol 1
            {1, 0, 0, 0, 1, 1},
            {0, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 1},
            {1, 0, 0, 1, 3, 1},
        };

        // [{0, 2}, {1, 1}]

        boolean[][] visited = new boolean[maze1.length][maze1[0].length];
        List<int[]> neighbors = validNeighbors(0, 1, maze1, visited);
        for(int[] neighbor : neighbors) {
            System.out.println(Arrays.toString(neighbor));
        }

        // row 1
        // col 1
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
        return false;
    }

    // [{}, {}]
    public static List<int[]> validNeighbors(int startRow, int startCol, int[][] maze, boolean[][] visited) {
        int[][] moves = {
            {-1, 0},    // UP
            {1, 0},     // DOWN
            {0, 1},     // RIGHT
            {0, -1}     //LEFT
        };

        List<int[]> neighbors = new ArrayList<>();

        for(int[] move : moves) {
            int newRow = startRow + move[0];
            int newCol = startCol + move[1];

            if(newRow >= 0 &&
               newRow < maze.length &&
               newCol >= 0 &&
               newCol < maze[0].length &&
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
        List<Location> list = new ArrayList<>();
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        visited[row][col] = true;
    
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int curRow = pos[0];
            int curCol = pos[1];
                
            
                for (int[] neighbor : validNeighbors(curRow, curCol, maze, visited)) {
                    list.add(curRow, curCol);
                    visited[neighbor[0]][neighbor[1]] = true;
                    queue.add(neighbor);
                }
                return list;
        }
        return null;

    }
}