import engine.Cell;
import engine.GOLList;
import engine.GameofLife;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;


/**
 * Created by michael.nyika on 21/08/15.
 */
public class GameOfLifeTests {

    private GameofLife testSubject;
    private static GameofLife centralCellTestSubject;
    private static GameofLife bottomLeftTestSubject;
    private static GameofLife topLeftTestSubject;
    private static GameofLife topRightTestSubject;
    private static GameofLife bottomRightTestSubject;
    private Cell refCell;
    private Cell neighbourCell;
    private static List<Cell> boardCells;

    @BeforeClass
    public static void beforeWeStartTheSuite(){
        boardCells = new GOLList();
        for (int x = 0; x < 128; x++) {
            for (int y = 0; y < 128; y++) {
                Cell cell = new Cell();
                cell.setX(x);
                cell.setY(y);
                boardCells.add(cell);
            }
        }

        centralCellTestSubject = new GameofLife(){
            public List<Cell> gatherAllNeighbourCells(Cell refCell, List<Cell> listOfBoardCells) {
                List<Cell> centralNeighbours = new GOLList();
                Cell n1 = new Cell();
                Cell n2 = new Cell();
                Cell n3 = new Cell();
                Cell n4 = new Cell();
                Cell n5 = new Cell();
                Cell n6 = new Cell();
                Cell n7 = new Cell();
                Cell n8 = new Cell();
                n1.setX(44);n1.setY(47);
                n2.setX(45);n2.setY(47);
                n3.setX(46);n3.setY(47);
                n4.setX(44);n4.setY(46);
                n5.setX(46);n5.setY(46);
                n6.setX(44);n6.setY(45);
                n7.setX(45);n7.setY(45);
                n8.setX(46);n8.setY(45);
                centralNeighbours.add(n1);centralNeighbours.add(n2);
                centralNeighbours.add(n3);centralNeighbours.add(n4);
                centralNeighbours.add(n5);centralNeighbours.add(n6);
                centralNeighbours.add(n7);centralNeighbours.add(n8);
                return centralNeighbours;
            }
        };

        bottomLeftTestSubject = new GameofLife(){
            public List<Cell> gatherAllNeighbourCells(Cell refCell, List<Cell> listOfBoardCells) {
                List<Cell> neighbours = new GOLList();
                Cell n1 = new Cell();
                Cell n2 = new Cell();
                Cell n3 = new Cell();
                Cell n4 = new Cell();
                Cell n5 = new Cell();

                n1.setX(0);n1.setY(1);
                n2.setX(1);n2.setY(1);
                n3.setX(1);n3.setY(0);
                n4.setX(-1);n4.setY(0);
                n5.setX(0);n5.setY(-1);


                neighbours.add(n1);neighbours.add(n2);
                neighbours.add(n3);neighbours.add(n4);
                neighbours.add(n5);
                return neighbours;
            }
        };

        topLeftTestSubject = new GameofLife(){
            public List<Cell> gatherAllNeighbourCells(Cell refCell, List<Cell> listOfBoardCells) {
                List<Cell> neighbours = new GOLList();
                Cell n1 = new Cell();
                Cell n2 = new Cell();
                Cell n3 = new Cell();
                Cell n4 = new Cell();
                Cell n5 = new Cell();

                n1.setX(0);n1.setY(126);
                n2.setX(1);n2.setY(127);
                n3.setX(1);n3.setY(126);
                n4.setX(-1);n4.setY(127);
                n5.setX(0);n5.setY(128);

                neighbours.add(n1);neighbours.add(n2);
                neighbours.add(n3);neighbours.add(n4);
                neighbours.add(n5);
                return neighbours;
            }
        };

        topRightTestSubject = new GameofLife(){
            public List<Cell> gatherAllNeighbourCells(Cell refCell, List<Cell> listOfBoardCells) {
                List<Cell> neighbours = new GOLList();
                Cell n1 = new Cell();
                Cell n2 = new Cell();
                Cell n3 = new Cell();
                Cell n4 = new Cell();
                Cell n5 = new Cell();

                n1.setX(126);n1.setY(127);
                n2.setX(126);n2.setY(126);
                n3.setX(127);n3.setY(126);
                n4.setX(128);n4.setY(127);
                n5.setX(127);n5.setY(128);

                neighbours.add(n1);neighbours.add(n2);
                neighbours.add(n3);neighbours.add(n4);
                neighbours.add(n5);
                return neighbours;
            }
        };

        bottomRightTestSubject = new GameofLife(){
            public List<Cell> gatherAllNeighbourCells(Cell refCell, List<Cell> listOfBoardCells) {
                List<Cell> neighbours = new GOLList();
                Cell n1 = new Cell();
                Cell n2 = new Cell();
                Cell n3 = new Cell();
                Cell n4 = new Cell();
                Cell n5 = new Cell();

                n1.setX(127);n1.setY(1);
                n2.setX(126);n2.setY(1);
                n3.setX(126);n3.setY(0);
                n4.setX(128);n4.setY(0);
                n5.setX(127);n5.setY(-1);

                neighbours.add(n1);neighbours.add(n2);
                neighbours.add(n3);neighbours.add(n4);
                neighbours.add(n5);
                return neighbours;
            }
        };
    }

    @Before
    public void startTheTest(){
        testSubject = new GameofLife();
         refCell = new Cell();
         neighbourCell = new Cell();
    }

    //GRID is 128 x 128 so indexes from 0 to 127 for X and Y coordinates

    //get your neighbours

    //engine.Cell right in the middle somewhere, nowhere near the edge of the board

    @Test
    public void given_45_46_then_44_47_is_an_immediate_neighbour(){

        refCell.setX(45);
        refCell.setY(46);
        
        neighbourCell.setX(44);
        neighbourCell.setY(47);
        confirmNeighbourIsInList(centralCellTestSubject);
    }

    @Test
    public void given_45_46_then_43_47_is_NOT_an_immediate_neighbour(){
        
        refCell.setX(45);
        refCell.setY(46);
        
        neighbourCell.setX(43);
        neighbourCell.setY(47);
        confirmNeighbourIsNotInList(centralCellTestSubject);
    }


    @Test
    public void given_45_46_then_44_45_is_an_immediate_neighbour(){
        
        refCell.setX(45);
        refCell.setY(46);
        
        neighbourCell.setX(44);
        neighbourCell.setY(45);
        confirmNeighbourIsInList(centralCellTestSubject);
    }

    @Test
    public void given_45_46_then_44_46_is_an_immediate_neighbour(){
        
        refCell.setX(45);
        refCell.setY(46);
        
        neighbourCell.setX(44);
        neighbourCell.setY(46);
        confirmNeighbourIsInList(centralCellTestSubject);
    }

    @Test
    public void given_45_46_then_45_47_is_an_immediate_neighbour(){
        
        refCell.setX(45);
        refCell.setY(46);
        
        neighbourCell.setX(45);
        neighbourCell.setY(47);
        confirmNeighbourIsInList(centralCellTestSubject);
    }

    @Test
    public void given_45_46_then_45_45_is_an_immediate_neighbour(){
        
        refCell.setX(45);
        refCell.setY(46);
        
        neighbourCell.setX(45);
        neighbourCell.setY(45);
        confirmNeighbourIsInList(centralCellTestSubject);
    }

    @Test
    public void given_45_46_then_46_47_is_an_immediate_neighbour(){
        
        refCell.setX(45);
        refCell.setY(46);
        
        neighbourCell.setX(46);
        neighbourCell.setY(47);
        confirmNeighbourIsInList(centralCellTestSubject);
    }

    @Test
    public void given_45_46_then_46_46_is_an_immediate_neighbour(){
        
        refCell.setX(45);
        refCell.setY(46);
        
        neighbourCell.setX(46);
        neighbourCell.setY(46);
        confirmNeighbourIsInList(centralCellTestSubject);
    }

    @Test
    public void given_45_46_then_46_45_is_an_immediate_neighbour(){
        
        refCell.setX(45);
        refCell.setY(46);
        
        neighbourCell.setX(46);
        neighbourCell.setY(45);
        confirmNeighbourIsInList(centralCellTestSubject);
    }

    @Test
    public void given_45_46_then_there_should_be_8_neighbours(){
        
        refCell.setX(45);
        refCell.setY(46);
        Assert.assertEquals(8, centralCellTestSubject.getNeighbours(refCell, boardCells).size());
    }

    //cells near the border edge
    //bottom left

    @Test
    public void given_0_0_then_0_1_is_an_immediate_neighbour(){
        
        refCell.setX(0);
        refCell.setY(0);
        
        neighbourCell.setX(0);
        neighbourCell.setY(1);
        confirmNeighbourIsInList(bottomLeftTestSubject);
    }

    @Test
    public void given_0_0_then_1_1_is_an_immediate_neighbour(){
        
        refCell.setX(0);
        refCell.setY(0);
        
        neighbourCell.setX(1);
        neighbourCell.setY(1);
        confirmNeighbourIsInList(bottomLeftTestSubject);
    }



    @Test
    public void given_0_0_then_1_0_is_an_immediate_neighbour(){
        
        refCell.setX(0);
        refCell.setY(0);
        
        neighbourCell.setX(1);
        neighbourCell.setY(0);
        confirmNeighbourIsInList(bottomLeftTestSubject);
    }
    @Test
    public void given_0_0_then_MIN1_0_is_an_NOT_immediate_neighbour(){
        
        refCell.setX(0);
        refCell.setY(0);
        
        neighbourCell.setX(-1);
        neighbourCell.setY(0);
        confirmNeighbourIsNotInList(bottomLeftTestSubject);
    }

    @Test
    public void given_0_0_then_0_MIN1_is_NOT_mmediate_neighbour(){
        
        refCell.setX(0);
        refCell.setY(0);
        
        neighbourCell.setX(0);
        neighbourCell.setY(-1);
        confirmNeighbourIsNotInList(bottomLeftTestSubject);
    }

    @Test
    public void given_0_0_then_there_should_be_3_neighbours(){
        
        refCell.setX(0);
        refCell.setY(0);
        Assert.assertEquals(3, bottomLeftTestSubject.getNeighbours(refCell, boardCells).size());
    }

    //top left
    @Test
    public void given_0_127_then_1_127_is_an_immediate_neighbour(){
        
        refCell.setX(0);
        refCell.setY(127);
        
        neighbourCell.setX(1);
        neighbourCell.setY(127);
        confirmNeighbourIsInList(topLeftTestSubject);
    }

    @Test
    public void given_0_127_then_1_126_is_an_immediate_neighbour(){
        
        refCell.setX(0);
        refCell.setY(127);
        
        neighbourCell.setX(1);
        neighbourCell.setY(126);
        confirmNeighbourIsInList(topLeftTestSubject);
    }

    @Test
    public void given_0_127_then_0_126_is_an_immediate_neighbour(){
        
        refCell.setX(0);
        refCell.setY(127);
        
        neighbourCell.setX(0);
        neighbourCell.setY(126);
        confirmNeighbourIsInList(topLeftTestSubject);
    }

    @Test
    public void given_0_127_then_there_should_be_3_neighbours(){
        
        refCell.setX(0);
        refCell.setY(127);
        Assert.assertEquals(3, topLeftTestSubject.getNeighbours(refCell, boardCells).size());
    }

    @Test
    public void given_0_127_then_MIN1_126_is_NOT_an_immediate_neighbour(){
        
        refCell.setX(0);
        refCell.setY(127);
        
        neighbourCell.setX(-1);
        neighbourCell.setY(126);
        confirmNeighbourIsNotInList(topLeftTestSubject);
    }

    @Test
    public void given_0_127_then_2_126_is_NOT_an_immediate_neighbour(){
        
        refCell.setX(0);
        refCell.setY(127);
        
        neighbourCell.setX(2);
        neighbourCell.setY(126);
        confirmNeighbourIsNotInList(topLeftTestSubject);
    }

    @Test
    public void given_0_127_then_1_128_is_NOT_an_immediate_neighbour(){
        
        refCell.setX(0);
        refCell.setY(127);
        
        neighbourCell.setX(1);
        neighbourCell.setY(128);
        confirmNeighbourIsNotInList(topLeftTestSubject);
    }

    //Top right

    @Test
    public void given_127_127_then_125_127_is_Not_immediate_neighbour(){

            refCell.setX(127);
            refCell.setY(127);

            neighbourCell.setX(125);
            neighbourCell.setY(127);
            confirmNeighbourIsNotInList(topRightTestSubject);
    }

    @Test
    public void given_127_127_then_126_127_is_immediate_neighbour(){

        refCell.setX(127);
        refCell.setY(127);

        neighbourCell.setX(126);
        neighbourCell.setY(127);
        confirmNeighbourIsInList(topRightTestSubject);
    }

    @Test
    public void given_127_127_then_126_126_is_immediate_neighbour(){

        refCell.setX(127);
        refCell.setY(127);

        neighbourCell.setX(126);
        neighbourCell.setY(126);
        confirmNeighbourIsInList(topRightTestSubject);
    }

    @Test
    public void given_127_127_then_127_126_is_immediate_neighbour(){

        refCell.setX(127);
        refCell.setY(127);

        neighbourCell.setX(127);
        neighbourCell.setY(126);
        confirmNeighbourIsInList(topRightTestSubject);
    }

    @Test
    public void given_127_127_then_128_127_is_Not_immediate_neighbour(){

        refCell.setX(127);
        refCell.setY(127);

        neighbourCell.setX(128);
        neighbourCell.setY(127);
        confirmNeighbourIsNotInList(topRightTestSubject);
    }


    @Test
    public void given_127_127_then_127_128_is_Not_immediate_neighbour(){

        refCell.setX(127);
        refCell.setY(127);

        neighbourCell.setX(127);
        neighbourCell.setY(128);
        confirmNeighbourIsNotInList(topRightTestSubject);
    }


    @Test
    public void given_127_127_then_there_should_be_3_neighbours(){

        refCell.setX(127);
        refCell.setY(127);
        Assert.assertEquals(3, topRightTestSubject.getNeighbours(refCell, boardCells).size());
    }

    //bottom right
    @Test
    public void given_127_0_then_127_1_is_immediate_neighbour(){

        refCell.setX(127);
        refCell.setY(0);

        neighbourCell.setX(127);
        neighbourCell.setY(1);
        confirmNeighbourIsInList(bottomRightTestSubject);
    }

    @Test
    public void given_127_0_then_126_0_is_immediate_neighbour(){

        refCell.setX(127);
        refCell.setY(0);

        neighbourCell.setX(126);
        neighbourCell.setY(0);
        confirmNeighbourIsInList(bottomRightTestSubject);
    }

    @Test
    public void given_127_0_then_126_1_is_immediate_neighbour(){

        refCell.setX(127);
        refCell.setY(0);

        neighbourCell.setX(126);
        neighbourCell.setY(1);
        confirmNeighbourIsInList(bottomRightTestSubject);
    }

    @Test
    public void given_127_0_then_128_1_is_Not_immediate_neighbour(){

        refCell.setX(127);
        refCell.setY(0);

        neighbourCell.setX(128);
        neighbourCell.setY(1);
        confirmNeighbourIsNotInList(bottomRightTestSubject);
    }

    @Test
    public void given_127_0_then_125_2_is_Not_immediate_neighbour(){

        refCell.setX(127);
        refCell.setY(0);

        neighbourCell.setX(125);
        neighbourCell.setY(2);
        confirmNeighbourIsNotInList(bottomRightTestSubject);
    }


    @Test
    public void given_127_0_then_there_should_be_3_neighbours(){

        refCell.setX(127);
        refCell.setY(0);
        Assert.assertEquals(3, bottomRightTestSubject.getNeighbours(refCell, boardCells).size());
    }


    //Game of Life Life Continuity Rules: Isolated tests
    //Rule 1: "Any live cell with fewer than two live neighbours dies, as if caused by under-population."

    @Test
    public void given_cell_is_alive_and_has_1_live_neighbour_then_cell_dies(){
        List<Cell> listOfNeighbours = new GOLList();
        neighbourCell.setAlive(true);
        listOfNeighbours.add(neighbourCell);
        refCell.setAlive(true);
        Assert.assertFalse(testSubject.determineLifeOrDeath(refCell, listOfNeighbours).isAlive());
    }

    @Test
    public void given_cell_is_alive_and_has_no_live_neighbours_then_cell_dies(){
        refCell.setAlive(true);
        Assert.assertFalse(testSubject.determineLifeOrDeath(refCell, new GOLList()).isAlive());
    }

    //Game of Life Life Continuity Rules: Isolated tests
    //Rule 2: "Any live cell with two or three live neighbours lives on to the next generation."
    @Test
    public void given_cell_is_alive_and_has_2_live_neighbours_then_cell_lives_on(){
        List<Cell> listOfNeighbours = new GOLList();
        Cell neighbour1 = new Cell();
        Cell neighbour2 = new Cell();
        neighbour1.setAlive(true);
        neighbour2.setAlive(true);
        listOfNeighbours.add(neighbour1);
        listOfNeighbours.add(neighbour2);
        refCell.setAlive(true);
        Assert.assertTrue(testSubject.determineLifeOrDeath(refCell, listOfNeighbours).isAlive());
    }

    @Test
    public void given_cell_is_alive_and_has_3_live_neighbours_then_cell_lives_on(){
        List<Cell> listOfNeighbours = new GOLList();
        Cell neighbour1 = new Cell();
        Cell neighbour2 = new Cell();
        Cell neighbour3 = new Cell();
        neighbour1.setAlive(true);
        neighbour2.setAlive(true);
        neighbour3.setAlive(true);
        listOfNeighbours.add(neighbour1);
        listOfNeighbours.add(neighbour2);
        listOfNeighbours.add(neighbour3);
        refCell.setAlive(true);
        Assert.assertTrue(testSubject.determineLifeOrDeath(refCell, listOfNeighbours).isAlive());
    }

    //Game of Life Life Continuity Rules: Isolated tests
    //Rule 3: "Any live cell with more than three live neighbours dies, as if by overcrowding."

    @Test
    public void given_cell_is_alive_and_has_4_live_neighbours_then_cell_lives_on(){
        List<Cell> listOfNeighbours = new GOLList();
        Cell neighbour1 = new Cell();
        Cell neighbour2 = new Cell();
        Cell neighbour3 = new Cell();
        Cell neighbour4 = new Cell();
        neighbour1.setAlive(true);
        neighbour2.setAlive(true);
        neighbour3.setAlive(true);
        neighbour4.setAlive(true);
        listOfNeighbours.add(neighbour1);
        listOfNeighbours.add(neighbour2);
        listOfNeighbours.add(neighbour3);
        listOfNeighbours.add(neighbour4);
        refCell.setAlive(true);
        Assert.assertFalse(testSubject.determineLifeOrDeath(refCell, listOfNeighbours).isAlive());
    }

    @Test
    public void given_cell_is_alive_and_has_5_live_neighbours_then_cell_lives_on(){
        List<Cell> listOfNeighbours = new GOLList();
        Cell neighbour1 = new Cell();
        Cell neighbour2 = new Cell();
        Cell neighbour3 = new Cell();
        Cell neighbour4 = new Cell();
        Cell neighbour5 = new Cell();
        neighbour1.setAlive(true);
        neighbour2.setAlive(true);
        neighbour3.setAlive(true);
        neighbour4.setAlive(true);
        neighbour5.setAlive(true);
        listOfNeighbours.add(neighbour1);
        listOfNeighbours.add(neighbour2);
        listOfNeighbours.add(neighbour3);
        listOfNeighbours.add(neighbour4);
        listOfNeighbours.add(neighbour5);
        refCell.setAlive(true);
        Assert.assertFalse(testSubject.determineLifeOrDeath(refCell, listOfNeighbours).isAlive());
    }

    //Game of Life Life Continuity Rules: Isolated tests
    //Rule 4: "Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction."

    @Test
    public void given_cell_is_dead_and_has_3_live_neighbours_then_cell_comes_alive(){
        List<Cell> listOfNeighbours = new GOLList();
        Cell neighbour1 = new Cell();
        Cell neighbour2 = new Cell();
        Cell neighbour3 = new Cell();
        neighbour1.setAlive(true);
        neighbour2.setAlive(true);
        neighbour3.setAlive(true);
        listOfNeighbours.add(neighbour1);
        listOfNeighbours.add(neighbour2);
        listOfNeighbours.add(neighbour3);
        refCell.setAlive(false);
        Assert.assertTrue(testSubject.determineLifeOrDeath(refCell, listOfNeighbours).isAlive());
    }

    @Test
    public void given_cell_is_dead_and_has_4_live_neighbours_then_cell_stays_dead(){
        List<Cell> listOfNeighbours = new GOLList();
        Cell neighbour1 = new Cell();
        Cell neighbour2 = new Cell();
        Cell neighbour3 = new Cell();
        Cell neighbour4 = new Cell();
        neighbour1.setAlive(true);
        neighbour2.setAlive(true);
        neighbour3.setAlive(true);
        neighbour4.setAlive(true);
        listOfNeighbours.add(neighbour1);
        listOfNeighbours.add(neighbour2);
        listOfNeighbours.add(neighbour3);
        listOfNeighbours.add(neighbour4);
        refCell.setAlive(false);
        Assert.assertFalse(testSubject.determineLifeOrDeath(refCell, listOfNeighbours).isAlive());
    }

    @Test
    public void given_cell_is_dead_and_has_only_2_live_neighbours_then_cell_stays_dead(){
        List<Cell> listOfNeighbours = new GOLList();
        Cell neighbour1 = new Cell();
        Cell neighbour2 = new Cell();
        neighbour1.setAlive(true);
        neighbour2.setAlive(true);
        listOfNeighbours.add(neighbour1);
        listOfNeighbours.add(neighbour2);
        refCell.setAlive(false);
        Assert.assertFalse(testSubject.determineLifeOrDeath(refCell, listOfNeighbours).isAlive());
    }

    @Test
    public void given_cell_is_dead_and_has_only_2_dead_neighbours_then_cell_stays_dead(){
        List<Cell> listOfNeighbours = new GOLList();
        Cell neighbour1 = new Cell();
        Cell neighbour2 = new Cell();
        neighbour1.setAlive(false);
        neighbour2.setAlive(false);
        listOfNeighbours.add(neighbour1);
        listOfNeighbours.add(neighbour2);
        refCell.setAlive(false);
        Assert.assertFalse(testSubject.determineLifeOrDeath(refCell, listOfNeighbours).isAlive());
    }

    @Test
    public void given_cell_is_alive_and_has_2_live_neighbours_among_8_complete_neighbours_then_cell_should_live_on(){
        List<Cell> listOfNeighbours = new GOLList();
        Cell neighbour1 = new Cell();
        Cell neighbour2 = new Cell();
        Cell neighbour3 = new Cell();
        Cell neighbour4 = new Cell();
        Cell neighbour5 = new Cell();
        Cell neighbour6 = new Cell();
        Cell neighbour7 = new Cell();
        Cell neighbour8 = new Cell();
        neighbour1.setAlive(true);
        neighbour2.setAlive(false);
        neighbour3.setAlive(false);
        neighbour4.setAlive(false);
        neighbour5.setAlive(false);
        neighbour6.setAlive(false);
        neighbour7.setAlive(true);
        neighbour8.setAlive(false);
        listOfNeighbours.add(neighbour1);
        listOfNeighbours.add(neighbour2);
        listOfNeighbours.add(neighbour3);
        listOfNeighbours.add(neighbour4);
        listOfNeighbours.add(neighbour5);
        listOfNeighbours.add(neighbour6);
        listOfNeighbours.add(neighbour7);
        listOfNeighbours.add(neighbour8);
        refCell.setAlive(true);
        Assert.assertTrue(testSubject.determineLifeOrDeath(refCell, listOfNeighbours).isAlive());
    }

    @Test
    public void given_cell_is_dead_and_has_only_3_dead_neighbours_then_cell_stays_dead(){
        List<Cell> listOfNeighbours = new GOLList();
        Cell neighbour1 = new Cell();
        Cell neighbour2 = new Cell();
        Cell neighbour3 = new Cell();
        neighbour1.setAlive(false);
        neighbour2.setAlive(false);
        neighbour3.setAlive(false);
        listOfNeighbours.add(neighbour1);
        listOfNeighbours.add(neighbour2);
        listOfNeighbours.add(neighbour3);
        refCell.setAlive(false);
        Assert.assertFalse(testSubject.determineLifeOrDeath(refCell, listOfNeighbours).isAlive());
    }


    @Test
    public void given_2_neighbours_alive_and_reference_is_dead_then_it_should_not_come_alive(){
        refCell.setX(126);
        refCell.setY(126);
        refCell.setAlive(false);
        List<Cell> listOfNeighbours = new GOLList();
        Cell neighbour1 = new Cell();
        Cell neighbour2 = new Cell();
        neighbour1.setX(126);
        neighbour1.setY(127);
        neighbour2.setX(127);
        neighbour2.setY(127);
        neighbour1.setAlive(true);
        neighbour2.setAlive(true);
        listOfNeighbours.add(neighbour1);
        listOfNeighbours.add(neighbour2);
        Assert.assertFalse(testSubject.determineLifeOrDeath(refCell,listOfNeighbours).isAlive());
    }


    @Test
    public void given_cell_is_dead_and_has_no_live_neighbours_then_cell_stays_dead(){
        refCell.setAlive(false);
        Assert.assertFalse(testSubject.determineLifeOrDeath(refCell, new GOLList()).isAlive());
    }

    //##################################################
    //CONTRACT TESTS FOR DETERMINING ONE'S NEIGHBOURS
    //#################################################

    //Getting one's neighbours

    //dealing with how many come back
    @Test
    public void given_list_of_no_neighbours_then_list_of_neighbours_for_any_cell_is_empty(){
        refCell.setX(45);
        refCell.setY(46);
        Assert.assertEquals(0,testSubject.gatherAllNeighbourCells(refCell, new GOLList()).size());
    }

    @Test
    public void given_list_of_2_neighbours_then_list_of_neighbours_for_a_cell_has_2_members(){
        refCell.setX(45);
        refCell.setY(46);
        List<Cell> listOfNeighbours = new GOLList();
        Cell neighbour1 = new Cell();
        Cell neighbour2 = new Cell();
        neighbour1.setX(44);
        neighbour1.setY(46);
        neighbour2.setX(46);
        neighbour2.setY(47);
        listOfNeighbours.add(neighbour1);
        listOfNeighbours.add(neighbour2);
        Assert.assertEquals(2,testSubject.gatherAllNeighbourCells(refCell,listOfNeighbours).size());
    }



    @Test
    public void given_list_of_2_neighbours_and_1_cell_not_neighbour_then_list_of_neighbours_for_a_cell_has_2_members(){
        refCell.setX(45);
        refCell.setY(46);
        List<Cell> listOfNeighbours = new GOLList();
        Cell neighbour1 = new Cell();
        Cell neighbour2 = new Cell();
        Cell not_A_Neighbour = new Cell();
        neighbour1.setX(44);
        neighbour1.setY(46);
        neighbour2.setX(46);
        neighbour2.setY(47);
        not_A_Neighbour.setX(88);
        not_A_Neighbour.setY(95);
        listOfNeighbours.add(neighbour1);
        listOfNeighbours.add(neighbour2);
        listOfNeighbours.add(not_A_Neighbour);
        Assert.assertEquals(2,testSubject.gatherAllNeighbourCells(refCell,listOfNeighbours).size());
    }

    //dealing with who one's neighbour is
    @Test
    public void given_45_46_then_confirm_top_left_cell_is_neighbour(){
        refCell.setX(45);
        refCell.setY(46);
        neighbourCell.setX(44);
        neighbourCell.setY(47);
        confirmNeighbourIsInList(testSubject);
    }

    @Test
    public void given_45_46_then_confirm_top_cell_is_neighbour(){
        refCell.setX(45);
        refCell.setY(46);
        neighbourCell.setX(45);
        neighbourCell.setY(47);
        confirmNeighbourIsInList(testSubject);
    }

    @Test
    public void given_45_46_then_confirm_top_right_cell_is_neighbour(){
        refCell.setX(45);
        refCell.setY(46);
        neighbourCell.setX(46);
        neighbourCell.setY(47);
        confirmNeighbourIsInList(testSubject);
    }

    @Test
    public void given_45_46_then_confirm_left_cell_is_neighbour(){
        refCell.setX(45);
        refCell.setY(46);
        neighbourCell.setX(44);
        neighbourCell.setY(46);
        confirmNeighbourIsInList(testSubject);
    }

    @Test
    public void given_45_46_then_confirm_right_cell_is_neighbour(){
        refCell.setX(45);
        refCell.setY(46);
        neighbourCell.setX(46);
        neighbourCell.setY(46);
        confirmNeighbourIsInList(testSubject);
    }


    @Test
    public void given_45_46_then_confirm_bottom_left_cell_is_neighbour(){
        refCell.setX(45);
        refCell.setY(46);
        neighbourCell.setX(44);
        neighbourCell.setY(45);
        confirmNeighbourIsInList(testSubject);
    }


    @Test
    public void given_45_46_then_confirm_bottom_cell_is_neighbour(){
        refCell.setX(45);
        refCell.setY(46);
        neighbourCell.setX(45);
        neighbourCell.setY(45);
        confirmNeighbourIsInList(testSubject);
    }

    @Test
    public void given_45_46_then_confirm_bottom_right_cell_is_neighbour(){
        refCell.setX(45);
        refCell.setY(46);
        neighbourCell.setX(46);
        neighbourCell.setY(45);
        confirmNeighbourIsInList(testSubject);
    }

    //Private test helper methods

    private void confirmNeighbourIsInList(GameofLife yourTestSubject) {
        Assert.assertTrue(yourTestSubject.getNeighbours(refCell, boardCells).contains(neighbourCell));
    }


    private void confirmNeighbourIsNotInList(GameofLife yourTestSubject) {
        Assert.assertFalse(yourTestSubject.getNeighbours(refCell, boardCells).contains(neighbourCell));
    }
    
    
}
