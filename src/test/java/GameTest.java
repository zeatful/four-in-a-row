import org.junit.Test;

import java.awt.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameTest {
    // board to test red and yellow wins in the y direction
    private char[][] board = {
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', 'R', ' ', 'R', ' ', ' ', ' '},
            {'Y', 'R', 'Y', 'R', 'Y', 'Y', ' '},
            {'Y', 'R', 'Y', 'R', 'R', 'Y', 'R'},
            {'R', 'Y', 'Y', 'Y', 'R', 'Y', 'R'}
    };

    // board to test a red win in the diagonal direction
    private char[][] redDiagonalBoard = {
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', 'R', ' ', 'R', ' ', ' ', ' '},
            {'Y', 'R', ' ', 'R', 'Y', 'Y', ' '},
            {'Y', 'R', 'Y', 'R', 'R', 'Y', 'R'},
            {'R', 'Y', 'Y', 'Y', 'R', 'Y', 'R'}
    };

    // board to test yellow win in the x direction
    private char[][] yellowXBoard = {
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {'R', 'Y', 'R', 'Y', ' ', 'R', 'Y'},
            {'Y', 'R', 'Y', 'R', ' ', 'Y', 'Y'},
            {'R', 'Y', 'Y', 'Y', ' ', 'R', 'R'}
    };

    // board to test red win in the x direction
    private char[][] redXBoard = {
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {'R', 'Y', 'R', 'Y', ' ', 'R', 'Y'},
            {'Y', 'R', 'Y', 'R', ' ', 'Y', 'Y'},
            {'R', 'Y', 'Y', 'R', ' ', 'R', 'R'}
    };

    @Test
    public void redWinsInYDirection() throws Exception {
        // verify a win in the y direction for red
        Game game = new Game(board);
        assertThat(game.move(new Point(1, 4), 'R'), is(true));
    }

    @Test
    public void yellowWinsInYDirection() throws Exception {
        // verify a win in the y direction for yellow
        Game game = new Game(board);
        assertThat(game.move(new Point(5, 3), 'Y'), is(true));
    }

    @Test
    public void redDoesNotWinYDirection() throws Exception {
        // verify a win for red in the y direction
        Game game = new Game(board);
        assertThat(game.move(new Point(2, 3), 'R'), is(false));
    }

    @Test
    public void yellowDoesNotWinInYDirection() throws Exception {
        // verify a win for yellow in the y direction
        Game game = new Game(board);
        assertThat(game.move(new Point(0, 3), 'Y'), is(false));
    }

    @Test
    public void yellowWinsInXDirection() throws Exception {
        // verify a win for yellow in the x direction
        Game game = new Game(yellowXBoard);
        assertThat(game.move(new Point(4, 0), 'Y'), is(true));
    }

    @Test
    public void yellowDoesNotWinInXDirection() throws Exception {
        // verify a win for yellow in the x direction
        Game game = new Game(redXBoard);
        assertThat(game.move(new Point(4, 0), 'Y'), is(false));
    }

    @Test
    public void redWinsInXDirection() throws Exception {
        // verify a win for yellow in the x direction
        Game game = new Game(redXBoard);
        assertThat(game.move(new Point(4, 0), 'R'), is(true));
    }

    @Test
    public void redDoesNotWinInXDirection() throws Exception {
        // verify a win for yellow in the x direction
        Game game = new Game(yellowXBoard);
        assertThat(game.move(new Point(4, 0), 'R'), is(false));
    }

    @Test
    public void redWinsInDiagonalDirection() throws Exception {
        // verify a win for red in the diagonal direction
        Game game = new Game(redDiagonalBoard);
        assertThat(game.move(new Point(2, 2), 'R'), is(true));
    }
}