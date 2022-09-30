# Reversi-AI
**Authors: Lorenzo Mendoza, Gus Vietze**


**Run:**

Start the program by running GAME and select the size of the board either 4x4 (Part 1) or 8x8 (Part 2) using 1 or 2. You can choose the type of difficulty for Ai, either random or intelligently using 1 or 2. Then pick the color of the player using X or O. The implementation of the formal model is within the BOARD and MOVE class.

**Part 1:** Minimax When declaring a 4x4 and intelligent Ai, the Ai will use a Minimax search algorithm to perform its actions. It will explore each state after the player moves (initial state) and to find the most optimal move. This Ai will play optimally.

**Part 2:** Heuristic Minimax Alpha Beta Pruning When declaring a 8x8 and intelligent Ai, the Ai will use a Heuristic Minimax with Alpha Beta Pruning search algorithm to perform its actions. It will explore each state after the player moves (initial state) and to find the most optimal move with a depth cutoff which YOU input. The most optimal, or depths that run efficiently, is anything <10. The heuristic will return an evaluation of its state at the cutoff to perform moves.

**Command Lines:**

javac *.java java Game
