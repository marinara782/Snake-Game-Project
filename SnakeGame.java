// Marena Matavao USCID: 30237507


import java.util.Random;
import java.util.Scanner;


    // Purpose: Implement a simple snake game in Java.
public class SnakeGame {

    /*
     * Holds the current state of the game grid. Coordinates are represented as (X,
     * Y).
     * Coordinate 0,0 is the top-left corner of the grid and is always a wall.
     */
    public static char[][] grid;
  
    /*
     * int[][] snake represents the snake's body with each row as a segment.
     * snake[i][0] is the X-coordinate (row) of segment i
     * snake[i][1] is the Y-coordinate (column) of segment i
     *
     * Example: snake = new int[3][2]; // A snake with 3 segments
     *
     * snake[0][0] = 3; // X-coordinate of head (first segment)
     * snake[0][1] = 2; // Y-coordinate of head (first segment)
     *
     * snake[1][0] = 2; // X-coordinate of second segment
     * snake[1][1] = 2; // Y-coordinate of second segment
     *
     * snake[2][0] = 1; // X-coordinate of third segment (tail)
     * snake[2][1] = 2; // Y-coordinate of third segment (tail)
     *
     * Visually, the snake on the grid looks like this (X, Y):
     * (3, 2) ← Head
     * (2, 2)
     * (1, 2) ← Tail
     *
     * The snake array holds these coordinates to represent its position on the
     * grid.
     */
    public static int[][] snake;
  
    // The current length of the snake
    public static int snakeLength;
  
    // The game over flag
    public static boolean isGameOver = false;
  
    // Implement the following constants
    public static final int GRID_SIZE_X = 20; // the number of columns
    public static final int GRID_SIZE_Y = 10; // the number of rows
    public static final char SNAKE_CHAR = 'S';
    public static final char FOOD_CHAR = '@';
    public static final char EMPTY_CHAR = '.';
    public static final char WALL_CHAR = '#';
    public static final int MAX_SNAKE_LENGTH = GRID_SIZE_X * GRID_SIZE_Y;
  
    /**
     * Initializes the grid, creates the wall, snake and food
     */
    public static void initializeGame() {
      grid = new char[GRID_SIZE_Y][GRID_SIZE_X];

      // TODO: Create the walls, snake and food (ACCOMPLISHED)

      // Initialize the grid with empty char. 
      for (int i = 0; i < GRID_SIZE_Y; i++) {
        for (int j = 0; j < GRID_SIZE_X; j++) {
          grid[i][j] = EMPTY_CHAR;
        }
      }

      // Create walls!
      for (int i = 0; i < GRID_SIZE_X; i++) {
        grid[0][i] = WALL_CHAR;
        grid[GRID_SIZE_Y - 1][i] = WALL_CHAR;
      }
      for (int i = 0; i < GRID_SIZE_Y; i++) {
        grid[i][0] = WALL_CHAR;
        grid[i][GRID_SIZE_X - 1] = WALL_CHAR;
      }

      // Create the snake!
      snake = new int[3][2];
      snake[0][0] = GRID_SIZE_Y / 2; 
      snake[0][1] = GRID_SIZE_X / 2;
      snake[1][0] = snake[0][0];
      snake[1][1] = snake[0][1] - 1;
      snake[2][0] = snake[0][0];
      snake[2][1] = snake[0][1] - 2;

      // The intial length of the snake!
      snakeLength = 3; 


      generateSnake();

      // The food!
      generateFood();
    }
  
    /**
     * Adds the snake to the game grid.
     */
    public static void generateSnake() {
      for (int i = 0; i < snakeLength; i++) {
        int x = snake[i][0];
        int y = snake[i][1];
        grid[x][y] = SNAKE_CHAR;
      }
    }
  
    /**
     * Updates the snake's position on the grid and adjusts the snake's body
     * coordinates. Note: coordinates are given in the order of (X, Y).
     *
     * @param newX The new X coordinate of the snake's head.
     * @param newY The new Y coordinate of the snake's head.
     */
    public static void updateSnakePosition(int newX, int newY) {
  
      // TODO: Trim the tail only if it's not the initial stages of the game
      //  i.e., the first three moves the length of the snake will be 1, 2, and 3
      //  respectively.
  
      // TODO: Move the segments of the body 2 <- 1, 1 <- 0, new 0
  
      // TODO: update the snake's head position (ACCOMPLISHED)

      // checks if it ate food!
      boolean ateFood = grid[newX][newY] == FOOD_CHAR;

      // let's track the current position before moving the body!
      int oldTAILX = snake[snakeLength - 1][0];
      int oldTAILY = snake[snakeLength - 1][1];

      // Moves the snakes body!
      for (int i = snakeLength - 1; i > 0; i--) {
        snake[i][0] = snake[i - 1][0]; // this moves the x-coor
        snake[i][1] = snake[i - 1][1]; // this moves the y-coor
      }

      // Updates the new head
      snake[0][0] = newX;
      snake[0][1] = newY;
      grid[newX][newY] = SNAKE_CHAR;


      grid[oldTAILX][oldTAILY] = EMPTY_CHAR; // will just update the tail after eating food!

      // Checks the tail removal based on if the snake is growing!
      if (ateFood) {
        // checks if the snake eats food!
        if (snakeLength < MAX_SNAKE_LENGTH) {
          snakeLength ++; // this increases the length of the snake if food is eaten
          

          // resize the array to make sure the array fits the snake. Due to the error that we keep getting
          if (snakeLength > snake.length) {
            int[][] newSnake = new int[snakeLength][2];
            for (int i = 0; i < snake.length; i++) {
              newSnake[i][0] = snake[i][0];
              newSnake[i][1] = snake[i][1];
            }
            snake = newSnake; // Updates the snake reference to the new array!
          }
        } 
        generateFood();

      }
    }
  
    /**
     * Checks if the given coordinates are a valid move for the snake.
     * A move is valid if it is within the grid boundaries and does not
     * result in the snake colliding with itself or the walls. (ACCOMPLISHED)
     *
     * @param x The X coordinate to check.
     * @param y The Y coordinate to check.
     * @return true if the move is valid, false otherwise.
     */
    public static boolean isValidMove(int x, int y) {
      if (x >= GRID_SIZE_Y || y >= GRID_SIZE_X) {
        System.err.println("Womp Womp you hit a wall.");
        isGameOver = true;
        return false; // Checks if its out of bounds!
      }
      for (int i = 0; i < snakeLength; i++) {
        if (snake[i][0] == x && snake[i][1] == y) {
          System.err.println("Nom Nom you ran into yourself.");
          isGameOver = true;
          return false; // If there is a collision!
        }
      }
      return true;
    }
  
    /**
     * Displays the current state of the game grid to the console.
     * i.e., this is where you print the display to the console
     */
    public static void displayGrid() {
      for (int i = 0; i < GRID_SIZE_Y; i++) {
        for (int j = 0; j < GRID_SIZE_X; j++) {
          System.err.print(grid[i][j]);
        }
        System.err.println();
      }
    }
  
    /**
     * Updates the snake's position based on the player's input direction.
     * If the move is valid, it updates the grid and checks for food.
     * Ends the game if the snake collides with the walls or itself.
     *
     * @param direction The direction in which to move the snake
     *                  ('w' for up, 's' for down, 'a' for left, 'd' for right).
     */
    public static void moveSnake(char direction) {
      // TODO: Implement a switch statement to handle the direction
      // checks if the move is valid otherwise the game ends
      int headX = snake[0][0];
      int headY = snake[0][1];
      int newX = headX;
      int newY = headY;

      switch (direction) {
        case 'w':
            newX--;
            break;
        case 's':
            newX++; 
            break; 
        case 'a':
            newY--;
            break;
        case 'd':
            newY++;
            break;
        default:
            System.err.println("Wrong Way!!");
            return;
      }

      if (!isValidMove(newX, newY)) {
        isGameOver = true;
        return;
      }

      updateSnakePosition(newX, newY);

    }
  
    /**
     * Generates a food item at a random empty position on the grid.
     */
    public static void generateFood() {
      Random rand = new Random();
      int foodX, foodY;


      do { 
          foodX = rand.nextInt(GRID_SIZE_Y - 2) + 1;
          foodY = rand.nextInt(GRID_SIZE_X - 2) + 1; 
      } while (grid[foodX][foodY] != EMPTY_CHAR); // Checks that the position is empty!

      grid[foodX][foodY] = FOOD_CHAR;
    }
  
    public static void main(String[] args) {
  
      // TODO: Create a scanner for input (ACCOMPLISHED)
  
      // TODO: Create a game loop that continues until the game is over
  
      // TODO: Close the scanner before exiting the game

      initializeGame();
      Scanner SCANNER = new Scanner(System.in);

      while (!isGameOver) {
        displayGrid();
        System.err.println("Enter a move! (w/a/s/d): ");
        char direction = SCANNER.next().charAt(0);
        moveSnake(direction);
      }

      System.err.println("Game Over! Go again!");
      SCANNER.close();
  
    }
  }
