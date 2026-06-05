package src.main;

import edu.ftdev.Maze.MazeCanvas;
import edu.ftdev.Maze.helpers.*;
import edu.ftdev.Maze.MazeCanvas.Side;
import java.awt.Color;

/**
 * Defines cells and fields of the maze
 * @author Reyansh Jajoo
 * @date 05-31-2026
 */
public class Maze {
    private MazeCanvas mc;

    // consts
    public static final Color RED = new Color(255, 0, 0);
    public static final Color DARK_RED = new Color(156, 0, 0);

    /**
     * Constructor for Maze class. Takes a MazeCanvas object as parameter and assigns it to the instance variable mc
     * @param mc the MazeCanvas object
     */
    public Maze(MazeCanvas mc) {
        this.mc = mc;
    }

    /**
     * Generates snake pattern on the maze canvas
     */
    public void genSnake() {
        for (int r = 0; r < mc.getRows(); r++) {
           for (int c = 0; c < mc.getCols(); c++) {
                mc.drawCell(r, c);
                mc.eraseWall(r, c, Side.Top);
                mc.eraseWall(r, c, Side.Bottom);
                if (r == 0) {
                    if (c % 2 == 0) {
                        mc.eraseWall(r, c, Side.Left);
                        mc.drawWall(r, c, Side.Top);
                        mc.drawPath(r, c, Side.Left, RED);
                    } else {
                        mc.eraseWall(r, c, Side.Right);
                        mc.drawWall(r, c, Side.Top);
                        mc.drawPath(r, c, Side.Right, RED);
                    }
                    mc.drawPath(r, c, Side.Center, DARK_RED);
                    mc.drawPath(r, c, Side.Bottom, RED);
                } else if (r == mc.getRows() - 1) {
                    if (c % 2 == 0) {
                        mc.eraseWall(r, c, Side.Right);
                        mc.drawWall(r, c, Side.Bottom);
                        mc.drawPath(r, c, Side.Right, RED);
                    } else {
                        mc.eraseWall(r, c, Side.Left);
                        mc.drawWall(r, c, Side.Bottom);
                        mc.drawPath(r, c, Side.Left, RED);
                    }
                    mc.drawPath(r, c, Side.Center, DARK_RED);
                    mc.drawPath(r, c, Side.Top, RED);
                } else {
                    mc.drawPath(r, c, Side.Center, RED);
                    mc.drawPath(r, c, Side.Top, RED);
                    mc.drawPath(r, c, Side.Bottom, RED);
                }
           } 
        }
    }
}
