package ua.com.alevel;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TimerTask;
import java.util.Timer;

public class ConwayGameOfLife extends JPanel {
    private static final int gap = 1;
    private static final Color bg = new Color(102, 0, 153);
    private static int[][] passedGeneration;
    private static int[][] pausedGeneration;
    private static Timer timer;

    private ConwayGameOfLife(int side) {
        JPanel[][] placeHolder = new JPanel[side][side];
        setPreferredSize(new Dimension(22 * side, 22 * side));
        passedGeneration = new int[side][side];
        setBackground(bg);
        setLayout(new GridLayout(side, side, gap, gap));

        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                placeHolder[i][j] = new JPanel();
                placeHolder[i][j].setBackground(Color.black);
                placeHolder[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                add(placeHolder[i][j]);

                int cellSize = 20;
                final Cell cell = new Cell(i, j, cellSize);
                placeHolder[i][j].addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if (cell.getAlive() == 0) {
                            int xPos = cell.getX();
                            int yPos = cell.getY();
                            placeHolder[xPos][yPos].setBackground(Color.RED);
                        } else {
                            int xPos = cell.getX();
                            int yPos = cell.getY();
                            placeHolder[xPos][yPos].setBackground(Color.black);
                        }
                        cell.toggleAlive();
                        createOriginalGeneration(passedGeneration, cell);
                    }
                });
            }
        }
    }

    private void createOriginalGeneration(int[][] generation, Cell cell) {
        int xPosition = cell.getX();
        int yPosition = cell.getY();
        generation[xPosition][yPosition] = cell.getAlive();
    }

    private static void timerPause() {
        timer.cancel();
    }

    private static void timerRestart() {
        timer = new Timer();
    }

    public static void runGui() {
        JFrame frame = new JFrame("Conway's Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel;
        mainPanel = new JPanel(new CardLayout());
        mainPanel.setPreferredSize(new Dimension(500, 500));
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        JButton startButton = new JButton("Click to start on Conway's game of life!");
        JPanel startPanel = new JPanel();
        String startMenu = "Start Menu";
        startPanel.setLayout(new GridBagLayout());
        GridBagConstraints s = new GridBagConstraints();
        startPanel.add(startButton, s);
        mainPanel.add(startPanel, startMenu);
        cardLayout.show(mainPanel, startMenu);
        startButton.addActionListener(e -> {
            String askingSide = JOptionPane.showInputDialog("How many squares do you want on each side?");
            int side = Integer.parseInt(askingSide);
            if (side > 0) {
                int input = JOptionPane.showOptionDialog(frame, "Making grid cells with the side of " + side + '!', "Creating cells...", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                if (input == JOptionPane.OK_OPTION) {
                    JPanel gamePanel = new JPanel();
                    String firstMenu = "First Menu";
                    JPanel boardPanel = new JPanel();
                    String nextMenu = "Game Board";
                    gamePanel.setLayout(new GridBagLayout());
                    boardPanel.setLayout(new GridBagLayout());
                    ConwayGameOfLife grids = new ConwayGameOfLife(side);
                    gamePanel.add(grids);
                    JPanel optionPanel = new JPanel();
                    optionPanel.setLayout(new GridBagLayout());
                    JButton playButton = new JButton("Play");
                    JButton restartButton = new JButton("Restart");
                    JButton quitButton = new JButton("Quit");
                    JButton pauseButton = new JButton("Pause");
                    JButton continueButton = new JButton("Continue");
                    JButton saveButton = new JButton("Save");

                    GridBagConstraints c = new GridBagConstraints();
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 0;
                    c.gridy = 1;
                    optionPanel.add(playButton, c);
                    c.gridx = 1;
                    optionPanel.add(restartButton, c);
                    restartButton.setEnabled(false);
                    c.gridx = 2;
                    optionPanel.add(quitButton, c);
                    c.gridx = 0;
                    c.gridy = 2;
                    optionPanel.add(pauseButton, c);
                    pauseButton.setEnabled(false);
                    c.gridx = 1;
                    optionPanel.add(continueButton, c);
                    continueButton.setEnabled(false);
                    c.gridx = 2;
                    optionPanel.add(saveButton, c);
                    saveButton.setEnabled(false);
                    c.gridx = 0;
                    c.gridy = 2;
                    gamePanel.add(optionPanel, c);
                    mainPanel.add(gamePanel, firstMenu);
                    cardLayout.show(mainPanel, firstMenu);
                    playButton.addActionListener(e1 -> {
                        passedGeneration = Runner.runner(passedGeneration, side);
                        GameBoard updatedGrids = new GameBoard(passedGeneration, side);
                        boardPanel.add(updatedGrids);
                        playButton.setEnabled(false);
                        restartButton.setEnabled(true);
                        pauseButton.setEnabled(true);
                        boardPanel.add(optionPanel, c);
                        mainPanel.add(boardPanel, nextMenu);
                        cardLayout.show(mainPanel, nextMenu);

                        timerRestart();
                        TimerTask myTask = new TimerTask() {
                            @Override
                            public void run() {
                                boardPanel.removeAll();
                                passedGeneration = Runner.runner(passedGeneration, side);
                                GameBoard updatedGrids = new GameBoard(passedGeneration, side);
                                boardPanel.add(updatedGrids);
                                boardPanel.add(optionPanel, c);
                                boardPanel.revalidate();
                                mainPanel.add(boardPanel, nextMenu);
                                cardLayout.show(mainPanel, nextMenu);
                            }
                        };
                        timer.scheduleAtFixedRate(myTask, 1000, 1000);
                    });
                    restartButton.addActionListener(e12 -> {
                        timerPause();
                        gamePanel.removeAll();
                        boardPanel.removeAll();
                        cardLayout.show(mainPanel, startMenu);
                    });
                    quitButton.addActionListener(e13 -> System.exit(0));
                    pauseButton.addActionListener(e14 -> {
                        timerPause();
                        pausedGeneration = passedGeneration;
                        continueButton.setEnabled(true);
                        saveButton.setEnabled(true);
                        pauseButton.setEnabled(false);
                    });
                    continueButton.addActionListener(e15 -> {
                        passedGeneration = pausedGeneration;
                        timerRestart();
                        TimerTask myTask = new TimerTask() {
                            @Override
                            public void run() {
                                boardPanel.removeAll();
                                passedGeneration = Runner.runner(passedGeneration, side);
                                GameBoard updatedGrids = new GameBoard(passedGeneration, side);
                                boardPanel.add(updatedGrids);
                                playButton.setEnabled(false);
                                pauseButton.setEnabled(true);
                                saveButton.setEnabled(false);
                                boardPanel.add(optionPanel, c);
                                boardPanel.revalidate();
                                mainPanel.add(boardPanel, nextMenu);
                                cardLayout.show(mainPanel, nextMenu);
                            }
                        };
                        timer.scheduleAtFixedRate(myTask, 1000, 1000);
                        continueButton.setEnabled(false);
                        pauseButton.setEnabled(true);
                    });
                    saveButton.addActionListener(e16 -> {
                        timerPause();
                        pausedGeneration = passedGeneration;
                        String askingFileName = JOptionPane.showInputDialog(null, "Enter a file name below.", "Game Of Life");
                        askingFileName = askingFileName + ".txt";
                        StringBuilder builder = new StringBuilder();
                        for (int i = 0; i < side; ++i) {
                            for (int j = 0; j < side; ++j) {
                                builder.append(pausedGeneration[i][j]);
                                if (j < side - 1) {
                                    builder.append(",");
                                }
                            }
                            builder.append("\n");
                        }
                        FileWriter file = null;
                        try {
                            file = new FileWriter(askingFileName);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        BufferedWriter writer = new BufferedWriter(file);
                        try {
                            writer.write(builder.toString());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        try {
                            writer.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        continueButton.setEnabled(true);
                        pauseButton.setEnabled(false);
                        JOptionPane.showOptionDialog(frame, "Saved!", "Save", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                    });
                }
            } else {
                JOptionPane.showMessageDialog(frame, "That is not a valid number. Try again with a positive integer!");
            }
        });

        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}