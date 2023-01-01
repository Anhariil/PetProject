package Main.figure;

public class Square extends Figure {
    private final double w; // Width and high the square

    public Square(double X, double Y, double W) {
        super(X, Y); // call parent constructor
        this.w = W;
    }

    @Override
    public double GetSquare() {
        return w * w;
    }
}
