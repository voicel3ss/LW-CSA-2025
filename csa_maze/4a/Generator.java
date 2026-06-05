package src.main;

import edu.ftdev.Maze.MazeCanvas;
import edu.ftdev.Maze.helpers.*;
import edu.ftdev.Maze.MazeCanvas.Side;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Class that generates the maze
 * 
 * @author Reyansh Jajoo
 * @date 05-31-2026
 */
public class Generator {
    // instance variables
    private MazeCanvas mc;
    private Maze maze;

    /**
     * Constructor for Generator class
     * @param mc MazeCanvas object
     * @param maze Maze object
     */
    public Generator(MazeCanvas mc, Maze maze) {
        this.mc = mc;
        this.maze = maze;
    }

    /**
     * Shuffles list of sides
     * @param sides list of sides to shuffle
     * @return new list of sides that is shuffled
     */
    public ArrayList<Side> shuffle(ArrayList<Side> sides) {
        ArrayList<Side> shuffledList = new ArrayList<>(sides);
        java.util.Collections.shuffle(shuffledList);
        return shuffledList;
    }

    /**
     * Gets opposite side of given side
     * @param side side to get opposite of
     * @return opposite side
     */
    public Side getOpposite(Side side) {
        switch (side) {
            case Top:
                return Side.Bottom;
            case Bottom:
                return Side.Top;
            case Left:
                return Side.Right;
            case Right:
                return Side.Left;
            case Center:
                return Side.Center;
            default:
                throw new IllegalArgumentException("Invalid side");
        }
    }

    /**
     * Runs maze generation algorithm
     * @return true if maze generation is successful, false otherwise
     */
    public boolean run() {
        Cell startCell = maze.getEntryCell();
        return run(startCell, Side.Center);
    }

    /**
     * Runs maze generation algorithm
     * @param cell  cell to run on
     * @param fromSide side
     * @return true if maze generation is successful, false otherwise
     */
    private boolean run(Cell cell, Side fromSide){
        return false;
    }
}
