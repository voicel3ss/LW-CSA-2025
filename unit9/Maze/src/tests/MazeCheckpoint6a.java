package src.tests;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.jupiter.api.AfterAll;

import edu.ftdev.Maze.MazeCanvas.Side;
import edu.ftdev.Maze.helpers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MazeCheckpoint6a {

    private static MazeCanvas mc;
    private static Maze m;
    private static Generator g;
    private static Solver s;
    private static Cell cell;
    private static ExitCell exitCell;
    
    @Before
    public void testSetup() {
        WClass.setup(getClass());
        if (mc != null) {
            return;
        }
        mc = new MazeCanvas(12, 18, 24);
        mc.open();
        m = Maze.newInstance(mc);
        m.initialize();
        // locate the first regular Cell (not BlockCell)
        cell = null;
        exitCell = null;
        for (int r = 0; r < mc.getRows(); r++) {
            for (int c = 0; c < mc.getCols(); c++) {
                Cell crtCell = m.getCell(r, c);
                if (crtCell instanceof ExitCell) {
                    if (exitCell != null) {
                        mc.assertPause(r, c,
                            String.format("Duplicated ExitCell in maze; Other @[%d,%d]",
                                    exitCell.getRow(), exitCell.getCol()), false);
                    }
                    exitCell = (ExitCell)crtCell;
                } else if (cell == null && !(crtCell instanceof BlockCell) && !(crtCell instanceof EdgeCell)) {
                    cell = crtCell;
                }
            }
        }
        mc.assertPause("Could not find any regular cell in the maze.", cell != null);
        mc.assertPause("Could not find the Exit cell in the maze", exitCell != null);
        g = Generator.newInstance(mc, m);
        s = Solver.newInstance(mc, m);
    }
    
    @AfterAll
    public static void terminate() {
        mc.close();
    }
    
    @Test
    public void test601_Generator_Class() {
        WClass.checkClass("#Generator#",
                "* boolean *.onEnterCell(#Cell#,edu.ftdev.Maze.MazeCanvas$Side)",
                "* java.util.*List *.onGetNextSteps(#Cell#)",
                "* void *.onStepForward(#Cell#,edu.ftdev.Maze.MazeCanvas$Side)",
                "* void *.onStepBack(boolean,#Cell#,edu.ftdev.Maze.MazeCanvas$Side)",
                "* void *.onExitCell(boolean,#Cell#,edu.ftdev.Maze.MazeCanvas$Side)");
    }

    @Test
    public void test602_Solver_Class() {
        WClass.checkClass("#Solver#",
                "* boolean *.onEnterCell(#Cell#,edu.ftdev.Maze.MazeCanvas$Side)",
                "* java.util.*List *.onGetNextSteps(#Cell#)",
                "* void *.onStepForward(#Cell#,edu.ftdev.Maze.MazeCanvas$Side)",
                "* void *.onStepBack(boolean,#Cell#,edu.ftdev.Maze.MazeCanvas$Side)",
                "* void *.onExitCell(boolean,#Cell#,edu.ftdev.Maze.MazeCanvas$Side)");
    }
    
    @Test
    public void test603_Generator_onMethods() {
        // cell is a regular Cell, surrounded by walls on all sides
        int r = cell.getRow();
        int c = cell.getCol();
        
        // Entering the cell from the left is expected to return false, remove the Left wall
        // and draw the path on the Left and Center sides.
        boolean done = g.onEnterCell(cell, Side.Left);
        mc.assertPause(r, c, "Wrong return value from onEnterCell; Expected: false; Actual: true", !done);
        mc.assertWalls(r, c, Side.Top, Side.Right, Side.Bottom);
        mc.assertPaths(r, c, Side.Left, Side.Center);
        
        // Next paths should only include Top, Right and Bottom, matching the cell's walls
        List<Side> steps = g.onGetNextSteps(cell);
        mc.assertWalls(r, c, (Side[])steps.toArray(new Side[steps.size()]));
        
        // Stepping to the right results in removing the Right wall and drawing the Right path
        g.onStepForward(cell, Side.Right);
        mc.assertWalls(r, c, Side.Top, Side.Bottom);
        mc.assertPaths(r, c, Side.Left, Side.Center, Side.Right);
        
        // Stepping back from Right results in erasing the Right path only
        g.onStepBack(false, cell, Side.Right);
        mc.assertWalls(r, c, Side.Top, Side.Bottom);
        mc.assertPaths(r, c, Side.Left, Side.Center);
        
        // Exiting the cell to the Left results in erasing the Center and Left paths
        g.onExitCell(done, cell, Side.Left);
        mc.assertWalls(r, c, Side.Top, Side.Bottom);
        mc.assertPaths(r, c);
    }
    
    @Test
    public void test604_Solver_onMethods() {
        // cell is a regular Cell, surrounded by walls on all sides
        // Configure it for solving, by removing the walls on the Left, Right and Bottom side
        int r = cell.getRow();
        int c = cell.getCol();
        cell.removeWall(Side.Left);
        cell.removeWall(Side.Right);
        cell.removeWall(Side.Bottom);
        
        // Entering the cell from the left is expected to return false and draw the paths on the Left and Center side
        boolean done = s.onEnterCell(cell, Side.Left);
        mc.assertPause(r, c, "Wrong return value from onEnterCell; Expected: false; Actual: true", !done);
        mc.assertWalls(r, c, Side.Top);
        mc.assertPaths(r, c, Side.Left, Side.Center);
        
        // Next paths should only include Left, Right and Bottom, matching the cell's opened sides
        List<Side> steps = s.onGetNextSteps(cell);
        mc.assertPathsMatch(r, c, (Side[])steps.toArray(new Side[steps.size()]));
        
        // Stepping to the right results in drawing the Right path
        s.onStepForward(cell, Side.Right);
        mc.assertPaths(r, c, Side.Left, Side.Center, Side.Right);

        // Stepping back from Right results in preserving all paths
        s.onStepBack(false, cell, Side.Right);
        mc.assertPaths(r, c, Side.Left, Side.Center, Side.Right);

        // Exiting the cell to the Left results in preserving all paths
        s.onExitCell(done, cell, Side.Left);
        mc.assertPaths(r, c, Side.Left, Side.Center, Side.Right);
    }
    
    @Test
    public void test605_Solver_onMethods_ExitCell() {
        // Configure the exit Cell for solving, by removing its Left side
        int r = exitCell.getRow();
        int c = exitCell.getCol();
        exitCell.removeWall(Side.Left);
        
        // Entering the ExitCell from the left is expected to return true and draw the paths on the Left and Center side
        boolean done = s.onEnterCell(exitCell, Side.Left);
        mc.assertPause(r, c, "Wrong return value from onEnterCell; Expected: true; Actual: false", done);
        mc.assertWalls(r, c, Side.Top, Side.Right, Side.Bottom);
        mc.assertPaths(r, c, Side.Left, Side.Center);
    }
}
