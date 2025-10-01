import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class MazeSolver {
    public static void main(String[] args) {
        // Location myLocation = new Location(4,7);
        // Location myLocation2 = new Location(myLocation.row()+1 , myLocation.col());


        int[][] maze1 = {
            {1, 0, 0, 0, 1, 1},
            {0, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 1},
            {1, 0, 0, 1, 0, 3},
        };

        List<Location> test = solve(0, 1, maze1);

        for (Location neighbor : test) {
            System.out.println(neighbor);
        }

        int[][] maze2 = {
            {0, 0, 0, 1, 1},
            {0, 1, 1, 0, 0},
            {0, 0, 1, 0, 1},
            {0, 0, 1, 3, 1},
            {1, 1, 1, 1, 1}
        };
                
        // List<Location> test2 = solve(0, 1, maze2);
        // try {
        //     for (Location neighbor : test2) {
        //         System.out.println(neighbor);
        //     }
        // } catch (Exception e) {
        //     System.out.println("Test is null");
        // }


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
        if(row < 0 || col < 0 || row >= maze.length || col >= maze[0].length) throw new IllegalArgumentException("Out of bounds location: " + row + ", " + col);

        if(maze[row][col] == 1) throw new IllegalArgumentException("Location is in a wall: " + row + ", " + col);
        boolean[][] visited = new boolean[maze.length][maze[0].length];

        return reachable(row, col, maze, visited);
    }

    private static boolean reachable(int row, int col, int[][] maze, boolean[][] visited){
        if(maze[row][col] == 3) return true;

        visited[row][col] = true;

        List<int[]> neighbors = validNeighbors(row, col, maze, visited);

        for (int[] neighbor : neighbors) {
            if(reachable(neighbor[0], neighbor[1], maze, visited)) return true;
        }

        return false;
    }

    public static List<int[]> validNeighbors(int startRow, int startCol, int[][] maze, boolean[][] visited){
        int[][] moves = {
            {-1, 0},
            {1 , 0},
            {0, 1},
            {0, -1}
        };

        List<int[]> neighbors = new ArrayList<>();

        for (int[] move : moves) {
            int newRow = startRow + move[0];
            int newCol = startCol + move[1];

            if(newRow < maze.length && newRow >= 0 && newCol < maze[0].length && newCol >= 0 && maze[newRow][newCol] != 1 && !visited[newRow][newCol]){
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
        
        if(row < 0 || col < 0 || row >= maze.length || col >= maze[0].length) throw new IllegalArgumentException("Out of bounds location: " + row + ", " + col);
        if(maze[row][col] == 1) throw new IllegalArgumentException("Location is in a wall: " + row + ", " + col);

        Stack<Location> path = new Stack<>();
        boolean[][] visited = new boolean[maze.length][maze[0].length];

        if(solve(row, col, maze, visited, path)) return path;
        return null;
    }
        
    private static boolean solve(int row, int col, int[][] maze, boolean[][] visited, Stack<Location> path){
        if(maze[row][col] == 3){
            path.add(new Location(row, col));
            return true;
        }
        visited[row][col] = true;
        path.add(new Location(row, col));

        for (Location location : validLocations(row, col, maze, visited)) {
            if(solve(location.row(), location.col(), maze, visited, path)){
                return true;
            }else{
                path.pop();
            }
            
        }

        return false;

    }
    
    public static List<Location> validLocations(int startRow, int startCol, int[][] maze, boolean[][] visited){
        List<Location> directions = new ArrayList<>();
        directions.add(new Location(1,0));
        directions.add(new Location(-1,0));
        directions.add(new Location(0,1));
        directions.add(new Location(0,-1));


        List<Location> neighbors = new ArrayList<>();

        for (Location direction : directions) {
            int newRow = startRow + direction.col();
            int newCol = startCol + direction.row();

            if(newRow < maze.length && newRow >= 0 && newCol < maze[0].length && newCol >= 0 && maze[newRow][newCol] != 1 && !visited[newRow][newCol]){
                neighbors.add(new Location(newRow, newCol));
            }
        }

        return neighbors;
    }   
}