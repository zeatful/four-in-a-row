import java.awt.*;
import java.util.Arrays;
import java.util.Collections;

public class Game {
    // to store the board dimensions
    private final static int X_DIMENSION = 7;
    private final static int Y_DIMENSION = 6;

    // how many other colors must exist in a row to make 4 (the current piece is 1, so the remaining is 3)
    private final static int REMAINING_TO_WIN = 3;

    // used to store game state
    private char[][] board;

    // initialize a new board, not used by my tests
    public Game() {
        board = new char[Y_DIMENSION][X_DIMENSION];
        // populate each spot with an empty character
        for (int y = 0; y < Y_DIMENSION; y++) {
            for (int x = 0; x < X_DIMENSION; x++) {
                board[y][x] = ' ';
            }
        }
    }

    // initialize the game with a board, since the array reads better upside down, reverse it prior to initialization for easier testing
    public Game(char[][] board) {
        Collections.reverse(Arrays.asList(board));
        this.board = board;
    }

    // takes a move as a Point, updates the board then checks if the move is a win
    public boolean move(Point p, char color) {
        board[p.y][p.x] = color;
        return hasWon(p, color);
    }

    /**
     * Go through each direction next to the current move to find four in a row
     *
     * @param p     - point to check
     * @param color - color we are checking
     * @return whether or not we have reached a count of 4
     */
    private boolean hasWon(Point p, char color) {
    /* Example board
        2| R R Y Y Y
        1| R R X Y Y
        0| R R Y Y Y
        -------------
           0 1 2 3 4

         1. start at the last move
         2. count nearby matches
            - count same colors in a row in both x and -x directions to see if the count is 4
            - count same colors in a row in both y and -y directions to see if the count is 4
            - count same colors in a row in in both y = x directions to see if the count is 4
            - count same colors in a row in in both y = -x directions to see if the count is 4
    */
        // count the x direction
        if (countDirection(0, p, color, 1, 0) + countDirection(0, p, color, -1, 0) == REMAINING_TO_WIN) {
            printBoard();
            return true;
        }

        // count the y direction, only need to count down as no piece can exist above
        if (countDirection(0, p, color, 0, -1) == REMAINING_TO_WIN) {
            printBoard();
            return true;
        }

        // the diagonal of line y = x
        if (countDirection(0, p, color, 1, 1) + countDirection(0, p, color, -1, -1) == REMAINING_TO_WIN) {
            printBoard();
            return true;
        }

        // the diagonal of line y = -x
        if (countDirection(0, p, color, 1, -1) + countDirection(0, p, color, -1, 1) == REMAINING_TO_WIN) {
            printBoard();
            return true;
        }

        // otherwise move did not win
        return false;
    }

    // should count in the direction recursively, updating the point, and returning the count of matching characters in that direction on the board
    private int countDirection(int count, Point p, char color, int x, int y) {
        // create new point for next step in direction
        Point p2 = new Point(p.x + x, p.y + y);

        // verify updated point is valid for array and is the same color
        if ((p2.x < X_DIMENSION && p2.x > -1) && (p2.y < Y_DIMENSION && p2.y > -1) && board[p2.y][p2.x] == color) {
            // increment count and move in provided direction
            return countDirection(count + 1, p2, color, x, y);
        } else {
            return count;
        }
    }

    // utility method to print out the winning board for visual confirmation
    private void printBoard() {
        for (int y = Y_DIMENSION - 1; y > -1; y--) {
            for (int x = 0; x < X_DIMENSION; x++) {
                System.out.print(board[y][x] + "  ");
            }
            System.out.println();
        }
    }
}