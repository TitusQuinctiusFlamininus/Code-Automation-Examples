import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by michael.nyika on 06/05/15.
 */
public class TicTacToeTests {

    private TicTacToe testSubject;

    @Before
    public void at_the_very_beginning(){
        testSubject = new TicTacToe();
    }

    //Assume order of cells for tictactoe board is:
    // bottom_row -> middle_row -> top_row (always from left to right)

    @Test
    public void given_unmarked_board_then_game_is_not_finished(){
        testSubject = new TicTacToe(){
            protected boolean areThereConsecutive(String theStateOfTheBoard, char tttSymbol) {
                return false;
            }
        };
        Assert.assertEquals("GAME_NOT_OVER", testSubject.decideTicTacToeWinner("UUUUUUUUU", 'E'));

    }

    @Test
    public void given_single_X_marked_board_then_game_is_not_finished(){
        testSubject = new TicTacToe(){
           protected boolean areThereConsecutive(String theStateOfTheBoard, char tttSymbol) {
                return false;
            }
        };
        Assert.assertEquals("GAME_NOT_OVER", testSubject.decideTicTacToeWinner("UUXUUUUUU", 'X'));

    }

    @Test
    public void given_3_X_not_in_a_row_then_game_is_not_finished() {
        testSubject = new TicTacToe(){
           protected boolean areThereConsecutive(String theStateOfTheBoard, char tttSymbol) {
                return false;
            }
        };
        Assert.assertEquals("GAME_NOT_OVER", testSubject.decideTicTacToeWinner("XUUUUXXUU", 'X'));
    }

    @Test
    public void given_3_X_in_a_middle_row_then_X_wins() {
        testSubject = new TicTacToe(){
           protected boolean areThereConsecutive(String theStateOfTheBoard, char tttSymbol) {
                return true;
            }
        };
        Assert.assertEquals("X_WINS", testSubject.decideTicTacToeWinner("UUUXXXUUU", 'X'));
    }

    @Test
    public void given_3_X_in_a_top_row_then_X_wins() {
        testSubject = new TicTacToe(){
           protected boolean areThereConsecutive(String theStateOfTheBoard, char tttSymbol) {
                return true;
            }
        };
        Assert.assertEquals("X_WINS", testSubject.decideTicTacToeWinner("UUUUUUXXX", 'X'));
    }

    @Test
    public void given_3_X_in_a_bottom_row_then_X_wins() {
        testSubject = new TicTacToe(){
           protected boolean areThereConsecutive(String theStateOfTheBoard, char tttSymbol) {
                return true;
            }
        };
        Assert.assertEquals("X_WINS", testSubject.decideTicTacToeWinner("UUUUUUXXX", 'X'));
    }

    @Test
    public void given_3_O_not_in_a_row_then_game_is_not_finished() {
        testSubject = new TicTacToe(){
           protected boolean areThereConsecutive(String theStateOfTheBoard, char tttSymbol) {
                return false;
            }
        };
        Assert.assertEquals("GAME_NOT_OVER", testSubject.decideTicTacToeWinner("OOUUUUUUO", 'O'));
    }

    @Test
    public void given_3_O_in_a_diagonal_then_O_Wins() {
        testSubject = new TicTacToe(){
           protected boolean areThereConsecutive(String theStateOfTheBoard, char tttSymbol) {
                return true;
            }

        };
        Assert.assertEquals("O_WINS", testSubject.decideTicTacToeWinner("OUUUOUUUO", 'O'));
    }

    @Test
    public void given_3_O_in_a_diagonal_righttoleft_then_O_Wins() {
        testSubject = new TicTacToe(){
           protected boolean areThereConsecutive(String theStateOfTheBoard, char tttSymbol) {
                return true;
            }

        };
        Assert.assertEquals("O_WINS", testSubject.decideTicTacToeWinner("UUOUOUOUU", 'O'));
    }

    @Test
    public void given_3_X_in_a_diagonal_then_O_Wins() {
        testSubject = new TicTacToe(){
           protected boolean areThereConsecutive(String theStateOfTheBoard, char tttSymbol) {
                return true;
            }

        };
        Assert.assertEquals("X_WINS", testSubject.decideTicTacToeWinner("XUUUXUUUX", 'X'));
    }


    @Test
    public void given_3_O_in_a_top_row_then_O_Wins() {
        testSubject = new TicTacToe(){
           protected boolean areThereConsecutive(String theStateOfTheBoard, char tttSymbol) {
                return true;
            }

        };
        Assert.assertEquals("O_WINS", testSubject.decideTicTacToeWinner("UUUUUUOOO", 'O'));
    }

    @Test
    public void given_3_X_in_a_diagonal_righttoleft_then_X_Wins() {
        testSubject = new TicTacToe(){
           protected boolean areThereConsecutive(String theStateOfTheBoard, char tttSymbol) {
                return true;
            }

        };
        Assert.assertEquals("X_WINS", testSubject.decideTicTacToeWinner("UUXUXUXUU", 'X'));
    }

    @Test
    public void given_3_O_in_a_middle_row_then_O_Wins() {
        testSubject = new TicTacToe(){
           protected boolean areThereConsecutive(String theStateOfTheBoard, char tttSymbol) {
                return true;
            }

        };
        Assert.assertEquals("O_WINS", testSubject.decideTicTacToeWinner("UUUOOOUUU", 'O'));
    }

    @Test
    public void given_3_O_in_a_bottom_row_then_O_Wins() {
        testSubject = new TicTacToe(){
           protected boolean areThereConsecutive(String theStateOfTheBoard, char tttSymbol) {
                return true;
            }

        };
        Assert.assertEquals("O_WINS", testSubject.decideTicTacToeWinner("UUUUUUOOO", 'O'));
    }

    //whole board marked
    @Test
    public void given_board_with_X_and_O_and_not_in_any_rows_then_game_not_over(){
        Assert.assertEquals("GAME_NOT_OVER", testSubject.decideTicTacToeWinner("XOXXOXOXO", 'X'));
    }


    //CONTRACT TESTS

    //Rows

    @Test
    public void given_3_X_in_row_then_affirm_consecutiveness(){
        Assert.assertTrue(testSubject.areThereConsecutive("UUUUUUXXX", 'X'));
    }

    @Test
    public void given_3_O_in_row_then_affirm_consecutiveness(){
        Assert.assertTrue(testSubject.areThereConsecutive("UUUUUUOOO", 'O'));
    }

    @Test
    public void given_3_X_not_in_row_then_affirm_consecutiveness(){
        Assert.assertFalse(testSubject.areThereConsecutive("UUXXUUXUU", 'X'));
    }

    @Test
    public void given_3_X_in_middle_row_then_affirm_consecutiveness(){
        Assert.assertTrue(testSubject.areThereConsecutive("UUUXXXUOO", 'X'));
    }

    @Test
    public void given_3_X_in_top_row_then_affirm_consecutiveness(){
        Assert.assertTrue(testSubject.areThereConsecutive("OOUUUUXXX", 'X'));
    }


    @Test
    public void given_2_X_in_bottom_row_and_single_X_first_column_middle_row_affirm_non_consecutiveness(){
        Assert.assertFalse(testSubject.areThereConsecutive("OXXXUUUUU", 'X'));
    }


    @Test
    public void given_1_X_in_bottom_row_and_2_X_first_and_second_column_middle_row_affirm_non_consecutiveness(){
        Assert.assertFalse(testSubject.areThereConsecutive("UUXXXOOUO", 'X'));
    }


    //diagonals

    //left to right
    @Test
    public void given_3_X_in_diagonal_leftbottom_to_righttop_then_affirm_consecutiveness(){
         Assert.assertTrue(testSubject.areThereConsecutive("XUUUXUUUX", 'X'));
    }

    //left to right
    @Test
    public void given_3_O_in_diagonal_leftbottom_to_righttop_then_affirm_consecutiveness(){
        Assert.assertTrue(testSubject.areThereConsecutive("OUUUOUUUO", 'O'));
    }

    //right to left
    @Test
    public void given_3_X_in_diagonal_lefttop_to_rightbottom_then_affirm_consecutiveness(){
        Assert.assertTrue(testSubject.areThereConsecutive("UUXUXUXUU", 'X'));
    }

    //right to left
    @Test
    public void given_3_O_in_diagonal_lefttop_to_rightbottom_then_affirm_consecutiveness(){
        Assert.assertTrue(testSubject.areThereConsecutive("UUOUOUOUU", 'O'));
    }


    //columns

    @Test
    public void given_3_X_in_left_column_affirm_consecutiveness(){
        Assert.assertTrue(testSubject.areThereConsecutive("OOXUUXUUX", 'X'));
    }

    @Test
    public void given_3_O_in_left_column_affirm_consecutiveness(){
        Assert.assertTrue(testSubject.areThereConsecutive("OUUOXXOXU", 'O'));
    }

    @Test
    public void given_3_X_in_right_column_affirm_consecutiveness(){
        Assert.assertTrue(testSubject.areThereConsecutive("XUUXOOXUO", 'X'));
    }

    @Test
    public void given_3_O_in_right_column_affirm_consecutiveness(){
        Assert.assertTrue(testSubject.areThereConsecutive("UUOXUOXUO", 'O'));
    }


    @Test
    public void given_3_X_in_middle_column_affirm_consecutiveness(){
        Assert.assertTrue(testSubject.areThereConsecutive("OXUUXUUXO", 'X'));
    }

    @Test
    public void given_3_O_in_middle_column_affirm_consecutiveness(){
        Assert.assertTrue(testSubject.areThereConsecutive("UOXUOUUOU", 'O'));
    }


    @Test
    public void given_2_X_one_leftmiddlerow_another_left_toprow_affirm_cnon_onsecutiveness(){
        Assert.assertFalse(testSubject.areThereConsecutive("UUUXUUXOO", 'X'));
    }
}
