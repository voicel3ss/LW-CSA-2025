package src;

import java.io.IOException;

import edu.ftdev.Sudoku.SudokuBoard;

/**
 * A class that solves a sudoku puzzle.
 * @author Reyansh Jajoo
 * @date 2026-04-02
 * @class AP Computer Science A
 */

public class Sudoku {
    private SudokuBoard _gui;
    private int _steps;

    public Sudoku(SudokuBoard gui) {
        _gui = gui;
        _steps = 0;
    }

    public boolean solve() {
        return solve(0, 0);
    }

    public boolean solve(int row, int col) {
        int nextRow = row + (col + 1) / 9;
        int nextCol = (col + 1) % 9;
        if (_gui.isPinned(row, col)) {
            return (nextRow >= _gui.NROWS || nextCol >= _gui.NCOLS) || solve(nextRow, nextCol);
        }

        for (int digit : new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }) {
            if (isValid(row, col, digit)) {
                _gui.set(row, col, digit);
                _steps++;
                _gui.breakStep("Step %d", _steps);
                if ((nextRow >= _gui.NROWS || nextCol >= _gui.NCOLS) || solve(nextRow, nextCol)) {
                    return true;
                }
                _gui.set(row, col, 0);
            }
        }
        return false;
    }

    private boolean isValid(int row, int col, int digit) {
        for (int r = 0; r < 9; r++) {
            if (_gui.get(r, col) == digit) {
                return false;
            }
        }

        for (int c = 0; c < 9; c++) {
            if (_gui.get(row, c) == digit) {
                return false;
            }
        }

        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (_gui.get(r, c) == digit) {
                    return false;
                }
            }
        }

        return true;

    }
}