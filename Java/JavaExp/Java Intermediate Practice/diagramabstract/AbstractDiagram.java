package java_intermediate.diagramabstract;

public abstract class AbstractDiagram implements Diagram {
    public abstract double getArea();
    public void display(){
        System.out.println(getName() + "의 면적은 : " + getArea());
    }
}
