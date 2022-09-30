import java.util.*;
 
public class Board {
 
	int[][] board;
	// White = 1
	// Black = 2
	// Empty = 0
	int size;
 
	int whitePieceCount;
	int blackPieceCount;
 
	final protected int loss = Integer.MIN_VALUE;
	final protected int win = Integer.MAX_VALUE;
	final protected int draw = 0;
	/*
 	* TO DO: 1. Set that store reached states (unavaible moves) 2. Score counter 3.
 	* Create Ai that chooses max score move 4.
 	*/
 
	// Sub Class of Board, Creates the intial state
	Board(int size) {
 
    	this.board = new int[size][size];
    	this.size = size;
 
    	this.blackPieceCount = 2;
    	this.whitePieceCount = 2;
 
    	board[size / 2 - 1][size / 2 - 1] = 1;
    	board[(size / 2)][size / 2 - 1] = 2;
    	board[size / 2 - 1][(size / 2)] = 2;
    	board[(size / 2)][(size / 2)] = 1;
 
	}
 
	HashSet<Move> reachedSpaceMoves(Board b, Player p, Move m) {
    	HashSet<Move> tiles = new HashSet<Move>(); // Empty Set
    	for (int i = 0; i < b.size; i++) { // Double for loop across the board
        	for (int j = 0; j < b.size; j++) {
        	}
    	}
    	return null;
 
	}
 
	void getScore(Board b) {
    	HashSet<Move> score = new HashSet<Move>();
 
	}
 
	// Set for to collect all legal moves for Black
	// STATES
	HashSet<Move> getLegalMovesBlack(Board b, Player p) {
 
    	HashSet<Move> legalMoves = new HashSet<Move>(); // Empty Set
 
    	for (int i = 0; i < b.size; i++) { // Double for loop across the board
        	for (int j = 0; j < b.size; j++) {
            	Move tempMove = new Move(i, j); // Goes to coordinate (r,c)
            	HashSet<Integer> emenySet = adjacentToEnemyBlack(b, tempMove); // Checks adjacent enemies to Black
 
            	if (emenySet.isEmpty()) { // If it is empty, stores in set
                	continue;
            	}
            	if (!(b.board[tempMove.getRow()][tempMove.getCol()] == 0)) {
                	continue;
            	}
 
            	// Legal Direction not empty and Enemy Set not empty
            	if ((!(getLegalDirectionsBlack(b, tempMove, p).isEmpty())) && (!emenySet.isEmpty())) {
                	legalMoves.add(tempMove);
 
            	}
        	}
 
    	}
    	// for setperimaterBlack, check if legal Move, return set of legal moves
 
    	return legalMoves;
	}
 
	HashSet<Move> getLegalMovesWhite(Board b, Player p) {
    	HashSet<Move> legalMoves = new HashSet<Move>(); // Empty Set
 
    	for (int i = 0; i < b.size; i++) { // Double for loop across the board
        	for (int j = 0; j < b.size; j++) {
            	Move tempMove = new Move(i, j); // Goes to coordinate (r,c)
            	HashSet<Integer> emenySet = adjacentToEnemyWhite(b, tempMove); // Checks adjacent enemies to Black
 
            	if (emenySet.isEmpty()) { // If it is empty, stores in set
                	continue;
            	}
 
            	if (!(b.board[tempMove.getRow()][tempMove.getCol()] == 0)) {
                	continue;
            	}
 
            	// Legal Direction not empty and Enemy Set not empty
            	if ((!(getLegalDirectionsWhite(b, tempMove, p).isEmpty())) && (!emenySet.isEmpty())) {
                	legalMoves.add(tempMove);
 
            	}
        	}
 
    	}
    	// for setperimaterBlack, check if legal Move, return set of legal moves
 
    	return legalMoves;
	}
 
	static HashSet<Integer> getLegalDirectionsBlack(Board b, Move m, Player p) {
 
    	HashSet<Integer> legalDirections = new HashSet<Integer>();
    	// printSet(legalDirections);
    	// 1 2 3
    	// 8 m 4 m is target move
    	// 7 6 5
 
    	HashSet<Integer> directionList = adjacentToEnemyBlack(b, m);
    	int cycle = 1;
    	if (onBoard(m, b) == false) {
        	return legalDirections;
    	}
    	Move initialMove = m;
 
    	if (directionList.contains(1)) {
        	m.row = m.row - cycle;
        	m.col = m.col - cycle;
        	while (onBoard(m, b)) {
 
            	if (b.board[m.row][m.col] == 0) {
                	m.row = m.row + cycle;
                	m.col = m.col + cycle;
 
                	cycle = 1;
                	break;
            	}
            	if (b.board[m.row][m.col] == 2) {
                	legalDirections.add(1);
            	}
 
            	m.col = m.col - 1;
            	m.row = m.row - 1;
            	cycle++;
 
        	}
        	if (!onBoard(m, b)) {
            	m.row = m.row + cycle;
            	m.col = m.col + cycle;
 
            	cycle = 1;
        	}
 
    	}
    	m = initialMove;
    	if (directionList.contains(2)) {
        	m.row = m.row - cycle;
        	while (onBoard(m, b)) {
 
            	if (b.board[m.row][m.col] == 0) {
                	m.row = m.row + cycle;
                	cycle = 1;
                	break;
            	}
            	if (b.board[m.row][m.col] == 2) {
                	legalDirections.add(2);
            	}
 
            	m.row = m.row - 1;
            	cycle++;
 
        	}
        	if (!onBoard(m, b)) {
            	m.row = m.row + cycle;
 
            	cycle = 1;
        	}
 
    	}
    	m = initialMove;
    	if (directionList.contains(3)) {
        	m.row = m.row - cycle;
        	m.col = m.col + cycle;
        	while (onBoard(m, b)) {
 
            	if (b.board[m.row][m.col] == 0) {
                	m.row = m.row + cycle;
                	m.col = m.col - cycle;
                	cycle = 1;
                	break;
            	}
            	if (b.board[m.row][m.col] == 2) {
                	legalDirections.add(3);
            	}
 
            	m.col = m.col + 1;
            	m.row = m.row - 1;
            	cycle++;
 
        	}
        	if (!onBoard(m, b)) {
            	m.row = m.row + cycle;
            	m.col = m.col - cycle;
 
            	cycle = 1;
        	}
 
    	}
    	m = initialMove;
    	if (directionList.contains(4)) {
 
        	m.col = m.col + cycle;
        	while (onBoard(m, b)) {
 
            	if (b.board[m.row][m.col] == 0) {
                	m.col = m.col - cycle;
                	cycle=1;
                	break;
            	}
            	if (b.board[m.row][m.col] == 2) {
                	legalDirections.add(4);
            	}
 
            	m.col = m.col + 1;
            	cycle++;
 
        	}
        	if (!onBoard(m, b)) {
            	m.col = m.col - cycle;
 
            	cycle = 1;
        	}
 
    	}
    	m = initialMove;
    	if (directionList.contains(5)) {
 
        	m.row = m.row + cycle;
        	m.col = m.col + cycle;
 
        	while (onBoard(m, b)) {
 
            	if (b.board[m.row][m.col] == 0) {
                	m.row = m.row - cycle;
                	m.col = m.col - cycle;
                	cycle = 1;
                	break;
            	}
            	if (b.board[m.row][m.col] == 2) {
                	legalDirections.add(5);
            	}
 
            	m.col = m.col + 1;
            	m.row = m.row + 1;
            	cycle++;
 
        	}
        	if (!onBoard(m, b)) {
            	m.row = m.row - cycle;
            	m.col = m.col - cycle;
 
            	cycle = 1;
        	}
 
    	}
    	m = initialMove;
    	if (directionList.contains(6)) {
        	m.row = m.row + cycle;
 
        	while (onBoard(m, b)) {
 
            	if (b.board[m.row][m.col] == 0) {
                	m.row = m.row - cycle;
                	cycle = 1;
                	break;
            	}
            	if (b.board[m.row][m.col] == 2) {
                	legalDirections.add(6);
            	}
 
            	;
            	m.row = m.row + 1;
            	cycle++;
 
        	}
        	if (!onBoard(m, b)) {
            	m.row = m.row - cycle;
 
            	cycle = 1;
        	}
 
    	}
    	m = initialMove;
    	if (directionList.contains(7)) {
        	m.row = m.row + cycle;
        	m.col = m.col - cycle;
        	while (onBoard(m, b)) {
 
            	if (b.board[m.row][m.col] == 0) {
                	m.row = m.row - cycle;
                	m.col = m.col + cycle;
                	cycle = 1;
                	break;
            	}
            	if (b.board[m.row][m.col] == 2) {
                	legalDirections.add(7);
            	}
 
            	m.col = m.col - 1;
            	m.row = m.row + 1;
            	cycle++;
 
        	}
        	if (!onBoard(m, b)) {
            	m.row = m.row - cycle;
            	m.col = m.col + cycle;
 
            	cycle = 1;
        	}
 
    	}
    	m = initialMove;
    	if (directionList.contains(8)) {
 
        	m.col = m.col - cycle;
        	while (onBoard(m, b)) {
 
            	if (b.board[m.row][m.col] == 0) {
                	m.col = m.col + cycle;
                	cycle = 1;
 
                	break;
            	}
            	if (b.board[m.row][m.col] == 2) {
                	legalDirections.add(8);
            	}
 
            	m.col = m.col - 1;
            	cycle++;
 
        	}
        	if (!onBoard(m, b)) {
            	m.col = m.col + cycle;
 
            	cycle = 1;
        	}
 
    	}
    	m = initialMove;
    	return legalDirections;
	}
 
	static HashSet<Integer> getLegalDirectionsWhite(Board b, Move m, Player p) {
 
    	HashSet<Integer> legalDirections = new HashSet<Integer>();
    	// printSet(legalDirections);
    	// 1 2 3
    	// 8 m 4 m is target move
    	// 7 6 5
 
    	HashSet<Integer> directionList = adjacentToEnemyWhite(b, m);
    	int cycle = 1;
    	if (onBoard(m, b) == false) {
        	return legalDirections;
    	}
    	Move initialMove = m;
 
    	if (directionList.contains(1)) {
   		 if(cycle ==2) {
   			 cycle =1;
   			 System.out.println("IN 1");
   		 }
        	m.row = m.row - cycle;
        	m.col = m.col - cycle;
        	while (onBoard(m, b)) {
 
            	if (b.board[m.row][m.col] == 0) {
                	m.row = m.row + cycle;
                	m.col = m.col + cycle;
 
                	cycle = 1;
                	break;
            	}
            	if (b.board[m.row][m.col] == 1) {
                	legalDirections.add(1);
            	}
 
            	m.col = m.col - 1;
            	m.row = m.row - 1;
            	cycle++;
 
        	}
        	if (!onBoard(m, b)) {
            	m.row = m.row + cycle;
            	m.col = m.col + cycle;
 
            	cycle = 1;
        	}
 
    	}
    	m = initialMove;
    	if (directionList.contains(2)) {
   		 if(cycle ==2) {
   			 cycle =1;
 
   			 System.out.println("IN 2");
   		 }
        	m.row = m.row - cycle;
        	while (onBoard(m, b)) {
 
            	if (b.board[m.row][m.col] == 0) {
                	m.row = m.row + cycle;
                	cycle = 1;
                	break;
            	}
            	if (b.board[m.row][m.col] == 1) {
                	legalDirections.add(2);
            	}
 
            	m.row = m.row - 1;
            	cycle++;
 
        	}
        	if (!onBoard(m, b)) {
            	m.row = m.row + cycle;
 
            	cycle = 1;
        	}
 
    	}
    	m = initialMove;
    	if (directionList.contains(3)) {
   		 if(cycle ==2) {
   			 cycle =1;
 
   			 System.out.println("IN 3");
   		 }
   		 
        	m.row = m.row - cycle;
        	m.col = m.col + cycle;
        	while (onBoard(m, b)) {
 
            	if (b.board[m.row][m.col] == 0) {
                	m.row = m.row + cycle;
                	m.col = m.col - cycle;
 
                	cycle = 1;
                	break;
            	}
           	 
            	if (b.board[m.row][m.col] == 1) {
                	legalDirections.add(3);
            	}
 
            	m.col = m.col + 1;
            	m.row = m.row - 1;
            	cycle++;
 
        	}
        	if (!onBoard(m, b)) {
            	m.row = m.row + cycle;
            	m.col = m.col - cycle;
 
            	cycle = 1;
        	}
 
    	}
    	m = initialMove;
    	if (directionList.contains(4)) {
   		 if(cycle ==2) {
   			 cycle =1;
 
   			 System.out.println("IN 4");
   		 }
        	m.col = m.col + cycle;
        	while (onBoard(m, b)) {
 
            	if (b.board[m.row][m.col] == 0) {
                	m.col = m.col - cycle;
                	cycle = 1;
 
                	break;
            	}
            	if (b.board[m.row][m.col] == 1) {
                	legalDirections.add(4);
            	}
 
            	m.col = m.col + 1;
            	cycle++;
 
        	}
        	if (!onBoard(m, b)) {
            	m.col = m.col - cycle;
 
            	cycle = 1;
        	}
 
    	}
    	m = initialMove;
    	if (directionList.contains(5)) {
   		 if(cycle ==2) {
   			 cycle =1;
 
   			 System.out.println("IN 5");
   		 }
        	m.row = m.row + cycle;
        	m.col = m.col + cycle;
 
        	while (onBoard(m, b)) {
 
            	if (b.board[m.row][m.col] == 0) {
                	m.row = m.row - cycle;
                	m.col = m.col - cycle;
                	cycle = 1;
 
                	break;
            	}
            	if (b.board[m.row][m.col] == 1) {
                	legalDirections.add(5);
            	}
 
            	m.col = m.col + 1;
            	m.row = m.row + 1;
            	cycle++;
 
        	}
        	if (!onBoard(m, b)) {
            	m.row = m.row - cycle;
            	m.col = m.col - cycle;
 
            	cycle = 1;
        	}
 
    	}
    	m = initialMove;
    	if (directionList.contains(6)) {
   		 if(cycle ==2) {
   			 cycle =1;
 
   			 System.out.println("IN 6");
   		 }
        	m.row = m.row + cycle;
 
        	while (onBoard(m, b)) {
 
            	if (b.board[m.row][m.col] == 0) {
                	m.row = m.row - cycle;
                	cycle = 1;
 
                	break;
            	}
            	if (b.board[m.row][m.col] == 1) {
                	legalDirections.add(6);
            	}
 
            	;
            	m.row = m.row + 1;
            	cycle++;
 
        	}
        	if (!onBoard(m, b)) {
            	m.row = m.row - cycle;
 
            	cycle = 1;
        	}
 
    	}
    	m = initialMove;
    	if (directionList.contains(7)) {
   		 if(cycle ==2) {
   			 cycle =1;
 
   			 System.out.println("IN 7");
   		 }
        	m.row = m.row + cycle;
        	m.col = m.col - cycle;
        	while (onBoard(m, b)) {
 
            	if (b.board[m.row][m.col] == 0) {
                	m.row = m.row - cycle;
                	m.col = m.col + cycle;
                	cycle = 1;
                	break;
            	}
            	if (b.board[m.row][m.col] == 1) {
                	legalDirections.add(7);
            	}
 
            	m.col = m.col - 1;
            	m.row = m.row + 1;
            	cycle++;
 
        	}
        	if (!onBoard(m, b)) {
            	m.row = m.row - cycle;
            	m.col = m.col + cycle;
 
            	cycle = 1;
        	}
 
    	}
    	m = initialMove;
    	if (directionList.contains(8)) {
   		 if(cycle ==2) {
   			 cycle =1;
 
   			 System.out.println("IN 8");
   		 }
        	m.col = m.col - cycle;
        	while (onBoard(m, b)) {
 
            	if (b.board[m.row][m.col] == 0) {
                	m.col = m.col + cycle;
                	cycle = 1;
 
                	break;
            	}
            	if (b.board[m.row][m.col] == 1) {
                	legalDirections.add(8);
            	}
 
            	m.col = m.col - 1;
            	cycle++;
 
        	}
        	if (!onBoard(m, b)) {
            	m.col = m.col + cycle;
 
            	cycle = 1;
        	}
 
    	}
    	m = initialMove;
    	return legalDirections;
	}
 
	// Checks the direction of move to see if legal
 
	// RESULT
	void handleAllCapturesBlack(Board b, Move m, Player p) {
 
    	int cycle = 1;
    	HashSet<Integer> legalDircetions = getLegalDirectionsBlack(b, m, p);
    	Move initialMove = m;
 
    	if (legalDircetions.contains(1)) {
        	m.row = m.row - cycle;
        	m.col = m.col - cycle;
       	 
 
        	while (onBoard(m, b)) {
 
            	if (b.board[m.row][m.col] == 1) {
                	b.board[m.row][m.col] = 2;
                	m.col = m.col - 1;
                	m.row = m.row - 1;
                	cycle++;
 
            	}
 
            	if (b.board[m.row][m.col] == 2) {
                	m.col = m.col + cycle;
                	m.row = m.row + cycle;
                	whitePieceCount = whitePieceCount - cycle;
                	blackPieceCount = blackPieceCount + cycle;
                	cycle = 1;
                	break;
            	}
 
        	}
 
    	}
    	m = initialMove;
    	if (legalDircetions.contains(2)) {
        	m.row = m.row - cycle;
 
        	while (onBoard(m, b)) {
 
            	if (b.board[m.row][m.col] == 1) {
                	b.board[m.row][m.col] = 2;
                	m.row = m.row - 1;
                	cycle++;
 
            	}
 
            	if (b.board[m.row][m.col] == 2) {
                	m.row = m.row + cycle;
                	whitePieceCount = whitePieceCount - cycle;
                	blackPieceCount = blackPieceCount + cycle;
                	cycle = 1;
                	break;
            	}
 
        	}
 
    	}
    	m = initialMove;
    	if (legalDircetions.contains(3)) {
        	m.row = m.row - cycle;
        	m.col = m.col + cycle;
        	if (b.board[m.row][m.col] == 0) {
       		 return;
        	}
        	while (onBoard(m, b)) {
 
            	if (b.board[m.row][m.col] == 1) {
                	b.board[m.row][m.col] = 2;
                	m.col = m.col + 1;
                	m.row = m.row - 1;
                	cycle++;
 
            	}
 
            	if (b.board[m.row][m.col] == 2) {
                	m.row = m.row + cycle;
                	m.col = m.col - cycle;
                	cycle = 1;
                	whitePieceCount = whitePieceCount - cycle;
                	blackPieceCount = blackPieceCount + cycle;
                	break;
            	}
 
        	}
 
    	}
    	m = initialMove;
    	if (legalDircetions.contains(4)) {
 
        	m.col = m.col + cycle;
 
        	while (onBoard(m, b)) {
 
            	if (b.board[m.row][m.col] == 1) {
                	b.board[m.row][m.col] = 2;
                	m.col = m.col + 1;
                	cycle++;
 
            	}
 
            	if (b.board[m.row][m.col] == 2) {
                	m.col = m.col - cycle;
                	whitePieceCount = whitePieceCount - cycle;
                	blackPieceCount = blackPieceCount + cycle;
                	cycle = 1;
 
                	break;
            	}
 
        	}
 
    	}
    	m = initialMove;
    	if (legalDircetions.contains(5)) {
 
        	m.row = m.row + cycle;
        	m.col = m.col + cycle;
        	if (b.board[m.row][m.col] == 0) {
       		 return;
        	}
        	while (onBoard(m, b)) {
 
 
            	if (b.board[m.row][m.col] == 1) {
                	b.board[m.row][m.col] = 2;
                	m.col = m.col + 1;
                	m.row = m.row + 1;
 
                	cycle++;
 
            	}
 
            	if (b.board[m.row][m.col] == 2) {
                	m.row = m.row - cycle;
                	m.col = m.col - cycle;
                	cycle = 1;
                	whitePieceCount = whitePieceCount - cycle;
                	blackPieceCount = blackPieceCount + cycle;
                	break;
            	}
 
        	}
 
    	}
    	m = initialMove;
    	if (legalDircetions.contains(6)) {
        	m.row = m.row + cycle;
        	if (b.board[m.row][m.col] == 0) {
       		 return;
        	}
        	while (onBoard(m, b)) {
 
            	if (b.board[m.row][m.col] == 1) {
                	b.board[m.row][m.col] = 2;
                	m.row = m.row + 1;
 
                	cycle++;
 
            	}
 
            	if (b.board[m.row][m.col] == 2) {
                	m.row = m.row - cycle;
                	whitePieceCount = whitePieceCount - cycle;
                	blackPieceCount = blackPieceCount + cycle;
                	cycle = 1;
                	break;
            	}
        	}
 
    	}
    	m = initialMove;
    	if (legalDircetions.contains(7)) {
        	m.row = m.row + cycle;
        	m.col = m.col - cycle;
       	 
        	if (b.board[m.row][m.col] == 0) {
       		 return;
        	}
        	while (onBoard(m, b)) {
 
            	if (b.board[m.row][m.col] == 1) {
                	b.board[m.row][m.col] = 2;
                	m.col = m.col - 1;
                	m.row = m.row + 1;
 
                	cycle++;
 
            	}
 
            	if (b.board[m.row][m.col] == 2) {
                	m.row = m.row - cycle;
                	m.col = m.col + cycle;
                	whitePieceCount = whitePieceCount - cycle;
                	blackPieceCount = blackPieceCount + cycle;
                	cycle = 1;
                	break;
            	}
 
        	}
 
    	}
    	m = initialMove;
    	if (legalDircetions.contains(8)) {
 
        	m.col = m.col - cycle;
       	 
 
        	while (onBoard(m, b)) {
 
            	if (b.board[m.row][m.col] == 1) {
                	b.board[m.row][m.col] = 2;
                	m.col = m.col - 1;
 
                	cycle++;
 
            	}
 
            	if (b.board[m.row][m.col] == 2) {
                	m.col = m.col + cycle;
                	whitePieceCount = whitePieceCount - cycle;
                	blackPieceCount = blackPieceCount + cycle;
                	cycle = 1;
                	break;
            	}
 
        	}
    	}
 
	}
 
	void handleAllCapturesWhite(Board b, Move m, Player p) {
 
 
    	int cycle = 1;
    	HashSet<Integer> legalDircetions = getLegalDirectionsWhite(b, m, p);
    	Move initialMove = m;
    	if (b.board[m.row][m.col] == 0) {
   		 return;
    	}
    	if (legalDircetions.contains(1)) {
        	m.row = m.row - cycle;
        	m.col = m.col - cycle;
        	if (b.board[m.row][m.col] == 0) {
       		 return;
        	}
        	while (onBoard(m, b)) {
       		 
//////////////////////////////////////////////////       		 
       		 if (b.board[m.row][m.col] == 0) {
                	m.row = m.row + cycle;
                	m.col = m.col + cycle;
                	cycle=1;
                	break;
            	}
//////////////////////////////////////////////////
    
       		 
            	if (b.board[m.row][m.col] == 2) {
                	b.board[m.row][m.col] = 1;
                	m.col = m.col - 1;
                	m.row = m.row - 1;
                	cycle++;
 
            	}
 
            	if (b.board[m.row][m.col] == 1) {
                	m.col = m.col + cycle;
                	m.row = m.row + cycle;
                	whitePieceCount = whitePieceCount + cycle;
                	blackPieceCount = blackPieceCount - cycle;
                	cycle = 1;
                	break;
            	}
 
        	}
 
    	}
    	m = initialMove;
    	if (legalDircetions.contains(2)) {
        	m.row = m.row - cycle;
        	if (b.board[m.row][m.col] == 0) {
       		 return;
        	}
        	while (onBoard(m, b)) {
//////////////////////////////////////////////////
if (b.board[m.row][m.col] == 0) {
m.row = m.row + cycle;
cycle=1;
break;
}
//////////////////////////////////////////////////
            	if (b.board[m.row][m.col] == 2) {
                	b.board[m.row][m.col] = 1;
                	m.row = m.row - 1;
                	cycle++;
 
            	}
 
            	if (b.board[m.row][m.col] == 1) {
                	m.row = m.row + cycle;
                	whitePieceCount = whitePieceCount + cycle;
                	blackPieceCount = blackPieceCount - cycle;
                	cycle = 1;
                	break;
            	}
 
        	}
 
    	}
    	m = initialMove;
    	if (legalDircetions.contains(3)) {
        	m.row = m.row - cycle;
        	m.col = m.col + cycle;
       	 
        	if (b.board[m.row][m.col] == 0) {
       		 return;
        	}
       	 
        	while (onBoard(m, b)) {
//////////////////////////////////////////////////
if (b.board[m.row][m.col] == 0) {
	m.row = m.row + cycle;
	m.col = m.col - cycle;
cycle=1;
break;
}
//////////////////////////////////////////////////
            	if (b.board[m.row][m.col] == 2) {
                	b.board[m.row][m.col] = 1;
                	m.col = m.col + 1;
                	m.row = m.row - 1;
                	cycle++;
 
            	}
 
            	if (b.board[m.row][m.col] == 1) {
                	m.row = m.row + cycle;
                	m.col = m.col - cycle;
                	whitePieceCount = whitePieceCount + cycle;
                	blackPieceCount = blackPieceCount - cycle;
                	cycle = 1;
                	break;
            	}
 
        	}
 
    	}
    	m = initialMove;
    	if (legalDircetions.contains(4)) {
 
        	m.col = m.col + cycle;
       	 
        	if (b.board[m.row][m.col] == 0) {
       		 return;
        	}
       	 
        	while (onBoard(m, b)) {
//////////////////////////////////////////////////
if (b.board[m.row][m.col] == 0) {
m.col = m.col - cycle;
cycle=1;
break;
}
//////////////////////////////////////////////////
            	if (b.board[m.row][m.col] == 2) {
                	b.board[m.row][m.col] = 1;
                	m.col = m.col + 1;
                	cycle++;
 
            	}
 
            	if (b.board[m.row][m.col] == 1) {
                	m.col = m.col - cycle;
                	whitePieceCount = whitePieceCount + cycle;
                	blackPieceCount = blackPieceCount - cycle;
                	cycle = 1;
 
                	break;
            	}
 
        	}
 
    	}
    	m = initialMove;
    	if (legalDircetions.contains(5)) {
        	// b.userInterface(b);
 
        	m.row = m.row + cycle;
        	m.col = m.col + cycle;
 
        	if (b.board[m.row][m.col] == 0) {
       		 return;
        	}
       	 
        	while (onBoard(m, b)) {
//////////////////////////////////////////////////
if (b.board[m.row][m.col] == 0) {
m.row = m.row + cycle;
m.col = m.col + cycle;
cycle=1;
break;
}
//////////////////////////////////////////////////
            	if (b.board[m.row][m.col] == 2) {
                	b.board[m.row][m.col] = 1;
                	m.col = m.col + 1;
                	m.row = m.row + 1;
 
                	cycle++;
 
            	}
 
            	if (b.board[m.row][m.col] == 1) {
                	m.row = m.row - cycle;
                	m.col = m.col - cycle;
                	whitePieceCount = whitePieceCount + cycle;
                	blackPieceCount = blackPieceCount - cycle;
                	cycle = 1;
                	break;
            	}
 
        	}
 
    	}
    	m = initialMove;
    	if (legalDircetions.contains(6)) {
        	m.row = m.row + cycle;
 
        	if (b.board[m.row][m.col] == 0) {
       		 return;
        	}
       	 
        	while (onBoard(m, b)) {
//////////////////////////////////////////////////
if (b.board[m.row][m.col] == 0) {
m.row = m.row + cycle;
cycle=1;
break;
}
//////////////////////////////////////////////////
 
            	if (b.board[m.row][m.col] == 2) {
                	b.board[m.row][m.col] = 1;
                	m.row = m.row + 1;
 
                	cycle++;
 
            	}
 
            	if (b.board[m.row][m.col] == 1) {
                	m.row = m.row - cycle;
                	whitePieceCount = whitePieceCount + cycle;
                	blackPieceCount = blackPieceCount - cycle;
                	cycle = 1;
                	break;
            	}
        	}
 
    	}
    	m = initialMove;
    	if (legalDircetions.contains(7)) {
        	m.row = m.row + cycle;
        	m.col = m.col - cycle;
 
        	while (onBoard(m, b)) {
 
       		 if (b.board[m.row][m.col] == 0) {
                	m.row = m.row - cycle;
                	m.col = m.col + cycle;
                	cycle=1;
                	break;
            	}
            	if (b.board[m.row][m.col] == 2) {
                	b.board[m.row][m.col] = 1;
                	m.col = m.col - 1;
                	m.row = m.row + 1;
 
                	cycle++;
 
            	}
 
            	if (b.board[m.row][m.col] == 1) {
                	m.row = m.row - cycle;
                	m.col = m.col + cycle;
                	whitePieceCount = whitePieceCount + cycle;
                	blackPieceCount = blackPieceCount - cycle;
                	cycle = 1;
 
                	break;
            	}
 
        	}
 
    	}
    	m = initialMove;
    	if (legalDircetions.contains(8)) {
 
        	m.col = m.col - cycle;
 
 
       	 
        	while (onBoard(m, b)) {
//////////////////////////////////////////////////
if (b.board[m.row][m.col] == 0) {
m.row = m.row + cycle;
m.col = m.col - cycle;
cycle=1;
break;
}
//////////////////////////////////////////////////
            	if (b.board[m.row][m.col] == 2) {
                	b.board[m.row][m.col] = 1;
                	m.col = m.col - 1;
 
                	cycle++;
                	whitePieceCount++;
                	blackPieceCount--;
            	}
 
            	if (b.board[m.row][m.col] == 1) {
                	m.col = m.col + cycle;
                	whitePieceCount = whitePieceCount + cycle;
                	blackPieceCount = blackPieceCount - cycle;
                	cycle = 1;
                	break;
            	}
 
        	}
    	}
 
	}
 
	static HashSet<Integer> adjacentToEnemyBlack(Board b, Move m) { // retruns a set which indicates which direction
                                                                	// the enemy peices are
    	HashSet<Integer> adjacentEnemies = new HashSet<Integer>();
    	Move m1 = new Move(m.row - 1, m.col - 1);
    	Move m2 = new Move(m.row - 1, m.col);
    	Move m3 = new Move(m.row - 1, m.col + 1);
    	Move m4 = new Move(m.row, m.col + 1);
    	Move m5 = new Move(m.row + 1, m.col + 1);
    	Move m6 = new Move(m.row + 1, m.col);
    	Move m7 = new Move(m.row + 1, m.col - 1);
    	Move m8 = new Move(m.row, m.col - 1);
 
    	if (onBoard(m1, b)) {
        	if (b.board[m1.row][m1.col] == 1) {
            	adjacentEnemies.add(1);
        	}
    	}
    	if (onBoard(m2, b)) {
        	if (b.board[m2.row][m2.col] == 1) {
            	adjacentEnemies.add(2);
        	}
    	}
    	if (onBoard(m3, b)) {
        	if (b.board[m3.row][m3.col] == 1) {
            	adjacentEnemies.add(3);
        	}
    	}
    	if (onBoard(m4, b)) {
        	if (b.board[m4.row][m4.col] == 1) {
            	adjacentEnemies.add(4);
        	}
    	}
    	if (onBoard(m5, b)) {
        	if (b.board[m5.row][m5.col] == 1) {
            	adjacentEnemies.add(5);
        	}
    	}
    	if (onBoard(m6, b)) {
        	if (b.board[m6.row][m6.col] == 1) {
            	adjacentEnemies.add(6);
        	}
    	}
    	if (onBoard(m7, b)) {
        	if (b.board[m7.row][m7.col] == 1) {
            	adjacentEnemies.add(7);
        	}
    	}
    	if (onBoard(m8, b)) {
        	if (b.board[m8.row][m8.col] == 1) {
            	adjacentEnemies.add(8);
        	}
    	}
 
    	// 1 2 3
    	// 8 m 4 m is target move
    	// 7 6 5
 
    	return adjacentEnemies;
	}
 
	static HashSet<Integer> adjacentToEnemyWhite(Board b, Move m) { // retruns a set which indicates which direction
                                                                	// the enemy peices are
    	HashSet<Integer> adjacentEnemies = new HashSet<Integer>();
    	Move m1 = new Move(m.row - 1, m.col - 1);
    	Move m2 = new Move(m.row - 1, m.col);
    	Move m3 = new Move(m.row - 1, m.col + 1);
    	Move m4 = new Move(m.row, m.col + 1);
    	Move m5 = new Move(m.row + 1, m.col + 1);
    	Move m6 = new Move(m.row + 1, m.col);
    	Move m7 = new Move(m.row + 1, m.col - 1);
    	Move m8 = new Move(m.row, m.col - 1);
 
    	if (onBoard(m1, b)) {
        	if (b.board[m1.row][m1.col] == 2) {
            	adjacentEnemies.add(1);
        	}
    	}
    	if (onBoard(m2, b)) {
        	if (b.board[m2.row][m2.col] == 2) {
            	adjacentEnemies.add(2);
        	}
    	}
    	if (onBoard(m3, b)) {
        	if (b.board[m3.row][m3.col] == 2) {
            	adjacentEnemies.add(3);
        	}
    	}
    	if (onBoard(m4, b)) {
        	if (b.board[m4.row][m4.col] == 2) {
            	adjacentEnemies.add(4);
        	}
    	}
    	if (onBoard(m5, b)) {
        	if (b.board[m5.row][m5.col] == 2) {
            	adjacentEnemies.add(5);
        	}
    	}
    	if (onBoard(m6, b)) {
        	if (b.board[m6.row][m6.col] == 2) {
            	adjacentEnemies.add(6);
        	}
    	}
    	if (onBoard(m7, b)) {
        	if (b.board[m7.row][m7.col] == 2) {
            	adjacentEnemies.add(7);
        	}
    	}
    	if (onBoard(m8, b)) {
        	if (b.board[m8.row][m8.col] == 2) {
            	adjacentEnemies.add(8);
        	}
    	}
 
    	// 1 2 3
    	// 8 m 4 m is target move
    	// 7 6 5
 
    	return adjacentEnemies;
	}
 
	// Checks if move is on board
	static boolean onBoard(Move m, Board b) {
    	boolean onBoard = true;
    	if (m.row < 0) {
        	onBoard = false;
    	}
    	if (m.row > b.size - 1) {
        	onBoard = false;
    	}
    	if (m.col < 0) {
        	onBoard = false;
    	}
    	if (m.col > b.size - 1) {
        	onBoard = false;
    	}
 
    	return onBoard;
	}
 
	// ACTION
	void makeMove(Board b, Move m, Player p) { // makes move;
 
    	if (p.getColor() == true) {
        	b.board[m.getRow()][m.getCol()] = 1;
        	handleAllCapturesWhite(b, m, p);
    	} else {
        	b.board[m.getRow()][m.getCol()] = 2;
        	handleAllCapturesBlack(b, m, p);
 
    	}
 
    	// whitePieceCount += whiteCaps + 1;
 
    	// blackPieceCount += blackCaps + 1;
    	// changeTurn(turn);
 
	}
 
	boolean isGameOver(Board b) {
    	boolean gameOver = false;
    	int zeroCount = 0;
    	int blackCount = 0;
    	int whiteCount = 0;
 
    	for (int i = 0; i < b.size; i++) {
        	for (int j = 0; j < b.size; j++) {
            	if (b.board[i][j] == 0) {
                	zeroCount++;
            	}
            	if (b.board[i][j] == 1) {
                	whiteCount++;
            	}
            	if (b.board[i][j] == 2) {
                	blackCount++;
            	}
        	}
    	}
 
    	if (zeroCount == 0) {
        	gameOver = true;
    	}
    	if (whiteCount == 0) {
        	gameOver = true;
    	}
 
    	if (blackCount == 0) {
        	gameOver = true;
    	}
 
    	return gameOver;
 
	}
 
	boolean changeTurn(boolean turn) {
    	if (turn == true) {
        	turn = false;
    	} else if (turn == false) {
        	turn = true;
    	}
    	return turn;
 
	}
 
	void userInterface(Board b) { // Creates the Visual Board
    	for (int i = 0; i < b.size; i++) {
        	if (i == 0) {
            	System.out.print(" a ");
        	}
        	if (i == 1) {
            	System.out.print(" b ");
        	}
        	if (i == 2) {
            	System.out.print(" c ");
        	}
        	if (i == 3) {
            	System.out.print(" d ");
        	}
        	if (i == 4) {
            	System.out.print(" e ");
        	}
        	if (i == 5) {
            	System.out.print(" f ");
        	}
        	if (i == 6) {
            	System.out.print(" g ");
        	}
        	if (i == 7) {
            	System.out.print(" h ");
        	}
 
    	}
    	System.out.println("");
 
    	for (int i = 0; i < b.size; i++) {
        	System.out.print(i + 1);
 
        	for (int j = 0; j < b.size; j++) {
 
            	if (b.board[i][j] == 0) {
                	System.out.print("   ");
            	}
            	if (b.board[i][j] == 1) {
                	System.out.print(" O ");
            	}
            	if (b.board[i][j] == 2) {
                	System.out.print(" X ");
            	}
        	}
        	System.out.print((i + 1) + "\n");
 
    	}
    	for (int i = 0; i < b.size; i++) {
        	if (i == 0) {
            	System.out.print(" a ");
        	}
        	if (i == 1) {
            	System.out.print(" b ");
        	}
        	if (i == 2) {
            	System.out.print(" c ");
        	}
        	if (i == 3) {
            	System.out.print(" d ");
        	}
        	if (i == 4) {
            	System.out.print(" e ");
        	}
        	if (i == 5) {
            	System.out.print(" f ");
        	}
        	if (i == 6) {
            	System.out.print(" g ");
        	}
        	if (i == 7) {
            	System.out.print(" h ");
        	}
 
    	}
    	System.out.println("");
    	System.out.print("Black Piece Count: ");
    	System.out.println(getBlackScore(b));
    	System.out.print("White Piece Count: ");
    	System.out.println(getWhiteScore(b));
    	if (isGameOver(b)) {
        	int white = getWhiteScore(b);
        	int black = getBlackScore(b);
 
        	if (white > black) {
            	System.out.println("White Wins!");
        	} else if (white == black) {
            	System.out.println("Draw");
        	} else {
            	System.out.println("Black Wins!");
        	}
    	}
	}
 
	int getBlackScore(Board b) {
    	int score = 0;
    	for (int i = 0; i < b.board.length; i++) {
        	for (int j = 0; j < b.board.length; j++) {
            	if (b.board[i][j] == 2) {
                	score++;
            	}
        	}
    	}
 
    	return score;
	}
 
	int getWhiteScore(Board b) {
    	int score = 0;
    	for (int i = 0; i < b.board.length; i++) {
        	for (int j = 0; j < b.board.length; j++) {
            	if (b.board[i][j] == 1) {
                	score++;
            	}
 
        	}
    	}
 
    	return score;
	}
 
	int winChecker(Board b) { // returns win if white win, returns loss if white loss;
    	int white = getWhiteScore(b);
    	int black = getBlackScore(b);
 
    	if (white > black) {
        	return win;
    	} else if (white == black) {
        	return 0;
    	} else {
        	return loss;
    	}
	}
 
	int getNumCaps(Board b, Move m, Player p) {
    	int numCaps = 0;
    	int scoreBefore = 0;
    	int scoreAfter = 0;
    	Board copy = new Board(b.size);
    	copy.size = b.size;
    	for (int i = 0; i < b.size; i++) { // Double for loop across the board
        	for (int j = 0; j < b.size; j++) {
            	copy.board[i][j] = b.board[i][j];
        	}
    	}
 
    	if (p.Color) {
        	scoreBefore = getWhiteScore(b);
        	copy.makeMove(copy, m, p);
        	scoreAfter = getWhiteScore(b);
        	numCaps = scoreAfter - scoreBefore;
 
    	} else {
        	scoreBefore = getBlackScore(b);
        	copy.makeMove(copy, m, p);
        	scoreAfter = getBlackScore(b);
        	numCaps = scoreAfter - scoreBefore;
    	}
 
    	return numCaps;
 
	}
 
}
 
 

