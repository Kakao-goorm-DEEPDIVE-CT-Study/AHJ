package java_intermediate.diagramabstract;

public class DiagramMain {
    public static void main(String[] args) {
        AbstractDiagram[] diagrams = {
            new Circle(10),
            new Triangle(10, 10),
            new Rectangle(10, 10)
        };

        for(AbstractDiagram diagram : diagrams){
            diagram.display();
        }


    }
}
