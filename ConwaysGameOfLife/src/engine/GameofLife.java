package engine;

import java.util.List;

/**
 * Created by michael.nyika on 21/08/15.
 */
public class GameofLife {

    public List<Cell> getNeighbours(Cell refCell, List<Cell> listOfBoardCells) {

        List<Cell> trimmedList = new GOLList();
        for (Cell cell : gatherAllNeighbourCells(refCell, listOfBoardCells)) {
            if ((!(cell.getX() < 0)) && (!(cell.getX() > 127))) {
                if ((!(cell.getY() < 0)) && (!(cell.getY() > 127))) {
                    trimmedList.add(cell);
                }
            }
        }
        return trimmedList;
    }

    public List<Cell> gatherAllNeighbourCells(Cell refCell, List<Cell> listOfBoardCells) {
       List<Cell> listOfNeighbours = new GOLList();
        for(Cell cell: listOfBoardCells) {

            if(  ((refCell.getX() + 1) ==  cell.getX())  && (refCell.getY() == cell.getY())  ){
               listOfNeighbours.add(cell);
            }

            if(  ((refCell.getX()) ==  cell.getX())  && ((refCell.getY() -1) == cell.getY())  ){
                listOfNeighbours.add(cell);
            }


            if(  ((refCell.getX() + 1) ==  cell.getX())  && ((refCell.getY()-1) == cell.getY())  ){
                listOfNeighbours.add(cell);
            }


            if(  ((refCell.getX() - 1) ==  cell.getX())  && ((refCell.getY()) == cell.getY())  ){
                listOfNeighbours.add(cell);
            }

            if(  ((refCell.getX() - 1) ==  cell.getX())  && ((refCell.getY() + 1) == cell.getY())  ){
                listOfNeighbours.add(cell);
            }

            if(  ((refCell.getX()) ==  cell.getX())  && ((refCell.getY() + 1) == cell.getY())  ){
                listOfNeighbours.add(cell);
            }

            if(  ((refCell.getX() + 1) ==  cell.getX())  && ((refCell.getY() + 1) == cell.getY())  ){
                listOfNeighbours.add(cell);
            }


            if(  ((refCell.getX() - 1) ==  cell.getX())  && ((refCell.getY() - 1) == cell.getY())  ){
                listOfNeighbours.add(cell);
            }


        }
        return listOfNeighbours;
    }

    public Cell determineLifeOrDeath(Cell refCell, List<Cell> listOfNeighbours) {
        int countOfLiveNeighbours = 0;
        for(Cell cell: listOfNeighbours){
            if(cell.isAlive()){
                countOfLiveNeighbours++;
            }
        }
        if((countOfLiveNeighbours==2 || countOfLiveNeighbours==3) && refCell.isAlive()){
            refCell.setAlive(true);
        }
        else if(countOfLiveNeighbours==3 && !refCell.isAlive()){
            refCell.setAlive(true);
        }
        else{
            refCell.setAlive(false);
        }
        return refCell;
    }
}



