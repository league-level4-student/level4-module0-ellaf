package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);

		// 4. select a random cell to start
		Cell randCell = maze.getCell(randGen.nextInt(width), randGen.nextInt(height));

		// 5. call selectNextPath method with the randomly selected cell
		selectNextPath(randCell);

		ArrayList<Cell> edgeCells = new ArrayList<Cell>();

		for (int i = 0; i < maze.cells.length; i++) {
			for (int j = 0; j < maze.cells[i].length; j++) {
				Cell thisCell = maze.getCell(i, j);
				if (thisCell.getX() == 0) {
					edgeCells.add(thisCell);
					System.out.println("X: " + thisCell.getX() + " Y: " + thisCell.getY());
					;
				} else if (thisCell.getY() == 0) {
					edgeCells.add(thisCell);
					System.out.println("X: " + thisCell.getX() + " Y: " + thisCell.getY());
					;
				} else if (thisCell.getY() == height - 1) {
					edgeCells.add(thisCell);
					System.out.println("X: " + thisCell.getX() + " Y: " + thisCell.getY());
					;
				} else if (thisCell.getY() == width - 1) {
					edgeCells.add(thisCell);
					System.out.println("X: " + thisCell.getX() + " Y: " + thisCell.getY());
					;
				}
			}
		}
		Cell startCell = edgeCells.get(randGen.nextInt(edgeCells.size()));
		ArrayList<Cell> oppEdgeCells = new ArrayList<Cell>();
		if (startCell.getX() == 0) {
			startCell.setWestWall(false);
			for (int i = 0; i < edgeCells.size(); i++) {
				if (edgeCells.get(i).getX() == width - 1) {
					oppEdgeCells.add(edgeCells.get(i));
				}
			}
			Cell endCell = oppEdgeCells.get(randGen.nextInt(oppEdgeCells.size()));
			endCell.setEastWall(false);

		} else if (startCell.getX() == width - 1) {
			startCell.setEastWall(false);
			for (int i = 0; i < edgeCells.size(); i++) {
				if (edgeCells.get(i).getX() == 0) {
					oppEdgeCells.add(edgeCells.get(i));
				}
			}
			Cell endCell = oppEdgeCells.get(randGen.nextInt(oppEdgeCells.size()));
			endCell.setWestWall(false);
			
		} else if (startCell.getY() == 0) {
			startCell.setNorthWall(false);
			for (int i = 0; i < edgeCells.size(); i++) {
				if (edgeCells.get(i).getY() == height - 1) {
					oppEdgeCells.add(edgeCells.get(i));
				}
			}
			Cell endCell = oppEdgeCells.get(randGen.nextInt(oppEdgeCells.size()));
			endCell.setSouthWall(false);

		} else if (startCell.getY() == height - 1) {
			startCell.setSouthWall(false);
			for (int i = 0; i < edgeCells.size(); i++) {
				if (edgeCells.get(i).getY() == 0) {
					oppEdgeCells.add(edgeCells.get(i));
				}
			}
			Cell endCell = oppEdgeCells.get(randGen.nextInt(oppEdgeCells.size()));
			endCell.setNorthWall(false);

		}

		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited
		currentCell.setBeenVisited(true);
		// B. check for unvisited neighbors using the cell
		ArrayList<Cell> unvisNeigh = new ArrayList<Cell>();
		boolean wExists = false;
		boolean eExists = false;
		boolean nExists = false;
		boolean sExists = false;
		Cell westNeigh = null;
		Cell eastNeigh = null;
		Cell southNeigh = null;
		Cell northNeigh = null;
		if (currentCell.getX() - 1 >= 0) {
			westNeigh = maze.getCell(currentCell.getX() - 1, currentCell.getY());
			wExists = true;
		}
		if (currentCell.getX() + 1 < width) {
			eastNeigh = maze.getCell(currentCell.getX() + 1, currentCell.getY());
			eExists = true;
		}
		if (currentCell.getY() - 1 >= 0) {
			southNeigh = maze.getCell(currentCell.getX(), currentCell.getY() - 1);
			sExists = true;
		}
		if (currentCell.getY() + 1 < height) {
			northNeigh = maze.getCell(currentCell.getX(), currentCell.getY() + 1);
			nExists = true;
		}
		if (wExists == true) {
			if (westNeigh.hasBeenVisited() == false) {
				unvisNeigh.add(westNeigh);
			}
		}
		if (eExists == true) {
			if (eastNeigh.hasBeenVisited() == false) {
				unvisNeigh.add(eastNeigh);
			}
		}
		if (nExists == true) {
			if (northNeigh.hasBeenVisited() == false) {
				unvisNeigh.add(northNeigh);
			}
		}
		if (sExists == true) {
			if (southNeigh.hasBeenVisited() == false) {
				unvisNeigh.add(southNeigh);
			}
		}
		// C. if has unvisited neighbors,
		if (unvisNeigh.size() > 0) {
			// C1. select one at random.
			Cell newCell = unvisNeigh.get(randGen.nextInt(unvisNeigh.size()));
			// C2. push it to the stack
			uncheckedCells.push(newCell);
			// C3. remove the wall between the two cells

			removeWalls(currentCell, newCell);
			// C4. make the new cell the current cell and mark it as visited
			currentCell = newCell;
			currentCell.setBeenVisited(true);

			selectNextPath(currentCell);

		}

		// D. if all neighbors are visited
		if (unvisNeigh.size() == 0) {
			// D1. if the stack is not empty
			if (uncheckedCells.size() > 0) {
				// D1a. pop a cell from the stack
				Cell poppedCell = uncheckedCells.pop();
				// D1b. make that the current cell
				currentCell = poppedCell;

				selectNextPath(currentCell);
			}
		}

	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if ((c1.getX() == c2.getX()) && (c1.getY() + 1 == c2.getY())) {
			c1.setSouthWall(false);
			c2.setNorthWall(false);
		} else if ((c1.getX() == c2.getX()) && (c1.getY() - 1 == c2.getY())) {
			c1.setNorthWall(false);
			c2.setSouthWall(false);
		} else if ((c1.getX() + 1 == c2.getX()) && (c1.getY() == c2.getY())) {
			c1.setEastWall(false);
			c2.setWestWall(false);
		} else if ((c1.getX() - 1 == c2.getX()) && (c1.getY() == c2.getY())) {
			c1.setWestWall(false);
			c2.setEastWall(false);
		} else {

		}
	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		return null;
	}
}