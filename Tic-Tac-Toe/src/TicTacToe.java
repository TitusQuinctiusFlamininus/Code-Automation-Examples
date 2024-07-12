/**
 * Created by michael.nyika on 06/05/15.
 */
public class TicTacToe {
    public String decideTicTacToeWinner(String boardState, char tttSymbol) {

        if (areThereConsecutive(boardState, tttSymbol)) {
            if (tttSymbol == 'X') {
                return "X_WINS";
            } else {
                return "O_WINS";
            }
        }
        return "GAME_NOT_OVER";
    }

    protected boolean areThereConsecutive(String boardState, char tictactoechar) {
        boolean weHaveAWinner = false;

        for (int counter = 0; counter <= 6; counter++) {
            if (boardState.toCharArray()[counter] == tictactoechar) {
                //rows
                if ((counter%3 == 0) && ((boardState.toCharArray()[counter + 1] == tictactoechar) && (boardState.toCharArray()[counter + 2] == tictactoechar)) )
                {
                    weHaveAWinner = true;
                    break;
                }
                //columns
                if(counter < 3 && ((boardState.toCharArray()[counter + 3] == tictactoechar) && (boardState.toCharArray()[counter + 6] == tictactoechar))){
                    weHaveAWinner = true;
                    break;
                }
            }
            //diagonal, bottom left to top right
            if( (boardState.toCharArray()[0] == tictactoechar) && (boardState.toCharArray()[4] == tictactoechar) && (boardState.toCharArray()[8] == tictactoechar)){
                weHaveAWinner = true;
                break;
            }
            //diagonal, top left to bottom right
            if( (boardState.toCharArray()[2] == tictactoechar) && (boardState.toCharArray()[4] == tictactoechar) && (boardState.toCharArray()[6] == tictactoechar)){
                weHaveAWinner = true;
                break;
            }

        }
        return weHaveAWinner;
    }


}
