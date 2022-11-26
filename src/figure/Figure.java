package figure;

import javax.lang.model.type.NullType;

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

    /**
     * Get square of figure
     */
    public double GetSquare() {return Double.NaN;};

    /**
     * Move figure on V for moment
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
