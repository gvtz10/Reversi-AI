import java.util.*;
 
public class Ai extends Player {
	boolean Color;
	int difficulty; // WHite = true =  1 black = false = 2
	final static int win = Integer.MAX_VALUE;
	final static int loss = Integer.MIN_VALUE;
	final static int draw = 0;
	final static long timeLimit = 10000;
	
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
 
	Move choseMove(Board b, Player p) {
    	Move chosenMove = new Move();
 
    	if (this.difficulty == 1) {
        	chosenMove = Random(b);
    	}
 
    	if (this.difficulty == 2) {
        	chosenMove = MINIMAX(b, this, p);
    	}
    	if (this.difficulty == 3) {
        	chosenMove =  iterativeDeepening(b, this, p);
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
   	 
    	HashMap<Double, Move> moveToVal = new HashMap<Double, Move>();
   	 
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
    	double maxKey = Collections.max(moveToVal.keySet());
    	chosenMove = moveToVal.get(maxKey);
 
    	return chosenMove;
 
	}
	
	Move iterativeDeepening(Board b, Ai ai, Player p) {
        Move bestMove = null;
        int depth = 1;
        long starttime = System.currentTimeMillis();
        
        while(System.currentTimeMillis() - starttime < timeLimit) {
                Move move = HMINIMAXABP(b, ai, p, depth);
                bestMove = move;
                depth++;
                System.out.println(depth);

        }
        return bestMove;
    }
 
	double ABPminH(Board b, Ai ai, int maxDepth, int moveCount, Player p, double alpha, double beta) {
    	
    	HashSet<Move> LegalMoves;
    	HashSet<Double> valSet = new HashSet<Double>();
 
    	if (ai.Color == true) {
        	LegalMoves = b.getLegalMovesWhite(b, ai);
    	} else {
        	LegalMoves = b.getLegalMovesBlack(b, ai);
    	}
 
    	if ((LegalMoves.isEmpty())||maxDepth <= moveCount) {
        
        	return dynamicHeuristicEvaluationFunction(b, ai);
       	 
        	}
 
    	moveCount++;
 
    	Board copy = new Board(b.size);
   	 
    	valSet = new HashSet<Double>();
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
 
	double ABPmaxH(Board b, Ai ai, int maxDepth, int moveCount, Player p, double alpha, double beta) {
    	
    	HashSet<Move> LegalMoves;
    	HashSet<Double> valSet = new HashSet<Double>();
    	moveCount++;
 
    	if (ai.Color == true) {
        	LegalMoves = b.getLegalMovesBlack(b, p);
    	} else {
        	LegalMoves = b.getLegalMovesWhite(b, p);
    	}
 
    	if (LegalMoves.isEmpty()||maxDepth <= moveCount) {
        	//System.out.print("NO LEGAL MOVES");
        	return dynamicHeuristicEvaluationFunction(b, ai);
    	}
 
    	Board copy = new Board(b.size);
   	 
    	valSet = new HashSet<Double>();
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
 
	
	public static double dynamicHeuristicEvaluationFunction(Board b, Ai ai) {
        int myTiles = 0, oppTiles = 0;
        int myFrontTiles = 0, oppFrontTiles = 0;
        double p = 0, c = 0, l = 0, m = 0, f = 0, d = 0;

        int[] xOffset = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] yOffset = {0, 1, 1, 1, 0, -1, -1, -1};

        int[][] valueMatrix = {
            {20, -3, 11, 8, 8, 11, -3, 20},
            {-3, -7, -4, 1, 1, -4, -7, -3},
            {11, -4, 2, 2, 2, 2, -4, 11},
            {8, 1, 2, -3, -3, 2, 1, 8},
            {8, 1, 2, -3, -3, 2, 1, 8},
            {11, -4, 2, 2, 2, 2, -4, 11},
            {-3, -7, -4, 1, 1, -4, -7, -3},
            {20, -3, 11, 8, 8, 11, -3, 20}
        };
        int[][] grid = b.board;
        int myColor = 0;
        int oppColor = 0; 
        
        if (ai.Color == true) {
        	 myColor = 1;
        	 oppColor = 2;
        }
        else {
        	 myColor = 2;
        	 oppColor = 1;
        }
       
        // Piece difference, frontier disks, and disk squares
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (grid[i][j] == myColor) {
                    d += valueMatrix[i][j];
                    myTiles++;
                } else if (grid[i][j] == oppColor) {
                    d -= valueMatrix[i][j];
                    oppTiles++;
                }
                if (grid[i][j] != '-') {
                    for (int k = 0; k < 8; k++) {
                        int x = i + xOffset[k];
                        int y = j + yOffset[k];
                        if (x >= 0 && x < 8 && y >= 0 && y < 8 && grid[x][y] == '-') {
                            if (grid[i][j] == myColor) {
                                myFrontTiles++;
                            } else {
                                oppFrontTiles++;
                            }
                            break;
                        }
                    }
                }
            }
        }

        if (myTiles > oppTiles) {
            p = (100.0 * myTiles) / (myTiles + oppTiles);
        } else if (myTiles < oppTiles) {
            p = -(100.0 * oppTiles) / (myTiles + oppTiles);
        }

        if (myFrontTiles > oppFrontTiles) {
            f = -(100.0 * myFrontTiles) / (myFrontTiles + oppFrontTiles);
        } else if (myFrontTiles < oppFrontTiles) {
            f = (100.0 * oppFrontTiles) / (myFrontTiles + oppFrontTiles);
        }

        // Corner occupancy
        myTiles = oppTiles = 0;
        if (grid[0][0] == myColor) myTiles++;
        else if (grid[0][0] == oppColor) oppTiles++;
        if (grid[0][7] == myColor) myTiles++;
        else if (grid[0][7] == oppColor) oppTiles++;
        if (grid[7][0] == myColor) myTiles++;
        else if (grid[7][0] == oppColor) oppTiles++;
        if (grid[7][7] == myColor) myTiles++;
        else if (grid[7][7] == oppColor) oppTiles++;
        c = 25 * (myTiles - oppTiles);

        // Corner closeness
        myTiles = oppTiles = 0;
        if (grid[0][0] == '-') {
            if (grid[0][1] == myColor) myTiles++;
            else if (grid[0][1] == oppColor) oppTiles++;
            if (grid[1][1] == myColor) myTiles++;
            else if (grid[1][1] == oppColor) oppTiles++;
            if (grid[1][0] == myColor) myTiles++;
            else if (grid[1][0] == oppColor) oppTiles++;
        }
        if (grid[0][7] == '-') {
            if (grid[0][6] == myColor) myTiles++;
            else if (grid[0][6] == oppColor) oppTiles++;
            if (grid[1][6] == myColor) myTiles++;
            else if (grid[1][6] == oppColor) oppTiles++;
            if (grid[1][7] == myColor) myTiles++;
            else if (grid[1][7] == oppColor) oppTiles++;
        }
        if (grid[7][0] == '-') {
            if (grid[7][1] == myColor) myTiles++;
            else if (grid[7][1] == oppColor) oppTiles++;
            if (grid[6][1] == myColor) myTiles++;
            else if (grid[6][1] == oppColor) oppTiles++;
            if (grid[6][0] == myColor) myTiles++;
            else if (grid[6][0] == oppColor) oppTiles++;
        }
        if (grid[7][7] == '-') {
            if (grid[6][7] == myColor) myTiles++;
            else if (grid[6][7] == oppColor) oppTiles++;
            if (grid[6][6] == myColor) myTiles++;
            else if (grid[6][6] == oppColor) oppTiles++;
            if (grid[7][6] == myColor) myTiles++;
            else if (grid[7][6] == oppColor) oppTiles++;
        }
        l = -12.5 * (myTiles - oppTiles);

        // Mobility (assuming a method `numValidMoves`) 
        if(ai.Color == true) {
        	myTiles = b.getLegalMovesWhite(b, ai).size();
		}
        if(ai.Color == false) {
        	oppTiles = b.getLegalMovesBlack(b, ai).size();
			}
        
        
        if (myTiles > oppTiles) {
            m = (100.0 * myTiles) / (myTiles + oppTiles);
        } else if (myTiles < oppTiles) {
            m = -(100.0 * oppTiles) / (myTiles + oppTiles);
        }

        // Final weighted score
        double score = (10 * p) + (801.724 * c) + (382.026 * l) + (78.922 * m) + (74.396 * f) + (10 * d);
        return score;
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
 
 



