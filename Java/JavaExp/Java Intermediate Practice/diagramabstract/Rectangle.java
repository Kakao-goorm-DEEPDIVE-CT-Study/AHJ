package java_intermediate.diagramabstract;

public class Rectangle extends AbstractDiagram implements Diagram{
    private int width;
    private int height;

    public Rectangle(int width, int height){
        this.width = width;
        this.height = height;
    }
    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public String getName(){
        return "Rectangle";
    }
}
