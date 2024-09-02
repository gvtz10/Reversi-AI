import java.util.*;
 
public class Game {
 
   Board b;
   Player p;
   Ai ai;
  // static boolean turn = true;
 
   static boolean moveChecker(String s) {
 
       char col = s.charAt(0);
       char row = s.charAt(1);
 
       boolean legalRow = false;
       boolean legalCol = false;
 
       switch (col) {
           case 'a':
               legalCol = true;
               break;
           case 'b':
               legalCol = true;
               break;
           case 'c':
               legalCol = true;
               break;
           case 'd':
               legalCol = true;
               break;
           case 'e':
               legalCol = true;
               break;
           case 'f':
               legalCol = true;
               break;
           case 'g':
               legalCol = true;
               break;
           case 'h':
               legalCol = true;
               break;
           case 'A':
               legalCol = true;
               break;
           case 'B':
               legalCol = true;
               break;
           case 'C':
               legalCol = true;
               break;
           case 'D':
               legalCol = true;
               break;
           case 'E':
               legalCol = true;
               break;
           case 'F':
               legalCol = true;
               break;
           case 'G':
               legalCol = true;
               break;
           case 'H':
               legalCol = true;
               break;
 
       }
       switch (row) {
           case '1':
               legalRow = true;
               break;
           case '2':
               legalRow = true;
               break;
           case '3':
               legalRow = true;
               break;
           case '4':
               legalRow = true;
               break;
           case '5':
               legalRow = true;
               break;
           case '6':
               legalRow = true;
               break;
           case '7':
               legalRow = true;
               break;
           case '8':
               legalRow = true;
               break;
       }
 
       if (legalRow && legalCol) {
           return true;
       } else {
           return false;
       }
 
   }
 
   static boolean setContainsMove(Move m, HashSet<Move> S) {
       boolean contains = false;
       for (Move mtemp : S) {
           if ((mtemp.col == m.col) && (mtemp.row == m.row)) {
               contains = true;
           }
       }
 
       return contains;
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
 
   static Move moveConverter(String s) {
 
       Move move = new Move();
 
       char col = s.charAt(0);
       char row = s.charAt(1);
 
       int rowInt = 0;
       int colInt = 0;
 
       switch (col) {
           case 'a':
               colInt = 0;
 
               break;
           case 'b':
               colInt = 1;
 
               break;
           case 'c':
               colInt = 2;
               break;
           case 'd':
               colInt = 3;
               break;
           case 'e':
               colInt = 4;
               break;
           case 'f':
               colInt = 5;
               break;
           case 'g':
               colInt = 6;
               break;
           case 'h':
               colInt = 7;
               break;
           case 'A':
               colInt = 0;
               break;
           case 'B':
               colInt = 1;
               break;
           case 'C':
               colInt = 2;
               break;
           case 'D':
               colInt = 3;
               break;
           case 'E':
               colInt = 4;
               break;
           case 'F':
               colInt = 5;
               break;
           case 'G':
               colInt = 6;
               break;
           case 'H':
               colInt = 7;
               break;
 
       }
       switch (row) {
           case '1':
               rowInt = 0;
               break;
           case '2':
               rowInt = 1;
               break;
           case '3':
               rowInt = 2;
               break;
           case '4':
               rowInt = 3;
               break;
           case '5':
               rowInt = 4;
               break;
           case '6':
               rowInt = 5;
               break;
           case '7':
               rowInt = 6;
               break;
           case '8':
               rowInt = 7;
               break;
       }
 
       move = new Move(rowInt, colInt);
       return move;
   }
 
   public static void main(String[] Args) {
 
       System.out.print(
               "Reversi by Gus Vietze and Lorenzo Mendoza\r\n" + "Choose your game:\r\n" + "1. Small 4x4 Reversi\r\n"
                      + "2. Standard 8x8 Reversi\r\n" + "Your choice? \r\n");
 
       Scanner scnr = new Scanner(System.in);
       int boardDIM = scnr.nextInt();
       Board b = new Board(10);
       Player p = new Player(true);
       Ai ai = new Ai(true);
       int depth = 0;
       if (boardDIM == 1) {
 
           b = new Board(4);
 
       } else if (boardDIM == 2) {
           b = new Board(8);
       } else {
           System.out.print("Please enter a valid input");
           System.exit(0);
       }
       System.out.print("Would you like to play a opponet that plays randomly (1) \n"
    		   + "Or an opponent that plays intelligently (2) \n");
       
       int aiDiff = scnr.nextInt();
       
      
       System.out.print("Do you want to play DARK (X) or LIGHT (O)? \r\n ");
       
 
       String playerColor = scnr.next();
       if(aiDiff == 1) {
    	   if (playerColor.equals("x") || playerColor.equals("X")) {
    		   
    		  
                   p = new Player(false);
                   ai = new Ai(true, 1);
     
    	   }else if (playerColor.equals("o") || playerColor.equals("O")) {
    	   } else {
    		   p = new Player(true);
               ai = new Ai(false, 1);
    		   
    		   System.out.print("Please enter a valid input");
               System.exit(0);
    	   }
    	   
    	   
       } else if (aiDiff == 2) {
       if (playerColor.equals("x") || playerColor.equals("X")) {
 
           if (boardDIM == 1) {
        	 
               p = new Player(false); // False = Dark
               ai = new Ai(true, 2); // True = White, Difficulty 1 = Random
 
           } else if (boardDIM == 2) {
        	  
        	   p = new Player(false);
               ai = new Ai(true, 3);
 
           } else {
               System.out.print("Please enter a valid input");
               System.exit(0);
           }
 
       } else if (playerColor.equals("o") || playerColor.equals("O")) {
 
           if (boardDIM == 1) {
               p = new Player(true);
               ai = new Ai(false, 2);
 
           } else if (boardDIM == 2) {
               p = new Player(true);
               ai = new Ai(false, 3);
 
           } else {
               System.out.print("Please enter a valid input\n");
               System.exit(0);
           }
 
       } else {
           System.out.print("Please enter a valid input\n");
           System.exit(0);
       }
       } else {
    	   System.out.print("Please enter a valid input\n");
           System.exit(0);
       }
 
       while (!b.isGameOver(b)) {
 
           b.userInterface(b);
 
           HashSet<Move> LegalMoves = new HashSet<Move>();
 
           if (p.getColor()== true) {
        	   LegalMoves = b.getLegalMovesWhite(b,p);
           }else {
        	   LegalMoves = b.getLegalMovesBlack(b,p);
           }
           
           System.out.println("Leagal Moves: " + LegalMoves + "\n");
           if(!LegalMoves.isEmpty()) {
        	  
        	  
           
           System.out.println("Your Move: ");
           String stringMove = scnr.next();
           if(stringMove.length()<2) {
        	   System.out.print("Please enter a valid input \n");
               continue;
           }
        	   
           
 
           // you place
           Move move = new Move();
           if (moveChecker(stringMove)) {
               move = moveConverter(stringMove);
           } else {
               System.out.print("Please enter a valid input \n");
               continue;
           }
           if (setContainsMove(move, LegalMoves)) {
 
               b.makeMove(b, move, p);
           }else {
        	   System.out.print("Please enter a valid input \n");
               continue;
           }
           System.out.println(move);
           
           
           b.userInterface(b);
           }
           HashSet<Move> AILegalMoves = new HashSet<Move>();
          
           if (p.getColor()== false) {
        	   AILegalMoves = b.getLegalMovesWhite(b,ai);
           }else {
        	   AILegalMoves = b.getLegalMovesBlack(b,ai);
           }
          if(!AILegalMoves.isEmpty()) {
           b.makeMove(b,ai.choseMove(b,p),ai);
          }
       }
      
       b.userInterface(b);
       
   }
 
}
 
 
 

