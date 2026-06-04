package src.tests;

import org.junit.Before;
import org.junit.Test;

import edu.ftdev.Maze.helpers.*;

public class MazeCheckpoint1 {

    @Before
    public void testSetup() {
        WClass.setup(getClass());
    }

    @Test
    public void testMaze_Class() {
        WClass.checkClass("#Maze#",
            "public #Maze#(edu.ftdev.Maze.MazeCanvas)",
            "public void #Maze#.genSnake()");
    }

    @Test
    public void testMaze_genSnake() {
        MazeCanvas mc = new MazeCanvas(6, 9, 32);
        mc.open();
        Maze m = Maze.newInstance(mc);
        m.genSnake();
        mc.assertSnake();
        mc.close();
    }
}
