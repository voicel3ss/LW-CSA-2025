package src.main;

import edu.ftdev.Maze.MazeCanvas;
import edu.ftdev.Maze.helpers.*;
import edu.ftdev.Maze.MazeCanvas.Side;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Cell in maze that is a block and shaded with light gray color
 * 
 * @author Reyansh Jajoo
 * @date 05-31-2026
 */
public class BlockCell extends ShadedCell {
    // const
    private static final Color blockShadeColor = Color.LIGHT_GRAY;

    /**
     * Constructor for BlockCell class.
     * 
     * @param mc the MazeCanvas object
     * @param r  the row
     * @param c  the column
     */
    public BlockCell(MazeCanvas mc, int r, int c) {
        super(mc, r, c, blockShadeColor);
    }
}
