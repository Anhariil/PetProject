package io.figure;
/**
 * Parents Class with  few general property and function
 */
public class Figure {
    public Coordinate center;
    private double square ;

    /**
     * Constructor with set center to all Child Figure
     * @param X
     * @param Y
     */
    public Figure(double X, double Y) {
        this.center = new Coordinate(X,Y);
    }
    //public double GetSquare() {};
}
