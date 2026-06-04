package src.tests;

import org.junit.Before;
import org.junit.Test;

import edu.ftdev.Maze.helpers.*;

public class MazeCheckpoint2b {
    @Before
    public void testSetup() {
        WClass.setup(getClass());
    }

    @Test
    public void testMaze_Class() {
        WClass.checkClass("#Maze#",
                "public #Cell# #Maze#.getCell(int,int)",
                "public void #Maze#.initialize()");
    }
    
    @Test
    public void testMaze_initialize() {
        MazeCanvas mc = new MazeCanvas(6, 9, 32);
        mc.open();
        Maze m = Maze.newInstance(mc);
        m.initialize();
        for (int r = 0; r < mc.getRows(); r++) {
            for (int c = 0; c < mc.getCols(); c++) {
                if (r == 0 || c == 0 || r == mc.getRows()-1 || c == mc.getCols()-1) {
                    mc.assertShade(r, c, null);
                }
            }
        }
        mc.close();
    }
    
    @Test
    public void testMaze_getCell() {
        MazeCanvas mc = new MazeCanvas(6, 9, 32);
        mc.open();
        Maze m = Maze.newInstance(mc);
        m.initialize();
        for (int r = 0; r < mc.getRows(); r++) {
            for (int c = 0; c < mc.getCols(); c++) {
                Cell cell = m.getCell(r, c);
                if (r == 0 || c == 0 || r == mc.getRows()-1 || c == mc.getCols()-1) {
                    cell.assertType(EdgeCell.class, EntryCell.class, ExitCell.class);
                } else {
                    cell.assertType(Cell.class, BlockCell.class);
                }
            }
        }
        mc.close();
    }
}
