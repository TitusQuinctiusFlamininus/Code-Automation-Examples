import engine.Cell;
import engine.GameofLife;

import java.util.HashMap;
import java.util.List;

/**
 * Created by michael.nyika on 08/09/15.
 */
public class Conway {

    public static void main(String[] args){
        try {
            Thread.sleep(2000L);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        String input = "0,0;0,1;1,1;2,2;20,20;21,20;22,22;21,22;22,23;23,24;2,3;3,3;3,4;4,5;7,6;7,7;7,8;8,8;8,9;8,7;10,10;11,11;12,12;13,13;14,13";
        Grid theGrid = new Grid();
        GameofLife game = new GameofLife();
        GOLExecutor mainGOExecutor = new GOLExecutor();
        mainGOExecutor.setSavedData(new HashMap<String, List<Cell>>());
        mainGOExecutor.PLAY_GAME_OF_LIFE(theGrid, game, input);
    }
}
