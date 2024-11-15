package java_intermediate.diagram;


public class Triangle extends Diagram {

    private int height;
    private int width;

    public Triangle(int height, int width) {
        super("triangle");
        this.height = height;
        this.width = width;
    }

    @Override
    public void calculateArea() {
        setArea((double) (height * width) / 2);
    }

}
