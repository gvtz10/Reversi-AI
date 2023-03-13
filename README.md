# MINIMAX Reversi AI

## Authors
- Lorenzo Mendoza, Gus Vietze

## Running the program
1. Compile the program by running `javac *.java` in the terminal.
2. Start the program by running `java Game`.
3. Select the size of the board: 4x4 (Part 1) or 8x8 (Part 2) using 1 or 2.
4. Choose the type of difficulty for the AI: random or intelligently using 1 or 2.
5. Pick the color of the player using X or O.

The implementation of the formal model is within the `BOARD` and `MOVE` class.

## Part 1: Minimax
When declaring a 4x4 board and intelligent AI, the AI will use a Minimax search algorithm to perform its actions. It will explore each state after the player moves (initial state) and find the most optimal move. This AI will play optimally.

## Part 2: Heuristic Minimax Alpha Beta Pruning
When declaring a 8x8 board and intelligent AI, the AI will use a Heuristic Minimax with Alpha Beta Pruning search algorithm to perform its actions. It will explore each state after the player moves (initial state) and find the most optimal move with a depth cutoff that you input. The most optimal (or efficient) depths are anything less than 10. The heuristic will return an evaluation of its state at the cutoff to perform moves.

## Command Lines
javac *.java 

java Game
