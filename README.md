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

5. **Complexity**: The basic MINIMAX algorithm explores all possible moves and their outcomes, which can become impractical for games with large state spaces, like larger Reversi boards.

### Alpha-Beta Pruning

Alpha-beta pruning enhances the MINIMAX algorithm by eliminating the need to explore all branches of the game tree, making it more efficient:

1. **Alpha and Beta Values**: Alpha represents the minimum score that the maximizing player is assured of, and Beta represents the maximum score that the minimizing player is assured of. These values help in pruning branches of the tree that won't affect the final decision.

2. **Pruning Process**: During the tree traversal, if a branch is found that cannot possibly affect the final decision (e.g., if a move is worse than the already explored best move), it is pruned away. This avoids unnecessary computations.

3. **Efficiency**: By cutting off branches that won’t influence the final outcome, alpha-beta pruning reduces the number of nodes evaluated, speeding up the decision-making process without affecting the outcome.

### Heuristic Cutoff

For larger board sizes, MINIMAX can become computationally prohibitive due to the vast number of possible game states. Heuristic cutoffs address this issue:

1. **Depth Limitation**: Instead of evaluating the game tree to its fullest extent, a heuristic cutoff limits the depth of the search. The AI evaluates only up to a certain number of moves ahead and estimates the game state beyond that depth.

2. **Heuristic Evaluation**: When the search reaches the cutoff depth, the AI uses a heuristic evaluation function to estimate the value of the game state. This heuristic function provides a quick approximation of the board's potential without a full simulation.

3. **Performance**: The heuristic evaluation helps in making the AI's decision-making process faster, especially for larger boards. For the 8x8 board, the AI uses MINIMAX with alpha-beta pruning and applies a heuristic to evaluate game states at the cutoff depth. A typical depth for this heuristic approach is less than 10 levels deep.

4. **Heuristic Function**: The heuristic function used in this project evaluates factors such as the number of discs, potential moves, and control of the board’s corners and edges. This function helps in making informed decisions when the full game tree cannot be explored.

By combining MINIMAX with alpha-beta pruning and heuristic cutoffs, the AI in this Reversi game balances depth of search with computational efficiency, providing a challenging and responsive opponent.

## Command Line Instructions

- **Compile the Code**:
  ```bash
  javac *.java
  ```

- **Run the Game**:
  ```bash
  java Game
  ```
