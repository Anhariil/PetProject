package figure;

public class Triangle extends Figure{
    private Coordinate top; // coordinate top of triangle
    private double r; // distance from center to top

    public Triangle(double X, double Y, double R){
        super(X,Y);
        this.r = R;
        this.top = new Coordinate(X,Y+R);
    }

    @Override
    public double GetSquare() {
        return Math.sqrt(3)*r*r/2;
    }
}
