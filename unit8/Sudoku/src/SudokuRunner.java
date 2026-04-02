package src;

import java.io.IOException;

import edu.ftdev.Sudoku.SudokuBoard;

public class SudokuRunner {

    /**
     * Demo code showcasing the use of a SudokuBoard instance.
     * @param gui the instance used for the demo.
     */
    public static void trySudokuGUI(SudokuBoard gui) {
        int count = 1;
        for (int r = 0; r < gui.NROWS; r++) {
            for (int c = 0; c < gui.NCOLS; c++) {
                if (gui.isPinned(r, c) && gui.get(r, c) == 7) {
                    count++;
                    gui.breakStep("Stop count: ", count);
                }
                if (!gui.isPinned(r, c)){
                    gui.set(r, c, 7);
                }
            }
        }
        gui.breakLeap("Resetting center position");
        gui.set(4, 4, 0);
        gui.setStatusMessage("Digit '7' pinned in %d positions", count);
    }
    
    /**
     * Main entry point in the program
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // Loading a demo Sudoku board provided in the drawing-lib package.
        //SudokuBoard gui = new SudokuBoard("Sudoku1.txt");
        SudokuBoard gui = new SudokuBoard("unit8\\Sudoku\\data\\Sudoku1.txt");
        
        // Open the board GUI and stop before proceeding with demo code.
        gui.open();
        gui.breakStep("Calling demo code!");
        
        // Call the demo code
        // trySudokuGUI(gui);
        Sudoku puzzle = new Sudoku(gui);
        boolean puzzleSolved = puzzle.solve();
        // Stope before closing the GUI and terminating the program.
        if (puzzleSolved) {
            gui.breakJump("Puzzle solved!");
        } else {
            gui.breakJump("Could not solve the puzzle!");
        }
        gui.close();
        System.out.println("Goodbye!");
    }
}