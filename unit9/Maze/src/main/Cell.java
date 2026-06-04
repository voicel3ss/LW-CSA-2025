package src.main;

import edu.ftdev.Maze.MazeCanvas;
import edu.ftdev.Maze.MazeCanvas.Side;
import java.util.ArrayList;

/**
 * Defines cell in maze, including position, maze canvas it belongs to, and
 * walls
 * 
 * @author Reyansh Jajoo
 * @date 05-31-2026
 */
public class Cell {
    // instance variables
    private int r;
    private int c;
    private MazeCanvas mc;
    private ArrayList<Side> listOfWalls;
    private boolean visited;

    /**
     * Constructor for Cell class. Takes MazeCanvas object, row, and column as
     * parameters, init cell's position, maze canvas, and walls
     * 
     * @param mc the MazeCanvas object
     * @param r  the row
     * @param c  the column
     */
    public Cell(MazeCanvas mc, int r, int c) {
        this.mc = mc;
        this.r = r;
        this.c = c;
        this.listOfWalls = new ArrayList<Side>();
        listOfWalls.add(Side.Top);
        listOfWalls.add(Side.Bottom);
        listOfWalls.add(Side.Left);
        listOfWalls.add(Side.Right);

        mc.drawCell(r, c);
    }

    /**
     * Returns list of paths for cell, excluding walls. Creates list of all sides,
     * 
     * @return an ArrayList of Side representing paths of cell, excluding walls
     */
    public ArrayList<Side> getPaths() {
        ArrayList<Side> paths = new ArrayList<Side>();
        paths.add(Side.Top);
        paths.add(Side.Bottom);
        paths.add(Side.Left);
        paths.add(Side.Right);

        for (Side wall : listOfWalls) {
            paths.remove(wall);
        }

        return paths;
    }

    /**
     * Getter for visited
     * 
     * @return true if cell has been visited, false otherwise
     */
    public boolean getVisited() {
        return visited;
    }

    /**
     * Setter for visited
     * 
     * @param visited the new value for visited
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * Getter for row
     * 
     * @return the row of the cell
     */
    public int getRow() {
        return r;
    }

    /**
     * Getter for column
     * 
     * @return the column of the cell
     */
    public int getCol() {
        return c;
    }

    /**
     * Getter for walls
     * 
     * @return a copy of the list of walls of the cell
     */
    public ArrayList<Side> getWalls() {
        return (ArrayList<Side>) listOfWalls.clone();
    }

    /**
     * Removes a wall from the cell and erases it from the maze canvas
     * 
     * @param s the side of the wall to be removed
     */
    public void removeWall(Side s) {
        listOfWalls.remove(s);
        mc.eraseWall(r, c, s);
    }
}
