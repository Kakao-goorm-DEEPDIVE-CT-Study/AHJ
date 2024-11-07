package java_intermediate.diagraminterface;

public class Rectangle implements DiagramInterface{

    double area;
    int height;
    int width;

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
        System.out.println("Rectangle area = " + area);
    }
}
