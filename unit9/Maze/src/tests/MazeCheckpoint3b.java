package src.tests;

import org.junit.Before;
import org.junit.Test;

import edu.ftdev.Maze.helpers.*;

public class MazeCheckpoint3b {

    @Before
    public void testSetup() {
        WClass.setup(getClass());
    }

    @Test
    public void testEntryExitCell_Class() {
        WClass.checkClass("#EntryCell# extends #EdgeCell# extends #ShadedCell# extends #Cell#",
                "public #EntryCell#(edu.ftdev.Maze.MazeCanvas,int,int)");
        WClass.checkClass("#ExitCell# extends #EdgeCell# extends #ShadedCell# extends #Cell#",
                "public #ExitCell#(edu.ftdev.Maze.MazeCanvas,int,int)");
    }

    @Test
    public void testMaze_Class_getEntryExitCell() {
        WClass.checkClass("#Maze#",
                "public #Cell# #Maze#.getEntryCell()",
                "public #Cell# #Maze#.getExitCell()");
    }
    
    @Test
    public void testMaze_getEntryExitCell() {
        MazeCanvas mc = new MazeCanvas(6, 9, 32);
        mc.open();
        Maze m = Maze.newInstance(mc);
        m.initialize();
        
        Cell c = m.getEntryCell();
        mc.assertPause("Null entry cell in maze.", c != null);
        c.assertType(EntryCell.class);
        int row = c.getRow();
        int col = c.getCol();
        mc.assertPause(row, col, "Entry cell not on the edge of the maze.", 
                row == 0 || row == mc.getRows()-1 || col == 0 || col == mc.getCols()-1);
        
        c = m.getExitCell();
        mc.assertPause("Null exit cell in maze.", c != null);
        c.assertType(ExitCell.class);
        row = c.getRow();
        col = c.getCol();
        mc.assertPause(row, col, "Exit cell not on the edge of the maze.", 
                row == 0 || row == mc.getRows()-1 || col == 0 || col == mc.getCols()-1);
        
        mc.close();
    }
}
