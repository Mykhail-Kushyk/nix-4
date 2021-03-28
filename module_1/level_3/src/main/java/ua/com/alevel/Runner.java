package ua.com.alevel;

public class Runner {
    public static int[][] runner(int[][] generation, int side) {
        int paddedSide = side + 2;
        int[][] prevGeneration = new int[paddedSide][paddedSide];
        for (int i = 0; i < paddedSide; ++i) {
            for (int j = 0; j < paddedSide; ++j) {
                if (i == 0 || j == 0 || i == paddedSide - 1 || j == paddedSide - 1) {
                    prevGeneration[i][j] = 0;
                } else {
                    prevGeneration[i][j] = generation[i - 1][j - 1];
                }
            }
        }
        generation = evolutionProcess(prevGeneration, paddedSide);
        return generation;
    }

    private static int countAliveNeighbours(int[][] grid, int i, int j) {
        int aliveNeighbours = 0;

        for (int width = i - 1; width <= i + 1; ++width) {
            for (int height = j - 1; height <= j + 1; ++height) {
                if (grid[width][height] == 1) {
                    ++aliveNeighbours;
                }
            }
        }
        return aliveNeighbours;
    }

    private static int gameOfLifeRules(int aliveNeighbours, int alive) {
        int aliveOrNot = alive;
        if (aliveNeighbours < 2 && alive == 1) {
            aliveOrNot = 0;
        } else if (aliveNeighbours > 3 && alive == 1) {
            aliveOrNot = 0;
        } else if (aliveNeighbours == 3 && alive == 0) {
            aliveOrNot = 1;
        }
        return aliveOrNot;
    }

    private static int[][] evolutionProcess(int[][] originalGeneration, int side) {
        int originalSide = side - 2;
        int[][] nextGeneration = new int[originalSide][originalSide];

        for (int i = 1; i < side - 1; ++i) {
            for (int j = 1; j < side - 1; ++j) {
                int aliveNeighbours = countAliveNeighbours(originalGeneration, i, j);
                aliveNeighbours -= originalGeneration[i][j];
                int aliveOrNot;
                aliveOrNot = gameOfLifeRules(aliveNeighbours, originalGeneration[i][j]);
                nextGeneration[i - 1][j - 1] = aliveOrNot;
            }
        }
        return nextGeneration;
    }
}