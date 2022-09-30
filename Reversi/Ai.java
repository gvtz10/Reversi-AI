import java.util.*;
 
public class Ai extends Player {
	boolean Color;
	int difficulty; // WHite = true black = false
	final static int win = Integer.MAX_VALUE;
	final static int loss = Integer.MIN_VALUE;
	final static int draw = 0;
   

    int count;
 
	public boolean getColor() {
    	return Color;
	}
 
	public void setColor(boolean color) {
    	Color = color;
	}
 
	public Ai(boolean color, int dif) {
    	super(color);
    	this.Color = color;
    	this.difficulty = dif;
 
	}
 
	public Ai(boolean color) {
    	super(color);
	}
 
	Move choseMove(Board b, Player p, int depth) {
    	Move chosenMove = new Move();
 
    	if (this.difficulty == 1) {
        	chosenMove = Random(b);
    	}
 
    	if (this.difficulty == 2) {
        	chosenMove = MINIMAX(b, this, p);
    	}
    	if (this.difficulty == 3) {
        	chosenMove = HMINIMAXABP(b, this, p, depth);
    	}
 
    	return chosenMove;
	}
 
	Move MINIMAX(Board b, Ai ai, Player p) {
    	Move chosenMove = new Move();
   

    	HashSet<Move> legalMoves = new HashSet<Move>();
 
    	if (this.Color == false) {
        	legalMoves = b.getLegalMovesBlack(b, this);
 
    	} else {
        	legalMoves = b.getLegalMovesWhite(b, this);
 
    	}
    	if (legalMoves.size() == 1) {
        	for (Move m : legalMoves) {
            	b.makeMove(b, m, this);
            	return m;
 
        	}
    	}
 
    	HashMap<Integer, Move> moveToVal = new HashMap<Integer, Move>();
    	for (Move m : legalMoves) {
 
        	moveToVal.put(min(b, ai, p), m);
    	}
 
    	int maxKey = Collections.max(moveToVal.keySet());
    	chosenMove = moveToVal.get(maxKey);
 
    	return chosenMove;
	}
 
	Move Random(Board b) {
    	Move chosenMove = new Move();
    	if (this.Color == false) {
        	HashSet<Move> legalMoves = b.getLegalMovesBlack(b, this);
 
        	Random rand = new Random();
        	int num = rand.nextInt(legalMoves.size());
 
        	Move[] arrayMoves = legalMoves.toArray(new Move[legalMoves.size()]);
        	chosenMove = arrayMoves[num];
 
    	} else {
        	HashSet<Move> legalMoves = b.getLegalMovesWhite(b, this);
 
        	Random rand = new Random();
        	int num = rand.nextInt(legalMoves.size());
 
        	Move[] arrayMoves = legalMoves.toArray(new Move[legalMoves.size()]);
        	chosenMove = arrayMoves[num];
 
    	}
    	return chosenMove;
	}
 
	Move HMINIMAXABP(Board b, Ai ai, Player p, int depth) {
    	Move chosenMove = new Move();
    	HashSet<Move> legalMoves = new HashSet<Move>();
 
    	if (this.Color == false) {
        	legalMoves = b.getLegalMovesBlack(b, this);
 
    	} else {
        	legalMoves = b.getLegalMovesWhite(b, this);
 
    	}
 
    	if (legalMoves.size() == 1) {
 
        	for (Move m : legalMoves) {
            	return m;
        	}
    	}
   	 
    	HashMap<Integer, Move> moveToVal = new HashMap<Integer, Move>();
   	 
    	PriorityQueue<Move> moveQueue = new PriorityQueue<Move>();
    	for (Move m : legalMoves) {
        	m.setNumCaps(b.getNumCaps(b,m,ai));
        	moveQueue.add(m);
    	}
    	while(!moveQueue.isEmpty()){
        	int alpha = Integer.MIN_VALUE;
        	int beta = Integer.MAX_VALUE;
        	moveToVal.put(ABPminH(b, ai,depth, 0, p, alpha, beta), moveQueue.poll());
    	}
    	int maxKey = Collections.max(moveToVal.keySet());
    	chosenMove = moveToVal.get(maxKey);
 
    	return chosenMove;
 
	}
 
	int ABPminH(Board b, Ai ai, int maxDepth, int moveCount, Player p, int alpha, int beta) {
    	
    	HashSet<Move> LegalMoves;
    	HashSet<Integer> valSet = new HashSet<Integer>();
 
    	if (ai.Color == true) {
        	LegalMoves = b.getLegalMovesWhite(b, ai);
    	} else {
        	LegalMoves = b.getLegalMovesBlack(b, ai);
    	}
 
    	if ((LegalMoves.isEmpty())||maxDepth <= moveCount) {
        
        	return heuristicVal(b, ai);
       	 
        	}
 
    	moveCount++;
 
    	Board copy = new Board(b.size);
   	 
    	valSet = new HashSet<Integer>();
    	PriorityQueue<Move> moveQueue = new PriorityQueue<Move>();
    	for (Move m : LegalMoves) {
        	m.setNumCaps(b.getNumCaps(b,m,ai));
        	moveQueue.add(m);
    	}
    	while(!moveQueue.isEmpty()){
          	Move m = moveQueue.poll();
       	 
        	copy.size = b.size;
        	for (int i = 0; i < b.size; i++) { // Double for loop across the board
            	for (int j = 0; j < b.size; j++) {
                	copy.board[i][j] = b.board[i][j];
            	}
        	}
        	copy.makeMove(copy, m, ai);
        	//b.userInterface(b);
        	//System.out.println("MOVE TEST");
       	 
        	valSet.add(ABPmaxH(copy, ai, maxDepth, moveCount, p, alpha, beta));
        	if (Collections.min(valSet) <= alpha) {
            	return Collections.min(valSet);
        	}
 
        	if (beta > Collections.min(valSet)) {
            	beta = Collections.min(valSet);
        	}
    	}
 
    	return Collections.min(valSet);
 
	}
 
	int ABPmaxH(Board b, Ai ai, int maxDepth, int moveCount, Player p, int alpha, int beta) {
    	
    	HashSet<Move> LegalMoves;
    	HashSet<Integer> valSet = new HashSet<Integer>();
    	moveCount++;
 
    	if (ai.Color == true) {
        	LegalMoves = b.getLegalMovesBlack(b, p);
    	} else {
        	LegalMoves = b.getLegalMovesWhite(b, p);
    	}
 
    	if (LegalMoves.isEmpty()||maxDepth <= moveCount) {
        	//System.out.print("NO LEGAL MOVES");
        	return heuristicVal(b, ai);
    	}
 
    	Board copy = new Board(b.size);
   	 
    	valSet = new HashSet<Integer>();
    	PriorityQueue<Move> moveQueue = new PriorityQueue<Move>();
    	for (Move m : LegalMoves) {
        	m.setNumCaps(b.getNumCaps(b,m,ai));
        	moveQueue.add(m);
    	}
    	while(!moveQueue.isEmpty()){
          	Move m = moveQueue.poll();
        	copy.size = b.size;
        	for (int i = 0; i < b.size; i++) { // Double for loop across the board
            	for (int j = 0; j < b.size; j++) {
                	copy.board[i][j] = b.board[i][j];
            	}
        	}
       	 
        	copy.makeMove(copy, m, p);
           	 
          // 	b.userInterface(b);
          //	System.out.println("MOVE TEST");
 
        	valSet.add(ABPminH(copy, ai, maxDepth, moveCount, p, alpha, beta));
 
        	if (Collections.max(valSet) >= beta) {
            	return Collections.max(valSet);
        	}
        	if (alpha < Collections.max(valSet)) {
            	alpha = Collections.max(valSet);
        	}
    	}
 
    	return Collections.max(valSet);
 
	}
 
	int heuristicVal(Board b, Ai ai) {
    	int val;
    	
    	if (ai.Color) {
    		
    		if(b.isGameOver(b)) {
        		
        		int white = b.getWhiteScore(b);
        		int black =  b.getBlackScore(b);
        		
        		if (white > black) {
        			return win;
        		} else if (white == black) {
        			return 0;
        		} else {
        			return loss;
        		}
        	
    		}
        	val = b.getWhiteScore(b);
        	
    	} else {
    		
    		if(b.isGameOver(b)) {
        		
        		int white = b.getWhiteScore(b);
        		int black =  b.getBlackScore(b);
        		
        		if (white < black) {
        			return win;
        		} else if (white == black) {
        			return 0;
        		} else {
        			return loss;
        		}
        	}
        	val = b.getBlackScore(b);
    	}
    	
    	return val;
    	
	}
 
	int min(Board b, Ai ai, Player p) {
    	if (b.isGameOver(b)) {
 
        	if (ai.Color == true) {
            	return b.winChecker(b);
        	}
        	if (ai.Color == false) {
            	int winVal = b.winChecker(b);
 
            	if (winVal == loss) {
                	return win;
            	} else if (winVal == win) {
                	return loss;
            	} else {
                	return 0;
            	}
        	}
 
    	}
 
    	HashSet<Move> LegalMoves;
    	HashSet<Integer> valSet = new HashSet<Integer>();
 
    	if (ai.Color == true) {
        	LegalMoves = b.getLegalMovesWhite(b, ai);
    	} else {
        	LegalMoves = b.getLegalMovesBlack(b, ai);
    	}
 
    	if (LegalMoves.isEmpty()) {
        	return loss;
        	/*
         	* if (ai.Color == true) { return b.winChecker(b); } if (ai.Color == false) {
         	* int winVal = b.winChecker(b);
         	*
         	* if (winVal == loss) { return win; } else if (winVal == win) { return loss; }
         	* else { return 0; } }
         	*/
    	}
 
    	Board copy = new Board(b.size);
    
    	valSet = new HashSet<Integer>();
    	for(Move m: LegalMoves) {
        	copy.size = b.size;
        	for (int i = 0; i < b.size; i++) { // Double for loop across the board
            	for (int j = 0; j < b.size; j++) {
                	copy.board[i][j] = b.board[i][j];
            	}
        	}
        	copy.makeMove(copy, m, ai);
 
        	valSet.add(max(copy, ai, p));
 
    	}
    	return Collections.min(valSet);
	}
 
	static <T> void printSet(HashSet<T> S) {
    	System.out.println("");
    	System.out.println("");
 
    	for (T mtemp : S) {
        	System.out.print(mtemp);
    	}
    	System.out.println("");
    	System.out.println("");
	}
 
	int max(Board b, Ai ai, Player p) {
 
    	if (b.isGameOver(b)) {
 
        	if (ai.Color == true) {
            	return b.winChecker(b);
        	}
        	if (ai.Color == false) {
            	int winVal = b.winChecker(b);
 
            	if (winVal == loss) {
                	return win;
            	} else if (winVal == win) {
                	return loss;
            	} else {
                	return 0;
            	}
        	}
 
    	}
    	HashSet<Move> LegalMoves;
    	HashSet<Integer> valSet = new HashSet<Integer>();
 
    	if (ai.Color == true) {
        	LegalMoves = b.getLegalMovesBlack(b, p);
    	} else {
        	LegalMoves = b.getLegalMovesWhite(b, p);
    	}
 
    	if (LegalMoves.isEmpty()) {
        	return loss;
        	/*
         	* if (ai.Color == true) { return b.winChecker(b); } if (ai.Color == false) {
         	* int winVal = b.winChecker(b);
         	*
         	* if (winVal == loss) { return win; } else if (winVal == win) { return loss; }
         	* else { return 0; } }
         	*/
    	}
 
    	Board copy = new Board(b.size);
   	 
    	valSet = new HashSet<Integer>();
 
    	for (Move m : LegalMoves) {
        	copy.size = b.size;
        	for (int i = 0; i < b.size; i++) { // Double for loop across the board
            	for (int j = 0; j < b.size; j++) {
                	copy.board[i][j] = b.board[i][j];
            	}
        	}
       	 
        	copy.makeMove(copy, m, p);
 
        	valSet.add(min(copy, ai, p));
 
    	}
    	return Collections.max(valSet);
	}
 
}
 
 



