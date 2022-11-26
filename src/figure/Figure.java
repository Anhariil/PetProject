package figure;

/**
 * Parents Class with  few general property and function
 */
public abstract class Figure {
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

    /**
     * Get square of figure
     */
    public abstract double GetSquare();

    /**
     * Move figure on V for moment on X axis
     */
    public void MoveY(double V){
        this.center.MoveY(V);
    }

    public double GetCenterX(){
        return this.center.GetX();
    }
    public double GetCenterY(){
        return this.center.GetY();
    }

}
