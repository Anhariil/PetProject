package io.figure;

public class Circle extends Figure {
    private double radius;

    public Circle(double X, double Y, double R) {
        super(X,Y); // call parent constructor
        this.radius = R;
    }
    public double GetSquare(){
        return 3.14*this.radius*this.radius;
    }
    public double GetCenterX(){
        return this.center.GetX();
    }
    public double GetCenterY(){
        return this.center.GetY();
    }
}
