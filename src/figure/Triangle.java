package figure;

public class Triangle extends Figure{
    private final Coordinate top; // coordinate top of triangle
    private final double r; // distance from center to top

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
