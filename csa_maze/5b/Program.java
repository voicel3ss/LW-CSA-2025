package src.main;

import edu.ftdev.Maze.MazeCanvas;

import src.main.Maze;

/**
 * Contains method which serves as entry point for maze generation
 * Initializes maze canvas, creates maze instance, generates snake pattern,
 * handles the display of the maze.
 * 
 * @author Reyansh Jajoo
 * @date 05-31-2026
 */
public class Program {

    // number of rows and columns in the maze and pixel-size of a cell
    private static final int NROWS = 20;
    private static final int NCOLS = 30;
    private static final int CELL_SIZE = 24;

    /**
     * initializes maze canvas, creates maze instance, generates snake pattern,
     * manages display of the maze
     * 
     * @param args
     */
    public static void main(String[] args) {
        MazeCanvas mc = new MazeCanvas(NROWS, NCOLS, CELL_SIZE);
        mc.open();

        Maze m = new Maze(mc);
        m.initialize();

        mc.breakLeap();

        Generator g = new Generator(mc, m);
        g.run();

        mc.breakLeap();

        Solver s = new Solver(mc, m);
        s.run();

        mc.breakLeap();
        mc.close();
    }
}