# AP10.Maze

Lab assignment for the Unit 10 AP CS-A class (topics post exam), with focus on OOP (class inheritance and virtual methods). This assignment is a complex, multi-checkpoints project
involving a class hierarchy which models a maze.

The maze is a rectangular grid of cells. Cells on its boundary are _edge_ cells. Scattered on its inside are some _block_ (impassable) cells. There's one _entry_ cell and one _exit_ cell on maze's edge boundary. The assignment requires designing and implementing the class hierarchy modeling this maze, then generating the maze randomly and finding a path from the _entry_ cell to the _exit_ cell via backtracking.

This project uses a graphical user interface (GUI) library for rendering the maze and for showing the progression of the algorithms. The content needed for this assignment:
* [ ] [Maze.pptx](https://florinteo.github.io/EduLabs/ap10.Maze/spec/Maze.pptx): complete specification of the project.
* [ ] [drawing-lib](https://florinteo.github.io/EduLabs/ap8.Sudoku/lib/drawing-lib-2.3-shaded.jar): Java API library supporting graphic operations such as rendering a puzzle and showing the algorithm progress. It is available in the assignment package, in the *lib* folder. Instructions on how to configure this library in your environment can be found [here](https://github.com/LWHSStave/Setup/blob/main/RefJar/README.md).
* [ ] [MazeCanvas](https://florinteo.github.io/EduCode/DrawingLib/edu/ftdev/Maze/MazeCanvas.html) class: detailed documentation of the class contained in the `drawing-lib` library, the only one needed for the GUI framework of this project.

