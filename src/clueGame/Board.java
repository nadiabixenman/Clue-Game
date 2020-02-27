package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Board {
	private int numRows;
	private int numColumns;
	public final int MAX_BOARD_SIZE = 50;
	private BoardCell[][] board;
	private Map<Character, String> legend;
	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	private Set<BoardCell> targets;
	private String boardConfigFile;
	private String roomConfigFile;

	// variable used for singleton pattern
	private static Board theInstance = new Board();
	// constructor is private to ensure only one can be created
	private Board() {}
	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}
	
	public void initialize() {
		
	}
	
	public void loadRoomConfig() throws FileNotFoundException, BadConfigFormatException{
		FileReader reader = new FileReader(roomConfigFile);
		Scanner in = new Scanner(reader);
		
		while(in.hasNextLine()) {
			String line = in.nextLine();
			char initial = line.charAt(0);
			int index = line.indexOf(",", 3);
			String roomName = line.substring(3, index-1);
			legend.put(initial, roomName);
			String roomType = line.substring(index + 2);
			if (roomType != "Card" && roomType != "Other") {
				BadConfigFormatException e = new BadConfigFormatException("Room type " + roomType + " is not Card or Other");
			}
		}
	}
	
	public void loadBoardConfig() throws FileNotFoundException, BadConfigFormatException{
		FileReader reader = new FileReader(boardConfigFile);
		Scanner in = new Scanner(reader);
		int row = 0;
		while(in.hasNextLine()) {
			String line = in.nextLine();
			int length = line.length();
			int column = 0;
			for (int i = 0; i<length; i++) {
				String roomInitial;
				if (line.charAt(i) != ',') {
					roomInitial = line.substring(i, (line.indexOf(',', i)) - 1);
					board[row][column] = new BoardCell(row, column);
					board[row][column].setInitial(roomInitial);
					column++;
					i = line.indexOf(',', i);
				}
			}
			row++;
		}
	}
	
	public void calcAdjancencies() {
		
	}
	
	public void calcTargets(BoardCell cell, int pathLength) {
		
	}
	
	public void setConfigFiles(String layoutName, String legendName) {
		roomConfigFile = legendName;
		boardConfigFile = layoutName;
	}
	
	public Map<Character, String> getLegend() {
		return new HashMap<Character, String>(50);
	}
	
	public int getNumRows() {
		return 0;
	}
	
	public int getNumColumns() {
		return 0;
	}
	
	public BoardCell getCellAt(int row, int column) {
		return new BoardCell(0, 0);
	}
	
}
