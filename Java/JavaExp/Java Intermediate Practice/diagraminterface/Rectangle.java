package java_intermediate.diagraminterface;

public class Rectangle implements DiagramInterface{

    private double area;
    private int height;
    private int width;

    public Rectangle(int height, int width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public void calculateArea() {
        area = height * width;
    }

    @Override
    public void display() {
        System.out.println("Rectangle Area = " + area);
    }
}
