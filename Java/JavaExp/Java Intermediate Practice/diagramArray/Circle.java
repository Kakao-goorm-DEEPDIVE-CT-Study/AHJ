package java_intermediate.diagram;


public class Circle extends Diagram{

    private int radius;

    public Circle(int radius) {
        super("Circle");
        this.radius = radius;
    }

    @Override
    public void calculateArea(){
        setArea(this.radius * this.radius * Math.PI);
    }

}
