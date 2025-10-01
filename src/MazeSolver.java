import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MazeSolver {
    public static void main(String[] args) {
        // Location myLocation = new Location(4, 7);
        // Location location2 = new Location(3, 8);
        // myLocation = new Location(myLocation.row() + 1, myLocation.col());
        // System.out.println(myLocation.equals(location2));
        int[][] maze1 = {
            {1, 0, 0, 0, 1, 1},
            {0, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 1},
            {1, 0, 0, 1, 3, 1},
        };
        List<Location> path = solve(0, 1, maze1);
        for(Location l : path)
        {
            System.out.println(l);
        }

        // boolean[][] visited = new boolean[maze1.length][maze1[0].length];
        // List<int[]> neighbors = validNeighbors(0, 1, maze1, visited);

        // for(int[] n : neighbors)
        // {
        //     System.out.println(Arrays.toString(n));
        // }

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
        if(row < 0 || col < 0 || row >= maze.length || col >= maze[0].length)
        {
            throw new IllegalArgumentException("Out of bounds location" + row + ", " + col);
        }
        if(maze[row][col] == 1)
        {
            throw new IllegalArgumentException("Location is a wall" + row + ", " + col);
        }

        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return reachable(row, col, maze, visited);
    }

    private static boolean reachable(int row, int col, int[][] maze, boolean[][] visited)
    {
        if(maze[row][col] == 3)
        {
            return true;
        }

        visited[row][col] = true;
        List<int[]> neighbors = validNeighbors(row, col, maze, visited);

        for(int[] n : neighbors)
        {
            if(reachable(n[0], n[1], maze, visited))
            {
                return true;
            }
        }

        return false;
    }

    public static List<int[]> validNeighbors(int startRow, int startCol, int[][]maze, boolean[][]visited)
    {
        int[][] moves = 
        {
            {-1, 0}, //up
            {1, 0}, //down
            {0, 1}, //right
            {0, -1} //left
        };

        List<int[]> neighbors  = new ArrayList<>();

        for(int[] move : moves)
        {
            int newR = startRow + move[0];
            int newC = startCol + move[1];

            if(newR >= 0 && 
               newR <maze.length && 
               newC >= 0 && 
               newC < maze[0].length && 
               maze[newR][newC] != 1 && 
               !visited[newR][newC])
            {
                neighbors.add(new int[]{newR, newC});
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

        // check if 3 is reachable from given row col
            //return null if false
        //else if true
        //  use valid neighborhs to find a valid path
        //  keep track of valid moves/ position using a list of locations
        //  add to list if a position is still able to reach the 3
        //check if position is a wall or out of bounds
        //  if yes to either throw error 
        //Once 3 is reached return list of locations (valid path)
        if(maze[row][col] == 1)
        {
            throw new IllegalArgumentException("location is a wall");
        }
        if(row < 0 || col < 0 || row >= maze.length || col >= maze[0].length)
        {
            throw new IllegalArgumentException("location is out of bounds");
        }
        if(!reachable(row, col, maze))
        {
            return null;
        }

        List<Location> validPath = new ArrayList<>();
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        List<int[]> neighbors = validNeighbors(row, col, maze, visited);
    
        //maybe use a boolean method so that if valid path is not found retrun null by default?
        return validPathFinder(row, col, maze, validPath, neighbors, visited);
    }

    public static List<Location> validPathFinder(int row, int col, int[][] maze, List<Location> validPath, List<int[]> neighbors, boolean[][] visited)
    {
        if(maze[row][col] == 3)
        {
            Location treasure = new Location(row, col);
            validPath.add(treasure);
            return validPath;
        }
        for(int[] n : neighbors)
        {
            if(reachable(n[0], n[1], maze))
            {
                if(maze[n[0]][n[1]] == 3)
                {
                    Location position = new Location(n[0], n[1]);
                    validPath.add(position);
                    return validPath;
                }
                row = n[0];
                col = n[1];
                Location position = new Location(row, col);
                validPath.add(position);
                // neighbors = validNeighbors(row, col, maze, visited);
            }
        }
        return validPathFinder(row, col, maze, validPath, neighbors, visited);
    }
}