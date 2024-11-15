package java_intermediate.diagram;

public class Rectangle extends Diagram{

    private int height;
    private int width;

    public Rectangle(int height,int width) {
        super("Rectangle");
        this.height = height;
        this.width = width;
    }

    @Override
    public void calculateArea() {
        setArea(height * width);
    }

}
