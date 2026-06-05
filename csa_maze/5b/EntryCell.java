package src.main;

import edu.ftdev.Maze.MazeCanvas;
import edu.ftdev.Maze.helpers.*;
import edu.ftdev.Maze.MazeCanvas.Side;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Cell in maze that is entry point
 * 
 * @author Reyansh Jajoo
 * @date 05-31-2026
 */
public class EntryCell extends EdgeCell {
    // const
    private static final Color entryShadeColor = new Color(0, 255, 0);

    /**
     * Constructor for EntryCell class
     * @param mc MazeCanvas object
     * @param r row 
     * @param c column
     */
    public EntryCell(MazeCanvas mc, int r, int c) {
        super(mc, r, c);
        mc.drawShade(r, c, entryShadeColor);
    }
}
