package src.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import edu.ftdev.Maze.MazeCanvas.Side;
import edu.ftdev.Maze.helpers.*;

import java.awt.Color;
import java.util.List;

public class MazeCheckpoint2a {

    @Before
    public void testSetup() {
        WClass.setup(getClass());
    }

    //Region: Cell tests
    @Test
    public void testCell_Class() {
        WClass.checkClass("Cell",
                "public #Cell#(edu.ftdev.Maze.MazeCanvas,int,int)",
                "public int #Cell#.getRow()",
                "public int #Cell#.getCol()",
                "public java.util.*List #Cell#.getWalls()",
                "public void #Cell#.removeWall(edu.ftdev.Maze.MazeCanvas$Side)");
    }
    
    @Test
    public void testCell_Ctor() {
        MazeCanvas mc = new MazeCanvas(6, 9, 32);
        mc.open();
        @SuppressWarnings("unused")
        Cell c = Cell.newInstance(mc, 3, 4);
        mc.assertWalls(3, 4, Side.Top, Side.Bottom, Side.Left, Side.Right);
        mc.close();
    }
    
    @Test
    public void testCell_getRow_getCol() {
        MazeCanvas mc = new MazeCanvas(6, 9, 32);
        Cell c = Cell.newInstance(mc, 3, 4);
        Assertions.assertEquals(3, c.getRow());
        Assertions.assertEquals(4, c.getCol());
    }
    
    @Test
    public void testCell_getWalls() {
        MazeCanvas mc = new MazeCanvas(6, 9, 32);
        Cell c = Cell.newInstance(mc, 3, 4);
        c.assertWalls(Side.Top, Side.Bottom, Side.Left, Side.Right);
        List<Side> walls1 = c.getWalls();
        List<Side> walls2 = c.getWalls();
        Assertions.assertTrue(walls1 != walls2, "getSide() returns internal list of walls instead of a copy.");
    }
    
    @Test
    public void testCell_removeWall() {
        MazeCanvas mc = new MazeCanvas(6, 9, 32);
        mc.open();
        Cell c = Cell.newInstance(mc, 3, 4);
        c.removeWall(Side.Left);
        c.assertWalls(Side.Top, Side.Bottom, Side.Right);
        mc.assertWalls(3, 4, Side.Top, Side.Bottom, Side.Right);
        mc.close();
    }
    //EndRegion: Cell tests
    
    //Region: ShadedCell tests
    @Test
    public void testShadedCell_Class() {
        WClass.checkClass("ShadedCell extends Cell",
                "public #ShadedCell#(edu.ftdev.Maze.MazeCanvas,int,int,java.awt.Color)");
    }
    
    @Test
    public void testShadedCell_Ctor() {
        MazeCanvas mc = new MazeCanvas(6, 9, 32);
        mc.open();
        @SuppressWarnings("unused")
        ShadedCell sc = ShadedCell.newInstance(mc, 3, 4, Color.LIGHT_GRAY);
        // check the rendered cell has the expected shade color
        mc.assertShade(3, 4, Color.LIGHT_GRAY);
        mc.close();
    }
    //EndRegion: ShadedCell tests
    
    //Region: EdgeCell tests
    @Test
    public void testEdgeCell_Class() {
        WClass.checkClass("EdgeCell extends ShadedCell extends Cell",
                "public #EdgeCell#(edu.ftdev.Maze.MazeCanvas,int,int)");
    }
    
    @Test
    public void testEdgeCell_Ctor() {
        MazeCanvas mc = new MazeCanvas(6, 9, 32);
        mc.open();
        @SuppressWarnings("unused")
        EdgeCell ec = EdgeCell.newInstance(mc, 3, 4);
        // check the rendered cell has any shade different than white
        mc.assertShade(3, 4, null);
        mc.close();
    }
    
    @Test
    public void testEdgeCell_Walls() {
        MazeCanvas mc = new MazeCanvas(7, 9, 32);
        mc.open();
        // top-left, top-middle, top-right edge cells
        EdgeCell tl = EdgeCell.newInstance(mc, 0, 0);
        EdgeCell tm = EdgeCell.newInstance(mc, 0, 4);
        EdgeCell tr = EdgeCell.newInstance(mc, 0, 8);
        // middle-left, middle-right edge cells
        EdgeCell ml = EdgeCell.newInstance(mc, 3, 0);
        EdgeCell mr = EdgeCell.newInstance(mc, 3, 8);
        // bottom-left, bottom-middle, bottom-right edge cells
        EdgeCell bl = EdgeCell.newInstance(mc, 6, 0);
        EdgeCell bm = EdgeCell.newInstance(mc, 6, 4);
        EdgeCell br = EdgeCell.newInstance(mc, 6, 8);

        // check walls for top-left, top-middle and top-right edge cells
        tl.assertWalls(Side.Bottom, Side.Right);
        tm.assertWalls(Side.Left, Side.Right, Side.Bottom);
        tr.assertWalls(Side.Left, Side.Bottom);
        // check walls for middle-left, middle-right edge cells
        ml.assertWalls(Side.Top, Side.Right, Side.Bottom);
        mr.assertWalls(Side.Top, Side.Left, Side.Bottom);
        // check walls bottom-left, bottom-middle, bottom-right edge cells
        bl.assertWalls(Side.Top, Side.Right);
        bm.assertWalls(Side.Left, Side.Top, Side.Right);
        br.assertWalls(Side.Left, Side.Top);

        mc.close();
    }
    //EndRegion: EdgeCell tests

}
