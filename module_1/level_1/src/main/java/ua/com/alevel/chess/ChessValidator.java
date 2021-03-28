package ua.com.alevel.chess;

public class ChessValidator {

    public boolean isImpossibleToGoByHorse(int startX, int startY, int finishX, int finishY) {
        return Math.abs(startX - startY) == 2 && Math.abs(finishX - finishY) == 1;
    }
}