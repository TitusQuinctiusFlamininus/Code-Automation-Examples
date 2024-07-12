package engine;

import engine.Cell;

import java.util.*;

/**
 * Created by michael.nyika on 21/08/15.
 */
public class GOLList extends ArrayList {
    @Override
    public int size() {
        return super.size();
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        boolean inTheList = false;
        Cell neighbour = (Cell)o;
        for(Cell cell: (ArrayList<Cell>)this){
            if(neighbour.getX()== cell.getX() && neighbour.getY() == cell.getY()){
                inTheList = true;
                break;
            }
        }
       return inTheList;
    }

    @Override
    public Iterator iterator() {
        return super.iterator();
    }

    @Override
    public Object[] toArray() {
        return super.toArray();
    }

    @Override
    public boolean add(Object o) {
        return super.add(o);
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public boolean addAll(Collection collection) {
        return super.add(collection);
    }

    @Override
    public boolean addAll(int i, Collection collection) {
        return super.addAll(i,collection);
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public Object get(int i) {
        return super.get(i);
    }

    @Override
    public Object set(int i, Object o) {
        return super.set(i,o);
    }

    @Override
    public void add(int i, Object o) {
        super.add(i,o);
    }

    @Override
    public Object remove(int i) {
        return super.remove(i);
    }

    @Override
    public int indexOf(Object o) {
        return super.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return super.lastIndexOf(o);
    }

    @Override
    public ListIterator listIterator() {
        return super.listIterator();
    }

    @Override
    public ListIterator listIterator(int i) {
        return super.listIterator(i);
    }

    @Override
    public List subList(int i, int i1) {
        return super.subList(i,i1);
    }

    @Override
    public boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    @Override
    public boolean removeAll(Collection collection) {
        return super.retainAll(collection);
    }

    @Override
    public boolean containsAll(Collection collection) {
        return super.containsAll(collection);
    }

}
