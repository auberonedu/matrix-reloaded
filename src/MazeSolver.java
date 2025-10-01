import java.util.ArrayList;
import java.util.List;

public class MazeSolver {
    public static void main(String[] args) {
        Location myLocation = new Location(4,7);
        System.out.println(myLocation.row());
        int[][] maze1 = {
            {1, 0, 0, 0, 1, 1},
            {0, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 1},
            {1, 0, 0, 1, 3, 1},
        };

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
        if (maze[row][col]==1||row<0||row>=maze.length||col<0||col>=maze[0].length) throw new IllegalArgumentException();
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return reachable(row, col, maze, visited);
    }

    public static boolean reachable(int row, int col, int[][] maze, boolean[][] visited) {
        if (maze[row][col] == 3) return true;
        visited[row][col] = true;
        List<int[]> neighbours = validNeighbours(row, col, maze, visited);
        for (int[] moves : neighbours) {
            if (reachable(moves[0], moves[1], maze, visited)) return true;
        }
        return false;
    }

    public static List<int[]> validNeighbours(int row, int col, int[][] maze, boolean[][] visited) {
        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
        List<int[]> neighbours = new ArrayList<>();
        for (int[] dir : directions) {
            int NEWROW = row + dir[0];
            int NEWCOL = col + dir[1];
            if (
                NEWROW>=0&&
                NEWROW<maze.length&&
                NEWCOL>=0&&
                NEWCOL<maze[0].length&&
                maze[NEWROW][NEWCOL] != 1&&
                !visited[NEWROW][NEWCOL]
            ) neighbours.add(new int[]{NEWROW, NEWCOL});
        }
        return neighbours;
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
        if (maze[row][col]==1||row<0||row>=maze.length||col<0||col>=maze[0].length) throw new IllegalArgumentException();
        List<Location> path = new ArrayList<>();
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return solve(row, col, maze, visited, path);
    }
    public static List<Location> solve(int row, int col, int[][] maze, boolean[][] visited, List<Location> path) {
        path.add(new Location(row, col));
        if (maze[row][col]==3) return path;
        visited[row][col] = true;
        List<int[]> neighbours = validNeighbours(row, col, maze, visited);
        for (int[] moves : neighbours) {
            return solve(moves[0], moves[1], maze, visited, path);
        }
        return null;
    }
}