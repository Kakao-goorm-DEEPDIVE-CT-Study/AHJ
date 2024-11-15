package java_intermediate.diagram;

public class Diagram {
    private String diagramName;
    private double area;

    public Diagram(String diagramName) {
        this.diagramName = diagramName;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void display(){
        System.out.println("Diagram Name: " + diagramName + ", Area: " + area);
    }

    public void calculateArea(){
    }
}
