package ua.com.alevel;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

class GameBoard extends JPanel {

    private static final int gap = 1;
    private static final Color bg = new Color(0, 102, 204);
    private static final int cellSize = 20;

    GameBoard(int[][] generation, int side) {
        JPanel[][] placeHolder = new JPanel[side][side];
        setPreferredSize(new Dimension(22 * side, 22 * side));
        setBackground(bg);
        setLayout(new GridLayout(side, side, gap, gap));
        for (int i = 0; i < side; ++i) {
            for (int j = 0; j < side; ++j) {
                placeHolder[i][j] = new JPanel();
                placeHolder[i][j].setBackground(Color.black);
                add(placeHolder[i][j]);
                final Cell cell = new Cell(i, j, cellSize);
                cell.setAlive(generation[i][j]);
                if (cell.getAlive() == 1) {
                    placeHolder[i][j].setBackground(Color.RED);
                }
            }
        }
    }
}