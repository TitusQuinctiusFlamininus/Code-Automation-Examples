import org.junit.Assert;
import org.junit.Test;

/**
 * Created by michael.nyika on 11/08/15.
 */
public class GameExampleTests {

    @Test
    public void testTheGame(){
        String expectedGameResult =  "O_WINS";
        TicTacToe game = new TicTacToe();
        System.out.println(game.decideTicTacToeWinner("UUUUUUUUU", 'S')); //game start
        System.out.println(game.decideTicTacToeWinner("UUUXUUUUU", 'X')); //x plays, middle row, left column
        System.out.println(game.decideTicTacToeWinner("UUUXUUUUO", 'O')); //o plays, top row, right column
        System.out.println(game.decideTicTacToeWinner("UUUXUUXUO", 'X')); //x plays, top row, left column
        System.out.println(game.decideTicTacToeWinner("OUUXUUXUO", 'O')); //o plays, botton row, left column (blocking x from vertical win on left column)
        System.out.println(game.decideTicTacToeWinner("OUUXUXXUO", 'X')); //x plays, middle row, right column (aiming for horizontal win, across middle row)
        String actualGameResult = game.decideTicTacToeWinner("OUUXOXXUO", 'O'); //o plays, middle row, middle column (TICTACTOE!)
        System.out.println(actualGameResult);
        Assert.assertEquals(expectedGameResult, actualGameResult);
    }

}
