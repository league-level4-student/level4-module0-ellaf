package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		Cell randCell = maze.getCell(randGen.nextInt(width), randGen.nextInt(height));
		
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(randCell);
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		currentCell.setBeenVisited(true);
		//B. check for unvisited neighbors using the cell
		ArrayList<Cell> unvisNeigh = new ArrayList<Cell>();
		Cell westNeigh = maze.getCell(currentCell.getX()-1, currentCell.getY());
		Cell eastNeigh = maze.getCell(currentCell.getX()+1, currentCell.getY());
		Cell southNeigh = maze.getCell(currentCell.getX(), currentCell.getY()-1);
		Cell northNeigh = maze.getCell(currentCell.getX(), currentCell.getY()+1);
		if(westNeigh.hasBeenVisited() == false) {
			unvisNeigh.add(westNeigh);
		}
		if(eastNeigh.hasBeenVisited() == false) {
			unvisNeigh.add(eastNeigh);
		}
		if(northNeigh.hasBeenVisited() == false) {
			unvisNeigh.add(northNeigh);
		}
		if(southNeigh.hasBeenVisited() == false) {
			unvisNeigh.add(southNeigh);
		}
		//C. if has unvisited neighbors,
		if(unvisNeigh.size() > 0) {
			//C1. select one at random.
			Cell newCell = unvisNeigh.get(randGen.nextInt(unvisNeigh.size()));
			//C2. push it to the stack
			uncheckedCells.push(newCell);
			//C3. remove the wall between the two cells
			if(newCell == westNeigh) {
				currentCell.setWestWall(false);
				newCell.setEastWall(false);
			}
			if(newCell == eastNeigh) {
				currentCell.setEastWall(false);
				newCell.setWestWall(false);
			}
			if(newCell == northNeigh) {
				currentCell.setNorthWall(false);
				newCell.setSouthWall(false);
			}
			if(newCell == southNeigh) {
				currentCell.setWestWall(false);
				newCell.setEastWall(false);
			}
			//C4. make the new cell the current cell and mark it as visited
			currentCell = newCell;
			currentCell.setBeenVisited(true);
		}
			
		//D. if all neighbors are visited
		if(unvisNeigh.size() == 0) {
			//D1. if the stack is not empty
			if(uncheckedCells.size() > 0) {
				// D1a. pop a cell from the stack
				Cell poppedCell = uncheckedCells.pop();
				// D1b. make that the current cell
				currentCell = poppedCell;
			}
		}
		
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if((c1.getX() == c2.getX()) && (c1.getY()+1 == c2.getY())) {
			c1.setSouthWall(false);
			c2.setNorthWall(false);
		}
		else if((c1.getX() == c2.getX()) && (c1.getY()-1 == c2.getY())) {
			c1.setNorthWall(false);
			c2.setSouthWall(false);
		}
		else if((c1.getX()+1 == c2.getX()) && (c1.getY() == c2.getY())) {
			c1.setEastWall(false);
			c2.setWestWall(false);
		}
		else if((c1.getX()-1 == c2.getX()) && (c1.getY() == c2.getY())) {
			c1.setWestWall(false);
			c2.setEastWall(false);
		}
		else {
			
		}
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		return null;
	}
}