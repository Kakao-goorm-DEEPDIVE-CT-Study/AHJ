package java_intermediate.diagraminterface;

public class Triangle implements DiagramInterface{

    private double area;
    private int height;
    private int width;

    public Triangle(int height, int width){
        this.height = height;
        this.width = width;
    }

    @Override
    public void calculateArea() {
        area = ((double) height * width) / 2;
    }

    @Override
    public void display() {
        System.out.println("Triangle Area = " + area);
    }
}
