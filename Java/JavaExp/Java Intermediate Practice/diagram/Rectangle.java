package java_intermediate.diagram;

public class Rectangle extends Diagram{

    int height;
    int width;

    public Rectangle(int height,int width) {
        this.diagramName = "rectangle";
        this.height = height;
        this.width = width;
    }

    @Override
    public void calculateArea() {
        this.area = height * width;
    }

    @Override
    public void display() {
        super.display();
    }
}
