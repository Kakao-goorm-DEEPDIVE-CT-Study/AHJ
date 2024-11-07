package java_intermediate.diagraminterface;

public class Circle implements DiagramInterface{
    double area;
    int radius;
    public Circle(int radius){
        this.radius = radius;
    }

    @Override
    public void calculateArea() {
        area = Math.PI * Math.pow(radius,2);
    }

    @Override
    public void display() {
        System.out.println("Circle area = " + area);
    }
}
