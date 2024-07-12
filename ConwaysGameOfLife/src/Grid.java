import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Created by michael.nyika on 25/08/15.
 */
public  class Grid extends JPanel {

    private List<Point> fillCells;

    public Grid() {
        fillCells = new ArrayList<Point>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Point fillCell : fillCells) {
            int cellX = 25 + (fillCell.x * 25);
            int cellY = 25 + (fillCell.y * 25);
            g.setColor(new Color(getRandomHue(), getRandomHue(), getRandomHue()));
            g.fillRect(cellX, cellY, 25, 25); // causes 3d effect of boxes on plane
        }
        g.setColor(Color.YELLOW);

        g.drawRect(0, 0, 1600, 1000);
        for (int i = 25; i <= 1610; i += 25) {
            g.drawLine(i, 25, i, 1025);
        }

        for (int i = 25; i <= 1000; i += 25) {
            g.drawLine(25, i, 1625, i);
        }

    }


    private int getRandomHue(){
        return new Random().nextInt((255 - 1) + 1) + 1;
    }


    public void fillCell(int x, int y) {
        fillCells.add(new Point(x, y));

        repaint();
    }

    public void clear() {
        fillCells.clear();
        repaint();
    }

}
