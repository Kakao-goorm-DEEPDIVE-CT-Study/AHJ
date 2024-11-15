package java_intermediate.diagramabstract;

public class Circle extends AbstractDiagram implements Diagram{

    private int radius;

    public Circle(int radius){
        this.radius = radius;
    }

    @Override
    public double getArea(){
        return Math.pow(radius,2) * Math.PI;
    }

    @Override
    public String getName(){
        return "Circle";
    }
}
