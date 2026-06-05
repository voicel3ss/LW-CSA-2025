package src.main;

import edu.ftdev.Maze.MazeCanvas;
import edu.ftdev.Maze.helpers.*;
import edu.ftdev.Maze.MazeCanvas.Side;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Class that solves the maze
 * 
 * @author Reyansh Jajoo
 * @date 05-31-2026
 */
public class Solver {
    // instance variables
    public MazeCanvas mc;
    public Maze maze;

    /**
     * Constructor for Solver class
     * @param mc MazeCanvas object
     * @param maze Maze object
     */
    public Solver(MazeCanvas mc, Maze maze) {
        this.mc = mc;
        this.maze = maze;
    }
    
    /**
     * Runs the maze solver algorithm
     * @return true if maze is solved, false otherwise
     */
    public boolean run() {
        for (int r = 0; r < mc.getRows(); r++) {
            for (int c = 0; c < mc.getCols(); c++) {
                maze.getCell(r, c).setVisited(false);
            }
        }

        return run(maze.getEntryCell(), Side.Center);
    }

    /**
     * Runs the maze solver algorithm
     * @param cell current cell
     * @param fromSide side from which cell was entered
     * @return true if maze is solved, false otherwise
     */
    public boolean run(Cell cell, Side fromSide) {
        return false;
    }

    /**
     * Shuffles list of sides
     * 
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
     * 
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
}
