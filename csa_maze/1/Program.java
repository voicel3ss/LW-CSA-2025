package src.main;

import edu.ftdev.Maze.MazeCanvas;

import src.main.Maze;

/**
 * Contains method which serves as entry point for maze generation
 * Initializes maze canvas, creates maze instance, generates snake pattern, handles the display of the maze.
 * @author Reyansh Jajoo
 * @date 05-31-2026
 */
public class Program {

    // number of rows and columns in the maze and pixel-size of a cell 
    private static final int NROWS = 20;
    private static final int NCOLS = 30;
    private static final int CELL_SIZE = 24;
    
    /**
     * initializes maze canvas, creates maze instance, generates snake pattern, manages  display of the maze
     * @param args
     */
    public static void main(String[] args) {
        // create and open a MazeCanvas instance.
        MazeCanvas mc = new MazeCanvas(NROWS, NCOLS, CELL_SIZE);
        mc.open();
        
        // create a Maze instance passing to it the opened MazeCanvas.
        Maze m = new Maze(mc);
        // generate the snake
        m.genSnake();

        // all is done, pause then close the maze before terminating the program.
        mc.breakLeap();
        mc.close();
    }
}