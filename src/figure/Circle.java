package figure;

public class Circle extends Figure {
    private double radius;

    public Circle(double X, double Y, double R) {
        super(X,Y); // call parent constructor
        this.radius = R;
    }

    @Override
    public double GetSquare(){
        return 3.14*this.radius*this.radius;
    }

}
