package ua.com.alevel.square;

public class CalculatorSquare {

    public int calculateTriangleSquare(Point a, Point b, Point c) {
        int square = ((a.getX() - c.getX()) * (b.getY() - c.getY())) - ((a.getY() - c.getY()) * (b.getX() - c.getX()));
        square = Math.abs(square) / 2;
        return square;
    }
}