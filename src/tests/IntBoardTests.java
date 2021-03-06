package tests;

import static org.junit.Assert.*;
import org.junit.*;
import experiment.BoardCell;
import experiment.IntBoard;
import java.util.*;

/**
 * @author Sam Mills, Nadia Bixenman
 *
 */
public class IntBoardTests {
	IntBoard gameBoard;
	
	// Set up gameBoard for all tests
	@Before
	public void beforeAll() {
		gameBoard = new IntBoard();
	}

	// Adjacency tests - test that the adjacency lists for the given cell are exactly the correct length and contain the correct cells
	
	// Cell 0_0
	@Test
	public void testAdjacancy0_0() {
		BoardCell cell = gameBoard.getCell(0,0);
		Set<BoardCell> testList = gameBoard.getAdjList(cell);
		assertTrue(testList.contains(gameBoard.getCell(1, 0)));
		assertTrue(testList.contains(gameBoard.getCell(0, 1)));
		assertEquals(2, testList.size());
	}
	
	// Cell 3_3
	@Test
	public void testAdjacancy3_3() {
		BoardCell cell = gameBoard.getCell(3,3);
		Set<BoardCell> testList = gameBoard.getAdjList(cell);
		assertTrue(testList.contains(gameBoard.getCell(3, 2)));
		assertTrue(testList.contains(gameBoard.getCell(2, 3)));
		assertEquals(2, testList.size());
	}
	
	// Cell 1_3
	@Test
	public void testAdjacancy1_3() {
		BoardCell cell = gameBoard.getCell(1,3);
		Set<BoardCell> testList = gameBoard.getAdjList(cell);
		assertTrue(testList.contains(gameBoard.getCell(0, 3)));
		assertTrue(testList.contains(gameBoard.getCell(1, 2)));
		assertTrue(testList.contains(gameBoard.getCell(2, 3)));
		assertEquals(3, testList.size());
	}
	
	// Cell 3_0
	@Test
	public void testAdjacancy3_0() {
		BoardCell cell = gameBoard.getCell(3,0);
		Set<BoardCell> testList = gameBoard.getAdjList(cell);
		assertTrue(testList.contains(gameBoard.getCell(2, 0)));
		assertTrue(testList.contains(gameBoard.getCell(3, 1)));
		assertEquals(2, testList.size());
	}
	
	// Cell 1_1
	@Test
	public void testAdjacancy1_1() {
		BoardCell cell = gameBoard.getCell(1,1);
		Set<BoardCell> testList = gameBoard.getAdjList(cell);
		assertTrue(testList.contains(gameBoard.getCell(0, 1)));
		assertTrue(testList.contains(gameBoard.getCell(2, 1)));
		assertTrue(testList.contains(gameBoard.getCell(1, 0)));
		assertTrue(testList.contains(gameBoard.getCell(1, 2)));
		assertEquals(4, testList.size());
	}
	
	// Cell 2_2
	@Test
	public void testAdjacancy2_2() {
		BoardCell cell = gameBoard.getCell(2,2);
		Set<BoardCell> testList = gameBoard.getAdjList(cell);
		assertTrue(testList.contains(gameBoard.getCell(1, 2)));
		assertTrue(testList.contains(gameBoard.getCell(3, 2)));
		assertTrue(testList.contains(gameBoard.getCell(2, 1)));
		assertTrue(testList.contains(gameBoard.getCell(2, 3)));
		assertEquals(4, testList.size());
	}
	
	
	// Target tests from two starting cells and with two path lengths per cell - test that the length of the target sets are exactly correct,
	// and that the sets contain the correct cells
	
	// Starting from cell 0_0 with path length 4
	@Test
	public void testTargets0_0_4() {
		BoardCell cell = gameBoard.getCell(0, 0);
		
		// Length 4
		gameBoard.calcTargets(cell, 4);
		Set targets = gameBoard.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(gameBoard.getCell(0, 2)));
		assertTrue(targets.contains(gameBoard.getCell(1, 1)));
		assertTrue(targets.contains(gameBoard.getCell(1, 3)));
		assertTrue(targets.contains(gameBoard.getCell(2, 0)));
		assertTrue(targets.contains(gameBoard.getCell(2, 2)));
		assertTrue(targets.contains(gameBoard.getCell(3, 1)));
		
	}
	
	// Starting from cell 0_0 with path length 6
	@Test
	public void testTargets0_0_6() {
		BoardCell cell = gameBoard.getCell(0, 0);
		
		//Length 6
		gameBoard.calcTargets(cell, 6);
		Set targets6 = gameBoard.getTargets();
		assertEquals(7, targets6.size());
		assertTrue(targets6.contains(gameBoard.getCell(0, 2)));
		assertTrue(targets6.contains(gameBoard.getCell(1, 1)));
		assertTrue(targets6.contains(gameBoard.getCell(1, 3)));
		assertTrue(targets6.contains(gameBoard.getCell(2, 0)));
		assertTrue(targets6.contains(gameBoard.getCell(2, 2)));
		assertTrue(targets6.contains(gameBoard.getCell(3, 1)));
		assertTrue(targets6.contains(gameBoard.getCell(3, 3)));
		
	}
	
	// Starting from cell 1_1 with path length 2
	@Test
	public void testTargets1_1_2() {
		BoardCell cell = gameBoard.getCell(2, 2);
		
		// Length 2
		gameBoard.calcTargets(cell, 2);
		Set targets = gameBoard.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(gameBoard.getCell(0, 2)));
		assertTrue(targets.contains(gameBoard.getCell(1, 3)));
		assertTrue(targets.contains(gameBoard.getCell(1, 1)));
		assertTrue(targets.contains(gameBoard.getCell(2, 0)));
		assertTrue(targets.contains(gameBoard.getCell(3, 3)));
		assertTrue(targets.contains(gameBoard.getCell(3, 1)));
		
	}
	
	// Starting from cell 1_1 with path length 3
	@Test
	public void testTargets1_1_3() {
		BoardCell cell = gameBoard.getCell(2, 2);
		//Length 3
		gameBoard.calcTargets(cell, 3);
		Set targets3 = gameBoard.getTargets();
		assertEquals(8, targets3.size());
		assertTrue(targets3.contains(gameBoard.getCell(0, 1)));
		assertTrue(targets3.contains(gameBoard.getCell(0, 3)));
		assertTrue(targets3.contains(gameBoard.getCell(1, 0)));
		assertTrue(targets3.contains(gameBoard.getCell(1, 2)));
		assertTrue(targets3.contains(gameBoard.getCell(2, 1)));
		assertTrue(targets3.contains(gameBoard.getCell(2, 3)));
		assertTrue(targets3.contains(gameBoard.getCell(3, 0)));
		assertTrue(targets3.contains(gameBoard.getCell(3, 2)));
	}
		
}


