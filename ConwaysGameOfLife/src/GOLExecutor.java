import engine.Cell;
import engine.GOLList;
import engine.GameofLife;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by michael.nyika on 01/09/15.
 */
public class GOLExecutor {


    private Map<String, List<Cell>> savedData;


    public void PLAY_GAME_OF_LIFE(Grid grid, GameofLife GAME, String input) {
        startTheMusic();
        createAndShowVisualBoard(grid);
        List<Cell> boardCells;
        if(null!=input) {
            System.out.print("set discrete game setup....");
            boardCells = setupTheBoardWithInitialData(input);
        }
        else{
            System.out.print("automatic game setup....");
            boardCells = setupBoardAutomatically();
        }

        int numberOfIterations = 1;
        while(true) {

            List<Cell> allCurrentCellsThatAreAlive = new ArrayList<Cell>();
            checkIfWeHaveOldCellData(boardCells, numberOfIterations);

            if (checkIfAllCellsAreDead()){
                System.out.println("THE CREEPER SAYS GoodNight and GoodLuck!");
                break;
            }

            findAllBoardCellsThatAreAlive(GAME, boardCells, allCurrentCellsThatAreAlive);
            getSavedData().put("data", allCurrentCellsThatAreAlive);

            try {

                //for(engine.Cell cell: allCurrentCellsThatAreAlive) {
                //     System.out.print("["+cell.getX()+","+cell.getY()+"] ");
                //}
                System.out.println(getSavedData().get("data").size() + " cells alive...");
                fillCellsAndShimmer(grid);

            }
            catch (Exception e){
                e.printStackTrace();
            }
            grid.clear();
            getSavedData().put("old", saveOldData(getSavedData().get("data")));
            getSavedData().put("data", null);
            allCurrentCellsThatAreAlive.clear();
            numberOfIterations++;

        }
    }

    private int getRandomCoodinate(int max, int min){
        return new Random().nextInt((max - min) + 1) + min;
    }


    private void fillCellsAndShimmer(Grid grid) throws InterruptedException {
        for(int flashCount=0;flashCount<20;flashCount++){
            for (int z = 0; z < getSavedData().get("data").size() ;z++) {
                if(getSavedData().get("data").get(z).isAlive()) {
                    grid.fillCell(getSavedData().get("data").get(z).getX(), getSavedData().get("data").get(z).getY());
                }
            }
            Thread.sleep(50);
            grid.clear();
        }
    }

    private void findAllBoardCellsThatAreAlive(GameofLife GAME, List<Cell> boardCells, List<Cell> allCurrentCellsThatAreAlive) {
        for (Cell boardCell : boardCells) {
            Cell currentCell = (GAME.determineLifeOrDeath(boardCell, GAME.getNeighbours(boardCell, getSavedData().get("data"))));
            if (currentCell.isAlive()) {
                allCurrentCellsThatAreAlive.add(currentCell);
            }
        }
    }

    private boolean checkIfAllCellsAreDead() {
        if(getSavedData().get("data").size() == 0){

            return true;
        }
        return false;
    }

    private void checkIfWeHaveOldCellData(List<Cell> boardCells, int numberOfIterations) {
        if(numberOfIterations == 1){
            getSavedData().put("data", boardCells);
        }

        else {
            getSavedData().put("data", getSavedData().get("old"));
        }
    }

    private List<Cell> setupTheBoardWithInitialData(String input) {
        //Setup Input Data first
        List<Cell> inputAliveCells = new ArrayList<Cell>();
        constructInitialBoardStateFromInput(input, inputAliveCells);

        //Initial Board Data
        //Create Board
        List<Cell> boardCells = new GOLList();
        for (int x = 0; x < 128; x++) {
            for (int y = 0; y < 128; y++) {
                Cell cell = new Cell();
                cell.setX(x);
                cell.setY(y);
                checkIfCellNeedsToBeAliveBeforeWeStart(cell, inputAliveCells);
                boardCells.add(cell);
            }
        }
        return boardCells;
    }

    private List<Cell> setupBoardAutomatically() {
        //Setup Input Data first
        //Create Board
        Map<Integer, Integer> coordinatesStashed = new HashMap<Integer, Integer>();
        List<Cell> boardCells = new GOLList();
        for (int x = 0; x < 50; x++) {
            for (int y = 0; y < 50; y++) {

                int randomx = getRandomCoodinate(x+3,x);
                int randomy = getRandomCoodinate(y+3,y);
                try {
                    if ((coordinatesStashed.get(randomx)) != randomy) {
                        addNewCell(coordinatesStashed, boardCells, randomx, randomy);
                    }
                }
                catch(NullPointerException np){
                    //means we dont have that value for the key
                    addNewCell(coordinatesStashed, boardCells, randomx, randomy);
                }
            }

        }
        for(Integer xcoord: coordinatesStashed.keySet()) {
            System.out.print(xcoord+","+coordinatesStashed.get(xcoord)+";");
        }
        return boardCells;
    }

    private void addNewCell(Map<Integer, Integer> coordinatesStashed, List<Cell> boardCells, int randomx, int randomy) {
        Cell cell = new Cell();
        cell.setX(randomx);
        cell.setY(randomy);
        cell.setAlive(true);
        boardCells.add(cell);
        coordinatesStashed.put(randomx, randomy);
    }

    private void constructInitialBoardStateFromInput(String input, List<Cell> inputAliveCells) {
        for (String inputCell : input.split(";")) {
            Cell iCell = new Cell();
            iCell.setX(Integer.parseInt(inputCell.split(",")[0]));
            iCell.setY(Integer.parseInt(inputCell.split(",")[1]));
            inputAliveCells.add(iCell);
        }
    }

    private void createAndShowVisualBoard(final Grid grid) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ex) {
                }

                JFrame window = new JFrame();
                window.setTitle("T H E L.A.T.E  N I G H T TELEVISION CREEPER (Conway's Game of Life) ");
                window.setSize(1600, 1000);
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.add(grid);
                window.setBackground(Color.BLACK);
                window.setVisible(true);
            }
        });
    }

    private void startTheMusic() {
        Music myMusic = new Music();
        Thread t = new Thread(myMusic);
        t.start();
    }

    private List<Cell> saveOldData(List<Cell> currentData){
        List<Cell> archiveData = new ArrayList<Cell>();
        for(Cell cell: currentData){
            Cell newcell = new Cell();
            newcell.setAlive(true);
            newcell.setX(cell.getX());
            newcell.setY(cell.getY());
            archiveData.add(newcell);
        }
        return archiveData;
    }


    private void checkIfCellNeedsToBeAliveBeforeWeStart(Cell cell, List<Cell> listOfInputCells){

        for(Cell inputCell: listOfInputCells){
            if(inputCell.getX()==cell.getX() && inputCell.getY()==cell.getY()){
                cell.setAlive(true);
                break;
            }
        }
    }


    public Map<String, List<Cell>> getSavedData() {
        return savedData;
    }

    public void setSavedData(Map<String, List<Cell>> savedData) {
        this.savedData = savedData;
    }
}
