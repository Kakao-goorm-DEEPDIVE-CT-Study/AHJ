package java_intermediate.diagram;


public class Triangle extends Diagram {

    int height;
    int width;

    public Triangle(int height, int width) {
        this.diagramName = "triangle";
        this.height = height;
        this.width = width;
    }

    @Override
    public void calculateArea() {
        this.area = (double) (height * width) / 2;
    }

    @Override
    public void display() {
        super.display();
    }
}
