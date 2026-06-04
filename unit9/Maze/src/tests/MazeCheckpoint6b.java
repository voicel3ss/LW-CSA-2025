package src.tests;

import org.junit.Before;
import org.junit.Test;

import edu.ftdev.Maze.helpers.*;

public class MazeCheckpoint6b {

    @Before
    public void testSetup() {
        WClass.setup(getClass());
    }

    @Test
    public void testExplorer_Class() {
        WClass.checkClass("#Explorer#",
                "public #Explorer#(edu.ftdev.Maze.MazeCanvas,#Maze#,java.awt.Color,java.awt.Color)",
                "protected boolean #Explorer#.onEnterCell(#Cell#,edu.ftdev.Maze.MazeCanvas$Side)",
                "protected java.util.*List #Explorer#.onGetNextSteps(#Cell#)",
                "protected void #Explorer#.onStepForward(#Cell#,edu.ftdev.Maze.MazeCanvas$Side)",
                "protected void #Explorer#.onStepBack(boolean,#Cell#,edu.ftdev.Maze.MazeCanvas$Side)",
                "protected void #Explorer#.onExitCell(boolean,#Cell#,edu.ftdev.Maze.MazeCanvas$Side)",
                "* boolean #Explorer#.run(#Cell#,edu.ftdev.Maze.MazeCanvas$Side)",
                "public boolean #Explorer#.run()");
    }
    
    @Test
    public void testGenerator_Class() {
        WClass.checkClass("#Generator# extends #Explorer#",
                "protected boolean #Generator#.onEnterCell(#Cell#,edu.ftdev.Maze.MazeCanvas$Side)",
                "protected java.util.*List #Generator#.onGetNextSteps(#Cell#)",
                "protected void #Generator#.onStepForward(#Cell#,edu.ftdev.Maze.MazeCanvas$Side)",
                "protected void #Explorer#.onStepBack(boolean,#Cell#,edu.ftdev.Maze.MazeCanvas$Side)",
                "protected void #Explorer#.onExitCell(boolean,#Cell#,edu.ftdev.Maze.MazeCanvas$Side)");
    }

    @Test
    public void testSolver_Class() {
        WClass.checkClass("#Solver# extends #Explorer#",
                "protected boolean #Solver#.onEnterCell(#Cell#,edu.ftdev.Maze.MazeCanvas$Side)",
                "protected java.util.*List #Solver#.onGetNextSteps(#Cell#)",
                "protected void #Explorer#.onStepForward(#Cell#,edu.ftdev.Maze.MazeCanvas$Side)",
                "protected void #Explorer#.onStepBack(boolean,#Cell#,edu.ftdev.Maze.MazeCanvas$Side)",
                "protected void #Explorer#.onExitCell(boolean,#Cell#,edu.ftdev.Maze.MazeCanvas$Side)");
    }
}
