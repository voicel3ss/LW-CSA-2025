package src.tests;

import org.junit.Before;
import org.junit.Test;

import edu.ftdev.Maze.MazeCanvas.Side;
import edu.ftdev.Maze.helpers.*;
import org.junit.jupiter.api.AfterAll;

public class MazeCheckpoint4b {

    private static MazeCanvas mc;
    private static Maze m;
    private static Generator g;
    
    @Before
    public void testSetup() {
        WClass.setup(getClass());
        if (mc == null) {
            mc = new MazeCanvas(12, 18, 24);
            mc.open();
            m = Maze.newInstance(mc);
            m.initialize();
            g = Generator.newInstance(mc, m);
            mc.assertPause("Generator.run() returned true. Expected: false", !g.run());
        }
    }
    
    @AfterAll
    public static void terminate() {
        mc.close();
    }
    
    @Test
    public void testGenerator_allVisited() {
        for (int r = 0; r < mc.getRows(); r++) {
            for (int c = 0; c < mc.getCols(); c++) {
                Cell cell = m.getCell(r, c);
                mc.assertPause(r, c, "Cell is not visited", cell.getVisited());
            }
        }
    }
    
    @Test
    public void testGenerator_blockCell_walled() {
        for (int r = 0; r < mc.getRows(); r++) {
            for (int c = 0; c < mc.getCols(); c++) {
                Cell cell = m.getCell(r, c);
                if (cell instanceof BlockCell) {
                    cell.assertWalls(Side.Top, Side.Left, Side.Bottom, Side.Right);
                    mc.assertWalls(r, c, Side.Top, Side.Left, Side.Bottom, Side.Right);
                }
            }
        }
    }
    
    @Test
    public void testGenerator_wallsMatch() {
        for (int r = 0; r < mc.getRows(); r++) {
            for (int c = 0; c < mc.getCols(); c++) {
                mc.assertWallsMatch(r, c);
            }
        }
    }
    
    @Test
    public void testGenerator_complete() {
        mc.assertGenerated();
    }
}
