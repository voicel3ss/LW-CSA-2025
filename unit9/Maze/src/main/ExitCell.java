package src.main;

import edu.ftdev.Maze.MazeCanvas;
import edu.ftdev.Maze.helpers.*;
import edu.ftdev.Maze.MazeCanvas.Side;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Cell in maze that is exit point
 * 
 * @author Reyansh Jajoo
 * @date 05-31-2026
 */
public class ExitCell extends EdgeCell {
    // const
    private static final Color exitShadeColor = new Color(0, 0, 255);

    /**
     * Constructor for ExitCell class
     * @param mc MazeCanvas object
     * @param r row 
     * @param c column
     */
    public ExitCell(MazeCanvas mc, int r, int c) {
        super(mc, r, c);
        mc.drawShade(r, c, exitShadeColor);
    }
}
