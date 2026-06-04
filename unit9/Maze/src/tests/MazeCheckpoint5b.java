package src.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;

import edu.ftdev.Maze.helpers.*;

public class MazeCheckpoint5b {

    private static MazeCanvas mc;
    private static Maze m;
    private static Generator g;
    private static Solver s;
    private static EntryCell enc;
    private static ExitCell exc;

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
        
        // locate the entry and exit cells;
        int rEnc = -1, cEnc = -1;
        int rExc = -1, cExc = -1;
        for (int r = 0; r < mc.getRows(); r++) {
            for (int c = 0; c < mc.getCols(); c++) {
                Cell cell = m.getCell(r, c);
                if (cell instanceof EntryCell) {
                    mc.assertPause(r, c, 
                            String.format("Duplicated EntryCell; Other @[%d,%d]", rEnc, cEnc),
                            enc == null);
                    enc = (EntryCell) cell;
                    rEnc = enc.getRow();
                    cEnc = enc.getCol();
                }
                if (cell instanceof ExitCell) {
                    mc.assertPause(r, c, 
                            String.format("Duplicated ExitCell; Other @[%d,%d]", rExc, cExc),
                            exc == null);
                    exc = (ExitCell) cell;
                    rExc = exc.getRow();
                    cExc = exc.getCol();
                }
            }
        }
        
        mc.assertPause("Missing EntryCell", enc != null);
        mc.assertPause("Missing ExitCell", exc != null);
        
        g = Generator.newInstance(mc, m);
        g.run();
        s = Solver.newInstance(mc, m);
        mc.assertPause("Solver.run() returned false. Expected: true", s.run());
    }
    
    @AfterAll
    public static void terminate() {
        mc.close();
    }
    
    @Test
    public void testSolver_matchEntryExit() {
        mc.assertCentersMatch(
                "EntryCell", enc.getRow(), enc.getCol(),
                "ExitCell", exc.getRow(), exc.getCol());
    }
    
    @Test
    public void testSolver_pathVisited() {
        // not all cells are visited, but at least the EntryCell and ExitCell must be
        int visitedCells = 0;
        for (int r = 0; r < mc.getRows(); r++) {
            for (int c = 0; c < mc.getCols(); c++) {
                Cell cell = m.getCell(r, c);
                // non-block cells that are visited should have at least one other neighbor visited as well.
                if (!(cell instanceof BlockCell) && cell.getVisited()) {
                    visitedCells++;
                    mc.assertVisitedNeighbors(r, c);
                }
            }
        }
        
        mc.assertPause(
                String.format("Wrong count of visited cells, false); Expected >= 2; Actual: %d", visitedCells),
                visitedCells >= 2);
    }
    
    @Test
    public void testSolver_pathSolved() {
        mc.assertPathSolved(enc.getRow(), enc.getCol(), exc.getRow(), exc.getCol());
    }
}
