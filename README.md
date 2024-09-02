# Reversi Game with AI Opponent

## Authors
- Gus Vietze
- Lorenzo Mendoza

Welcome to my Reversi repository! This project features a complete implementation of the classic board game Reversi (also known as Othello), including an advanced AI opponent. The AI leverages the MINIMAX algorithm with alpha-beta pruning, and uses heuristic cutoffs for larger board sizes to optimize performance.

## Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Algorithm Details](#algorithm-details)
- [Command Line Instructions](#command-line-instructions)

## Project Overview

Reversi is a strategy board game for two players. The goal is to have the majority of your discs on the board by the end of the game. Players place discs alternately on the board, aiming to capture their opponent’s discs by sandwiching them between their own.

This project includes:
- **Graphical User Interface (GUI)**: A user-friendly interface for playing Reversi.
- **AI Opponent**: A challenging AI powered by advanced algorithms.

## Features

- **Play Against AI**: Challenge an AI opponent using MINIMAX with alpha-beta pruning.
- **Custom Board Sizes**: Play on a standard 8x8 board or choose larger board sizes.
- **Heuristic Cutoff**: Enhances AI performance on larger boards with heuristic-based cutoffs.
- **Interactive GUI**: A visually intuitive interface for an enjoyable gaming experience.

## Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/reversi-game.git
   cd reversi-game
   ```

2. **Compile the Program**:
   ```bash
   javac *.java
   ```

## Usage

1. **Run the Program**:
   ```bash
   java Game
   ```

2. **Game Setup**:
   - **Select Board Size**: Choose between 4x4 (Part 1) or 8x8 (Part 2) using options `1` or `2`.
   - **Set AI Difficulty**: Choose between random or intelligent AI using options `1` or `2`.
   - **Pick Player Color**: Choose between X or O.

The formal model of the game is implemented in the `BOARD` and `MOVE` classes.

## Algorithm Details

The core of the AI in this Reversi game is built on classic time-tested game playing algorithms designed to evaluate and decide on the best moves. Here's a deeper look into the algorithms used:

### MINIMAX Algorithm

The MINIMAX algorithm is a fundamental approach in decision-making for two-player games, providing a way to simulate and evaluate potential moves. It works as follows:

1. **Game Tree Creation**: MINIMAX generates a game tree where each node represents a possible game state. The root of the tree is the current state of the game, and the branches represent potential moves.

2. **Recursive Evaluation**: MINIMAX recursively explores the game tree. At each node, it evaluates the outcome of possible moves assuming both players play optimally. The algorithm assumes that both players aim to maximize their own score and minimize the opponent's score.

3. **Maximizing and Minimizing**: The algorithm assigns values to each node. If it’s the player’s turn (MAX player), the node value represents the maximum score achievable. If it’s the opponent’s turn (MIN player), the node value represents the minimum score achievable. This is done by alternating between maximizing and minimizing at each level of the tree.

4. **Optimal Move Selection**: At the leaf nodes (terminal states), the algorithm evaluates the final game states based on a utility function. These evaluations are then propagated up the tree to determine the best move for the current player.

5. **Complexity**: The non-heuristic based MINIMAX algorithm explores all possible moves and their outcomes, which can become impractical for games with large state spaces, like larger Reversi boards.

### Iterative Deepening
To manage the trade-off between search depth and time constraints, the AI uses iterative deepening:

Time-Bound Search: The algorithm progressively deepens the search until a time limit is reached. This ensures the AI can make a move within a reasonable time frame.
Best Move Identification: The best move found at each depth is recorded, and once the time limit is reached, the AI uses the best move identified so far.
Flexibility: This method allows the AI to adjust its depth dynamically based on available time, making it adaptable to different board sizes and game states.

### Alpha-Beta Pruning

Alpha-beta pruning enhances the MINIMAX algorithm by eliminating the need to explore all branches of the game tree, making it more efficient:

1. **Alpha and Beta Values**: Alpha represents the minimum score that the maximizing player is assured of, and Beta represents the maximum score that the minimizing player is assured of. These values help in pruning branches of the tree that won't affect the final decision.

2. **Pruning Process**: During the tree traversal, if a branch is found that cannot possibly affect the final decision (e.g., if a move is worse than the already explored best move), it is pruned away. This avoids unnecessary computations.

3. **Efficiency**: By cutting off branches that won’t influence the final outcome, alpha-beta pruning reduces the number of nodes evaluated, speeding up the decision-making process without affecting the outcome.

Certainly! Here's a more detailed explanation of the **Dynamic Heuristic Evaluation Function** used in the AI for this Reversi game:

### Dynamic Heuristic Evaluation Function

The Dynamic Heuristic Evaluation Function is a critical component in the AI's decision-making process, especially when the search depth is limited due to the complexity of the game tree. This function is designed to estimate the potential value of a given board state by considering various strategic factors that influence the outcome of the game. The AI uses this heuristic to make informed decisions when it cannot exhaustively search the entire game tree, particularly in larger board sizes or under time constraints.

#### Key Components of the Heuristic Function

1. **Piece Difference (`p`)**:
   - **Definition**: This factor evaluates the difference in the number of discs between the AI and the opponent.
   - **Calculation**: The formula is:
     \[
     p = \frac{100 \times (\text{myTiles} - \text{oppTiles})}{\text{myTiles} + \text{oppTiles}}
     \]
   - **Purpose**: The piece difference gives a basic indication of the player's dominance on the board. A higher piece difference in favor of the AI indicates a stronger position, while a lower difference suggests a weaker one.

2. **Frontier Discs (`f`)**:
   - **Definition**: Frontier discs are those that are adjacent to empty spaces, making them vulnerable to capture in the opponent's next move.
   - **Calculation**: The frontier disc value is calculated similarly to the piece difference but focuses on the discs' vulnerability:
     \[
     f = -\frac{100 \times (\text{myFrontTiles} - \text{oppFrontTiles})}{\text{myFrontTiles} + \text{oppFrontTiles}}
     \]
   - **Purpose**: Minimizing frontier discs is essential as these discs are easier for the opponent to flip. The AI aims to reduce its own frontier discs while maximizing the opponent's, thus increasing its stability on the board.

3. **Corner Occupancy (`c`)**:
   - **Definition**: Occupying the corners of the board is a critical strategy in Reversi, as corner discs are stable and cannot be flipped once placed.
   - **Calculation**: The value assigned to corner occupancy is:
     \[
     c = 25 \times (\text{myCornerTiles} - \text{oppCornerTiles})
     \]
   - **Purpose**: Controlling the corners provides a significant advantage, making it a high-priority goal. The AI heavily favors moves that either secure a corner or prevent the opponent from doing so.

4. **Corner Closeness (`m`)**:
   - **Definition**: This factor penalizes positions where the AI has discs near an unoccupied corner that the opponent might later capture.
   - **Calculation**: It evaluates the presence of AI or opponent discs adjacent to unoccupied corners, reducing the AI’s score if its discs are close to such corners without securing them.
   - **Purpose**: The goal is to avoid placing discs near corners unless the AI is in a position to secure the corner. This prevents the opponent from leveraging the AI's discs to gain control of the corner.

5. **Mobility (`l`)**:
   - **Definition**: Mobility refers to the number of legal moves available to a player at any given point.
   - **Calculation**: While not explicitly calculated in the provided function, mobility can be evaluated as:
     \[
     l = \frac{100 \times (\text{myLegalMoves} - \text{oppLegalMoves})}{\text{myLegalMoves} + \text{oppLegalMoves}}
     \]
   - **Purpose**: Mobility is crucial as it determines the flexibility and potential for future moves. The AI favors moves that maximize its own mobility while limiting the opponent’s options.

6. **Disc Square Table (`d`)**:
   - **Definition**: This is a pre-defined table that assigns values to each square on the board based on its strategic importance.
   - **Example**: The corners are given high positive values, while squares adjacent to the corners are given negative values to discourage placing discs there unless the corner is secured.
   - **Purpose**: The disc square table guides the AI to occupy strategically valuable positions on the board, thereby strengthening its overall position.

#### Dynamic Nature of the Heuristic

The evaluation function is considered "dynamic" because it adjusts based on the state of the game and the specific configuration of the board. This dynamic adjustment allows the AI to weigh different strategic elements more heavily depending on the current phase of the game. For example:

- **Early Game**: The AI might prioritize mobility and piece difference, ensuring it has many options for future moves while trying to gain a numerical advantage.
- **Mid-Game**: The focus might shift to controlling key positions, such as corners, while minimizing frontier discs to reduce vulnerability.
- **End-Game**: The AI might place more weight on corner occupancy and the disc square table to secure a strong final position and maximize its control over the board.

By integrating these components, the Dynamic Heuristic Evaluation Function provides a sophisticated mechanism for the AI to assess the board and make decisions that balance immediate gains with long-term strategic considerations. This leads to more robust and competitive gameplay, especially against human opponents.

## Command Line Instructions

- **Compile the Code**:
  ```bash
  javac *.java
  ```

- **Run the Game**:
  ```bash
  java Game
  ```
