package clueGame;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Sam Mills, Nadia Bixenman
 *
 */
public class BoardCell {
	private int row;
	private int column;
	private char roomInitial;
	private DoorDirection direction;
	private boolean isWalkway;
	private boolean isRoom;
	private boolean isDoorway;
	
	/** BoardCell constructor
	 * @param row
	 * @param column
	 */
	public BoardCell(int row, int column) {
		this.row = row;
		this.column = column;
		isWalkway = false;
		isDoorway = false;
		isRoom = false;
	}
	
	/** Returns integer row
	 * @return
	 */
	public int getRow() {
		return this.row;
	}
	
	/** Returns integer column
	 * @return
	 */
	public int getCol() {
		return this.column;
	}
	
	public boolean isWalkway() {
		return isWalkway;
	}
	
	public boolean isRoom() {
		return isRoom;
	}
	
	public boolean isDoorway() {
		return isDoorway;
	}
	
	public DoorDirection getDoorDirection() {
		return direction;
	}
	
	public char getInitial() {
		return roomInitial;
	}
	
	// Convert to character
	public void setInitial(String initial) {
		roomInitial = initial.charAt(0);
	}
	
	public void setWalkway() {
		isWalkway = true;
	}
	
	public void setRoom() {
		isRoom = true;
	}
	
	/** Given that the cell is a door(two character initial, setDoor called), sets the direction of the door cell and that it is a door
	 * @param door
	 * 
	 */
	public void setDoor(String door) {
		char directionChar = door.charAt(1);
		
		switch(directionChar) {
		case 'R':
			direction = DoorDirection.RIGHT;
			break;
		case 'L':
			direction = DoorDirection.LEFT;
			break;
		case 'U':
			direction = DoorDirection.UP;
			break;
		case 'D':
			direction = DoorDirection.DOWN;
			break;
		default:
			direction = DoorDirection.NONE;
			break;
		}
		
		if(direction != DoorDirection.NONE) {
			isDoorway = true;
		}
		
	}
	
	public void draw(Graphics g) {
		int width = 20;
		int height = 20;
		
		if(isRoom) {
			g.setColor(Color.gray);
			g.fillRect(column+width*column, row+height*row, width, height);
		}
		else {
			g.setColor(Color.black);
			g.drawRect(column+width*column, row+height*row, width, height);
			g.setColor(Color.yellow);
			g.fillRect(column+width*column, row+height*row, width, height);
		}
		
		if(isDoorway) {
			int doorHeight = 20;
			int doorWidth = 20;
			int rowCorrector = 0;
			int columnCorrector = 0; //values to correct the location of the door if it is at the bottom or right of cell
			
			switch(direction) {
			case RIGHT:
				doorWidth = 2;
				columnCorrector = width - doorWidth;
				break;
			case LEFT:
				doorWidth = 2;
				break;
			case UP:
				doorHeight = 2;
				break;
			case DOWN:
				doorHeight = 2;
				rowCorrector = height = doorHeight;
				break;
			default:
				break;	
			}
			
			g.setColor(Color.blue);
			g.fillRect(column+width*column+columnCorrector, row+height*row+rowCorrector, doorWidth, doorHeight);
		}
	}
	
}
