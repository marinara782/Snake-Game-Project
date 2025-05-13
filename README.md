# Java Snake Game

A console-based Snake game implemented in Java. The game runs in the terminal and uses ASCII characters to render the snake, walls, and food.

## 📌 Overview

This project simulates the classic Snake game where the player navigates a snake to consume food (`@`) while avoiding collisions with walls (`#`) or the snake’s own body. The game ends when the snake collides with any of these obstacles.

## 🕹️ Gameplay

- The snake (`S`) starts with a length of 3 and grows every time it eats food.
- The game is played by entering one of the following directions:
  - `w`: up
  - `s`: down
  - `a`: left
  - `d`: right
- The snake grows in length with each food item eaten until it reaches the maximum possible length.

## 🧠 Features

- Grid-based rendering using characters:
  - `#`: Walls
  - `.`: Empty space
  - `S`: Snake body
  - `@`: Food
- Movement logic using a 2D integer array to track the snake's coordinates
- Collision detection with boundaries and self
- Randomized food placement on the grid
- Automatic length increase when food is consumed
- Console input with Java's `Scanner` class

## 🏗️ How It Works

- `initializeGame()`: Sets up the game grid, walls, snake, and initial food.
- `updateSnakePosition()`: Moves the snake based on player input and handles growth logic.
- `isValidMove()`: Checks whether the next move is valid (not into wall or self).
- `generateFood()`: Randomly places a food item on the grid.
- `displayGrid()`: Prints the current game state to the terminal.
- `main()`: Runs the game loop, taking player input and updating the game state.

## 🧪 Requirements

- Java 8 or higher

## ▶️ Running the Game

1. Compile the program:

```bash
javac SnakeGame.java
