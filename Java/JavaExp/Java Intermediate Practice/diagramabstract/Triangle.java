package java_intermediate.diagramabstract;

public class Triangle extends AbstractDiagram implements Diagram{
    private int width;
    private int height;

    public Triangle(int width, int height){
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea(){
        return (width * height) / 2;
    }

    @Override
    public String getName(){
        return "Triangle";
    }
}
