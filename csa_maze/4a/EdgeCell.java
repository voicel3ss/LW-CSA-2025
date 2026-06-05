package src.main;

import edu.ftdev.Maze.MazeCanvas;
import edu.ftdev.Maze.helpers.*;
import edu.ftdev.Maze.MazeCanvas.Side;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Defines cell in maze that is on the edge of maze and shaded with specific
 * color
 * 
 * @author Reyansh Jajoo
 * @date 05-31-2026
 */
public class EdgeCell extends ShadedCell {
    // instance variable
    private ArrayList<Side> listOfEdges;

    // const
    private static final Color EDGE_COLOR = new Color(0, 0, 255);

    /**
     * Constructor for EdgeCell class. Takes MazeCanvas object, row, column as
     * parameters, initializes cell and draws shade on maze canvas. Identifies which
     * sides of the cell are edges.
     */
    public EdgeCell(MazeCanvas mc, int r, int c) {
        super(mc, r, c, EDGE_COLOR);

        listOfEdges = new ArrayList<Side>();
        if (r == 0) {
            listOfEdges.add(Side.Top);
        } else if (r == mc.getRows() - 1) {
            listOfEdges.add(Side.Bottom);
        }

        if (c == 0) {
            listOfEdges.add(Side.Left);
        } else if (c == mc.getCols() - 1) {
            listOfEdges.add(Side.Right);
        }
    }

    /**
     * Return list of walls for cell, excluding edges. Removes sides identified as
     * edges from list of walls.
     * 
     * @return an ArrayList of Side representing walls of cell, excluding edges
     */
    @Override
    public ArrayList<Side> getWalls() {
        ArrayList<Side> walls = super.getWalls();
        walls.removeAll(listOfEdges);
        return walls;
    }
}