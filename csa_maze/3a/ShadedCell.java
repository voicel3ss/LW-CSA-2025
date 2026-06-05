package src.main;

import edu.ftdev.Maze.MazeCanvas;
import edu.ftdev.Maze.MazeCanvas.Side;
import java.awt.Color;

/**
 * Defines cell in the maze that is shaded with a specific color
 * 
 * @author Reyansh Jajoo
 * @date 05-31-2026
 */
public class ShadedCell extends Cell {
    // instance variable
    private Color shadeColor;

    /**
     * Constructor for ShadedCell class. Takes MazeCanvas object, row, column, and
     * shade color as parameters, initializes cell and draws shade on maze canvas
     * 
     * @param mc         the MazeCanvas object
     * @param r          the row
     * @param c          the column
     * @param shadeColor the color to shade the cell
     */
    public ShadedCell(MazeCanvas mc, int r, int c, Color shadeColor) {
        super(mc, r, c);
        this.shadeColor = shadeColor;
        mc.drawShade(r, c, shadeColor);
    }
}
