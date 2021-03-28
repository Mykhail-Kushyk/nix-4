package ua.com.alevel;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

public class Cell extends JPanel {
    private int xPos;
    private int yPos;
    private int alive;

    Cell(int x, int y, int cellSize) {
        xPos = x;
        yPos = y;
        alive = 0;
        setBackground(new Color(0, 102, 204));
        setPreferredSize(new Dimension(cellSize, cellSize));
    }

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }

    int getAlive() {
        return alive;
    }

    void toggleAlive() {
        if (alive == 0) {
            alive = 1;
        } else {
            alive = 0;
        }
    }

    void setAlive(int aliveOrNot) {
        alive = aliveOrNot;
    }
}